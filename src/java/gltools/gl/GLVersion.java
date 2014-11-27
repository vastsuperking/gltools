package gltools.gl;

public class GLVersion {
	private int m_major;
	private int m_minor;
	
	public GLVersion(int major, int minor) {
		m_major = major;
		m_minor = minor;
	}
	
	public int getMajor() { return m_major; }
	public int getMinor() { return m_minor; }
}
