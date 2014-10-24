package glcommon.image;

import glcommon.util.Pair;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class Image2D {
	private int m_width;
	private int m_height;
	private ByteBuffer m_data;
	private ImageFormat m_format;
	
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
}
