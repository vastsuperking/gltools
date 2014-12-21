package glcommon.util;

public class Timer {
	//The second to last mark
	private long m_preMarkTime = -1;
	//The last mark
	private long m_markTime = -1;
	public Timer() {
		mark();
	}
	/**
	 * Takes the current time and marks it,
	 * the difference from the last mark can be retrieved via getDeltaMillis()
	 */
	public void mark() {
		m_preMarkTime = m_markTime;
		m_markTime = System.currentTimeMillis();
	}
	
	public long getDeltaMillis() {
		if (m_preMarkTime == -1) return 0;
		return m_markTime - m_preMarkTime;
	}
	public float getDeltaSeconds() {
		if (m_preMarkTime == -1) return 0;
		return (float) ((m_markTime - m_preMarkTime) * 0.001);
	}
}
