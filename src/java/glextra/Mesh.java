package glextra;

import glextra.material.GlobalParamBindingSet;
import glextra.material.Material;
import gltools.buffer.Geometry;
import gltools.shader.InputUsage;
import gltools.util.Loadable;

import java.util.HashMap;
import java.util.Map;

public class Mesh {
	private HashMap<Geometry, Material> m_geoMap = new HashMap<Geometry, Material>();
	
	public void addGeometry(Geometry g, Material m) { m_geoMap.put(g, m); }
	public void removeGeometry(Geometry g) { m_geoMap.remove(g); }
	public HashMap<Geometry, Material> getGeometries() { return m_geoMap; }
	public void render(GlobalParamBindingSet globals) {
		for (Map.Entry<Geometry, Material> entry : m_geoMap.entrySet()) {
			Geometry geo = entry.getKey();
			Material mat = entry.getValue();
			
			mat.bind(globals);
			geo.render();
			mat.unbind();
		}
	}
	public void render(HashMap<InputUsage, Loadable> globals) {
		for (Map.Entry<Geometry, Material> entry : m_geoMap.entrySet()) {
			Geometry geo = entry.getKey();
			Material mat = entry.getValue();
			
			mat.bind(globals);
			geo.render();
			mat.unbind();
		}
	}
}
