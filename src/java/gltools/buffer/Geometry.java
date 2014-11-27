package gltools.buffer;

import gltools.Mode;
import gltools.gl.GL1;
import gltools.gl.GL2;

import java.util.ArrayList;
import java.util.Collection;

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

	public void render(GL2 gl) {
		bind(gl);
		//For IBOs
		if (m_ibo != null) {
			gl.glDrawElements(m_mode.getMode(), m_vertexCount, GL1.GL_UNSIGNED_INT, m_ibo.getOffset());
		} else gl.glDrawArrays(m_mode.getMode(), 0, m_vertexCount);
		
		unbind(gl);
	}

	public void bind(GL2 gl) {
		for (AttribArray a : m_arrays) {
			a.enable(gl);
			a.point(gl);
		}
		
		if (m_ibo != null) m_ibo.bind(gl);
	}
	
	public void unbind(GL2 gl) {
		if (m_ibo != null) m_ibo.unbind(gl);
		
		for (AttribArray a : m_arrays) {
			a.disable(gl);
		}
	}
}
