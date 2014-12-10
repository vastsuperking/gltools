package glcommon.image;

import glcommon.BufferUtils;
import glcommon.util.Pair;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class Image2D extends Image {
	private int m_width;
	private int m_height;
	private ByteBuffer m_data;
	private ImageFormat m_format;
	
	private ImageFilterMode m_minFilterMode = ImageFilterMode.NEAREST;
	private ImageFilterMode m_maxFilterMode = ImageFilterMode.NEAREST;
	private ImageWrapMode m_sWrapMode = ImageWrapMode.CLAMP_TO_EDGE;
	private ImageWrapMode m_tWrapMode = ImageWrapMode.CLAMP_TO_EDGE;
	
	public Image2D(int width, int height, ImageFormat format) {
		m_width = width;
		m_height = height;
		m_format = format;
		//Initialize buffer
		m_data = BufferUtils.createByteBuffer(width * height * format.getSize());
	}
	public Image2D(int width, int height, ImageFormat format, ByteBuffer buffer) {
		m_width = width;
		m_height = height;
		m_format = format;
		m_data = buffer;
	}
	public Image2D(BufferedImage image) {
		Pair<ImageFormat, ByteBuffer> pair = ImageUtils.s_imageToByteBuffer(image);
		m_format = pair.getKey();
		m_data = pair.getValue();
		m_width = image.getWidth();
		m_height = image.getHeight();
	}
	public int getWidth() { return m_width; }
	public int getHeight() { return m_height; }
	public ByteBuffer getData() { return m_data; }
	public ImageFormat getFormat() { return m_format; }
	
	public ImageFilterMode 	getMinFilterMode() 	{ return m_minFilterMode; 	}
	public ImageFilterMode 	getMaxFilterMode() 	{ return m_maxFilterMode; 	}
	public ImageWrapMode 	getSWrapMode() 		{ return m_sWrapMode; 		}
	public ImageWrapMode 	getTWrapMode() 		{ return m_tWrapMode; 		}
	
	public void setMinFilterMode(ImageFilterMode minFilterMode) 	{ m_minFilterMode 	= minFilterMode; 	}
	public void setMaxFilterMode(ImageFilterMode maxFilterMode) 	{ m_maxFilterMode 	= maxFilterMode; 	}
	public void setSWrapMode(ImageWrapMode sTextureWrapMode) 		{ m_sWrapMode 		= sTextureWrapMode; }
	public void setTWrapMode(ImageWrapMode tTextureWrapMode) 		{ m_tWrapMode 		= tTextureWrapMode; }

}
