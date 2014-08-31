package gltools.texture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public enum TextureFormat {
	RGBA8(GL11.GL_RGBA8, GL11.GL_RGBA), 
	RGBA16(GL11.GL_RGBA16, GL11.GL_RGBA),
	 
	RGBA16F(GL30.GL_RGBA16F, GL11.GL_RGBA), 
	RGBA32F(GL30.GL_RGBA32F, GL11.GL_RGBA),
	 
	RGBA8I(GL30.GL_RGBA8I, GL11.GL_RGBA), 
	RGBA16I(GL30.GL_RGBA16I, GL11.GL_RGBA), 
	RGBA32I(GL30.GL_RGBA32I, GL11.GL_RGBA),
	 
	RGBA8UI(GL30.GL_RGBA8UI, GL11.GL_RGBA), 
	RGBA16UI(GL30.GL_RGBA16UI, GL11.GL_RGBA), 
	RGBA32UI(GL30.GL_RGBA32UI, GL11.GL_RGBA), 
	
	RGB8(GL11.GL_RGB8, GL11.GL_RGBA), 
	RGB16(GL11.GL_RGB16, GL11.GL_RGBA),
	
	RGB16F(GL30.GL_RGB16F, GL11.GL_RGBA), 
	RGB32F(GL30.GL_RGB32F, GL11.GL_RGBA),
	
	RED8(GL30.GL_R8, GL11.GL_RED),
	RED16(GL30.GL_R16, GL11.GL_RED);
	
	private int m_sized;
	private int m_base;
	TextureFormat(int sized, int base) {
		m_sized = sized;
		m_base = base;
	}
	public int getSized() { return m_sized; }
	public int getBase() { return m_base; } 
}
