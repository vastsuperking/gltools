package gltools.buffer;

import glcommon.BufferUtils;
import gltools.gl.GL1;

import java.nio.IntBuffer;

public class IndexBuffer extends Buffer {
	private int m_offset = 0;

	public IndexBuffer() {
		super();
		setTarget(BufferTarget.ELEMENT_ARRAY_BUFFER);
		setUsage(BufferUsage.STATIC_DRAW);
	}
	
	public void setValues(GL1 gl, int[] values) {
		IntBuffer buffer = BufferUtils.createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		bufferData(gl, buffer);
	}
	
	public int getOffset() { return m_offset; }
	public void setOffset(int offset) { m_offset = offset; }
}
