package glextra.material;

import gltools.shader.InputUsage;
import gltools.util.Loadable;

import java.util.HashMap;
import java.util.Map.Entry;

public class GlobalParamBindingSet {
	public HashMap<InputUsage, Loadable> m_map = new HashMap<InputUsage, Loadable>();
	
	public HashMap<InputUsage, Loadable> getParamMap() { return m_map; }

	public GlobalParamBindingSet() {}
	
	public void addParam(InputUsage usage, Loadable loader) {
		m_map.put(usage, loader);
	}
	public void removeParam(InputUsage usage) {
		m_map.remove(usage);
	}
	
	public void loadAll() {
		for (Entry<InputUsage,Loadable> p : m_map.entrySet()) {
			p.getValue().load(p.getKey());
		}
	}
}
