package gltools.buffer;

import gltools.gl.GL1;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

public class Buffer {
	private static HashMap<BufferTarget, Buffer> s_currentBuffers = new HashMap<BufferTarget, Buffer>();

	private BufferUsage m_usage = BufferUsage.STATIC_DRAW;
	private BufferTarget m_target = BufferTarget.ARRAY_BUFFER;
	
	private int m_handle = -1;

	public Buffer() {}
	
	public Buffer(int handle) {
		m_handle = handle;
	}
	
	public BufferTarget getTarget() { return m_target; }
	public BufferUsage getUsage() { return m_usage; }
	public int getHandle() { return m_handle; }
	
	public void setTarget(BufferTarget target) { m_target = target; }
	public void setUsage(BufferUsage usage) { m_usage = usage; }
	
	public void getData(GL1 gl, long offset, FloatBuffer dest) {
		checkBound();
		gl.glGetBufferSubData(m_target.getID(), offset, dest);
	}
	public void getData(GL1 gl, long offset, IntBuffer dest) {
		checkBound();
		gl.glGetBufferSubData(m_target.getID(), offset, dest);
	}
	
	public void init(GL1 gl) {
		if (m_handle == -1) m_handle = gl.glGenBuffers();
	}
	
	/**
	 * This function will buffer an array of bytes, overriding any existing data
	 * It assumes that the data has been flipped, as do all the following functions
	 */
	 public void bufferData(GL1 gl, ByteBuffer buffer) {
		checkBound();
		gl.glBufferData(m_target.getID(), buffer, m_usage.getID());
	}

	public void bufferData(GL1 gl ,FloatBuffer data) {
		checkBound();
		gl.glBufferData(m_target.getID(), data, m_usage.getID());
	}
	public void bufferData(GL1 gl, IntBuffer data) {
		checkBound();
		gl.glBufferData(m_target.getID(), data, m_usage.getID());
	}
	
	public void bind(GL1 gl) {
		s_currentBuffers.put(m_target, this);
		gl.glBindBuffer(m_target.getID(), getHandle());
	}
	public void unbind(GL1 gl) {
		s_currentBuffers.put(m_target, null);
		gl.glBindBuffer(m_target.getID(), 0);
	}
	public void delete(GL1 gl) {
		gl.glDeleteBuffers(m_handle);
	}
	private void checkBound() {
		if (s_currentBuffers.get(m_target) != this) throw new RuntimeException("bind() must be called first!");
	}
}
