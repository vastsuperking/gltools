package glextra.renderer;

import glcommon.vector.Matrix3f;
import glcommon.vector.MatrixFactory;
import glcommon.vector.MatrixUtils;
import glcommon.vector.Vector2f;
import glextra.font.Font;
import glextra.font.Font.Glyph;
import glextra.material.GlobalParamBindingSet;
import glextra.material.Material;
import glextra.renderer.GBuffer.GBufferMode;
import gltools.Mode;
import gltools.buffer.AttribArray;
import gltools.buffer.Geometry;
import gltools.buffer.IndexBuffer;
import gltools.buffer.VertexBuffer;
import gltools.display.Display;
import gltools.display.ResizeListener;
import gltools.extra.GeometryFactory;
import gltools.shader.InputUsage;
import gltools.util.GLMatrix3f;

import java.util.ArrayList;
import java.util.HashSet;

import org.lwjgl.opengl.GL11;

/*
 * Notes on non-lighted rendering in deferred (startLighting()) mode
 * If input normals(normalMap) is 
 */
public class LWJGLRenderer2D implements Renderer2D {
	private static LWJGLRenderer2D INSTANCE = null;
	
	//Temporary list with all lights to be rendered
	private ArrayList<Light> m_lights = new ArrayList<Light>();
	private Geometry m_fullScreenQuad;
	
	private GBuffer m_gBuffer;
	
	public GLMatrix3f m_modelMat;
	public GLMatrix3f m_viewMat;
	public GLMatrix3f m_projMat;
	
	private VertexBuffer m_verticesBuf;
	private VertexBuffer m_texCoordsBuf;
	private IndexBuffer m_indicesBuf;
	
	//CS
	private float m_csTop;
	private float m_csBottom;
	private float m_csRight;
	private float m_csLeft;
	
	private Display m_display;
	
	//private GlobalParamProvider m_provider;
	
	//Current states
	private Font m_font;
	private Material m_material;
	
	private boolean m_usingDeferred = false;
	
	private GlobalParamBindingSet m_globals = new GlobalParamBindingSet();
	
	private LWJGLRenderer2D() {
	}
	
	@Override
	public void init(float left, float right, float top, float bottom, Display display) {
		m_gBuffer = new GBuffer(display.getWidth(), display.getHeight());
		m_fullScreenQuad = GeometryFactory.s_generateFullScreenQuad();
		
		m_modelMat = new GLMatrix3f();
		m_viewMat = new GLMatrix3f();
		m_projMat = new GLMatrix3f();
		
		//m_provider = new ListParamProvider(new GlobalParam(InputUsage.MODEL_MATRIX_2D, m_modelMat),
											//new GlobalParam(InputUsage.VIEW_MATRIX_2D, m_viewMat),
											//new GlobalParam(InputUsage.PROJECTION_MATRIX_2D, m_projMat));
		m_globals.addParam(InputUsage.MODEL_MATRIX_2D, m_modelMat);
		m_globals.addParam(InputUsage.VIEW_MATRIX_2D, m_viewMat);
		m_globals.addParam(InputUsage.PROJECTION_MATRIX_2D, m_projMat);
		
		updateProjection(left, right, top, bottom);
		
		display.addResizedListener(new ResizeListener() {
			public void onResize(int width, int height) {
				GL11.glViewport(0, 0, width, height);
				m_gBuffer.resize(width, height);
			}
		});
		
		m_verticesBuf = new VertexBuffer();
		m_texCoordsBuf = new VertexBuffer();
		m_indicesBuf = new IndexBuffer();
		

		
		m_display = display;
	}
	
	@Override
	public void setMaterial(Material mat) {
		m_material = mat;
	}
	@Override
	public Material getMaterial() { return m_material; }
	
	@Override
	public void setFont(Font font) {
		m_font = font;
	}
	@Override
	public Font getFont() { return m_font; }

	@Override
	public float getCSTop() { return m_csTop; }
	@Override
	public float getCSBottom() { return m_csBottom; }
	@Override
	public float getCSRight() { return m_csRight; }
	@Override
	public float getCSLeft() { return m_csLeft; }
	
	@Override
	public float getCSWidth() { return m_csLeft + m_csRight; }
	@Override
	public float getCSHeight() { return m_csTop + m_csBottom; }
	
	@Override
	public Display getDisplay() { return m_display; }
	
