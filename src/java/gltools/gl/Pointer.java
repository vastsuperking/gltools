package gltools.gl;

public class Pointer {
	protected final long m_pointer;
	
	protected Pointer(final long pointer) {
		m_pointer = pointer;
	}
	
	public boolean isValid() { return m_pointer != 0; }
	public final long getPointer() { return m_pointer; }

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pointer)) return false;
		Pointer pointer = (Pointer) o;
		return pointer.getPointer() == getPointer();
	}
	
	public int hashCode() {
		return (int)(m_pointer ^ (m_pointer >>> 32));
	}
	public String toString() {
		return getClass().getSimpleName() + " pointer (0x" + Long.toHexString(m_pointer).toUpperCase() + ")";
	}
}
