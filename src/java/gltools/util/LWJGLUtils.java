package gltools.util;

import org.lwjgl.opengl.GL11;

public class LWJGLUtils {
	public static String transError(int error) {
		return org.lwjgl.opengl.Util.translateGLErrorString(error);
	}
	public static String getError() {
		return transError(GL11.glGetError());
	}
}
