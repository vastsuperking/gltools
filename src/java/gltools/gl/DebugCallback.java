package gltools.gl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugCallback {
	private static final Logger logger = LoggerFactory.getLogger(DebugCallback.class);
	/** Severity levels. */
	public static final int
		GL_DEBUG_SEVERITY_HIGH         = 0x9146,
		GL_DEBUG_SEVERITY_MEDIUM       = 0x9147,
		GL_DEBUG_SEVERITY_LOW          = 0x9148,
		GL_DEBUG_SEVERITY_NOTIFICATION = 0x826B;

	/** Sources. */
	public static final int
		GL_DEBUG_SOURCE_API             = 0x8246,
		GL_DEBUG_SOURCE_WINDOW_SYSTEM   = 0x8247,
		GL_DEBUG_SOURCE_SHADER_COMPILER = 0x8248,
		GL_DEBUG_SOURCE_THIRD_PARTY     = 0x8249,
		GL_DEBUG_SOURCE_APPLICATION     = 0x824A,
		GL_DEBUG_SOURCE_OTHER           = 0x824B;

	/** Types. */
	public static final int
		GL_DEBUG_TYPE_ERROR               = 0x824C,
		GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 0x824D,
		GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR  = 0x824E,
		GL_DEBUG_TYPE_PORTABILITY         = 0x824F,
		GL_DEBUG_TYPE_PERFORMANCE         = 0x8250,
		GL_DEBUG_TYPE_OTHER               = 0x8251,
		GL_DEBUG_TYPE_MARKER              = 0x8268;
	
	
	private final Handler m_handler;
	
	public DebugCallback(Handler handler) {
		m_handler = handler;
	}
	
	
	public Handler getHandler() { return m_handler; } 
	
	
	public interface Handler {
		public void handleMessage(int source, int type, int id, int severity, String message);
	}
	
	public static String s_severityToString(int severity) {
		switch(severity) {
		case GL_DEBUG_SEVERITY_HIGH: return "HIGHT";
		case GL_DEBUG_SEVERITY_MEDIUM: return "MEDIUM";
		case GL_DEBUG_SEVERITY_LOW: return "LOW";
		case GL_DEBUG_SEVERITY_NOTIFICATION: return "NOTIFICATION";
		default: return "UNKNOWN_SEVERITY(" + severity + ")";	
		}
	}
	public static String s_sourceToString(int source) {
		switch ( source ) {
		case GL_DEBUG_SOURCE_API: return "API";
		case GL_DEBUG_SOURCE_WINDOW_SYSTEM: return "WINDOW_SYSTEM";
		case GL_DEBUG_SOURCE_SHADER_COMPILER: return "SHADER_COMPILER";
		case GL_DEBUG_SOURCE_THIRD_PARTY: return "THIRD_PARTY";
		case GL_DEBUG_SOURCE_APPLICATION: return "APPLICATION";
		case GL_DEBUG_SOURCE_OTHER: return "OTHER";
		default: return "SOURCE_UNKNOWN(" + source + ")"; 
		}
	}
	public static String s_typeToString(int type) {
		switch(type) {
		case GL_DEBUG_TYPE_ERROR: return "ERROR";
		case GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR: return "DEPRECATED_BEHAVIOR";
		case GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR: return "UNDEFINED_BEHAVIOR";
		case GL_DEBUG_TYPE_PORTABILITY: return "PORTABILITY";
		case GL_DEBUG_TYPE_PERFORMANCE: return "PERFORMANCE";
		case GL_DEBUG_TYPE_OTHER: return "OTHER";
		case GL_DEBUG_TYPE_MARKER: return "MARKER";
		default: return "TYPE_UNKNOWN(" + type + ")";
		}
	}
	
	public static final class DefaultHandler implements Handler {
		public void handleMessage(int source, int type, int id, int severity, String message) {
			logger.debug("[DefaultHandler] Debug Message: [" + s_sourceToString(source) + "] [" + s_typeToString(type) + "] ID: " + id + " [" + s_severityToString(severity) + "]: " + message);
		}
	}
}
