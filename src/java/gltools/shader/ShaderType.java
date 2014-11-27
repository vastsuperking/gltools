package gltools.shader;

import gltools.gl.GL2;
import gltools.gl.GL3;
import gltools.gl.GL4;

public enum ShaderType {
	FRAGMENT_SHADER(GL2.GL_FRAGMENT_SHADER),
	VERTEX_SHADER(GL2.GL_VERTEX_SHADER),
	GEOMETRY_SHADER(GL3.GL_GEOMETRY_SHADER),
	TESSELLATION_CONTROL_SHADER(GL4.GL_TESS_CONTROL_SHADER),
	TESSELLATION_EVALUATION_SHADER(GL4.GL_TESS_EVALUATION_SHADER);
	
	private int m_type;
	
	ShaderType(int type) {
		m_type = type;
	}
	
	public int getType() { return m_type; }
}