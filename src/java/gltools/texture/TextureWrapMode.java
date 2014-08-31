package gltools.texture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;

public enum TextureWrapMode {
	REPEAT(GL11.GL_REPEAT),
	CLAMP_TO_BORDER(GL13.GL_CLAMP_TO_BORDER),
	CLAMP_TO_EDGE(GL12.GL_CLAMP_TO_EDGE);
	private int m_id;
	
	TextureWrapMode(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
