package gltools.shader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {
	private ShaderType m_type;
	private ShaderSource m_source = null;
	private int m_id;

	public Shader(ShaderType type) {
		setID(GL20.glCreateShader(type.getType()));
		setShaderType(type);
	}
	public Shader(Shader src) {
		setID(src.getID());
		setShaderType(src.getShaderType());
	}
	public Shader(String src, ShaderType type) {
		this(type);
		setSource(new ShaderSource(src));
	}
	public Shader(InputStream src, ShaderType type) throws IOException {
		this(type);
		setSource(new ShaderSource(src));
	}
	public Shader(File src, ShaderType type) throws IOException {
		this(type);
		setSource(new ShaderSource(src));
	}
	public Shader() {}
	
	public int getID() { return m_id;}
	public void setID(int id) {m_id = id;}
	public void setShaderType(ShaderType type) {m_type = type;}
	public void setSource(ShaderSource source) { m_source = source; }
	
	public ShaderType getShaderType() { return m_type; }
	public ShaderSource getSource() { return m_source; }
	
	public void compile() throws ShaderCompileException {
		if (m_source == null) throw new ShaderCompileException("Source not set!");
		GL20.glShaderSource(getID(), m_source.getFullSource());
		GL20.glCompileShader(getID());
		if (!isCompiled()) {
			throw new ShaderCompileException("Could not compile " + getShaderType() + " : " + getInfoLog());
		}
	}
	public boolean isCompiled() {
		return (GL20.glGetShaderi(getID(), GL20.GL_COMPILE_STATUS) 
					== GL11.GL_TRUE);
	}
	
	public void delete() {
		GL20.glDeleteShader(getID());
	}

	public String getInfoLog() {
		return GL20.glGetShaderInfoLog(getID(), GL20.glGetShaderi(getID(), GL20.GL_INFO_LOG_LENGTH));
	}
	
	public static class ShaderCompileException extends Exception {
		private static final long serialVersionUID = 1L;

		public ShaderCompileException(String r) { super(r); }
	}
}