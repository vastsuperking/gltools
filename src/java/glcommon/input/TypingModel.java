package glcommon.input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TypingModel {	
	private HashSet<TypingListener> m_listeners = new HashSet<TypingListener>();
	
	//If a key is released, the combo goes inactive until
	//a key is added again
	private boolean m_comboActive;
	private HashSet<Key> m_pressedKeys = new HashSet<Key>();
	private ArrayList<Key> m_combinationKeys = new ArrayList<Key>();
	
	
	public TypingModel() {}
	
	public void addListener(TypingListener t) { m_listeners.add(t); }
	
	public void press(Key key) {
		if ((key.getChar() == '\0' ||
				key.getChar() == '\b' ||
				key.getChar() == '\n') &&
				!key.isShift() && !key.isMod()) {
			if (m_combinationKeys.size() != 0) {
				processCombo(m_combinationKeys);
			}
			ArrayList<Key> tmp = new ArrayList<Key>();
			tmp.add(key);
			processCombo(tmp);
			return;
		}

		m_pressedKeys.add(key);
		m_combinationKeys.add(key);
		
		
		m_comboActive = true;
	}

	public void release(Key key) {
		m_pressedKeys.remove(key);
		
		if (m_comboActive) processCombo(m_combinationKeys);
		
		m_combinationKeys.remove(key);
		
		if (m_pressedKeys.size() == 0) {
			clearCombo();
		}
		
		m_comboActive = false;
	}
	
	private void clearCombo() {
		m_combinationKeys.clear();
	}
	private void processCombo(ArrayList<Key> combo) {
		boolean caps = false;
		for (Key k : combo) {
			if (k.isShift()) caps = true;
			else if (k.isMod());
			else if (k.getChar() != '\u0000') {
				switch(k.getChar()) {
				case '\b': {					
					for (TypingListener l : m_listeners) {
						l.backspace();
					}
				} break;
				default: {
					char c = caps ? Character.toUpperCase(k.getChar()) : Character.toLowerCase(k.getChar());
					for (TypingListener l : m_listeners) {
						l.charTyped(c);
					}
				} break;
				}
			} else System.err.println("No char for: " + k);
		}
	}
	
	public interface TypingListener {
		public void charTyped(char c);
		public void comboTyped(List<Key> keys);
		public void specialKeyTyped(Key k);
		public void backspace();
		public void newline();
	}
}
