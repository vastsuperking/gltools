package glcommon.image;

import glcommon.BufferUtils;
import glcommon.util.Pair;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class Image1D extends Image {
	private int m_length;
	private ByteBuffer m_data;
	private ImageFormat m_format;
	
	public Image1D(int length, ImageFormat format) {
		m_length = length;
		m_format = format;
		//Initialize buffer
		m_data = BufferUtils.createByteBuffer(length * format.getSize());
	}
	public Image1D(int length, ImageFormat format, ByteBuffer buffer) {
		m_length = length;
		m_format = format;
		m_data = buffer;
	}
	public Image1D(BufferedImage image) {
		int length = image.getWidth() > image.getHeight() ? image.getWidth() : image.getHeight();
		if (!(image.getWidth() == 1 || image.getHeight() == 1)) throw new RuntimeException("Width or height must be 1!");
		m_length = length;
		Pair<ImageFormat, ByteBuffer> pair = ImageUtils.s_imageToByteBuffer(image);
		m_format = pair.getKey();
		m_data = pair.getValue();
	}
	public int getLength() { return m_length; }
	public ByteBuffer getData() { return m_data; }
	public ImageFormat getFormat() { return m_format; }
}
