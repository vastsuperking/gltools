package glextra.particle;


public interface ParticleTechnique {
	public void init() throws Exception;
	public void render(ParticleSystem system);
}
