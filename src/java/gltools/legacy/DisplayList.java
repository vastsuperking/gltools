package gltools.legacy;

import gltools.gl.GL1;

@Deprecated
public class DisplayList {
	private int m_handle = -1;
	
	public DisplayList() {}
	public DisplayList(int handle) {
		m_handle = handle;
	}
	
	public void init(GL1 gl) { 
		if (m_handle != -1) m_handle = gl.glGenLists(1);
	}
	
	public void start(GL1 gl) {
		gl.glNewList(m_handle, GL1.GL_COMPILE);
	}
	public void stop(GL1 gl) {
		gl.glEndList();
	}
	public void render(GL1 gl) {
		gl.glCallList(m_handle);
	}
	public void delete(GL1 gl) {
		gl.glDeleteLists(m_handle, 1);
	}
}
