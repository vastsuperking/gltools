package gltools.gl.lwjgl;

import gltools.gl.DebugCallback;
import gltools.gl.GLSync;
import gltools.gl.Pointer;
import gltools.gl.PointerBuffer;

import org.lwjgl.PointerWrapper;

public class LWJGLUtils {
	public static org.lwjgl.opengl.GLSync s_wrapGLSync(GLSync sync) {
		return new org.lwjgl.opengl.GLSync(sync.getPointer());
	}
	public static GLSync s_unwrapGLSync(org.lwjgl.opengl.GLSync sync) {
		return new GLSync(sync.getPointer());
	}
	public static org.lwjgl.opengl.KHRDebugCallback s_wrapDebugCallback(DebugCallback callback) {
		return new org.lwjgl.opengl.KHRDebugCallback(new LWJGLHandler(callback.getHandler()));
	}
	public static org.lwjgl.PointerWrapper s_wrapPointer(Pointer p) {
		return new LWJGLPointer(p.getPointer());
	}
	public static org.lwjgl.PointerBuffer s_wrapPointerBuffer(PointerBuffer pb) {
		//Use our hackishly defined pointerbuffer constructor
		org.lwjgl.PointerBuffer lpb = new org.lwjgl.PointerBuffer(pb.getPointerBuffer(), pb.getView(), pb.get32View(), pb.get64View());
		return lpb;
	}
	
	
	public static class LWJGLPointer implements org.lwjgl.PointerWrapper {
		private final long m_pointer;
		
		public LWJGLPointer(long pointer) {
			m_pointer = pointer;
		}
		
		@Override
		public long getPointer() {
			return m_pointer;
		}
		
	}
	public static class LWJGLHandler implements org.lwjgl.opengl.KHRDebugCallback.Handler {
		private final DebugCallback.Handler m_handler;
		
		public LWJGLHandler(DebugCallback.Handler handler) { m_handler = handler; }
		
		@Override
		public void handleMessage(int source, int type, int id, int severity,
				String message) {
			m_handler.handleMessage(source, type, id, severity, message);
		}
	}
}
