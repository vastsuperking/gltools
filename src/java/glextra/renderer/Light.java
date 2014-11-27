package glextra.renderer;

import glcommon.Color;
import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glcommon.vector.Vector3f;
import glextra.GBuffer;
import glextra.GBuffer.GBufferMode;
import gltools.gl.GL;
import gltools.gl.GL3;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Shader.ShaderCompileException;
import gltools.shader.Uniform;

import java.io.IOException;

public interface Light {
	/**
	 * When bind is called, a GBuffer is supplied.
	 * The light must bind its own program,
	 * call buffer.bind(GBufferMode.READ),
	 */
	public void bind(GL3 gl, GBuffer buffer);
	public void unbind(GL3 gl, GBuffer buffer); 
	
	public static class NoLight implements Light {
		private static final String PROG_LOCATION = "Programs/Lights2D/no_light.prog";
		private static Program s_program = null;
		
		public void bind(GL3 gl, GBuffer buffer) {
			s_program.bind(gl);
			buffer.bind(gl, GBufferMode.READ);
		}
		public void unbind(GL3 gl, GBuffer buffer) {
			buffer.unbind(gl, GBufferMode.READ);
			s_program.unbind(gl);
		}
		/**
		 * Will compile the program necessary for this light
		 */
		public static void init(GL gl) {
			try {
				s_program = ProgramXMLLoader.s_load(PROG_LOCATION, new ClasspathResourceLocator(), gl).get(0);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ShaderCompileException e) {
				e.printStackTrace();
			} catch (ProgramLinkException e) {
				e.printStackTrace();
			}
		}
	}
	public static class PointLight implements Light {
		private static final String PROG_LOCATION = "Programs/Lights2D/point_light.prog";
		private static Program s_program = null;
		
		private Vector3f m_position;
		private Vector3f m_attenuation;
		private Color m_diffuseColor;
		private Color m_ambientColor;
		
		public PointLight(Vector3f pos, Vector3f atten, Color diffuse, Color ambient) {
			m_position = pos;
			m_attenuation = atten;
			m_diffuseColor = diffuse;
			m_ambientColor = ambient;
		}
	
		public void setPosition(Vector3f position) { m_position = position; }
		public void setAttenuation(Vector3f attenuation) { m_attenuation = attenuation; }
		public void setDiffuseColor(Color diffuseColor) { m_diffuseColor = diffuseColor; }
		public void setAmbientColor(Color ambientColor) { m_ambientColor = ambientColor; }

		public Vector3f getPosition() { return m_position; }		
		public Vector3f getAttenuation() { return m_attenuation; }
		public Color getDiffuseColor() { return m_diffuseColor; }
		public Color getAmbientColor() { return m_ambientColor; }

		public void bind(GL3 gl, GBuffer buffer) {
			s_program.bind(gl);
			//Set uniforms to light values
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_POSITION", DataType.VEC3, Uniform.class)).setValue(m_position, gl);
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_DIFFUSE_COLOR", DataType.VEC4, Uniform.class)).setValue(m_diffuseColor.toVector4f(), gl);
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_AMBIENT_COLOR", DataType.VEC4, Uniform.class)).setValue(m_ambientColor.toVector4f(), gl);
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_ATTENUATION", DataType.VEC3, Uniform.class)).setValue(m_attenuation, gl);
			
			//Will bind the buffers to their correct texture units
			//setReadSamplers() is called in the buffer
			buffer.bind(gl, GBufferMode.READ);
		}
		public void unbind(GL3 gl, GBuffer buffer) {
			buffer.unbind(gl, GBufferMode.READ);
			s_program.unbind(gl);
		}
		/**
		 * Will compile the program necessary for this light
		 */
		public static void init(GL gl) {
			try {
				s_program = ProgramXMLLoader.s_load(PROG_LOCATION, new ClasspathResourceLocator(), gl).get(0);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ShaderCompileException e) {
				e.printStackTrace();
			} catch (ProgramLinkException e) {
				e.printStackTrace();
			}
		}
	}
}
