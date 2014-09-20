package gltools.buffer;

import gltools.Mode;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;

/**
 * This class holds geometry data, a series of attribute arrays and an optional IndexBuffer
 * This was originally called VAO, but was renamed
 */
public class Geometry {
	private ArrayList<AttribArray> m_arrays = new ArrayList<AttribArray>();
	private IndexBuffer m_ibo;
	private Mode m_mode = Mode.TRIANGLES;
	private int m_vertexCount = 0;
	
	public Geometry() {}
	
	public Mode getMode() {
		return m_mode;
	}
	public int getVertexCount() {
		return m_vertexCount;
	}
	public IndexBuffer getIndexBuffer() { return m_ibo; }
	public ArrayList<AttribArray> getAllArrays() { return m_arrays; }
	
	public void setMode(Mode mode) {
		m_mode = mode;
	}
	public void setVertexCount(int count) {
		m_vertexCount = count;
	}
	public void setIndexBuffer(IndexBuffer ibo) {
		m_ibo = ibo;
	}
	
	public void addArray(AttribArray array) {
		m_arrays.add(array);
	}
	public void addAllArrays(Collection<AttribArray> arrays) {
		m_arrays.addAll(arrays);
	}

	public void render() {
		bind();
		//For IBOs
		if (m_ibo != null) {
			GL11.glDrawElements(m_mode.getMode(), m_vertexCount, GL11.GL_UNSIGNED_INT, m_ibo.getOffset());
		} else GL11.glDrawArrays(m_mode.getMode(), 0, m_vertexCount);
		
		unbind();
	}

	public void bind() {
		for (AttribArray a : m_arrays) {
			a.enable();
			a.point();
		}
		
		if (m_ibo != null) m_ibo.bind();
	}
	
	public void unbind() {
		if (m_ibo != null) m_ibo.unbind();
		
		for (AttribArray a : m_arrays) {
			a.disable();
		}
	}
}
