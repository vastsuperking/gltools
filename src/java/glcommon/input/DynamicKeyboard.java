package glcommon.input;

/**
 * A special kind of keyboard which builds
 * the keyboard's keyset dynamically
 * @author Daniel Pfrommer
 *
 */
public class DynamicKeyboard extends Keyboard {
	//Instead of throwing an error b/c the key doesn't exist,
	//return null, for which isPressed/isMod/isShift will return false
	@Override
	public Key getKey(String name) {
		return m_keyNames.get(name);
	}
	public Key getKey(int id) {
		return m_keyIds.get(id);
	}
	public Key getKey(char c) {
		return m_keyChars.get(Character.toUpperCase(c));
	}
	
	@Override
	public void poll() {}
	
	public void onKeyPressed(Key key) {
		if (!m_keyIds.containsKey(key.getID())) addKey(key);
		updateState(key, true);
	}
	public void onKeyReleased(Key key) {
		if (!m_keyIds.containsKey(key.getID())) addKey(key);
		updateState(key, false);
	}


}
