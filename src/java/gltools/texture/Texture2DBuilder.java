package gltools.texture;

import glcommon.image.Image2D;

import java.nio.ByteBuffer;

public class Texture2DBuilder {
	private int m_unit = 0;
	private TextureTarget m_target = TextureTarget.TEXTURE_2D;
	private TextureFilterMode m_minFilterMode = TextureFilterMode.NEAREST;
	private TextureFilterMode m_maxFilterMode = TextureFilterMode.NEAREST;
	private TextureWrapMode m_sWrapMode = TextureWrapMode.CLAMP_TO_EDGE;
	private TextureWrapMode m_tWrapMode = TextureWrapMode.CLAMP_TO_EDGE;
	private TextureFormat m_format = TextureFormat.RGBA8;
	private int m_width = 0;
	private int m_height = 0;
	
	private ByteBuffer m_data = null;

	public Texture2DBuilder setUnit(int unit) { m_unit = unit; return this; }
	public Texture2DBuilder setTarget(TextureTarget target) { m_target = target; return this; }
	public Texture2DBuilder setFormat(TextureFormat format) { m_format = format; return this; }
	public Texture2DBuilder setMinFilterMode(TextureFilterMode minFilterMode) { m_minFilterMode = minFilterMode; return this; }
	public Texture2DBuilder setMaxFilterMode(TextureFilterMode maxFilterMode) { m_maxFilterMode = maxFilterMode; return this; }
	public Texture2DBuilder setSWrapMode(TextureWrapMode sWrapMode) { m_sWrapMode = sWrapMode; return this; }
	public Texture2DBuilder setTWrapMode(TextureWrapMode tWrapMode) { m_tWrapMode = tWrapMode; return this; }
	public Texture2DBuilder setWidth(int width) { m_width = width; return this; }
	public Texture2DBuilder setHeight(int height) { m_height = height; return this; }
	public Texture2DBuilder setData(ByteBuffer data) { m_data = data; return this; }
	
	//--------Special Function Setters---------
	
	public Texture2DBuilder setImage(Image2D image) {
		//TODO: Set format
		setData(image.getData());
		setWidth(image.getWidth());
		setHeight(image.getHeight());
		return this;
	}
	
	//--------Build function-----------
	/**
	 * After build is called, the texture still needs to be loaded into gpu via <code>load()</code>
	 */
	public Texture2D build() {
		Texture2D tex = new Texture2D();
		
		tex.setHeight(m_height);
		tex.setWidth(m_width);
		tex.setData(m_data);
		
		tex.setUnit(m_unit);
		tex.setTarget(m_target);
		tex.setFormat(m_format);
		
		tex.setSWrapMode(m_sWrapMode);
		tex.setTWrapMode(m_tWrapMode);
		tex.setMinFilterMode(m_minFilterMode);
		tex.setMaxFilterMode(m_maxFilterMode);
		
		return tex;
	}
}
