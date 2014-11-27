package gltools.texture;

import gltools.gl.GL1;
import gltools.gl.GL3;

public enum TextureFormat {
	RGBA8(GL1.GL_RGBA8, GL1.GL_RGBA), 
	RGBA16(GL1.GL_RGBA16, GL1.GL_RGBA),
	 
	RGBA16F(GL3.GL_RGBA16F, GL1.GL_RGBA), 
	RGBA32F(GL3.GL_RGBA32F, GL1.GL_RGBA),
	 
	RGBA8I(GL3.GL_RGBA8I, GL1.GL_RGBA), 
	RGBA16I(GL3.GL_RGBA16I, GL1.GL_RGBA), 
	RGBA32I(GL3.GL_RGBA32I, GL1.GL_RGBA),
	 
	RGBA8UI(GL3.GL_RGBA8UI, GL1.GL_RGBA), 
	RGBA16UI(GL3.GL_RGBA16UI, GL1.GL_RGBA), 
	RGBA32UI(GL3.GL_RGBA32UI, GL1.GL_RGBA), 
	
	RGB8(GL1.GL_RGB8, GL1.GL_RGBA), 
	RGB16(GL1.GL_RGB16, GL1.GL_RGBA),
	
	RGB16F(GL3.GL_RGB16F, GL1.GL_RGBA), 
	RGB32F(GL3.GL_RGB32F, GL1.GL_RGBA),
	
	RED8(GL3.GL_R8, GL1.GL_RED),
	RED16(GL3.GL_R16, GL1.GL_RED);
	
	private int m_internal;
	private int m_base;
	TextureFormat(int internal, int base) {
		m_internal = internal;
		m_base = base;
	}
	public int getInternal() { return m_internal; }
	public int getBase() { return m_base; } 
}
