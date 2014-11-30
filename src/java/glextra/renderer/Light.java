package glextra.renderer;

import glcommon.Color;
import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glcommon.vector.Vector3f;
import glextra.GBuffer;
import glextra.GBuffer.GBufferMode;
import gltools.gl.GL;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Uniform;

import java.util.HashMap;

public interface Light {
	
	public LightType getType();
	
	/**
	 * When bind is called, a GBuffer is supplied.
	 * The light must bind its own program from the provider,
	 * call buffer.bind(GBufferMode.READ),
	 */
	public void bind(GL gl, GBuffer buffer, LightProgramProvider provider);
	public void unbind(GL gl, GBuffer buffer, LightProgramProvider provider); 

	public Light clone();
	
	/**
	 * Responsible for managing all the light's programs
	 * Each renderer has one (programs are context-specific) 
	 */
	public static class LightProgramProvider {
		private HashMap<LightType, Program> m_types = new HashMap<LightType, Program>();
		
		public Program get(GL gl, LightType lightType) {
			if (!m_types.containsKey(lightType)) {
				//Program not available, compile
				Program p = lightType.createProgram(gl);
				m_types.put(lightType, p);
			}
			return m_types.get(lightType);
		}
	}
	public interface LightType {
		public Program createProgram(GL gl);
	}
	
	public static class NoLight implements Light {
		private static final String PROG_LOCATION = "Programs/Lights2D/no_light.prog";
		private static LightType TYPE = new LightType() {
			@Override
			public Program createProgram(GL gl) {
				try {
					return ProgramXMLLoader.s_load(gl, PROG_LOCATION, 
							new ClasspathResourceLocator()).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
		public LightType getType() { return TYPE; }
		
		public void bind(GL gl, GBuffer buffer, LightProgramProvider provider) {
			provider.get(gl.getContext(), TYPE).bind(gl);
			buffer.bind(gl, GBufferMode.READ);
		}
		public void unbind(GL gl, GBuffer buffer, LightProgramProvider provider) {
			buffer.unbind(gl, GBufferMode.READ);
			provider.get(gl, TYPE).unbind(gl);
		}
		
		@Override
		public NoLight clone() {
			return new NoLight();
		}
	}
	public static class PointLight implements Light {
		private static final String PROG_LOCATION = "Programs/Lights2D/point_light.prog";
		private static LightType TYPE = new LightType() {
			@Override
			public Program createProgram(GL gl) {
				try {
					return ProgramXMLLoader.s_load(gl, PROG_LOCATION, 
							new ClasspathResourceLocator()).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
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
		
		public LightType getType() { return TYPE; }

		public void bind(GL gl, GBuffer buffer, LightProgramProvider provider) {
			Program program = provider.get(gl, TYPE);
			program.bind(gl);
			//Set uniforms to light values
			program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_POSITION", DataType.VEC3, Uniform.class)).setValue(gl, m_position);
			program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_DIFFUSE_COLOR", DataType.VEC4, Uniform.class)).setValue(gl, m_diffuseColor.toVector4f());
			program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_AMBIENT_COLOR", DataType.VEC4, Uniform.class)).setValue(gl, m_ambientColor.toVector4f());
			program.getInputs(Uniform.class, 
					new InputUsage("LIGHT_ATTENUATION", DataType.VEC3, Uniform.class)).setValue(gl, m_attenuation);
			
			//Will bind the buffers to their correct texture units
			//setReadSamplers() is called in the buffer
			buffer.bind(gl, GBufferMode.READ);
		}
		public void unbind(GL gl, GBuffer buffer, LightProgramProvider provider) {
			Program program = provider.get(gl, TYPE);
			
			buffer.unbind(gl, GBufferMode.READ);
			program.unbind(gl);
		}
		
		public PointLight clone() {
			return new PointLight(m_position, m_attenuation, m_diffuseColor, m_ambientColor);
		}
	}
}
