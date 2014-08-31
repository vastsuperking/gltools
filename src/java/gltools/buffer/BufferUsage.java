package gltools.buffer;

import org.lwjgl.opengl.GL15;

public enum BufferUsage {
	STATIC_DRAW(GL15.GL_STATIC_DRAW),
	STREAM_DRAW(GL15.GL_STREAM_DRAW);
	
	private int m_id;
	
	BufferUsage(int id) { 
		m_id = id;
	}
	
	public int getID() { return m_id; }
}
