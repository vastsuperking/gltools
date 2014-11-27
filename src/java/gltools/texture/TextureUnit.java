package gltools.texture;

import gltools.gl.GL1;

public class TextureUnit {
	private static int s_current = 0;
	public static void s_use(GL1 gl, int unit) {
		s_current = unit;
		gl.glActiveTexture(GL1.GL_TEXTURE0 + unit);
	}
	public static int s_getCurrent() { return s_current; }
}