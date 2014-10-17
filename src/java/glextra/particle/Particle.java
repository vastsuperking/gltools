package glextra.particle;

import glcommon.vector.Vector3f;

import java.nio.FloatBuffer;

public class Particle {
	private Vector3f m_velocity;
	private Vector3f m_position;
	
	private float m_distanceCache;
	
	//Time in millis
	private long m_startLifetime;
	private long m_lifetimeLeft;
	
	public Particle(Vector3f position, Vector3f velocity, long lifetime) {
		m_position = position;
		m_velocity = velocity;
		m_startLifetime = lifetime;
		m_lifetimeLeft = lifetime;
	}
	public Vector3f getVelocity() { return m_velocity; }
	public Vector3f getPosition() { return m_position; }
	public long getLifetimeLeft() { return m_lifetimeLeft; }
	public long getStartingLifetime() { return m_startLifetime; }
	public float getCachedDistance() { return m_distanceCache; }
	public void setVelocity(Vector3f velocity) { m_velocity = velocity; }
	public void setPosition(Vector3f position) { m_position = position; }
	public void setLifetimeLeft(long lifetimeLeft) { m_lifetimeLeft = lifetimeLeft; }
	
	public void addTo(FloatBuffer buffer) {

	}
	
	/**
	 * Update the particle
	 * @param deltaMillis the delta time in millis
	 * @return Whether or not the particle has expired
	 */
	public boolean update(long deltaMillis) {
		m_lifetimeLeft -= deltaMillis;
		//Scale the velocity by the time in seconds and add to the position
		float seconds = (float) (deltaMillis * 0.001);
		Vector3f delta = m_velocity.scale(seconds, null);
		//System.out.println("Adding: " + delta + " for " + seconds + " sec with velocity: " + m_velocity); 
		m_position.add(delta);
		return m_lifetimeLeft <= 0;
	}
	/**
	 * Updates the cache with the given arguments
	 */
	public void updateDistanceCache(Vector3f point, Vector3f viewDir) {
		m_distanceCache = distance(point, viewDir);
		//System.out.println("Particle: " + this + "(" + m_position + ") distanceCache: " + m_distanceCache);
	}
	/**
	 * Returns the distance to this particle from point along viewDirection
	 */
	public float distance(Vector3f point, Vector3f viewDirection) {
		Vector3f relative = m_position.sub(point, null);
		float dist = viewDirection.dot(relative);
		//System.out.println("Point: " + m_position + " CamPos" + point + " Relative: " + relative);
		//System.out.println("Point: " + m_position + " Distance: " + dist);
		return dist;
	}
}
