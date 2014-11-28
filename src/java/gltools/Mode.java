package gltools;

import gltools.gl.GL1;

public enum Mode {
	LINES(GL1.GL_LINES),
	LINE_LOOP(GL1.GL_LINE_LOOP),
	POINTS(GL1.GL_POINTS),
	TRIANGLES(GL1.GL_TRIANGLES),
	TRIANGLE_FAN(GL1.GL_TRIANGLE_FAN),
	TRIANGLE_STRIP(GL1.GL_TRIANGLE_STRIP),
	//Legacy modes
	@Deprecated
	QUADS(GL1.GL_QUADS);
	
	private int m_mode;
	
	Mode(int mode) {
		m_mode = mode;
	}
	
	public int getMode() { return m_mode; }
}
