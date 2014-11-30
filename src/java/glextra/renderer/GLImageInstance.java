package glextra.renderer;

import glcommon.image.Image;
import glcommon.image.Image2D;
import glcommon.image.ImageInstance;
import gltools.gl.GL1;
import gltools.texture.Texture2D;
import gltools.texture.TextureFactory;
import gltools.texture.TextureWrapMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GLImageInstance implements ImageInstance {
	private static final Logger logger = LoggerFactory.getLogger(GLImageInstance.class);
	
	private Texture2D m_glTexture;
	private GL1 m_gl;
	
	private boolean m_deleted = false;

	public GLImageInstance(GL1 gl, Image2D image) {
		m_gl = gl;
		m_glTexture = TextureFactory.s_loadTexture(gl, image);
		
		//Setup repeating
		m_glTexture.bind(gl);
		m_glTexture.setTWrapMode(TextureWrapMode.REPEAT);
		m_glTexture.setSWrapMode(TextureWrapMode.REPEAT);
		m_glTexture.loadParams(gl);
		m_glTexture.unbind(gl);
	}
	
	public Texture2D getGLTexture() { return m_glTexture; }
	
	@Override
	public void delete() {
		logger.debug("Deleting texture: " + m_glTexture.getID());
		
		boolean current = m_gl.getContext().isCurrent();
		if (!current) m_gl.getContext().makeCurrent();
		m_glTexture.delete(m_gl);
		if (!current) m_gl.getContext().releaseCurrent();
		m_deleted = true;
	}
	@Override
	public void update(Image image) {
		logger.error("Updating GLImageInstance not implemented!");
	}
	
	@Override
	public void finalize() {
		if (!m_deleted) {
			logger.warn("Auto-cleaning up GLTexture, delete() not called!");
			delete();
		}
	}
}
