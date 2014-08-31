package gltools.buffer;

import org.lwjgl.opengl.GL11;

public class ColorBuffer {
	private static ColorBuffer s_instance = null;
	
	private ColorBuffer() {}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	public static ColorBuffer getInstance() {
		if (s_instance == null) s_instance = new ColorBuffer();
		return s_instance;
	}
}
