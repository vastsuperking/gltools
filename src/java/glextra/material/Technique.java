package glextra.material;

import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.Program.ProgramValidateException;
import gltools.shader.Shader;
import gltools.shader.Shader.ShaderCompileException;
import gltools.shader.ShaderSource;
import gltools.utils.Loadable;

import java.util.HashMap;

public class Technique {
	private final String m_name;
	private Program m_program;

	private HashMap<InputUsage, String> m_defines = new HashMap<InputUsage, String>();
	private boolean m_needsRecompile = false;
	
	public Technique(String name, Program program) { 
		m_name = name;
		m_program = program;
	}
	public boolean needsRecompile() { return m_needsRecompile; }
	public Program getProgram() { return m_program; }
	public String getName() { return m_name; }
	
	/**
	 * When a parameter with inputusage usage is set,
	 * then the define will be added to all shaders in this technique
	 */
	public void addDefine(InputUsage usage, String define) {
		m_defines.put(usage, define);
		//We could possibly need a reload
		m_needsRecompile = true;
	}
	
	public void parameterChanged(String param) {
		//TODO: Only need reload if define has gone from on to off
		//or off to on
		if (m_defines.containsKey(param)) m_needsRecompile = true;
	}
	
	/**
	 * Will recompile the technique if need be
	 */
	public void recompile(HashMap<String, MatParam> params) {
		for (Shader s : m_program.getShaders()) {
			//Setup shader source and defines
			ShaderSource source = s.getSource();
			source.clearDefines();
			for (MatParam p : params.values()) {
				if (p == null || p.getValue() == null) continue;
				InputUsage usage = p.getUsage();
				//If the param is a boolean and the value is false, ignore the param
				if (usage.getDataType() == DataType.BOOL && !(Boolean) p.getValue()) continue;
				//See if we have a define related the the param
				if (m_defines.containsKey(usage)) {
					String define = m_defines.get(usage);
					if (define != null) source.addDefine(define);
				}
			}
			//Compile the shader again
			try {
				s.compile();
			} catch (ShaderCompileException e) {
				throw new RuntimeException("Could not load technique: " + e);
			}
		}
		try {
			m_program.bind();
			m_program.link();
			m_program.unbind();
		} catch (ProgramLinkException e) {
			e.printStackTrace();
		}
	}
	
	public void bind(HashMap<String, MatParam> params, HashMap<String, Loadable> globals) {
		//make sure the program is compiled correctly
		if (m_needsRecompile) recompile(params);
		m_program.bind();

		load(params, globals);
		
		try {
			m_program.validate();
		} catch (ProgramValidateException e) {
			e.printStackTrace();
		}
	}
	public void unbind(HashMap<String, MatParam> params) {
		m_program.unbind();
		for (MatParam p : params.values()) {
			if (p instanceof MatTexParam) {
				((MatTexParam) p).getValue().unbind();
			}
		}
	}
	public void load(HashMap<String, MatParam> params, HashMap<String, Loadable> globals) {
		//Set the params
		for (MatParam p : params.values()) {
			p.load();
		}
		//Now globals
		for (Loadable l : globals.values()) {
			System.out.println("Loading: " + l);
			l.load();
		}
	}
}