	@Override
	public void updateProjection(float left, float right, float top, float bottom) {
		//TODO: Fix to get rid of negatives on left and bottom
		m_projMat.setCurrentMatrix(
				MatrixFactory.create2DProjectionMatrix(-left, right, top, -bottom));		
		m_csLeft = left;
		m_csRight = right;
		m_csTop = top;
		m_csBottom = bottom;
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
	public Vector2f getViewTranslation() {
		return MatrixUtils.getTranslation(m_viewMat.getCurrentMatrix());
	}
	@Override
	public Vector2f getModelTranslation() {
		return MatrixUtils.getTranslation(m_modelMat.getCurrentMatrix());
	}
	
	@Override
	public Vector2f getViewScale() {
		return MatrixUtils.getScale(m_viewMat.getCurrentMatrix());
	}
	@Override
	public Vector2f getModelScale() {
		return MatrixUtils.getScale(m_modelMat.getCurrentMatrix());
	}
	
	@Override
	public float getViewRotation() {
		return MatrixUtils.getRotation(m_viewMat.getCurrentMatrix());
	}
	
	@Override
	public float getModelRotation() {
		return MatrixUtils.getRotation(m_modelMat.getCurrentMatrix());
	}
	
	@Override
	public Matrix3f getModelMatrix() {
		return m_modelMat.getCurrentMatrix();
	}
	@Override
	public Matrix3f getViewMatrix() {
		return m_viewMat.getCurrentMatrix();
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
	public void fillRect(float x, float y, float width, float height) {
		fillRect(x, y, width, height, 1, 1);
	}
	
	@Override
	public void fillRect(float x, float y, float width, float height, float texCoordWidth, float texCoordHeight) {
		if (m_material == null) throw new RuntimeException("Must call setMaterial() first!");
		
		HashSet<String> modes = new HashSet<String>();
		if (m_usingDeferred) modes.add("DEFERRED");
		m_material.selectTechnique(modes);
		m_material.bind(m_globals);
		//Sets the output buffers to point
		//to the correct attachments
		m_gBuffer.setDrawBuffers();
		//For bottom left origin
		float vertices[] = {x + width, y + height,
							 x, y + height,
							 x, y,
							 x + width, y };
		
		float texCoords[] = {texCoordWidth, texCoordHeight,
							  0.0f, texCoordHeight,
							  0.0f, 0.0f,
							  texCoordWidth, 0 };
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
	public void drawString(float x, float y, float scale, String string) {
		//The font we will be using
		Font font = getFont();
		if (font == null) throw new RuntimeException("No font set!");
		
		//First, push the transformation matrix
		pushModel();
		translate(x, y);
		
		char[] chars = string.toCharArray();
		for (char c : chars) {
			if (c == '\n' || c == '\t') continue;
			Glyph g = font.getGlyph(c);
			g.renderAndTranslate(this, scale, getMaterial());
		}
		
		//Pop it to get back to what we had before
		popModel();
	}
	
	@Override
	public void doLightingComputations() {
		//Light alpha blending - additive
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		for (Light l : m_lights) {
			l.bind(m_gBuffer);
			m_fullScreenQuad.render();
			l.unbind(m_gBuffer);
		}
		//Disable blending after done
		GL11.glDisable(GL11.GL_BLEND);
		
		//Clear lights array - already rendered these
		m_lights.clear();
	}

	@Override
	public void renderLight(Light light) {
		m_lights.add(light);
	}

	@Override
	public void startGeometry() {
		m_modelMat.getCurrentMatrix().setIdentity();
		m_viewMat.getCurrentMatrix().setIdentity();	

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA,	GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	@Override
	public void finishGeometry() {
		//Disable blending
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	@Override
	public void startLighted() {
		m_gBuffer.bind(GBufferMode.WRITE);	
		m_usingDeferred = true;
	}
	@Override
	public void finishLighted() {
		m_gBuffer.unbind(GBufferMode.WRITE);
		m_usingDeferred = false;
	}
	
	
	@Override
	public void clear() {
		//Bind gbuffer and clear that too
		m_gBuffer.bind(GBufferMode.WRITE);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | 
				 GL11.GL_DEPTH_BUFFER_BIT |
				 GL11.GL_STENCIL_BUFFER_BIT);
		m_gBuffer.unbind(GBufferMode.WRITE);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | 
				 GL11.GL_DEPTH_BUFFER_BIT |
				 GL11.GL_STENCIL_BUFFER_BIT);
	}
	
	public static LWJGLRenderer2D getInstance() {
		if (INSTANCE == null) INSTANCE = new LWJGLRenderer2D();
		return INSTANCE;
	}
}
