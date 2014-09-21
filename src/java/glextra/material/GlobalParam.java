package glextra.material;

import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.utils.Loadable;

public class GlobalParam implements Loadable {
	private DataType m_type;
	private String m_name;
	private Loadable m_loadable = null;
	
	public GlobalParam(String name, DataType type) {
		m_type = type;
		m_name = name;
	}
	public GlobalParam(String name, DataType type, Loadable loader) {
		m_name = name;
		m_type = type;
		m_loadable = loader;
	}
	public GlobalParam(GlobalParam param) {
		m_type = param.m_type;
		m_name = param.m_name;
		m_loadable = param.m_loadable;
	}
	
	public Loadable getLoader() { return m_loadable; }
	public String getName() { return m_name; }
	public DataType getDataType() { return m_type; }
	
	@Override
	public void load(InputUsage usage) {
		m_loadable.load(usage);
	}
}
