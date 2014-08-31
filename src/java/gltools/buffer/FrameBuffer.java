package gltools.buffer;

import gltools.texture.Texture2D;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class FrameBuffer {
	public enum AttachmentPoint {
		COLOR_ATTACHMENT0(GL30.GL_COLOR_ATTACHMENT0),
		COLOR_ATTACHMENT1(GL30.GL_COLOR_ATTACHMENT1),
		COLOR_ATTACHMENT2(GL30.GL_COLOR_ATTACHMENT2),
		COLOR_ATTACHMENT3(GL30.GL_COLOR_ATTACHMENT3),
		DEPTH_ATTACHMENT(GL30.GL_DEPTH_ATTACHMENT);
		
		private int m_id;
		
		AttachmentPoint(int id) {
			m_id = id;
		}
		public int getID() { return m_id; }
	}
	public enum AttachmentType {
		NONE(GL11.GL_NONE), RENDER_BUFFER(GL30.GL_RENDERBUFFER),
		TEXTURE(GL11.GL_TEXTURE);
		
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
	public FrameBuffer() {
		this(GL30.glGenFramebuffers());
	}
	
	public int getID() { return m_id; }
	
	public boolean isComplete() {
		checkBound();
		return GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER) == GL30.GL_FRAMEBUFFER_COMPLETE;
	}
	
	public void attach(Texture2D t, AttachmentPoint attachmentPoint) {
		checkBound();
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, attachmentPoint.getID(), GL11.GL_TEXTURE_2D, t.getID(), 0);
	}
	public void attach(RenderBuffer r, AttachmentPoint attachment) {
		checkBound();
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment.getID(), GL30.GL_RENDERBUFFER, r.getID());
	}
	public AttachmentType getAttachmentType(AttachmentPoint attachment) {
		checkBound();
		int id = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, attachment.getID(), GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE);
		return AttachmentType.getType(id);
	}
	public void bind() {
		if (m_id > 0) {
			GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, m_id);
			s_current = this;
		}
	}
	public void unbind() {
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		s_current = null;
	}
	
	public void destroy() {
		GL30.glDeleteFramebuffers(getID());
	}
	
	private void checkBound() {	
		if (s_current != this) throw new RuntimeException("bind() must be called first!");
	}
	public static FrameBuffer s_getCurrent() { return s_current; }
}
