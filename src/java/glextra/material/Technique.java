package glextra.material;

import gltools.gl.GL;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.Program.ProgramValidateException;
import gltools.shader.Shader;
import gltools.shader.Shader.ShaderCompileException;
import gltools.shader.ShaderSource;
import gltools.texture.Texture;
import gltools.util.Loadable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Technique {
	private static final Logger logger = LoggerFactory.getLogger(Technique.class);
	
	private final String m_name;
	private Program m_program;

	private HashMap<String, String> m_defines = new HashMap<String, String>();
	
	//A set of input usages representing the global params
	private HashSet<InputUsage> m_globalParams = new HashSet<InputUsage>();
	//A set of strings representing render modes for which this technique is
	//suited
	private HashSet<String> m_renderModes = new HashSet<String>();
	
	private boolean m_needsRecompile = false;
	
	public Technique(String name, Program program) { 
		m_name = name;
		m_program = program;
	}
	public boolean needsRecompile() { return m_needsRecompile; }
	public Program getProgram() { return m_program; }
	public String getName() { return m_name; }
	public HashSet<String> getRenderModes() { return m_renderModes; }
	
	public void addRenderMode(String mode) {
		m_renderModes.add(mode);
	}
	public void addAllRenderModes(Collection<String> modes) {
		m_renderModes.addAll(modes);
	}
	
	public void clearRenderModes() { m_renderModes.clear(); }
	
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
	 * Adds a global param(input usage) to this technique
	 */
	public void addGlobalParam(InputUsage param) {
		m_globalParams.add(param);
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
	public void recompile(GL gl, HashMap<String, MatParam> params) {
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
				s.compile(gl.getGL2());
			} catch (ShaderCompileException e) {
				throw new RuntimeException("Could not load technique: " + e);
			}
		}
		try {
			m_program.bind(gl.getGL2());
			m_program.link(gl.getGL2());
			m_program.unbind(gl.getGL2());
		} catch (ProgramLinkException e) {
			e.printStackTrace();
		}
		m_needsRecompile = false;
	}
	
	public void bind(GL gl, HashMap<String, MatParam> params, HashMap<InputUsage, Loadable> globalParamMap) {
		m_program.bind(gl.getGL2());
		//Will check to make sure compiled and uniforms up to date
		load(gl, params, globalParamMap);
		
		try {
			m_program.validate(gl.getGL2());
		} catch (ProgramValidateException e) {
			e.printStackTrace();
		}
	}
	public void unbind(GL gl, HashMap<String, MatParam> params) {
		m_program.unbind(gl.getGL2());
		for (MatParam p : params.values()) {
			if (p instanceof MatTexParam) {
				Texture t = ((MatTexParam) p).getValue();
				if (t != null) t.unbind(gl.getGL1());
				
			}
		}
	}
	public void load(GL gl, HashMap<String, MatParam> params, HashMap<InputUsage, Loadable> globalParamMap) {
		//make sure the program is compiled correctly
		if (m_needsRecompile) {
			//Unbind before recompiling
			m_program.unbind(gl.getGL2());
			recompile(gl, params);
			m_program.bind(gl.getGL2());
		}

		//Set the params
		for (MatParam p : params.values()) {
			p.load(gl);
		}
		//Now globals
		for (InputUsage p : m_globalParams) {
			if (globalParamMap.containsKey(p)) {
				globalParamMap.get(p).load(p, gl);
			} else {
				logger.warn("No global param vailable for: {}", p);
			}
		}
	}
}
