package glextra.renderer;

import glextra.renderer.GBuffer.GBufferMode;
import gltools.ResourceLocator.ClasspathResourceLocator;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Shader.ShaderCompileException;
import gltools.shader.Uniform;
import gltools.texture.Color;
import gltools.vector.Vector3f;

import java.io.IOException;

public interface Light {
	/**
	 * When bind is called, a GBuffer is supplied.
	 * The light must bind its own program,
	 * call buffer.bind(GBufferMode.READ),
	 * and call buffer.setReadSamplers();
	 */
	public void bind(GBuffer buffer);
	public void unbind(GBuffer buffer); 
	
	public static class NoLight implements Light {
		private static final String PROG_LOCATION = "Programs/Lights/no_light.prog";
		private static Program s_program = null;
		
		public void bind(GBuffer buffer) {
			s_program.bind();
			buffer.bind(GBufferMode.READ);
			buffer.setReadSamplers();
		}
		public void unbind(GBuffer buffer) {
			buffer.unbind(GBufferMode.READ);
			s_program.unbind();
		}
		/**
		 * Will compile the program necessary for this light
		 */
		public static void init() {
			try {
				s_program = ProgramXMLLoader.s_load(PROG_LOCATION, new ClasspathResourceLocator()).get(0);
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
		private static final String PROG_LOCATION = "Programs/Lights/point_light.prog";
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
		
		public void bind(GBuffer buffer) {
			s_program.bind();
			//Set uniforms to light values
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_POSITION", DataType.VEC3, Uniform.class)).setValue(m_position);
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_DIFFUSE_COLOR", DataType.VEC4, Uniform.class)).setValue(m_diffuseColor.toVector4f());
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_AMBIENT_COLOR", DataType.VEC4, Uniform.class)).setValue(m_ambientColor.toVector4f());
			s_program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_ATTENUATION", DataType.VEC3, Uniform.class)).setValue(m_attenuation);
			
			//Will bind the buffers to their correct texture units
			buffer.bind(GBufferMode.READ);
			//Will set GLSL samplers to point to correct texture units
			buffer.setReadSamplers();
		}
		public void unbind(GBuffer buffer) {
			buffer.unbind(GBufferMode.READ);
			s_program.unbind();
		}
		/**
		 * Will compile the program necessary for this light
		 */
		public static void init() {
			try {
				s_program = ProgramXMLLoader.s_load(PROG_LOCATION, new ClasspathResourceLocator()).get(0);
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