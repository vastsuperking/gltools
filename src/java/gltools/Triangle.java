package gltools;

import gltools.gl.GL;
import gltools.gl.GL1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Triangle implements Primitive {
	private Vertex[] m_vertices = new Vertex[3];
	
	public Triangle() {}
	public Triangle(Vertex v1, Vertex v2, Vertex v3) {
		m_vertices = new Vertex[] {v1, v2, v3};
	}

	/**
	 * Sets a vertex in the triangle with valid indices from 0-2
	 */
	public void setVertex(int index, Vertex v) {
		m_vertices[index] = v;
	}
	public Vertex getVertex(int index) {
		return m_vertices[index];
	}
	@Override
	public ArrayList<Vertex> getVertices() {
		return new ArrayList<Vertex>(Arrays.asList(m_vertices));
	}
	
	@Override
	public int getVertexCount() {
		return m_vertices.length;
	}
	
	@Override
	public Mode getMode() { return Mode.TRIANGLES; }
	
	public String toString() {
		return "Triangle" + getVertices();
	}
	@Override
	public Iterator<Vertex> iterator() {
		return getVertices().iterator();
	}
	/**
	 * Note, this function uses legacy begin and end calls,
	 * as well as the good old glVertex(...) and such calls
	 * This functions might be removed in the future
	 */
	@Deprecated
	public void render(GL gl) {
		GL1 gl1 = gl.getGL1();
		gl1.glBegin(GL1.GL_TRIANGLES);
		for (Vertex v : m_vertices) {
			if (v != null) {
				v.render(gl);
			}
		}
		gl1.glEnd();
	}
}
