package glextra.particle;

import gltools.vector.Vector3f;

import java.util.Random;

public class ParticleEmitter {
	private Random m_randomGenerator = new Random();
	private ParticleSystem m_system;
	
	//Particle spawn data
	private Vector3f m_location;
	private Vector3f m_velocity;
	private long m_particleLifetime = 1000;
	private Vector3f m_locationRandomFactor = new Vector3f(0, 0, 0);
	private Vector3f m_velocityRandomFactor = new Vector3f(1f, 1f, 1f);
	//Particles per second
	private int m_spawningRate;
	
	//Contains the left over particle that wasn't
	//generate the last time update() was called
	private float m_particleQueue;
	
	public ParticleEmitter(ParticleSystem system) {
		m_system = system;
	}
	
	public ParticleSystem getSystem() { return m_system; }
	public Vector3f getLocation() { return m_location; }
	public Vector3f getVelocity() { return m_velocity; }
	public int getSpawningRate() {	return m_spawningRate; }
	public long getLifetime() { return m_particleLifetime; }
	public Vector3f getVelocityRandomFactor() { return m_velocityRandomFactor; }
	public Vector3f getLocationRandomFactor() { return m_locationRandomFactor; }
	
	public void setSystem(ParticleSystem system) { m_system = system; }

	public void setLocation(Vector3f location) { m_location = location; }
	public void setVelocity(Vector3f velocity) { m_velocity = velocity; }
	public void setSpawningRate(int spawningRate) { m_spawningRate = spawningRate; }
	public void setLifetime(long lifetime) { m_particleLifetime = lifetime; } 
	public void setVelocityRandomFactor(Vector3f randomFactor) { m_velocityRandomFactor = randomFactor; }
	public void setLocationRandomFactor(Vector3f randomFactor) { m_locationRandomFactor = randomFactor; }

	public Particle generateNewParticle() {
		Vector3f particleVelocity = new Vector3f(m_velocity);
		
		float randomX = m_randomGenerator.nextFloat() - 0.5f;
		float randomY = m_randomGenerator.nextFloat() - 0.5f;
		float randomZ = m_randomGenerator.nextFloat() - 0.5f;
		
		Vector3f randomVelocity = new Vector3f(randomX, randomY, randomZ).scale(m_velocityRandomFactor);
		particleVelocity.add(randomVelocity);
		
		Vector3f particleLocation = new Vector3f(m_location);
		Vector3f randomLocation = new Vector3f(m_randomGenerator.nextFloat() - 0.5f,
											   m_randomGenerator.nextFloat() - 0.5f,
											   m_randomGenerator.nextFloat() - 0.5f);
		randomLocation.scale(m_locationRandomFactor);
		particleLocation.add(randomLocation);
		Particle particle = new Particle(particleLocation, particleVelocity,
											m_particleLifetime);
		return particle;
	}
	public void update(long deltaMillis) {
		m_particleQueue += ((float) (deltaMillis * 0.001)) * m_spawningRate;
		int newParticles = (int) m_particleQueue;
		for (int i = 0; i < newParticles; i++) {
			m_system.addParticle(generateNewParticle());
		}
		m_particleQueue -= newParticles;
	}
}
