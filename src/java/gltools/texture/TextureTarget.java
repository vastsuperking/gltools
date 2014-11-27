package gltools.texture;

import gltools.gl.GL1;

public enum TextureTarget {
	TEXTURE_1D(GL1.GL_TEXTURE_1D),
	TEXTURE_2D(GL1.GL_TEXTURE_2D);

	int m_id;

	TextureTarget(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
