package glextra.material;

import gltools.shader.InputUsage;
import gltools.util.Loadable;

public class GlobalParam implements Loadable {
	private InputUsage m_usage = null;
	private Loadable m_loadable = null;
	
	public GlobalParam(InputUsage usage) {
		m_usage = usage;		
	}
	public GlobalParam(InputUsage usage, Loadable loader) {
		m_usage = usage;
		m_loadable = loader;
	}
	public GlobalParam(GlobalParam param) {
		m_usage = param.m_usage;
		m_loadable = param.m_loadable;
	}
	
	public Loadable getLoader() { return m_loadable; }
	public InputUsage getUsage() { return m_usage; }
	
	
	public void load() { 
		m_loadable.load(m_usage);
	}
	
	@Override
	public void load(InputUsage usage) {
		m_loadable.load(usage);
	}
}
