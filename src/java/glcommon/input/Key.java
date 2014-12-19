package glcommon.input;

/**
 * An immutable class with all the data of a key
 * This class does not actually store if the key is down
 */
public class Key {
	//TODO: Change to enum
	private boolean m_isShiftKey;
	private boolean m_isCapsKey;
	private boolean m_isModKey;
	
	private char m_char;
	private String m_name;
	private int m_id;
	
	public Key(char c, String name, int id, boolean isShift, boolean isMod) {
		m_char = c;
		m_name = name;
		m_id = id;
		m_isShiftKey = isShift;
		m_isModKey = isMod;
	}
	public Key(String name, int id, boolean isShift, boolean isMod) {
		this('\0', name, id, isShift, isMod);
	}
	public Key(char c, int id) {
		this(c, Character.toString(c), id, false, false);
	}
	
	public boolean isShift() { return m_isShiftKey; }
	public boolean isMod() { return m_isModKey; }
	
	/**
	 * @return the char
	 */
	public char getChar() {
		return m_char;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return m_name;
	}
	/**
	 * @return the id
	 */
	public int getID() {
		return m_id;
	}
	
	@Override
	public String toString() {
		if (getChar() == '\u0000') return getID() + ":" + getName();
		return getID() + ":" + getName() + ":" + getChar(); 
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Key) {
			Key key = (Key) o;
			return (key.getChar() == m_char) && (key.getName().equals(m_name)) && (m_id == key.getID());
		} else return false;
	}
	@Override
	public int hashCode() {
		return m_id;
	}
}
