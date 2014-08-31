package gltools.transform;

import gltools.vector.Vector3f;

public class Ray {
	//Start point
	private Vector3f m_start;
	//A normilized direction vector
	private Vector3f m_direction;
	
	public Ray() {}
	public Ray(Vector3f start, Vector3f dir) {
		m_start = start;
		m_direction = dir;
	}
	
	public Vector3f getStart() {
		return m_start;
	}
	public Vector3f getDirection() {
		return m_direction;
	}
	public void setStart(Vector3f start) {
		m_start = start;
	}
	public void setDirection(Vector3f direction) {
		m_direction = direction;
	}
}
