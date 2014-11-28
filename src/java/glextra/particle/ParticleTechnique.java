package glextra.particle;

import gltools.gl.GL;


public interface ParticleTechnique {
	public void init(GL gl) throws Exception;
	public void render(GL gl, ParticleSystem system);
}
