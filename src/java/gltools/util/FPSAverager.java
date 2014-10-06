package gltools.util;

public class FPSAverager {
	private float m_fps = -1f;
	
	public void average(float fps) {
		if (m_fps == -1) m_fps = fps;
		else m_fps = (float) ((fps * 0.6) + (m_fps * 0.4));
	}
	public float getFPS() { return m_fps; }
}
