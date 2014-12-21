package glcommon.input;

import glcommon.util.ResourceLocator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Keyboard {
	protected HashSet<Key> m_shiftKeys = new HashSet<Key>();
	protected HashSet<Key> m_modKeys = new HashSet<Key>();

	protected HashMap<Integer, Key> m_keyIds = new HashMap<Integer, Key>();
	protected HashMap<String, Key> m_keyNames = new HashMap<String, Key>();
	protected HashMap<Character, Key> m_keyChars = new HashMap<Character, Key>();
	
	protected HashMap<Key, Boolean> m_state = new HashMap<Key, Boolean>();
	
	protected ArrayList<KeyListener> m_listeners = new ArrayList<KeyListener>();
	
	public void addModKey(Key key) { m_modKeys.add(key); }
	public void addShiftKey(Key key) { m_shiftKeys.add(key); }
	
	public void addKey(Key key) {
		m_keyIds.put(key.getID(), key);
		m_keyNames.put(key.getName(), key);
		if (key.getChar() != '\0')
			m_keyChars.put(Character.toUpperCase(key.getChar()), key);
		m_state.put(key, false);
	}
	
	public void addAll(Collection<Key> keys) {
		for (Key k : keys) {
			addKey(k);
		}
	}
	
	public void addListener(KeyListener l) {
		m_listeners.add(l);
	}
	public void readXMLKeyConfig(String resource, ResourceLocator locator) throws Exception {
		readXMLKeyConfig(locator.getResource(resource));
	}
	public void readXMLKeyConfig(InputStream stream) throws Exception {
		XMLKeyConfigLoader.s_parseConfig(stream, this);
	}
	
	public HashSet<Key> getModKeys() { return m_modKeys; }
	public HashSet<Key> getShiftKeys() { return m_shiftKeys; }
	
	public Key getKey(String name) {
		if (!m_keyNames.containsKey(name))
			throw new RuntimeException("No key for name: " + name);
		return m_keyNames.get(name);
	}
	public Key getKey(int id) {
		if (!m_keyIds.containsKey(id))
			throw new RuntimeException("No key for id: " + id);
		return m_keyIds.get(id);
	}
	public Key getKey(char c) {
		if (!m_keyChars.containsKey(Character.toUpperCase(c)))
			throw new RuntimeException("No key for char: " + c);
		return m_keyChars.get(c);
	}
	
	public boolean isKeyPressed(Key k) {
		if (k == null) return false;
		return m_state.get(k);
	}
	public boolean isModKey(Key k) {
		if (k == null) return false;
		return m_modKeys.contains(k);
	}
	public boolean isShiftKey(Key k) {
		if (k == null) return false;
		return m_shiftKeys.contains(k);
	}
	
	protected void updateState(Key k, boolean state) {
		if (k == null) return;
		m_state.put(k, state);
		if (state)
			for (KeyListener l : m_listeners) {
				l.keyPressed(this, k);
			}
		else
			for (KeyListener l : m_listeners) {
				l.keyReleased(this, k);
			}
	}
	
	//-----Abstract functions------
	
	public abstract void poll();
	
	//-----Triggers------
	
	public static class KeyPressedTrigger extends InputTrigger implements KeyListener {
		private Key m_key;
		public KeyPressedTrigger(Keyboard keyboard, Key key) {
			keyboard.addListener(this);
			m_key = key;
		}
		
		@Override
		public void keyPressed(Keyboard k, Key key) {
			if (key.equals(m_key)) {
				trigger(1.0f);
			}
		}
		@Override
		public void keyReleased(Keyboard k, Key key) {	}
	}
	public static class KeyReleasedTrigger extends InputTrigger implements KeyListener {
		private Key m_key;
		
		public KeyReleasedTrigger(Keyboard keyboard, Key key) {
			keyboard.addListener(this);
			m_key = key;
		}
		
		@Override
		public void keyPressed(Keyboard k, Key key) {}
		@Override
		public void keyReleased(Keyboard k, Key key) {	
			if (key.equals(m_key)) trigger(1.0f);
		}
	}
}
