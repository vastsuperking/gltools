package gltools.buffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.opengl.GL15;

public class Buffer {
	private static HashMap<BufferTarget, Buffer> s_currentBuffers = new HashMap<BufferTarget, Buffer>();

	private BufferUsage m_usage = BufferUsage.STATIC_DRAW;
	private BufferTarget m_target = BufferTarget.ARRAY_BUFFER;
	
	private int m_handle;

	public Buffer() {
		this(GL15.glGenBuffers());
	}
	
	public Buffer(int handle) {
		m_handle = handle;
	}
	
	public BufferTarget getTarget() { return m_target; }
	public BufferUsage getUsage() { return m_usage; }
	public int getHandle() { return m_handle; }
	
	public void setTarget(BufferTarget target) { m_target = target; }
	public void setUsage(BufferUsage usage) { m_usage = usage; }
	
	public void getData(long offset, FloatBuffer dest) {
		checkBound();
		GL15.glGetBufferSubData(m_target.getID(), offset, dest);
	}
	public void getData(long offset, IntBuffer dest) {
		checkBound();
		GL15.glGetBufferSubData(m_target.getID(), offset, dest);
	}
	
	
	/**
	 * This function will buffer an array of bytes, overriding any existing data
	 * It assumes that the data has been flipped, as do all the following functions
	 */
	 public void bufferData(ByteBuffer buffer) {
		checkBound();
		GL15.glBufferData(m_target.getID(), buffer, m_usage.getID());
	}

	public void bufferData(FloatBuffer data) {
		checkBound();
		GL15.glBufferData(m_target.getID(), data, m_usage.getID());
	}
	public void bufferData(IntBuffer data) {
		checkBound();
		GL15.glBufferData(m_target.getID(), data, m_usage.getID());
	}
	
	public void bind() {
		s_currentBuffers.put(m_target, this);
		GL15.glBindBuffer(m_target.getID(), getHandle());
	}
	public void unbind() {
		s_currentBuffers.put(m_target, null);
		GL15.glBindBuffer(m_target.getID(), 0);
	}
	public void delete() {
		GL15.glDeleteBuffers(m_handle);
	}
	private void checkBound() {
		if (s_currentBuffers.get(m_target) != this) throw new RuntimeException("bind() must be called first!");
	}
}
