package glextra;

import glcommon.BufferUtils;
import gltools.buffer.FrameBuffer;
import gltools.buffer.FrameBuffer.AttachmentPoint;
import gltools.buffer.RenderBuffer;
import gltools.buffer.RenderBuffer.RBOFormat;
import gltools.gl.GL1;
import gltools.gl.GL2;
import gltools.gl.GL3;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Uniform;
import gltools.texture.Texture2D;
import gltools.texture.Texture2DBuilder;
import gltools.texture.TextureFormat;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Like a framebuffer, but has a list of attachments and is easily:
 * 	 - resized
 *   - written to and read from
 */
public class FBuffer {
	private static final Logger logger = LoggerFactory.getLogger(FBuffer.class);

	
	public enum FBufferMode {
		READ,
		WRITE
	}
	
	private int m_width;
	private int m_height;
	private FrameBuffer m_fbo;
	private RenderBuffer m_rbo;
	
	private Rectangle2D m_previousViewport;
	
	private ArrayList<FBufferAttachment> m_attachments = new ArrayList<FBufferAttachment>();
	public FBuffer(int width, int height) {
		m_width = width;
		m_height = height;
	}
	public int getWidth() { return m_width; }
	public int getHeight() { return m_height; }
	
	/**
	 * Attachments should only be added before init();
	 */
	public void addAttachment(FBufferAttachment a) { 
		m_attachments.add(a);
	}
	
	public void resize(GL3 gl, int width, int height) {
		if (m_fbo != null) m_fbo.bind(gl);
		if (m_rbo != null) {
			m_rbo.bind(gl);
			m_rbo.setStorage(RBOFormat.DEPTH_COMPONENT24, width, height, gl);
			m_rbo.unbind(gl);
		}
		
		for (FBufferAttachment a : m_attachments) {
			a.resize(gl, width, height);
		}
		
		m_width = width;
		m_height = height;
		if (m_fbo != null) m_fbo.unbind(gl);
	}

	public void attachTo(FrameBuffer fbo, GL3 gl) {
		if (FrameBuffer.s_getCurrent() != fbo) 
			throw new RuntimeException("FrameBuffer.bind() must be called before FBuffer.attachTo(FrameBuffer)");
		
		fbo.attach(gl, m_rbo, AttachmentPoint.DEPTH_ATTACHMENT);
		
		for (FBufferAttachment a : m_attachments) {
			a.attachTo(gl, fbo);
		}
	}
	/**
	 * This function is called internally, but
	 * it calls glDrawBuffers when the FBO is being initialized
	 */
	public void setDrawBuffers(GL2 gl) {
		//Now set the output buffers to the textures
		IntBuffer buffer = BufferUtils.createIntBuffer(m_attachments.size());
		for (FBufferAttachment a : m_attachments) {
			a.addDrawBuffer(buffer);
		}
		gl.glDrawBuffers(buffer);
	}
	/**
	 * Will set the sampler uniforms of the currently bound program
	 * Normally, this function is called when bind(GBufferMode.READ) is called,
	 * but if a program has not been bound before the GBuffer is bound, this call
	 * must be made after the program is bound
	 */
	public void setReadSamplers(GL2 gl) {
		for (FBufferAttachment a : m_attachments) {
			a.setReadSampler(gl);
		}
	}
	
	/**
	 * Attaches all attachments and sets read samplers
	 */
	public void init(GL3 gl) {
		m_fbo = new FrameBuffer();
		m_rbo = new RenderBuffer();
		
		m_fbo.init(gl);
		m_rbo.init(gl);
		
		m_rbo.bind(gl);
		m_rbo.setStorage(RBOFormat.DEPTH_COMPONENT24, m_width, m_height, gl);
		m_rbo.unbind(gl);
		
		for (FBufferAttachment a : m_attachments) {
			a.init(m_width, m_height, gl);
		}
		
		m_fbo.bind(gl);
		attachTo(m_fbo, gl);
		logger.info("FBuffer Complete: " + m_fbo.isComplete(gl));
		for (FBufferAttachment a : m_attachments) {
			logger.info("Attachment " + a.getAttachmentPoint() + " is valid " + a.getTexture().isValid(gl));
		}
		setDrawBuffers(gl);
		m_fbo.unbind(gl);
	}

	public void bind(GL3 gl, FBufferMode mode) {
		switch(mode) {
		//Attach all the textures to their samplers
		case READ: {
			setReadSamplers(gl);
	    	for (FBufferAttachment a : m_attachments) a.bind(gl);
		} break;
		case WRITE: {
			m_fbo.bind(gl);
			IntBuffer buf = BufferUtils.createIntBuffer(4);
			gl.glGetInteger(GL1.GL_VIEWPORT, buf);
			//Set the viewport
			gl.glViewport(0, 0, getWidth(), getHeight());
			m_previousViewport = new Rectangle(buf.get(), buf.get(), buf.get(), buf.get());
		} break;
		}
	}
	public void unbind(GL3 gl, FBufferMode mode) {
		switch(mode) {
		case READ: {
			for (FBufferAttachment a : m_attachments) a.unbind(gl);
		} break;
		case WRITE: {
			m_fbo.unbind(gl);
			//Set the previous viewport
			Rectangle2D r = m_previousViewport;
			gl.glViewport((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
		} break;
		}
	}

	public static Texture2D s_createBuffer(int unit, TextureFormat format, int width, int height, GL1 gl) {
		Texture2DBuilder builder = new Texture2DBuilder();
		builder.setUnit(unit);
		builder.setWidth(width);
		builder.setHeight(height);
		builder.setFormat(format);
		Texture2D tex = builder.build();
		tex.init(gl);
		tex.bind(gl);
		tex.load(gl);
		tex.unbind(gl);
		return tex;
	}
	
	public static class FBufferAttachment {
		private Texture2D m_buffer;
		private AttachmentPoint m_attachment;
		private InputUsage m_usage;
		
		private int m_unit;
		private TextureFormat m_format;
		
		public FBufferAttachment(int unit, AttachmentPoint attachment, InputUsage usage, TextureFormat format) {
			m_unit = unit;
			m_attachment = attachment;
			m_usage = usage;
			m_format = format;
		}
		
		public Texture2D getTexture() { return m_buffer; }
		public AttachmentPoint getAttachmentPoint() { return m_attachment; }
		
		public void init(int width, int height, GL1 gl) {
			m_buffer = s_createBuffer(m_unit, m_format, width, height, gl);			
		}
		public void resize(GL1 gl, int width, int height) {
			if (m_buffer != null) {
				m_buffer.bind(gl);
				m_buffer.setWidth(width);
				m_buffer.setHeight(height);
				m_buffer.load(gl);
				m_buffer.unbind(gl);
			}
		}
		public void attachTo(GL3 gl, FrameBuffer fbo) {
			fbo.attach(gl, m_buffer, m_attachment);
		}
		public void bind(GL1 gl) {
			m_buffer.bind(gl);
		}
		public void unbind(GL1 gl) {
			m_buffer.unbind(gl);
		}
		public void addDrawBuffer(IntBuffer buffer) {
			//TODO: Change putting by unit, change to sorting by attachment
			buffer.put(m_unit, m_attachment.getID());
		}
		public void setReadSampler(GL2 gl) {
			Program program = Program.s_getCurrent();
			if (program != null)
				program.getInputs(Uniform.class, m_usage).setValue(gl, m_unit);
		}
	}
}
