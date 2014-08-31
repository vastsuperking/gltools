package gltools.texture;

import org.lwjgl.opengl.GL11;

public enum TextureFilterMode {
	NEAREST(GL11.GL_NEAREST),
	LINEAR(GL11.GL_LINEAR),
	NEAREST_MIPMAP_NEAREST(GL11.GL_NEAREST_MIPMAP_NEAREST),
	NEAREST_MIPMAP_LINEAR(GL11.GL_NEAREST_MIPMAP_LINEAR),
	LINEAR_MIPMAP_NEAREST(GL11.GL_LINEAR_MIPMAP_NEAREST),
	LINEAR_MIPMAP_LINEAR(GL11.GL_LINEAR_MIPMAP_LINEAR);
	
	private int m_id;
	
	TextureFilterMode(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
