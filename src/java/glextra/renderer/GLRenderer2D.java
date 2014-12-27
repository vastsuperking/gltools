package glextra.renderer;

import glcommon.BufferUtils;
import glcommon.font.Font;
import glcommon.font.Font.Glyph;
import glcommon.vector.Matrix3f;
import glcommon.vector.MatrixFactory;
import glcommon.vector.MatrixUtils;
import glcommon.vector.Vector2f;
import glextra.FBuffer;
import glextra.FBuffer.FBufferAttachment;
import glextra.FBuffer.FBufferMode;
import glextra.material.GlobalParamBindingSet;
import glextra.material.Material;
import glextra.renderer.Light.LightProgramProvider;
import glextra.renderer.Light.NoLight;
import gltools.Mode;
import gltools.buffer.AttribArray;
import gltools.buffer.FrameBuffer;
import gltools.buffer.FrameBuffer.AttachmentPoint;
import gltools.buffer.Geometry;
import gltools.buffer.IndexBuffer;
import gltools.buffer.VertexBuffer;
import gltools.display.Display;
import gltools.extra.GeometryFactory;
import gltools.gl.GL;
import gltools.gl.GL1;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.texture.Texture2D;
import gltools.texture.TextureFormat;
import gltools.util.GLMatrix3f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/*
 * Notes on non-lighted rendering in deferred (startLighting()) mode
 * If input normals(normalMap) is 
 */
public class GLRenderer2D implements Renderer2D {
	private static final String GLYPH_TEX_PARAM = "glyphTexture";
	
	//Temporary list with all lights to be rendered
	private ArrayList<Light> m_lights = new ArrayList<Light>();
	private Geometry m_fullScreenQuad;
	
	private FloatBuffer m_quadVertices;
	private FloatBuffer m_quadTexCoords;
	private IntBuffer m_quadIndices;
	
	//Previously attached framebuffers
	private Stack<FrameBuffer> m_prevFrameBuffers = new Stack<FrameBuffer>();
	private FBuffer m_gBuffer;
	
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
	
	private GL m_gl;
	
	private GLTextureManager m_textureManager;
	private LightProgramProvider m_lightPrograms;
	
	public GLRenderer2D() {}
	
	@Override
	public void init(float left, float right, float top, float bottom,
					 GL gl, int width, int height) {
		m_gl = gl;
		
		m_textureManager = new GLTextureManager();
		m_lightPrograms = new LightProgramProvider();
		
		m_gBuffer = new FBuffer(width, height);
		m_gBuffer.addAttachment(new FBufferAttachment(0, AttachmentPoint.COLOR_ATTACHMENT0, InputUsage.GBUFFER_VERTEX_SAMPLER, TextureFormat.RGBA16F));
		m_gBuffer.addAttachment(new FBufferAttachment(1, AttachmentPoint.COLOR_ATTACHMENT1, InputUsage.GBUFFER_NORMAL_SAMPLER, TextureFormat.RGBA16F));
		m_gBuffer.addAttachment(new FBufferAttachment(2, AttachmentPoint.COLOR_ATTACHMENT2, InputUsage.GBUFFER_DIFFUSE_SAMPLER, TextureFormat.RGBA8));
		FrameBuffer prev = FrameBuffer.s_getCurrent();
		m_gBuffer.init(m_gl.getGL3());
		if (prev != null) prev.bind(m_gl);
		
		m_fullScreenQuad = GeometryFactory.s_generateFullScreenQuad(m_gl.getGL2());
		
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
		
		m_verticesBuf = new VertexBuffer();
		m_texCoordsBuf = new VertexBuffer();
		m_indicesBuf = new IndexBuffer();
		
		m_verticesBuf.init(m_gl.getGL1());
		m_texCoordsBuf.init(m_gl.getGL1());
		m_indicesBuf.init(m_gl.getGL1());
		
		m_quadVertices = BufferUtils.createFloatBuffer(8);
		m_quadTexCoords = BufferUtils.createFloatBuffer(8);
		m_quadIndices = BufferUtils.createIntBuffer(6);
	}
	
