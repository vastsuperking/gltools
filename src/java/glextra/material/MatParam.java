package glextra.material;

import gltools.shader.DataType;
import gltools.shader.Input;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.utils.Loadable;

public class MatParam implements Loadable {
	protected DataType m_type = null;
	protected String m_name = null;
	protected InputUsage m_usage = null;
	protected Object m_value = null;
	
	public MatParam(DataType type, String name, InputUsage usage, Object value) {
		m_name = name;
		m_type = type;
		m_usage = usage;
		m_value = value;
	}
	protected MatParam() {}
	
	public DataType getDataType() { return m_type; }
	public InputUsage getUsage() { return m_usage; }
	public Object getValue() { return m_value; }
	public String getName() { return m_name; }
	
	public void setValue(Object value) { m_value = value; }
	public void setName(String name) { m_name = name; }
	
	public void load() {
		load(m_usage);
	}
	/**
	 * Like load(), but only sets inputs with the specified usage
	 */
	public void load(InputUsage usage) {
		Input input = Program.s_getCurrent().getInputs(usage.getInputType(), usage);
		input.setValue(m_type, m_value);		
	}
	
	@Override
	public String toString() {
		return m_name + "(" + m_type + ", " + m_usage + ", " + m_value + ")";
	}
}
