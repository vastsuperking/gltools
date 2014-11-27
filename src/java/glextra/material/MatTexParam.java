package glextra.material;

import gltools.gl.GL;
import gltools.shader.DataType;
import gltools.shader.Input;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.texture.Texture;

public class MatTexParam extends MatParam {
	private Texture m_texture;
	private int m_unit;
	
	public MatTexParam(DataType type, String name, InputUsage usage, Texture texture, int unit) {
		super(type, name, usage, null);
		m_texture = texture;
		m_unit = unit;
	}
	@Override
	public Texture getValue() { return m_texture; }
	
	public int getUnit() { return m_unit; }
	public void setUnit(int unit) { m_unit = unit; }
	
	@Override
	public void setValue(Object o) {
		if (o instanceof Texture) m_texture = (Texture) o;
		else if (o == null) m_texture = null;
		else throw new RuntimeException("Not a texture!");
	}
	
	public void setTexture(Texture t) {
		m_texture = t;
	}
	@Override
	public void load(GL gl) {
		Input input = Program.s_getCurrent().getInputs(m_usage.getInputType(), m_usage);
		input.setValue(m_unit, gl.getGL2());
		if (m_texture != null) m_texture.bind(m_unit, gl.getGL1());
	}
	@Override
	public String toString() {
		return m_name + "(type=" + m_type + " usage=" 
				+ m_usage + ", texture= " + m_texture + 
				" unit=" + m_unit + ")";
	}
}
