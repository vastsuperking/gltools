package gltools.buffer;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class IndexBuffer extends Buffer {
	private int m_offset = 0;

	public IndexBuffer() {
		super();
		setTarget(BufferTarget.ELEMENT_ARRAY_BUFFER);
		setUsage(BufferUsage.STATIC_DRAW);
	}
	
	public void setValues(int[] values) {
		IntBuffer buffer = BufferUtils.createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		bufferData(buffer);
	}
	
	public int getOffset() { return m_offset; }
	public void setOffset(int offset) { m_offset = offset; }
}
