package gltools.shader;

import java.util.HashSet;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Program {
	private static Program s_current = null;
	
	private HashSet<Shader> m_shaders = new HashSet<Shader>();
	int m_id;
	
	InputDictionary m_inputs = new InputDictionary();
	
	public Program() {
		setID(GL20.glCreateProgram());
	}
	public Program(int id) {
		setID(id);
	}
	public Program(Program program) {
		setID(program.getID());
	}
	public HashSet<Shader> getShaders() { return m_shaders; }
	public int getID() { return m_id; }
	public void setID(int id) {m_id = id;}

	public void attachShader(Shader shader) {
		m_shaders.add(shader);
		GL20.glAttachShader(getID(), shader.getID());
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
	public void updateInputIDs() {
		for (Uniform u : m_inputs.getInputs(Uniform.class)) {
			u.updateID(true);
		}
		for (Attribute a : m_inputs.getInputs(Attribute.class)) {
			a.updateID(true);
		}
	}
	public void link() throws ProgramLinkException {
		GL20.glLinkProgram(getID());
		if (!isLinked()) throw new ProgramLinkException("Linking failed: " + getInfoLog());
		updateInputIDs();
	}

	public void validate() throws ProgramValidateException {
		GL20.glValidateProgram(getID());
		if (!isValidated()) throw new ProgramValidateException("Validate failed: " + getInfoLog());
	}
	public boolean isLinked() {
		return GL20.glGetProgrami(getID(), GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
	}
	public boolean isValidated() {
		return GL20.glGetProgrami(getID(), GL20.GL_VALIDATE_STATUS) == GL11.GL_TRUE;
	}
	
	public void bind() {
		GL20.glUseProgram(getID());
		s_current = this;
	}
	public void unbind() {
		GL20.glUseProgram(0);
		s_current = null;
	}
	
	public String getInfoLog() {
		int length = GL20.glGetProgrami(getID(), GL20.GL_INFO_LOG_LENGTH);
		String log = GL20.glGetProgramInfoLog(getID(), length);
		return log;		
	}

	public void delete() {
		GL20.glDeleteProgram(getID());
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