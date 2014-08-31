package gltools.buffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class RenderBuffer {
	public enum RBOFormat {
		RGBA4(GL11.GL_RGBA4), RGBA8(GL11.GL_RGBA8), DEPTH_COMPONENT16(GL14.GL_DEPTH_COMPONENT16), 
		DEPTH_COMPONENT24(GL14.GL_DEPTH_COMPONENT24), DEPTH_COMPONENT32(GL14.GL_DEPTH_COMPONENT32),
		STENCIL_INDEX8(GL30.GL_STENCIL_INDEX8), STENCIL_INDEX16(GL30.GL_STENCIL_INDEX16);
		private int m_id;
		RBOFormat(int id) { m_id = id; }
		public int getID() { return m_id; }
	}

	private static RenderBuffer s_current = null;

	private int m_width = 0;
	private int m_height = 0;
	
	private int m_id = -1;
	
	public RenderBuffer() {
		m_id = GL30.glGenRenderbuffers();
	}
	public int getWidth() { return m_width; }
	public int getHeight() { return m_height; }
	public int getID() { return m_id; }
	
	public void setStorage(RBOFormat format, int width, int height) {
		m_width = width;
		m_height = height;
		
		checkBound();
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, format.getID(), width, height);
	}
	public void bind() {
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, m_id);
		s_current = this;
	}
	public void unbind() {
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
		s_current = null;
	}
	
	public void destroy() {
		GL30.glDeleteRenderbuffers(getID());
	}
	
	private void checkBound() {
		if (s_current != this) throw new RuntimeException("bind() must be called first!");
	}
	
	public static RenderBuffer s_getCurrent() { return s_current; }
}
