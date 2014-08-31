package gltools.extra;

import gltools.Mode;
import gltools.Primitive;
import gltools.Vertex;
import gltools.Vertex.VertexAttribute;
import gltools.buffer.AttribArray;
import gltools.buffer.IndexBuffer;
import gltools.buffer.Geometry;
import gltools.buffer.VertexBuffer;
import gltools.shader.InputUsage;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.lwjgl.BufferUtils;

/**
 * This is a optional helper class responsible for creating a vbo (actually a vao) from a
 * set of a triangles
 */
public class GeometryFactory {
	public static boolean DEBUG = false;
	//VBO format:
	//Position[x, y, z], TexCoord[t, s],  Normal[x, y, z]
	private static final float[] QUAD_VBO = new float[] {
		+1.0f, +1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
		-1.0f, +1.0f, 0.0f, 0f,   1.0f, 0.0f, 0.0f, -1.0f,
		-1.0f, -1.0f, 0.0f, 0f,   0f,   0.0f, 0.0f, -1.0f,
		+1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f
	};
	private static final int[] QUAD_IBO = new int[] {
        0, 1, 2, 0, 2, 3
    };
	
	/**
	 * Generates a VAO from an array of primitives
	 * Uses the first primitives in the array to determine the 
	 * necessary attributes
	 */
	@SuppressWarnings("unchecked")
	public static <P extends Primitive> Geometry s_generateVAO(P... primitives) {
		if (primitives.length < 1) throw new IllegalArgumentException("Need at least 1 triangle to build VAO");

		P firstPrimitive = primitives[0];
		//This must be an arraylist because we want to get the same order of attributes when
		//we initialize the arrays and fill the buffer
		ArrayList<InputUsage> activeUsages = new ArrayList<InputUsage>();
		
		int stride = 0;
		
		//Assuming all primitives have same amount of vertices
		int verticesPerPrimitive = firstPrimitive.getVertexCount();
		
		//Use the first vertex of the first triangle to determine required "usages"
		if (firstPrimitive.getVertexCount() < 1) 
			throw new IllegalArgumentException("First Primitive has no vertices!");
		Vertex firstVertex = firstPrimitive.getVertices().get(0);
		for (VertexAttribute va : firstVertex.getAttributes()) {
			InputUsage usage = va.getUsage();
			//A vertex cannot have two attributes with the same usage, so we should not be adding it twice
			activeUsages.add(usage);
			stride += usage.getDataType().getSize();
		}
		
		if (DEBUG) System.out.println("Stride: " + stride);
		if (DEBUG) System.out.println("Vertices per primitive: " + verticesPerPrimitive);
		
		//Now do a second pass and initialize the attribute arrays as well as the vbo
		//Make it linked, just to be safe
		LinkedHashMap<InputUsage, AttribArray> arrays = new LinkedHashMap<InputUsage, AttribArray>();
		VertexBuffer vbo = new VertexBuffer();
		
		int arrayOffset = 0;
		for (InputUsage u : activeUsages) {
			arrays.put(u, new AttribArray(vbo, u, stride, arrayOffset));
			arrayOffset += u.getDataType().getSize();
		}
		
		//Finally, initialize and fill the buffer
		//Stride is essentially the length of one vertex, so * by vpp and by num of primitives
		ByteBuffer vboBuffer = BufferUtils.createByteBuffer(stride * verticesPerPrimitive * primitives.length);
		for (P primitive : primitives) {
			if (DEBUG) System.out.println("Adding primitive: " + primitive);
			for (Vertex v : primitive) {
				if (DEBUG) System.out.println("Adding vertex: " + v + " at pos " + vboBuffer.position());
				//The position at the start of the vertex
				int vPos = vboBuffer.position();
				for (InputUsage u : activeUsages) {
					VertexAttribute a = v.getAttribute(u);
					if (a == null)
						throw new RuntimeException("Could not find attribute for required usage: " + u + " in " + v + ":" + primitive); 
					//Since the position changes might not happen in a bytebuffer
					//but in a floatbuffer, which uses different positions,
					//we need to update the position manually by adding the offset
					//which is also the offset from the start of the vertex
					AttribArray array = arrays.get(u);
					vboBuffer.position(vPos + (int) array.getOffset()); 
					if (DEBUG) System.out.println("Adding attribute: " + a + " at " + vboBuffer.position());
					a.addTo(vboBuffer);
				}
				//Finally, increase the position by the stride
				//Since the last position change did not include
				//the length, we need to change the pos by the stride
				vboBuffer.position(vPos + stride);
				if (DEBUG) System.out.println("Done adding vertex at " + vboBuffer.position());
			}
		}
		vboBuffer.flip();
		if (DEBUG) System.out.println(vboBuffer);
		vbo.bind();
		vbo.bufferData(vboBuffer);
		vbo.unbind();
		
		Geometry geometry = new Geometry();
		geometry.setVertexCount(primitives.length * verticesPerPrimitive);
		geometry.addAllArrays(arrays.values());
		//Set the mode to the mode of the first primitive
		geometry.setMode(firstPrimitive.getMode());
		if (DEBUG) System.out.println("Arrays: " + geometry.getAllArrays());
		if (DEBUG) System.out.println("Vertex Count: " + geometry.getVertexCount());
		if (DEBUG) System.out.println("Mode: " + geometry.getMode());
		return geometry;
	}
	public static Geometry s_generateFullScreenQuad() {
		VertexBuffer vbo = new VertexBuffer();
		vbo.bind();
		vbo.setValues(QUAD_VBO);
		vbo.unbind();
		IndexBuffer ibo = new IndexBuffer();
		ibo.bind();
		ibo.setValues(QUAD_IBO);
		ibo.unbind();
				
		Geometry geometry = new Geometry();
		geometry.setMode(Mode.TRIANGLES);
		geometry.setVertexCount(6);
		geometry.setIndexBuffer(ibo);

		geometry.addArray(new AttribArray(vbo, InputUsage.VERTEX_POSITION_3D, 	8 * Float.SIZE, 0 * Float.SIZE));
		geometry.addArray(new AttribArray(vbo, InputUsage.VERTEX_TEX_COORD,		8 * Float.SIZE, 3 * Float.SIZE));
		geometry.addArray(new AttribArray(vbo, InputUsage.VERTEX_NORMAL, 		8 * Float.SIZE, 5 * Float.SIZE));

		return geometry;
	}
}
