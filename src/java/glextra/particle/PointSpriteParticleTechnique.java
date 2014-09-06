package glextra.particle;

import gltools.Mode;
import gltools.ResourceLocator.ClasspathResourceLocator;
import gltools.buffer.AttribArray;
import gltools.buffer.BufferUsage;
import gltools.buffer.Geometry;
import gltools.buffer.VertexBuffer;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Uniform;
import gltools.texture.Texture1D;
import gltools.texture.Texture2D;
import gltools.utils.GLMatrix4f;
import gltools.vector.Vector3f;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class PointSpriteParticleTechnique implements ParticleTechnique {
	private static String PARTICLE_PROGRAM = "Programs/particle.prog";
	//3 bytes vertex, 1 byte life remaining, 1 byte size
	private int FLOATS_PER_PARTICLE = 3 + 1;
	private int BYTES_PER_PARTICLE = FLOATS_PER_PARTICLE * 4;
	public static int POINT_TEXTURE_UNIT = 0;
	public static int POINT_COLOR_TEXTURE_UNIT = 1;
	public static int POINT_SIZE_TEXTURE_UNIT = 2;

	private VertexBuffer m_buffer = new VertexBuffer(BufferUsage.STREAM_DRAW);
	private Geometry m_particleGeo = null;
	private Program m_program;
	private GLMatrix4f m_model 		 = null;
	private GLMatrix4f m_view 		 = null;
	private GLMatrix4f m_projection = null;

	//Particle data
	private Texture2D m_particleTexture;
	private Texture1D m_colorTexture;
	private Texture1D m_sizeTexture;
	private float m_sizeFactor;

	public Texture2D getParticleTexture() { return m_particleTexture; }
	public Texture1D getColorTexture() { return m_colorTexture; }
	public Texture1D getSizeTexture() { return m_sizeTexture; }
	public float getSizeFactor() { return m_sizeFactor; }
	public void setParticleTexture(Texture2D texture) { m_particleTexture = texture; }
	public void setColorTexture(Texture1D color) { m_colorTexture = color; }
	public void setSizeTexture(Texture1D sizeTex) { m_sizeTexture = sizeTex; }
	public void setSizeFactor(float size) { m_sizeFactor = size; } 

	public PointSpriteParticleTechnique(GLMatrix4f model, GLMatrix4f view, GLMatrix4f projection) {
		m_model = model;
		m_view = view;
		m_projection = projection;
	}
	
	@Override
	public void init() throws Exception {
		m_particleGeo = new Geometry();
		m_particleGeo.setMode(Mode.POINTS);
		//Don't set the vertex count, that will be done in render();
		m_particleGeo.addArray(new AttribArray(m_buffer, InputUsage.VERTEX_POSITION_3D, BYTES_PER_PARTICLE, 0));
		m_particleGeo.addArray(new AttribArray(m_buffer, InputUsage.PARTICLE_LIFE_REMAINING_PERCENTAGE, BYTES_PER_PARTICLE, 3 * 4));
		//m_particleGeo.addArray(new AttribArray(m_buffer, InputUsage.POINT_SIZE, BYTES_PER_PARTICLE, 4 * 4));
	
		m_program = ProgramXMLLoader.s_load(PARTICLE_PROGRAM, new ClasspathResourceLocator()).get(0);
		m_program.bind();
		m_program.getInputs(Uniform.class, InputUsage.POINT_TEXTURE_SAMPLER)
			.setValue(POINT_TEXTURE_UNIT);
		m_program.getInputs(Uniform.class, InputUsage.POINT_COLOR_TEXTURE_SAMPLER)
			.setValue(POINT_COLOR_TEXTURE_UNIT);
		m_program.getInputs(Uniform.class, InputUsage.POINT_SIZE_TEXTURE_SAMPLER)
			.setValue(POINT_SIZE_TEXTURE_UNIT);
		m_program.unbind();
		m_program.validate();
	}
	
	@Override
	public void render(ParticleSystem system) {
		m_program.bind();
		
		m_projection.load();
		m_model.load();
		m_view.load();

		//Set point size factor
		Program.s_getCurrent()
			.getInputs(Uniform.class, InputUsage.POINT_SIZE_FACTOR)
			.setValue(m_sizeFactor);
	
		m_particleTexture.bind(POINT_TEXTURE_UNIT);
		m_colorTexture.bind(POINT_COLOR_TEXTURE_UNIT);
		m_sizeTexture.bind(POINT_SIZE_TEXTURE_UNIT);

		GL11.glEnable(GL20.GL_VERTEX_PROGRAM_POINT_SIZE);
		GL11.glEnable(GL20.GL_POINT_SPRITE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
	    GL11.glTexEnvi(GL20.GL_POINT_SPRITE, GL20.GL_COORD_REPLACE, GL11.GL_TRUE);
		
	
		m_particleGeo.setVertexCount(system.getParticles().size());
		m_buffer.bind();
		m_buffer.bufferData(generateBuffer(system));
		m_particleGeo.render();//*/
		
	    /*GL11.glBegin(GL11.GL_POINTS);
		//System.out.println("R Start");
		for (Particle p : system.getParticles()) {
			float lifeRemaining = p.getLifetimeLeft() / (float) p.getStartingLifetime();
			//System.out.println("Rendering: " + p + " " + p.getPosition() + " " + lifeRemaining);
			Program.s_getCurrent().getInputs(Attribute.class, InputUsage.PARTICLE_LIFE_REMAINING_PERCENTAGE).setValue(lifeRemaining);
			GL11.glVertex3f(p.getPosition().x, p.getPosition().y, p.getPosition().z);
		}
		//System.out.println("R End");
		GL11.glEnd();//*/
		
		m_particleTexture.unbind();
		m_colorTexture.unbind();
		m_sizeTexture.unbind();
		
		m_program.unbind();
	}
	public FloatBuffer generateBuffer(ParticleSystem system) {
		ArrayList<Particle> particles = system.getParticles();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(particles.size() * FLOATS_PER_PARTICLE);
		for (Particle p : particles) {
			addParticle(p, system, buffer);
		}
		buffer.flip();
		return buffer;
	}
	public void addParticle(Particle p, ParticleSystem system, FloatBuffer buffer) {
		Vector3f position = p.getPosition();
		float lifeRemaining = p.getLifetimeLeft() / (float) p.getStartingLifetime();
		
		buffer.put(position.x).put(position.y).put(position.z);
		buffer.put(lifeRemaining);
		//buffer.put(m_sizeFactor);
	}
}
