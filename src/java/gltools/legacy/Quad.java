package gltools.legacy;

import gltools.Mode;
import gltools.Primitive;
import gltools.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

/**
 * This is a legacy Quad class, and should not be used
 */
public class Quad implements Primitive {
	private Vertex[] m_vertices = new Vertex[4];
	
	public Quad() {}
	public Quad(Vertex tr, Vertex tl, Vertex bl, Vertex br) {
		m_vertices = new Vertex[] {tr, tl, bl, br};
	}
	
	/**
	 * Sets a vertex in the quad with valid indices from 0-3
	 * In order top-right, top-left, bottom-left, bottom-right
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
	@SuppressWarnings("deprecation")
	@Override
	public Mode getMode() {
		return Mode.QUADS;
	}
	
	@Override
	public Iterator<Vertex> iterator() {
		return getVertices().iterator();
	}
	
	@Deprecated
	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
		for (Vertex v : m_vertices) {
			if (v != null) {
				v.render();
			}
		}
		GL11.glEnd();
	}
}
