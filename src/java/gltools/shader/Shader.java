package gltools.shader;

import gltools.gl.GL1;
import gltools.gl.GL2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shader {
	private static final Logger logger = LoggerFactory.getLogger(Shader.class);
	
	private ShaderType m_type;
	private ShaderSource m_source = null;
	private int m_id = -1;
	private boolean m_compiled = false;

	public Shader(ShaderType type) {
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
	
	public void init(GL2 gl) {
		if (m_id == -1) setID(gl.glCreateShader(m_type.getType()));	
	}
	
	public void compile(GL2 gl) throws ShaderCompileException {
		checkValid();
		if (m_source == null) throw new ShaderCompileException("Source not set!");
		gl.glShaderSource(getID(), m_source.getFullSource());
		gl.glCompileShader(getID());
		if (gl.glGetShaderi(getID(), GL2.GL_COMPILE_STATUS) 
				!= GL1.GL_TRUE) {
			m_compiled = false;
			throw new ShaderCompileException("Could not compile " + getShaderType() + " : " + getInfoLog(gl));
		} else m_compiled = true;
	}
	public boolean isCompiled(GL2 gl) {
		return m_compiled;
	}
	
	public void delete(GL2 gl) {
		checkValid();
		gl.glDeleteShader(getID());
	}

	public String getInfoLog(GL2 gl) {
		checkValid();
		int len = gl.glGetShaderi(getID(), GL2.GL_INFO_LOG_LENGTH);
		logger.info("Len {}", len);
		//If there is more than a null byte
		if (len > 1) return gl.glGetShaderInfoLog(getID(), len);
		else return "";
	}
	
	@Override
	public void finalize() {
		logger.warn("Shader {} not destroyed!", getID());
	}
	
	private void checkValid() {
		if (m_id == -1) throw new RuntimeException("Shader not initialized!");
	}
	
	public static class ShaderCompileException extends Exception {
		private static final long serialVersionUID = 1L;

		public ShaderCompileException(String r) { super(r); }
	}
}