package gltools.legacy;

import org.lwjgl.opengl.GL11;

@Deprecated
public class DisplayList {
	private int m_handle;
	
	public DisplayList() {
		this(GL11.glGenLists(1));
	}
	public DisplayList(int handle) {
		m_handle = handle;
	}
	
	public void start() {
		GL11.glNewList(m_handle, GL11.GL_COMPILE);
	}
	public void stop() {
		GL11.glEndList();
	}
	public void render() {
		GL11.glCallList(m_handle);
	}
	public void delete() {
		GL11.glDeleteLists(m_handle, 1);
	}
}
