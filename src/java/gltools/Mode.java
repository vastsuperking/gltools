package gltools;

import org.lwjgl.opengl.GL11;

public enum Mode {
	POINTS(GL11.GL_POINTS),
	TRIANGLES(GL11.GL_TRIANGLES),
	TRIANGLE_FAN(GL11.GL_TRIANGLE_FAN),
	TRIANGLE_STRIP(GL11.GL_TRIANGLE_STRIP),
	//Legacy modes
	@Deprecated
	QUADS(GL11.GL_QUADS);
	
	private int m_mode;
	
	Mode(int mode) {
		m_mode = mode;
	}
	
	public int getMode() { return m_mode; }
}
