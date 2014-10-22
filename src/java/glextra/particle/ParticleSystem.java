package glextra.particle;

import glcommon.vector.Vector3f;
import gltools.transform.Camera3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ParticleSystem {
	public static DepthComparator s_comp = new DepthComparator();

	private ParticleTechnique m_technique;
	private ArrayList<Particle> m_particles = null;


	
	public ParticleSystem(ParticleTechnique technique, int size) throws Exception {
		m_technique = technique;
		m_particles = new ArrayList<Particle>(size);
		m_technique.init();
	}
	public ParticleSystem(ParticleTechnique technique) throws Exception {
		this(technique, 1024);
	}
	/** Returns a depth-sorted list of all the particles*/
	public ArrayList<Particle> getParticles() { return m_particles; }
	
	public void addParticle(Particle particle) {
		m_particles.add(particle);
	}
	
	public void render() {
		m_technique.render(this);
	}
	
	public void update(long deltaMillis, Camera3D cam) {
		Vector3f camPos = cam.getPosition();
		Vector3f viewDir = cam.getViewDirection();
		
		for (Iterator<Particle> it = m_particles.iterator(); it.hasNext(); ) {
			Particle p = it.next();
			//If the particle has expire, remove it
			if (p.update(deltaMillis)) {
				it.remove();
			} else p.updateDistanceCache(camPos, viewDir);
		}
		//Resort the depth list
		Collections.sort(m_particles, s_comp);
	}
	public static class DepthComparator implements Comparator<Particle>{
		@Override
		public int compare(Particle o1, Particle o2) {
			float o1d = o1.getCachedDistance();
			float o2d = o2.getCachedDistance();
			//return -1 on dist == because otherwise set will remove those particles
			return (o1.equals(o2) ? 0 : (o1d == o2d ? 0 : (o1d > o2d ? -1 : 1))); 
		}
	}
}
