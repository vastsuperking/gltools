package gltools.buffer;

import gltools.gl.GL1;

public enum BufferTarget {
	ARRAY_BUFFER(GL1.GL_ARRAY_BUFFER),
	ELEMENT_ARRAY_BUFFER(GL1.GL_ELEMENT_ARRAY_BUFFER);
	
	private int m_id;
	
	BufferTarget(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
