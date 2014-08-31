package gltools;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

public class Point implements Primitive {
	private Vertex m_vertex = null;
	
	public Point(Vertex v) {
		m_vertex = v;
	}
	
	public Vertex getVertex() { return m_vertex; }
	
	@Override
	public ArrayList<Vertex> getVertices() {
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		list.add(m_vertex);
		return list;
	}
	@Override
	public int getVertexCount() { return 1; }
	
	@Override
	public Mode getMode() { return Mode.POINTS; }
	
	public void setVertex(Vertex vertex) { m_vertex = vertex; }
	
	@Deprecated
	public void render() {
		GL11.glBegin(GL11.GL_POINTS);
		m_vertex.render();
		GL11.glEnd();
	}
	
	@Override
	public Iterator<Vertex> iterator() {
		return getVertices().iterator();
	}
}
