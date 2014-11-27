package glextra;

import glcommon.util.ResourceLocator;
import glcommon.util.ResourceLocator.FolderResourceLocator;
import glcommon.util.ResourceUtils;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glextra.material.Material;
import gltools.Triangle;
import gltools.TriangleFactory;
import gltools.Vertex;
import gltools.Vertex.TexCoord;
import gltools.Vertex.VertexNormal;
import gltools.buffer.Geometry;
import gltools.extra.GeometryFactory;
import gltools.gl.GL;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OBJReader {
	public static Mesh s_read(File file, GL gl) throws IOException {
		return s_read(file.getName(), new FolderResourceLocator(file.getParentFile()), gl);
	}
	/**
	 * Will read and parse a .obj file from an InputStream
	 * @param input the stream to read text from
	 */
	public static Mesh s_read(String resource, ResourceLocator locator, GL gl) throws IOException {
		InputStream input = locator.getResource(resource);

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		Mesh mesh = new Mesh();
		
		HashMap<String, Material> materials = new HashMap<String, Material>();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Triangle> primitives = new ArrayList<Triangle>();
		ArrayList<VertexNormal> normals = new ArrayList<VertexNormal>();
		ArrayList<TexCoord> texcoords = new ArrayList<TexCoord>();
		
		Material currentMaterial = null;
		
		String line;
		while((line = reader.readLine()) != null) {
			if (line.startsWith("v ")) vertices.add(s_parseVertex(line.substring(2)));
			else if (line.startsWith("vt ")) texcoords.add(s_parseTexCoord(line.substring(3)));
			else if (line.startsWith("vn ")) normals.add(s_parseNormal(line.substring(3)));	
			else if (line.startsWith("vp "));
			else if (line.startsWith("f ")) primitives.addAll(s_parseFace(line.substring(2), vertices, normals, texcoords));
			else if (line.startsWith("usemtl ")) {
				//Finish the old geometry
				if (primitives.size() > 0) {
					Geometry geo = GeometryFactory.s_generateGeometry(gl, primitives.toArray(new Triangle[0]));
					primitives.clear();
					mesh.addGeometry(geo, currentMaterial);
				}
				//Set next material
				String matName = line.substring(7);
				currentMaterial = materials.get(matName);
				if (currentMaterial == null) throw new RuntimeException("Could not find material: " + matName);
			} else if (line.startsWith("mtllib ")) {
				String libloc = line.substring(7);
				String parent = ResourceUtils.s_getParentDirectory(resource);
				String libResource = parent + libloc;
				materials.putAll(OBJMaterialReader.s_readMaterials(libResource, locator, gl));
			}
		}
		//Finish last geometry
		if (primitives.size() > 0) {
			Geometry geo = GeometryFactory.s_generateGeometry(gl, primitives.toArray(new Triangle[0]));
			mesh.addGeometry(geo, currentMaterial);
			primitives.clear();
		}
		
		return mesh;
	}
	/**
	 * same as normal read, but ignores mtlib call and only uses supplied hashmap for material lib
	 */
	public static Mesh s_read(String resource, ResourceLocator locator, HashMap<String, Material> materials, GL gl) throws IOException {
		InputStream input = locator.getResource(resource);

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		Mesh mesh = new Mesh();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Triangle> primitives = new ArrayList<Triangle>();
		ArrayList<VertexNormal> normals = new ArrayList<VertexNormal>();
		ArrayList<TexCoord> texcoords = new ArrayList<TexCoord>();
		
		Material currentMaterial = null;
		
		String line;
		while((line = reader.readLine()) != null) {
			if (line.startsWith("v ")) vertices.add(s_parseVertex(line.substring(2)));
			else if (line.startsWith("vt ")) texcoords.add(s_parseTexCoord(line.substring(3)));
			else if (line.startsWith("vn ")) normals.add(s_parseNormal(line.substring(3)));	
			else if (line.startsWith("vp "));
			else if (line.startsWith("f ")) primitives.addAll(s_parseFace(line.substring(2), vertices, normals, texcoords));
			else if (line.startsWith("usemtl ")) {
				//Finish the old geometry
				if (primitives.size() > 0) {
					Geometry geo = GeometryFactory.s_generateGeometry(gl, primitives.toArray(new Triangle[0]));
					primitives.clear();
					
					mesh.addGeometry(geo, currentMaterial);
				}
				//Set next material
				String matName = line.substring(7);
				currentMaterial = materials.get(matName);
				if (currentMaterial == null) throw new RuntimeException("Could not find material: " + matName);
			}
		}
		//Finish last geometry
		if (primitives.size() > 0) {
			Geometry geo = GeometryFactory.s_generateGeometry(gl, primitives.toArray(new Triangle[0]));
			mesh.addGeometry(geo, currentMaterial);
			primitives.clear();
		}
		
		return mesh;
	}
	public static Geometry s_readGeometry(String resource,
			ResourceLocator locator, GL gl) throws IOException {
		return s_readGeometry(locator.getResource(resource), gl);
	}
	/**
	 * Will read and parse a .obj file from an InputStream - only reads the actual geometry, not the materials as well
	 * @param input the stream to read text from
	 */
	public static Geometry s_readGeometry(InputStream input, GL gl) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Triangle> primitives = new ArrayList<Triangle>();
		ArrayList<VertexNormal> normals = new ArrayList<VertexNormal>();
		ArrayList<TexCoord> texcoords = new ArrayList<TexCoord>();
		
		
		String line;
		while((line = reader.readLine()) != null) {
			if (line.startsWith("v ")) vertices.add(s_parseVertex(line.substring(2)));
			else if (line.startsWith("vt ")) texcoords.add(s_parseTexCoord(line.substring(3)));
			else if (line.startsWith("vn ")) normals.add(s_parseNormal(line.substring(3)));	
			else if (line.startsWith("vp "));
			else if (line.startsWith("f ")) primitives.addAll(s_parseFace(line.substring(2), vertices, normals, texcoords));
		}
		
		Geometry geo = GeometryFactory.s_generateGeometry(gl, primitives.toArray(new Triangle[0]));
		
		return geo;
	}
	
	private static Vertex s_parseVertex(String vertex) {
		String[] parts = vertex.split(" ");
		float x = Float.parseFloat(parts[0].trim());
		float y = Float.parseFloat(parts[1].trim());
		float z = Float.parseFloat(parts[2].trim());
		
		return new Vertex(new Vector3f(x, y, z));
	}
	/**
	 * Responsible for taking a face string definition
	 * See: http://en.wikipedia.org/wiki/Wavefront_.obj_file for more details
	 * Outputs a list of triangles that are the makeup of the Face
	 */
	private static List<Triangle> s_parseFace(String face, ArrayList<Vertex> vertices, ArrayList<VertexNormal> normals, ArrayList<TexCoord> texcoords) {
		String[] faceVertices = face.split(" ");
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		for(String v : faceVertices) {
			String[] attributes = v.split("/");
			//Indexing starts at 1 for obj files, so shift it back by one
			int vertexIndex = Integer.parseInt(attributes[0]) - 1;
			Vertex vertex = new Vertex(vertices.get(vertexIndex));
			if (attributes.length > 1 && (!attributes[1].equals(""))) {
				int texIndex = Integer.parseInt(attributes[1]);
				vertex.addAttribute(texcoords.get(texIndex - 1));
			}
			if (attributes.length > 2 && (!attributes[2].equals(""))) {
				int normalIndex = Integer.parseInt(attributes[2]);
				vertex.addAttribute(normals.get(normalIndex - 1));
			}
			vertexList.add(vertex);
		}
		return TriangleFactory.s_create(vertexList);
	}
	
	private static VertexNormal s_parseNormal(String normal) {
		String[] parts = normal.split(" ");
		float x = Float.parseFloat(parts[0].trim());
		float y = Float.parseFloat(parts[1].trim());
		float z = Float.parseFloat(parts[2].trim());
		return new VertexNormal(new Vector3f(x, y, z));
	}
	private static TexCoord s_parseTexCoord(String normal) {
		String[] parts = normal.split(" ");
		float x = Float.parseFloat(parts[0].trim());
		float y = Float.parseFloat(parts[1].trim());
		return new TexCoord(new Vector2f(x, y));
	}
}
