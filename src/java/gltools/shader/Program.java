package gltools.shader;

import gltools.gl.GL1;
import gltools.gl.GL2;

import java.util.HashSet;

public class Program {
	private static Program s_current = null;
	
	private HashSet<Shader> m_shaders = new HashSet<Shader>();
	int m_id = -1;
	
	InputDictionary m_inputs = new InputDictionary();
	
	public Program() {}
	public Program(int id) {
		setID(id);
	}
	public Program(Program program) {
		setID(program.getID());
	}
	public HashSet<Shader> getShaders() { return m_shaders; }
	public int getID() { return m_id; }
	public void setID(int id) {m_id = id;}

	public void init(GL2 gl) {
		if (m_id == -1) m_id = gl.glCreateProgram();
	}
	
	public void attachShader(GL2 gl, Shader shader) {
		m_shaders.add(shader);
		gl.glAttachShader(getID(), shader.getID());
	}
	public InputDictionary getInputs() {
		return m_inputs;
	}
	public <T extends Input> InputList<T> getInputs(Class<T> type, InputUsage usage) {
		return m_inputs.getInputs(type, usage);
	}
	public <I extends Input> I getInput(Class<I> c, String name) {
		return m_inputs.getInput(c, name);
	}
	/**
	 * Will update the locations of all the uniforms(and also whether they are active or not)
	 */
	public void updateInputIDs(GL2 gl) {
		for (Uniform u : m_inputs.getInputs(Uniform.class)) {
			u.updateID(true, gl);
		}
		for (Attribute a : m_inputs.getInputs(Attribute.class)) {
			a.updateID(true, gl);
		}
	}
	public void link(GL2 gl) throws ProgramLinkException {
		gl.glLinkProgram(getID());
		if (!isLinked(gl)) throw new ProgramLinkException("Linking failed: " + getInfoLog(gl));
		updateInputIDs(gl);
	}

	public void validate(GL2 gl) throws ProgramValidateException {
		gl.glValidateProgram(getID());
		if (!isValidated(gl)) throw new ProgramValidateException("Validate failed: " + getInfoLog(gl));
	}
	public boolean isLinked(GL2 gl) {
		return gl.glGetProgrami(getID(), GL2.GL_LINK_STATUS) == GL1.GL_TRUE;
	}
	public boolean isValidated(GL2 gl) {
		return gl.glGetProgrami(getID(), GL2.GL_VALIDATE_STATUS) == GL1.GL_TRUE;
	}
	
	public void bind(GL2 gl) {
		gl.glUseProgram(getID());
		s_current = this;
	}
	public void unbind(GL2 gl) {
		gl.glUseProgram(0);
		s_current = null;
	}
	
	public String getInfoLog(GL2 gl) {
		int length = gl.glGetProgrami(getID(), GL2.GL_INFO_LOG_LENGTH);
		String log = gl.glGetProgramInfoLog(getID(), length);
		return log;		
	}

	public void delete(GL2 gl) {
		gl.glDeleteProgram(getID());
	}
	
	
	public static Program s_getCurrent() {
		return s_current;
	}
	
	public static class ProgramLinkException extends Exception {
		private static final long serialVersionUID = 1L;

		public ProgramLinkException(String r) { super(r); }
	}
	public static class ProgramValidateException extends Exception {
		private static final long serialVersionUID = 1L;

		public ProgramValidateException(String r) { super(r); }
	}
}