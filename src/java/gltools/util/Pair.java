package gltools.util;

import java.util.Objects;

/**
 * Generic pair class.
 *
 */

public class Pair<K, V> {
	private K m_key;
	private V m_value;

	/**
	 * Constructs a new <code>Pair</code> with a given key/value
	 * 
	 * @param key  the key
	 * @param value the value
	 */
	public Pair(K key, V value) {
		setKey(key);
		setValue(value);
	}

	public K getKey() 				{ return m_key; }
	public V getValue() 			{ return m_value; }

	public void setKey(K key) 		{ m_key = key; }
	public void setValue(V value) 	{ m_value = value; }
	
	@Override
	public int hashCode() {
		return Objects.hash(m_key, m_value);
	}
	@Override
	public String toString() {
		return "(" + m_key.toString() + ", " + m_value.toString() + ")";
	}
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			return p.getKey().equals(getKey()) && p.getValue().equals(getValue());
 		}
		return false;
	}
}