package glextra.material;

import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.Program.ProgramValidateException;
import gltools.shader.Shader;
import gltools.shader.Shader.ShaderCompileException;
import gltools.shader.ShaderSource;
import gltools.texture.Texture;

import java.util.HashMap;
import java.util.Map.Entry;

public class Technique {
	private final String m_name;
	private Program m_program;

	private HashMap<String, String> m_defines = new HashMap<String, String>();
	
	private HashMap<GlobalParam, InputUsage> m_globalParams = new HashMap<GlobalParam, InputUsage>();
	
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
	public void addDefine(String paramName, String define) {
		m_defines.put(paramName, define);
		//We could possibly need a recompile
		m_needsRecompile = true;
	}
	
	/**
	 * Adds a world parameter to this technique
	 */
	public void addGlobalParam(GlobalParam param, InputUsage usage) {
		if (usage.getDataType() != param.getDataType()) throw new RuntimeException("Param datatype and usage datatype do not match!");
		m_globalParams.put(param, usage);
	}
	
	public void parameterChanged(String param, Object oldValue, Object newValue) {
		//The define could go from on to off if:
		//oldValue is null and newValue is not
		//or vice versa
		if (m_defines.containsKey(param) && 
			((oldValue == null && newValue != null) || 
			(oldValue != null && newValue == null))) {
			m_needsRecompile = true;
		}
	}
	
	/**
	 * Will recompile the technique
	 */
	public void recompile(HashMap<String, MatParam> params) {
		for (Shader s : m_program.getShaders()) {
			//Setup shader source and defines
			ShaderSource source = s.getSource();
			source.clearDefines();
			for (MatParam p : params.values()) {
				if (p == null || p.getValue() == null) continue;
				//If the param is a boolean and the value is false, ignore the param
				if (p.getUsage().getDataType() == DataType.BOOL && !(Boolean) p.getValue()) continue;
				
				//See if we have a define related the the param
				String paramName = p.getName();
				if (m_defines.containsKey(paramName)) {
					String define = m_defines.get(paramName);
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
		m_needsRecompile = false;
	}
	
	public void bind(HashMap<String, MatParam> params) {
		//make sure the program is compiled correctly
		if (m_needsRecompile) recompile(params);
		m_program.bind();

		load(params);
		
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
				Texture t = ((MatTexParam) p).getValue();
				if (t != null) t.unbind();
				
			}
		}
	}
	public void load(HashMap<String, MatParam> params) {
		//Set the params
		for (MatParam p : params.values()) {
			p.load();
		}
		//Now globals
		for (Entry<GlobalParam, InputUsage> p : m_globalParams.entrySet()) {
			p.getKey().load(p.getValue());
		}
	}
}
