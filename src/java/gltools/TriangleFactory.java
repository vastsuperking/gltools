package gltools;

import glcommon.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class TriangleFactory {
	public static List<Triangle> s_create(ArrayList<Vertex> vertices) {
		if (vertices.size() < 3) throw new IllegalArgumentException("Need at least 3 vertices to create a triangle");
		
		List<Triangle> triangles = new ArrayList<Triangle>();
		
		for (int i = 2; i < vertices.size(); i++) {
			Vertex v1 = vertices.get(i);
			Vertex v2 = vertices.get(0);
			Vertex v3 = vertices.get(i - 1);
			triangles.add(new Triangle(v1, v2, v3));
		}
		return triangles;
	}
	public static void main(String[] args) {
		Vertex v1 = new Vertex(new Vector3f(1f, 1f, -10f));
		Vertex v2 = new Vertex(new Vector3f(0f, 1f, -10f));
		Vertex v3 = new Vertex(new Vector3f(0f, 0f, -10f));
		Vertex v4 = new Vertex(new Vector3f(1f, 0f, -10f));
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		List<Triangle> triangles = s_create(vertices);
		for (Triangle triangle : triangles) {
			System.out.println("Triangle: " + triangle.getVertices());
		}
	}
}
