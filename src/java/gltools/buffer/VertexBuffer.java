package gltools.buffer;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;


public class VertexBuffer extends Buffer {
	public VertexBuffer() {
		super();
		setTarget(BufferTarget.ARRAY_BUFFER);
		setUsage(BufferUsage.STATIC_DRAW);
	}
	public VertexBuffer(BufferUsage usage) {
		super();
		setTarget(BufferTarget.ARRAY_BUFFER);
		setUsage(usage);
	}
	public void setValues(float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		bufferData(buffer);
	}
}
