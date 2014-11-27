package gltools.texture;

import gltools.gl.GL1;

public enum TextureWrapMode {
	REPEAT(GL1.GL_REPEAT),
	CLAMP_TO_BORDER(GL1.GL_CLAMP_TO_BORDER),
	CLAMP_TO_EDGE(GL1.GL_CLAMP_TO_EDGE);
	private int m_id;
	
	TextureWrapMode(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
