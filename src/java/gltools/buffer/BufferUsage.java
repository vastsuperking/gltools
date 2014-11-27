package gltools.buffer;

import gltools.gl.GL1;

public enum BufferUsage {
	STATIC_DRAW(GL1.GL_STATIC_DRAW),
	STREAM_DRAW(GL1.GL_STREAM_DRAW);
	
	private int m_id;
	
	BufferUsage(int id) { 
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
