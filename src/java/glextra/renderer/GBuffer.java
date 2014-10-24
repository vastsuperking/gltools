package glextra.renderer;

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

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GBuffer {
	private static final Logger logger = LoggerFactory.getLogger(GBuffer.class);
	
	//Buffer unit for vertex positions
	private static final int VERTEX_BUFFER_UNIT = 0;
	//Unit for normal buffer
	private static final int NORMAL_BUFFER_UNIT = 1;
	//Diffuse buffer unit
	private static final int DIFFUSE_BUFFER_UNIT = 2;
	
	public enum GBufferMode {
		READ,
		WRITE
	}
	
	private int m_width;
	private int m_height;
	private final FrameBuffer m_fbo;
	private final RenderBuffer m_rbo;
	
	//Store position of each pixel in world coordinates
	private final Texture2D m_vertexBuffer;
	//Stores normal of each pixel
	private final Texture2D m_normalBuffer;
	//Stores diffuse color of each pixel
	private final Texture2D m_diffuseBuffer;
	
	public GBuffer(int width, int height) {
		m_width = width;
		m_height = height;
		
		m_fbo = new FrameBuffer();
		m_rbo = new RenderBuffer();
		
		m_rbo.bind();
		m_rbo.setStorage(RBOFormat.DEPTH_COMPONENT24, width, height);
		m_rbo.unbind();
		
		m_vertexBuffer = s_createBuffer(VERTEX_BUFFER_UNIT, TextureFormat.RGBA16F, width, height);
		m_normalBuffer = s_createBuffer(NORMAL_BUFFER_UNIT, TextureFormat.RGBA16F, width, height);
		m_diffuseBuffer = s_createBuffer(DIFFUSE_BUFFER_UNIT, TextureFormat.RGBA8, width, height);
		
		m_fbo.bind();		
		
		attachTo(m_fbo);
		
		logger.debug("Checking FBO Validity...");
		logger.debug("COLOR0: " + m_fbo.getAttachmentType(AttachmentPoint.COLOR_ATTACHMENT0));
		logger.debug("COLOR1: " + m_fbo.getAttachmentType(AttachmentPoint.COLOR_ATTACHMENT1));
		logger.debug("COLOR2: " + m_fbo.getAttachmentType(AttachmentPoint.COLOR_ATTACHMENT2));
		logger.debug("COLOR3: " + m_fbo.getAttachmentType(AttachmentPoint.COLOR_ATTACHMENT3));
		logger.debug("DEPTH: " + m_fbo.getAttachmentType(AttachmentPoint.DEPTH_ATTACHMENT));
		logger.debug("FBO Complete: " + m_fbo.isComplete());
		
		setDrawBuffers();
		
		m_fbo.unbind();
	}
	
	public void resize(int width, int height) {
		m_rbo.bind();
		m_rbo.setStorage(RBOFormat.DEPTH_COMPONENT24, width, height);
		m_rbo.unbind();
		
		m_vertexBuffer.bind();
		m_vertexBuffer.setWidth(width);
		m_vertexBuffer.setHeight(height);
		m_vertexBuffer.load();
		m_vertexBuffer.unbind();

		m_normalBuffer.bind();
		m_normalBuffer.setWidth(width);
		m_normalBuffer.setHeight(height);
		m_normalBuffer.load();		
		m_normalBuffer.unbind();
		
		m_diffuseBuffer.bind();
		m_diffuseBuffer.setWidth(width);
		m_diffuseBuffer.setHeight(height);
		m_diffuseBuffer.load();	
		m_diffuseBuffer.unbind();
		
		m_fbo.bind();
		//Resize the fbo viewport
		GL11.glViewport(0, 0, width, height);
		m_fbo.unbind();
		
		m_width = width;
		m_height = height;
	}
	
	public int getWidth() { return m_width; }
	public int getHeight() { return m_height; }
	
	public void attachTo(FrameBuffer fbo) {
		if (FrameBuffer.s_getCurrent() != fbo) 
			throw new RuntimeException("FrameBuffer.bind() must be called before GBuffer.attachTo(FrameBuffer)");
		
		fbo.attach(m_rbo, AttachmentPoint.DEPTH_ATTACHMENT);
		
		fbo.attach(m_vertexBuffer, AttachmentPoint.COLOR_ATTACHMENT0);
		fbo.attach(m_normalBuffer, AttachmentPoint.COLOR_ATTACHMENT1);
		fbo.attach(m_diffuseBuffer, AttachmentPoint.COLOR_ATTACHMENT2);
	}
	/**
	 * This function is called internally, but
	 * it calls glDrawBuffers when the FBO is being initialized
	 */
	public void setDrawBuffers() {
		//Now set the output buffers to the textures
		IntBuffer buffer = BufferUtils.createIntBuffer(4);
		buffer.put(m_vertexBuffer.getUnit(), GL30.GL_COLOR_ATTACHMENT0);
		buffer.put(m_normalBuffer.getUnit(), GL30.GL_COLOR_ATTACHMENT1);
		buffer.put(m_diffuseBuffer.getUnit(), GL30.GL_COLOR_ATTACHMENT2);
		GL20.glDrawBuffers(buffer);
	}
	/**
	 * Will set the sampler uniforms of the currently bound program
	 */
	public void setReadSamplers() {
		Program program = Program.s_getCurrent();
		program.getInputs(Uniform.class, InputUsage.GBUFFER_VERTEX_SAMPLER).setValue(VERTEX_BUFFER_UNIT);
		program.getInputs(Uniform.class, InputUsage.GBUFFER_NORMAL_SAMPLER).setValue(NORMAL_BUFFER_UNIT);
		program.getInputs(Uniform.class, InputUsage.GBUFFER_DIFFUSE_SAMPLER).setValue(DIFFUSE_BUFFER_UNIT);
	}

	public void bind(GBufferMode mode) {
		switch(mode) {
		case READ: {
	    	m_vertexBuffer.bind();
	    	m_normalBuffer.bind();
	    	m_diffuseBuffer.bind();
		} break;
		case WRITE: {
			m_fbo.bind();
		} break;
		}
	}
	public void unbind(GBufferMode mode) {
		switch(mode) {
		case READ: {
	    	m_vertexBuffer.unbind();
	    	m_normalBuffer.unbind();
	    	m_diffuseBuffer.unbind();
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

}
