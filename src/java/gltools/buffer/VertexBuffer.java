package gltools.buffer;

import glcommon.BufferUtils;
import gltools.gl.GL1;

import java.nio.FloatBuffer;


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
	public void setValues(GL1 gl, float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		bufferData(gl, buffer);
	}
}
