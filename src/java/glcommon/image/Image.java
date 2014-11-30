package glcommon.image;

import glcommon.image.ImageInstance.Key;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Image {
	private static final Logger logger = LoggerFactory.getLogger(Image2D.class);
	
	//Image instances managing hashmap
	public HashMap<Key<? extends ImageInstance>, ImageInstance> m_instances = 
			new HashMap<Key<? extends ImageInstance>, ImageInstance>();
	
	// Functions for Instance Managing
	public <T extends ImageInstance> void addInstance(ImageInstance.Key<T> key, T i) {
		m_instances.put(key, i);
	}
	public void removeInstance(ImageInstance.Key< ? extends ImageInstance> key) {
		m_instances.remove(key);
	}
	
	public  boolean hasInstance(ImageInstance.Key<? extends ImageInstance> key) {
		return m_instances.containsKey(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ImageInstance> T getInstance(ImageInstance.Key<T> key) {
		return (T) m_instances.get(key);
	}
	
	public void destroyInstances() {
		for (ImageInstance o : m_instances.values()) {
			o.delete();
		}
		m_instances.clear();
	}
	
	@Override
	public void finalize() {
		if (m_instances.size() > 0) {
			logger.warn("Auto-cleaning up texture, delete() not called!");
			destroyInstances();
		}
	}
}
