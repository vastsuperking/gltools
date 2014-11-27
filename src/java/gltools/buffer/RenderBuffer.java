package gltools.buffer;

import gltools.gl.GL1;
import gltools.gl.GL3;

public class RenderBuffer {
	public enum RBOFormat {
		RGBA4(GL1.GL_RGBA4), RGBA8(GL1.GL_RGBA8), DEPTH_COMPONENT16(GL1.GL_DEPTH_COMPONENT16), 
		DEPTH_COMPONENT24(GL1.GL_DEPTH_COMPONENT24), DEPTH_COMPONENT32(GL1.GL_DEPTH_COMPONENT32),
		STENCIL_INDEX8(GL3.GL_STENCIL_INDEX8), STENCIL_INDEX16(GL3.GL_STENCIL_INDEX16);
		private int m_id;
		RBOFormat(int id) { m_id = id; }
		public int getID() { return m_id; }
	}

	private static RenderBuffer s_current = null;

	private int m_width = 0;
	private int m_height = 0;
	
	private int m_id = -1;
	
	public RenderBuffer() {}
	
	public int getWidth() { return m_width; }
	public int getHeight() { return m_height; }
	public int getID() { return m_id; }
	
	public void setStorage(RBOFormat format, int width, int height, GL3 gl) {
		m_width = width;
		m_height = height;
		
		checkBound();
		gl.glRenderbufferStorage(GL3.GL_RENDERBUFFER, format.getID(), width, height);
	}
	
	public void init(GL3 gl) {
		m_id = gl.glGenRenderbuffers();		
	}
	
	public void bind(GL3 gl) {
		gl.glBindRenderbuffer(GL3.GL_RENDERBUFFER, m_id);
		s_current = this;
	}
	public void unbind(GL3 gl) {
		gl.glBindRenderbuffer(GL3.GL_RENDERBUFFER, 0);
		s_current = null;
	}
	
	public void destroy(GL3 gl) {
		gl.glDeleteRenderbuffers(getID());
	}
	
	private void checkBound() {
		if (s_current != this) throw new RuntimeException("bind() must be called first!");
	}
	
	public static RenderBuffer s_getCurrent() { return s_current; }
}
