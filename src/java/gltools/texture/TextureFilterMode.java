package gltools.texture;

import gltools.gl.GL1;

public enum TextureFilterMode {
	NEAREST(GL1.GL_NEAREST),
	LINEAR(GL1.GL_LINEAR),
	NEAREST_MIPMAP_NEAREST(GL1.GL_NEAREST_MIPMAP_NEAREST),
	NEAREST_MIPMAP_LINEAR(GL1.GL_NEAREST_MIPMAP_LINEAR),
	LINEAR_MIPMAP_NEAREST(GL1.GL_LINEAR_MIPMAP_NEAREST),
	LINEAR_MIPMAP_LINEAR(GL1.GL_LINEAR_MIPMAP_LINEAR);
	
	private int m_id;
	
	TextureFilterMode(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
