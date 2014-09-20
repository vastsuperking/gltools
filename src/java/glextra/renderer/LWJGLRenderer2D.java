package glextra.renderer;

import org.lwjgl.opengl.GL11;

import glextra.material.Material;
import gltools.Mode;
import gltools.buffer.AttribArray;
import gltools.buffer.Geometry;
import gltools.buffer.IndexBuffer;
import gltools.buffer.VertexBuffer;
import gltools.shader.InputUsage;
import gltools.utils.GLMatrix3f;
import gltools.vector.MatrixFactory;
import gltools.vector.Vector2f;


public class LWJGLRenderer2D implements Renderer2D {
	private static LWJGLRenderer2D INSTANCE = null;
	
	
	public GLMatrix3f m_modelMat;
	public GLMatrix3f m_viewMat;
	public GLMatrix3f m_projMat;
	
	private VertexBuffer m_verticesBuf;
	private VertexBuffer m_texCoordsBuf;
	private IndexBuffer m_indicesBuf;
	
	
	private Material m_material;
	
	private LWJGLRenderer2D() {}
	
	@Override
	public void init(float left, float right, float top, float bottom) {
		m_modelMat = new GLMatrix3f(InputUsage.MODEL_MATRIX_2D);
		m_viewMat = new GLMatrix3f(InputUsage.VIEW_MATRIX_2D);
		m_projMat = new GLMatrix3f(InputUsage.PROJECTION_MATRIX_2D);
		
		m_projMat.setCurrentMatrix(
				MatrixFactory.create2DProjectionMatrix(left, right, top, bottom));
		
		m_verticesBuf = new VertexBuffer();
		m_texCoordsBuf = new VertexBuffer();
		m_indicesBuf = new IndexBuffer();
	}

	
	@Override
	public void setMaterial(Material mat) {
		m_material = mat;
		if (mat.getGlobals().size() == 0) {
			initMaterialGlobals(mat);
		}
	}
	@Override
	public Material getMaterial() { return m_material; }
	
	private void initMaterialGlobals(Material mat) {
		mat.addGlobalParam("modelMat", m_modelMat);
		mat.addGlobalParam("viewMat", m_viewMat);
		mat.addGlobalParam("projectionMat", m_projMat);
	}
	@Override
	public void viewTrans(float x, float y) {
		m_viewMat.getCurrentMatrix().mul(
				MatrixFactory.create2DTranslationMatrix(new Vector2f(x, y)));
	}
	@Override
	public void viewScale(float x, float y) {
		m_viewMat.getCurrentMatrix().mul(
				MatrixFactory.create2DScaleMatrix(new Vector2f(x, y)));
	}
	
	
	@Override
	public void translate(float x, float y) {
		m_modelMat.getCurrentMatrix().mul(
				MatrixFactory.create2DTranslationMatrix(new Vector2f(x, y)));
	}
	@Override
	public void scale(float x, float y) {
		m_modelMat.getCurrentMatrix().mul(
				MatrixFactory.create2DScaleMatrix(new Vector2f(x, y)));
		
	}
	@Override
	public void rotate(float radians) {
		m_modelMat.getCurrentMatrix().mul(
				MatrixFactory.create2DRotationMatrix(radians));
	}
	
	@Override
	public void pushView() {
		m_viewMat.push();
	}
	@Override
	public void popView() {
		m_viewMat.pop();
	}
	
	@Override
	public void pushModel() {
		m_modelMat.push();
	}
	@Override
	public void popModel() {
		m_modelMat.pop();
	}	
	
	@Override
	public void startGeometry() {
		m_modelMat.getCurrentMatrix().setIdentity();
		m_viewMat.getCurrentMatrix().setIdentity();	
	}
	
	@Override
	public void fillRect(float x, float y, float width, float height) {
		if (m_material == null) throw new RuntimeException("Must call setMaterial() first!");
		
		m_material.bind();
		//For bottom left origin
		float vertices[] = {x + width, y + height,
							 x, y + height,
							 x, y,
							 x + width, y };
		
		float texCoords[] = {1.0f, 1.0f,
							  0.0f, 1.0f,
							  0.0f, 0.0f,
							  1.0f, 0.0f };
		int indices[] = {0, 1, 2, 0, 2, 3};
		
		m_verticesBuf.bind();
		m_verticesBuf.setValues(vertices);
		m_verticesBuf.unbind();
		
		m_texCoordsBuf.bind();
		m_texCoordsBuf.setValues(texCoords);
		m_texCoordsBuf.unbind();
		
		m_indicesBuf.bind();
		m_indicesBuf.setValues(indices);
		m_indicesBuf.unbind();
		
		Geometry geo = new Geometry();
		geo.addArray(new AttribArray(m_verticesBuf, InputUsage.VERTEX_POSITION_2D, 0, 0));
		geo.addArray(new AttribArray(m_texCoordsBuf, InputUsage.VERTEX_TEX_COORD, 0, 0));
		geo.setVertexCount(6);
		geo.setMode(Mode.TRIANGLES);
		geo.setIndexBuffer(m_indicesBuf);
		
		geo.render();
		m_material.unbind();
	}
	
	@Override
	public void finishGeometry() {}
	
	@Override
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | 
					 GL11.GL_DEPTH_BUFFER_BIT |
					 GL11.GL_STENCIL_BUFFER_BIT);
	}
	
	public static LWJGLRenderer2D getInstance() {
		if (INSTANCE == null) INSTANCE = new LWJGLRenderer2D();
		return INSTANCE;
	}
}
