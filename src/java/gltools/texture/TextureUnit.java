package gltools.texture;

import org.lwjgl.opengl.GL13;

public class TextureUnit {
	private static int s_current = 0;
	public static void s_use(int unit) {
		s_current = unit;
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit);
	}
	public static int s_getCurrent() { return s_current; }
}