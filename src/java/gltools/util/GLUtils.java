package gltools.util;

import static gltools.gl.GL1.GL_INVALID_ENUM;
import static gltools.gl.GL1.GL_INVALID_OPERATION;
import static gltools.gl.GL1.GL_INVALID_VALUE;
import static gltools.gl.GL1.GL_NO_ERROR;
import static gltools.gl.GL1.GL_OUT_OF_MEMORY;
import static gltools.gl.GL1.GL_STACK_OVERFLOW;
import static gltools.gl.GL1.GL_STACK_UNDERFLOW;
import static gltools.gl.GL3.GL_INVALID_FRAMEBUFFER_OPERATION;
import gltools.gl.GL1;

public class GLUtils {
	public static String transError(int error) {
		switch (error) {
		case GL_NO_ERROR:
			return "No error";
		case GL_INVALID_ENUM:
			return "Enum argument out of range";
		case GL_INVALID_VALUE:
			return "Numeric argument out of range";
		case GL_INVALID_OPERATION:
			return "Operation illegal in current state";
		case GL_STACK_OVERFLOW:
			return "Command would cause a stack overflow";
		case GL_STACK_UNDERFLOW:
			return "Command would cause a stack underflow";
		case GL_OUT_OF_MEMORY:
			return "Not enough memory left to execute command";
		case GL_INVALID_FRAMEBUFFER_OPERATION:
			return "Framebuffer object is not complete";
		default:
			return "Unknown error code";
		}
	}
	public static String getError(GL1 gl) {
		return transError(gl.glGetError());
	}
}
