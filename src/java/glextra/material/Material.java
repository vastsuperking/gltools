package glextra.material;

import gltools.shader.DataType;
import gltools.texture.Color;
import gltools.texture.Texture1D;
import gltools.texture.Texture2D;
import gltools.vector.Vector2f;
import gltools.vector.Vector3f;
import gltools.vector.Vector4f;

import java.util.HashMap;

public class Material {
	private String m_name = "";
	private HashMap<String, MatParam> m_parameters = new HashMap<String, MatParam>();
	private Technique m_currentTechnique = null;
	private Technique m_defaultTechnique = null;
	private HashMap<String, Technique> m_techniques = new HashMap<String, Technique>();
	
	public Material() {}
	public Material(String name) {
		m_name = name;
	}
	
	public void setName(String name) { m_name = name; }	
	public void setDefaultTechnique(Technique technique) { m_defaultTechnique = technique; }
	public String getName() { return m_name; }
	public Technique getDefaultTechnique() { return m_defaultTechnique; }
	public HashMap<String, MatParam> getMatParams() { return m_parameters; }
	public MatParam getParam(String param) { return m_parameters.get(param); }
	
	public void addTechnique(Technique technique) {
		m_techniques.put(technique.getName(), technique);
	}
	public void removeTechnique(Technique technique) {
		m_techniques.remove(technique.getName());
	}
	public void addParameter(MatParam param) {
		m_parameters.put(param.getName(), param);
	}
	public void setColor(String param, Color color) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.VEC4) throw new RuntimeException("Param is not a vec4!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(color.toVector4f());
		notifyParamChanged(param, old, color);
	}
	public void setVector4f(String param, Vector4f vec) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.VEC4) throw new RuntimeException("Param is not a vec4!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(vec);
		notifyParamChanged(param, old, vec);
	}
	public void setVector3f(String param, Vector3f vec) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.VEC3) throw new RuntimeException("Param is not a vec3!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(vec);
		notifyParamChanged(param, old, vec);
	}
	
	public void setVector2f(String param, Vector2f vec) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.VEC2) throw new RuntimeException("Param is not a vec2!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(vec);
		notifyParamChanged(param, old, vec);
	}
	
	public void setFloat(String param, float f) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.FLOAT) throw new RuntimeException("Param is not a float!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(f);
		notifyParamChanged(param, old, f);
	}
	public void setBoolean(String param, boolean bool) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.BOOL) throw new RuntimeException("Param is not a float!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(bool);
		notifyParamChanged(param, old, bool);
	}
	public void setTexture2D(String param, Texture2D texture) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.SAMPLER2D) throw new RuntimeException("Param is not a texture!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(texture);
		notifyParamChanged(param, old, texture);
	}
	public void setTexture1D(String param, Texture1D texture) {
		if (!m_parameters.containsKey(param)) throw new RuntimeException("Could not find param: " + param);
		if (m_parameters.get(param).getDataType() != DataType.SAMPLER1D) throw new RuntimeException("Param is not a texture!");
		
		Object old = m_parameters.get(param).getValue();
		m_parameters.get(param).setValue(texture);
		notifyParamChanged(param, old, texture);
	}
	
	private void notifyParamChanged(String param, Object oldValue, Object newValue) {		
		//Notify that an update is necessary
		m_defaultTechnique.parameterChanged(param, oldValue, newValue);
		for (Technique t : m_techniques.values()) {
			t.parameterChanged(param, oldValue, newValue);
		}
	}
	
	public void selectTechnique() {
		m_currentTechnique = m_defaultTechnique;
	}

	/**
	 * Will ready the current technique or select one
	 * if the current technique has not been defined
	 */
	public void ready() {
		if (m_currentTechnique == null) selectTechnique();
		if (m_currentTechnique.needsRecompile()) m_currentTechnique.recompile(m_parameters);
	}

	/**
	 * Will load the mat params again
	 */
	public void load() {
		if (m_currentTechnique != null) m_currentTechnique.load(m_parameters);
		else System.err.println("Warning, currentTechnique null!, params not updated");
	}
	
	public void bind() {
		if (m_currentTechnique == null) throw new RuntimeException("Must set currentTechnique");
		m_currentTechnique.bind(m_parameters);
	}
	public void unbind() {
		if (m_currentTechnique == null) throw new RuntimeException("Must set currentTechnique");
		m_currentTechnique.unbind(m_parameters);
	}
}