	public void resize(int width, int height) {
		FrameBuffer prev = FrameBuffer.s_getCurrent();
		m_gBuffer.resize(m_gl.getGL3(), width, height);
		
		if (prev != null) prev.bind(m_gl);
	}
	
	@Override
	public GL getGL() { return m_gl; }
	
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
		m_projMat.setCurrentMatrix(
				MatrixFactory.createAffineProjectionMatrix(left, right, top, bottom));		
		//System.out.println("Updating projection");
		m_csLeft = left;
		m_csRight = right;
		m_csTop = top;
		m_csBottom = bottom;
	}
	
	@Override
	public void viewTrans(float x, float y) {
		m_viewMat.getCurrentMatrix().mul(
				MatrixFactory.createAffineTranslationMatrix(new Vector2f(x, y)));
	}
	@Override
	public void viewScale(float x, float y) {
		m_viewMat.getCurrentMatrix().mul(
				MatrixFactory.createAffineScaleMatrix(new Vector2f(x, y)));
	}
	
	
	@Override
	public void translate(float x, float y) {
		m_modelMat.getCurrentMatrix().mul(
				MatrixFactory.createAffineTranslationMatrix(new Vector2f(x, y)));
	}
	@Override
	public void scale(float x, float y) {
		m_modelMat.getCurrentMatrix().mul(
				MatrixFactory.createAffineScaleMatrix(new Vector2f(x, y)));
		
	}
	@Override
	public void rotate(float radians) {
		m_modelMat.getCurrentMatrix().mul(
				MatrixFactory.createAffineRotationMatrix(radians));
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
		m_material.bind(m_gl, m_globals);
		//Sets the output buffers to point
		//to the correct attachments
		m_gBuffer.setDrawBuffers(m_gl.getGL2());
		//For bottom left origin
		m_quadVertices.rewind();
		m_quadVertices.put(x + width).put(y + height);
		m_quadVertices.put(x).put(y + height);
		m_quadVertices.put(x).put(y);
		m_quadVertices.put(x + width).put(y);

		m_quadTexCoords.rewind();
		m_quadTexCoords.put(texCoordWidth).put(texCoordHeight);
		m_quadTexCoords.put(0).put(texCoordHeight);
		m_quadTexCoords.put(0).put(0);
		m_quadTexCoords.put(texCoordWidth).put(0);
		
		m_quadIndices.rewind();
		m_quadIndices.put(0).put(1).put(2);
		m_quadIndices.put(0).put(2).put(3);

		m_quadVertices.flip();
		m_quadTexCoords.flip();
		m_quadIndices.flip();
		
		m_verticesBuf.bind(m_gl.getGL1());
		m_verticesBuf.bufferData(m_gl, m_quadVertices);
		m_verticesBuf.unbind(m_gl.getGL1());
		
		m_texCoordsBuf.bind(m_gl.getGL1());
		m_texCoordsBuf.bufferData(m_gl.getGL1(), m_quadTexCoords);
		m_texCoordsBuf.unbind(m_gl.getGL1());
		
		m_indicesBuf.bind(m_gl.getGL1());
		m_indicesBuf.bufferData(m_gl.getGL1(), m_quadIndices);
		m_indicesBuf.unbind(m_gl.getGL1());
		
		Geometry geo = new Geometry();
		geo.addArray(new AttribArray(m_verticesBuf, InputUsage.VERTEX_POSITION_2D, 0, 0));
		geo.addArray(new AttribArray(m_texCoordsBuf, InputUsage.VERTEX_TEX_COORD, 0, 0));
		geo.setVertexCount(6);
		geo.setMode(Mode.TRIANGLES);
		geo.setIndexBuffer(m_indicesBuf);
		
		geo.render(m_gl.getGL2());
		m_gl.glFlush();
		m_material.unbind(m_gl);	
	}
	
	@Override
	public void drawString(float x, float y, float scale, String string) {
		//The font we will be using
		Font font = getFont();
		if (font == null) throw new RuntimeException("No font set!");
		if (m_material == null) throw new RuntimeException("No material set");
		
		//First, push the transformation matrix
		pushModel();
		translate(x, y);
		
		char[] chars = string.toCharArray();
		for (char c : chars) {
			if (c == '\n' || c == '\t') continue;
			Glyph g = font.getGlyph(c);
			
			//Translate across the xoffset and down by the yoffset
			if (m_material.getParam(GLYPH_TEX_PARAM) == null || 
				m_material.getParam(GLYPH_TEX_PARAM).getDataType() != DataType.SAMPLER2D)
					throw new RuntimeException("Material does not contain " + GLYPH_TEX_PARAM + " as a parameter");
			
			Texture2D texture = m_textureManager.getTexture(m_gl, g.getImage());
			m_material.setTexture2D(GLYPH_TEX_PARAM, texture);
			//Material is already set...
			fillRect(g.getXOff() * scale, scale * g.getYOff(), 
						scale * g.getImage().getWidth(), 
						scale * g.getImage().getHeight());

			translate(g.getXAdvance() * scale, 0);
		}
		
		//Pop it to get back to what we had before
		popModel();
	}
	
	@Override
	public void doLightingComputations() {
		//Light alpha blending - additive
		GL1 gl = m_gl.getGL1();
		gl.glEnable(GL1.GL_BLEND);
		gl.glBlendFunc(GL1.GL_ONE, GL1.GL_ONE);
		
		if (m_lights.size() < 1) {
			//The No Light will draw only
			//where the normals are < -1
			//which signifies unlighted
			//Essentially, make sure unlighted stuff
			//still gets drawn
			Light l = new NoLight();
			l.bind(m_gl, m_gBuffer, m_lightPrograms);
			m_fullScreenQuad.render(m_gl.getGL2());
			l.unbind(m_gl, m_gBuffer, m_lightPrograms);
		}
		for (Light l : m_lights) {
			l.bind(m_gl, m_gBuffer, m_lightPrograms);
			m_fullScreenQuad.render(m_gl.getGL2());
			l.unbind(m_gl, m_gBuffer, m_lightPrograms);
		}
		//Disable blending after done
		gl.glDisable(GL1.GL_BLEND);
		
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
		
		m_gl.getGL1().glEnable(GL1.GL_BLEND);
		m_gl.getGL1().glBlendFunc(GL1.GL_SRC_ALPHA,	GL1.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	@Override
	public void finishGeometry() {
		//Disable blending
		m_gl.getGL1().glDisable(GL1.GL_BLEND);
	}
	
	@Override
	public void startLighted() {
		//If there is an FBO bound, save it
		FrameBuffer buffer = FrameBuffer.s_getCurrent();
		if (buffer != null) m_prevFrameBuffers.push(buffer);
		
		m_gBuffer.bind(m_gl.getGL3(), FBufferMode.WRITE);	
		m_usingDeferred = true;
	}
	@Override
	public void finishLighted() {
		m_gBuffer.unbind(m_gl.getGL3(), FBufferMode.WRITE);
		if (!m_prevFrameBuffers.isEmpty())
			m_prevFrameBuffers.pop().bind(m_gl);
		m_usingDeferred = false;
	}
	
	
	@Override
	public void clear() {
		GL1 gl = m_gl.getGL1();
		//Bind gbuffer and clear that too
		FrameBuffer prev = FrameBuffer.s_getCurrent();
		m_gBuffer.bind(m_gl.getGL3(), FBufferMode.WRITE);
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL1.GL_COLOR_BUFFER_BIT | 
				 GL1.GL_DEPTH_BUFFER_BIT |
				 GL1.GL_STENCIL_BUFFER_BIT);
		m_gBuffer.unbind(m_gl.getGL3(), FBufferMode.WRITE);
		if (prev != null) prev.bind(m_gl);
		
		gl.glClear(GL1.GL_COLOR_BUFFER_BIT | 
				 GL1.GL_DEPTH_BUFFER_BIT |
				 GL1.GL_STENCIL_BUFFER_BIT);
	}
}
