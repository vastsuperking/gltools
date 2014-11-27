package gltools;

import gltools.gl.GL;
import gltools.gl.GL1;

import java.util.ArrayList;
import java.util.Iterator;

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
	public void render(GL gl) {
		GL1 gl1 = gl.getGL1();
		gl1.glBegin(GL1.GL_POINTS);
		m_vertex.render(gl);
		gl1.glEnd();
	}
	
	@Override
	public Iterator<Vertex> iterator() {
		return getVertices().iterator();
	}
}
