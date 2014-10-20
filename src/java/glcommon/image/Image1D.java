package glcommon.image;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class Image1D {
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
		//TODO: Implement
		throw new RuntimeException("Not implemented!");
	}
	public int getLength() { return m_length; }
	public ByteBuffer getData() { return m_data; }
	public ImageFormat getFormat() { return m_format; }
}
