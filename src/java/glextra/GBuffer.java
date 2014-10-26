package glextra;

import glcommon.BufferUtils;
import gltools.buffer.FrameBuffer;
import gltools.buffer.FrameBuffer.AttachmentPoint;
import gltools.buffer.RenderBuffer;
import gltools.buffer.RenderBuffer.RBOFormat;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Uniform;
import gltools.texture.Texture2D;
import gltools.texture.Texture2DBuilder;
import gltools.texture.TextureFormat;

import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GBuffer {
	private static final Logger logger = LoggerFactory.getLogger(GBuffer.class);

	
	public enum GBufferMode {
		READ,
		WRITE
	}
	
	private int m_width;
	private int m_height;
	private FrameBuffer m_fbo;
	private RenderBuffer m_rbo;
	
	private ArrayList<GBufferAttachment> m_attachments = new ArrayList<GBufferAttachment>();
	public GBuffer(int width, int height) {
		m_width = width;
		m_height = height;
		

	}
	public int getWidth() { return m_width; }
	public int getHeight() { return m_height; }
	
	/**
	 * Attachments should only be added before init();
	 */
	public void addAttachment(GBufferAttachment a) { 
		m_attachments.add(a);
	}
	
	public void resize(int width, int height) {
		if (m_rbo != null) {
			m_rbo.bind();
			m_rbo.setStorage(RBOFormat.DEPTH_COMPONENT24, width, height);
			m_rbo.unbind();
		}
		
		for (GBufferAttachment a : m_attachments) {
			a.resize(width, height);
		}
		
		if (m_fbo != null) {
			m_fbo.bind();
			//Resize the fbo viewport
			GL11.glViewport(0, 0, width, height);
			m_fbo.unbind();
		}
		
		m_width = width;
		m_height = height;
	}

	public void attachTo(FrameBuffer fbo) {
		if (FrameBuffer.s_getCurrent() != fbo) 
			throw new RuntimeException("FrameBuffer.bind() must be called before GBuffer.attachTo(FrameBuffer)");
		
		fbo.attach(m_rbo, AttachmentPoint.DEPTH_ATTACHMENT);
		
		for (GBufferAttachment a : m_attachments) {
			a.attachTo(fbo);
		}
	}
	/**
	 * This function is called internally, but
	 * it calls glDrawBuffers when the FBO is being initialized
	 */
	public void setDrawBuffers() {
		//Now set the output buffers to the textures
		IntBuffer buffer = BufferUtils.createIntBuffer(m_attachments.size());
		for (GBufferAttachment a : m_attachments) {
			a.addDrawBuffer(buffer);
		}
		GL20.glDrawBuffers(buffer);
	}
	/**
	 * Will set the sampler uniforms of the currently bound program
	 * Normally, this function is called when bind(GBufferMode.READ) is called,
	 * but if a program has not been bound before the GBuffer is bound, this call
	 * must be made after the program is bound
	 */
	public void setReadSamplers() {
		for (GBufferAttachment a : m_attachments) {
			a.setReadSampler();
		}
	}
	
	/**
	 * Attaches all attachments and sets read samplers
	 */
	public void init() {
		m_fbo = new FrameBuffer();
		m_rbo = new RenderBuffer();
		
		m_rbo.bind();
		m_rbo.setStorage(RBOFormat.DEPTH_COMPONENT24, m_width, m_height);
		m_rbo.unbind();
		
		for (GBufferAttachment a : m_attachments) {
			a.init(m_width, m_height);
		}
		
		m_fbo.bind();
		attachTo(m_fbo);
		logger.info("GBuffer Complete: " + m_fbo.isComplete());
		for (GBufferAttachment a : m_attachments) {
			logger.info("Attachment " + a.getAttachmentPoint() + " is valid " + a.getTexture().isValid());
		}
		setDrawBuffers();
		m_fbo.unbind();
	}

	public void bind(GBufferMode mode) {
		switch(mode) {
		//Attach all the textures to their samplers
		case READ: {
			setReadSamplers();
	    	for (GBufferAttachment a : m_attachments) a.bind();
		} break;
		case WRITE: {
			m_fbo.bind();
		} break;
		}
	}
	public void unbind(GBufferMode mode) {
		switch(mode) {
		case READ: {
			for (GBufferAttachment a : m_attachments) a.unbind();
		} break;
		case WRITE: {
			m_fbo.unbind();
		} break;
		}
	}

	public static Texture2D s_createBuffer(int unit, TextureFormat format, int width, int height) {
		Texture2DBuilder builder = new Texture2DBuilder();
		builder.setUnit(unit);
		builder.setWidth(width);
		builder.setHeight(height);
		builder.setFormat(format);
		Texture2D tex = builder.build();
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
	
	public static class GBufferAttachment {
		private Texture2D m_buffer;
		private AttachmentPoint m_attachment;
		private InputUsage m_usage;
		
		private int m_unit;
		private TextureFormat m_format;
		
		public GBufferAttachment(int unit, AttachmentPoint attachment, InputUsage usage, TextureFormat format) {
			m_unit = unit;
			m_attachment = attachment;
			m_usage = usage;
			m_format = format;
		}
		
		public Texture2D getTexture() { return m_buffer; }
		public AttachmentPoint getAttachmentPoint() { return m_attachment; }
		
		public void init(int width, int height) {
			m_buffer = s_createBuffer(m_unit, m_format, width, height);			
		}
		public void resize(int width, int height) {
			if (m_buffer != null) {
				m_buffer.bind();
				m_buffer.setWidth(width);
				m_buffer.setHeight(height);
				m_buffer.load();	
				m_buffer.unbind();
			}
		}
		public void attachTo(FrameBuffer fbo) {
			fbo.attach(m_buffer, m_attachment);
		}
		public void bind() {
			m_buffer.bind();
		}
		public void unbind() {
			m_buffer.unbind();
		}
		public void addDrawBuffer(IntBuffer buffer) {
			//TODO: Change putting by unit, change to sorting by attachment
			buffer.put(m_unit, m_attachment.getID());
		}
		public void setReadSampler() {
			Program program = Program.s_getCurrent();
			if (program != null)
				program.getInputs(Uniform.class, m_usage).setValue(m_unit);
		}
	}
}
