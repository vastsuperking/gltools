package gltools.buffer;

import gltools.gl.GL1;
import gltools.gl.GL3;
import gltools.texture.Texture2D;

public class FrameBuffer {
	public enum AttachmentPoint {
		COLOR_ATTACHMENT0(GL3.GL_COLOR_ATTACHMENT0),
		COLOR_ATTACHMENT1(GL3.GL_COLOR_ATTACHMENT1),
		COLOR_ATTACHMENT2(GL3.GL_COLOR_ATTACHMENT2),
		COLOR_ATTACHMENT3(GL3.GL_COLOR_ATTACHMENT3),
		DEPTH_ATTACHMENT(GL3.GL_DEPTH_ATTACHMENT);
		
		private int m_id;
		
		AttachmentPoint(int id) {
			m_id = id;
		}
		public int getID() { return m_id; }
	}
	public enum AttachmentType {
		NONE(GL1.GL_NONE), RENDER_BUFFER(GL3.GL_RENDERBUFFER),
		TEXTURE(GL1.GL_TEXTURE);
		
		int m_id;
		AttachmentType(int id) {
			m_id = id;
		}
		public int getID() { return m_id; }
		public static AttachmentType getType(int id) {
			for (AttachmentType type : values()) {
				if (type.getID() == id) return type;
			}
			return null;
		}
	}
	
	private static FrameBuffer s_current = null;
	
	private int m_id = -1;
	
	public FrameBuffer(int id) { m_id = id; }
	public FrameBuffer() {}
	
	public int getID() { return m_id; }
	
	public boolean isComplete(GL3 gl) {
		checkBound();
		return gl.glCheckFramebufferStatus(GL3.GL_FRAMEBUFFER) == GL3.GL_FRAMEBUFFER_COMPLETE;
	}
	
	public void init(GL3 gl) {
		if (m_id == -1) m_id = gl.glGenFramebuffers();
	}
	
	public void attach(GL3 gl, Texture2D t, AttachmentPoint attachmentPoint) {
		checkBound();
		gl.glFramebufferTexture2D(GL3.GL_FRAMEBUFFER, attachmentPoint.getID(), GL1.GL_TEXTURE_2D, t.getID(), 0);
	}
	public void attach(GL3 gl, RenderBuffer r, AttachmentPoint attachment) {
		checkBound();
		gl.glFramebufferRenderbuffer(GL3.GL_FRAMEBUFFER, attachment.getID(), GL3.GL_RENDERBUFFER, r.getID());
	}
	public AttachmentType getAttachmentType(GL3 gl, AttachmentPoint attachment) {
		checkBound();
		int id = gl.glGetFramebufferAttachmentParameteri(GL3.GL_FRAMEBUFFER, attachment.getID(), GL3.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE);
		return AttachmentType.getType(id);
	}
	public void bind(GL3 gl) {
		if (m_id > 0) {
			gl.glBindFramebuffer(GL3.GL_FRAMEBUFFER, m_id);
			s_current = this;
		}
	}
	public void unbind(GL3 gl) {
		gl.glBindFramebuffer(GL3.GL_FRAMEBUFFER, 0);
		s_current = null;
	}
	
	public void destroy(GL3 gl) {
		gl.glDeleteFramebuffers(getID());
	}
	
	private void checkBound() {	
		if (s_current != this) throw new RuntimeException("bind() must be called first!");
	}
	public static FrameBuffer s_getCurrent() { return s_current; }
}
