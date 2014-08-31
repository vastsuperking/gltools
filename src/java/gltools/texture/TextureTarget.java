package gltools.texture;

import org.lwjgl.opengl.GL11;

public enum TextureTarget {
	TEXTURE_1D(GL11.GL_TEXTURE_1D),
	TEXTURE_2D(GL11.GL_TEXTURE_2D);

	int m_id;

	TextureTarget(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
