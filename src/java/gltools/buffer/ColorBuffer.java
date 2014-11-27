package gltools.buffer;

import gltools.gl.GL1;

public class ColorBuffer {
	private static ColorBuffer s_instance = null;
	
	private ColorBuffer() {}
	
	public void clear(GL1 gl) {
		gl.glClear(GL1.GL_COLOR_BUFFER_BIT);
	}
	public static ColorBuffer getInstance() {
		if (s_instance == null) s_instance = new ColorBuffer();
		return s_instance;
	}
}
