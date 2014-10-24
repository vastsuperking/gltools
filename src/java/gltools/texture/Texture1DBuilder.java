package gltools.texture;

import glcommon.image.Image1D;

import java.nio.ByteBuffer;

public class Texture1DBuilder {
	private int m_unit = 0;
	private TextureTarget m_target = TextureTarget.TEXTURE_2D;
	private TextureFilterMode m_minFilterMode = TextureFilterMode.NEAREST;
	private TextureFilterMode m_maxFilterMode = TextureFilterMode.NEAREST;
	private TextureWrapMode m_wrapMode = TextureWrapMode.CLAMP_TO_EDGE;
	private TextureFormat m_format = TextureFormat.RGBA8;
	private int m_length = 0;
	
	private ByteBuffer m_data = null;

	public Texture1DBuilder setUnit(int unit) { m_unit = unit; return this; }
	public Texture1DBuilder setTarget(TextureTarget target) { m_target = target; return this; }
	public Texture1DBuilder setFormat(TextureFormat format) { m_format = format; return this; }
	public Texture1DBuilder setMinFilterMode(TextureFilterMode minFilterMode) { m_minFilterMode = minFilterMode; return this; }
	public Texture1DBuilder setMaxFilterMode(TextureFilterMode maxFilterMode) { m_maxFilterMode = maxFilterMode; return this; }
	public Texture1DBuilder setWrapMode(TextureWrapMode sWrapMode) { m_wrapMode = sWrapMode; return this; }
	public Texture1DBuilder setLength(int width) { m_length = width; return this; }
	public Texture1DBuilder setData(ByteBuffer data) { m_data = data; return this; }
	
	//--------Special Function Setters---------
	
	public Texture1DBuilder setImage(Image1D image) {
		//TODO: Set format
		setData(image.getData());
		setLength(image.getLength());
		return this;
	}
	
	//--------Build function-----------
	/**
	 * After build is called, the texture still needs to be loaded into gpu via <code>load()</code>
	 */
	public Texture1D build() {
		Texture1D tex = new Texture1D();
		
		tex.setLength(m_length);
		tex.setData(m_data);
		
		tex.setUnit(m_unit);
		tex.setTarget(m_target);
		tex.setFormat(m_format);
		
		tex.setSWrapMode(m_wrapMode);
		tex.setMinFilterMode(m_minFilterMode);
		tex.setMaxFilterMode(m_maxFilterMode);
		
		return tex;
	}
}
