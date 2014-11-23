package gltools.gl.lwjgl.glfw;

import org.lwjgl.system.glfw.GLFW;

public class GLFWUtils {
	public static String getModState(int mods) {
		if ( mods == 0 )
			return "";

		StringBuilder modState = new StringBuilder(16);
		if ( (mods & GLFW.GLFW_MOD_SHIFT) != 0 )
			modState.append("SHIFT+");
		if ( (mods & GLFW.GLFW_MOD_CONTROL) != 0 )
			modState.append("CONTROL+");
		if ( (mods & GLFW.GLFW_MOD_ALT) != 0 )
			modState.append("ALT+");
		if ( (mods & GLFW.GLFW_MOD_SUPER) != 0 )
			modState.append("SUPER+");

		return modState.toString();
	}

	public static void printEvent(long window, String format, Object... args) {
		Object[] formatArgs = new Object[2 + args.length];

		formatArgs[0] = GLFW.glfwGetTime();
		formatArgs[1] = window;
		System.arraycopy(args, 0, formatArgs, 2, args.length);

		System.out.format("%.3f: Window [%d] " + format + "%n", formatArgs);
	}
}
