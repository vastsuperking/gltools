package gltools.input;

import glcommon.vector.Vector2f;
import gltools.input.Mouse.MouseButton;

public interface MouseListener {
	public void mouseMoved(Mouse m, int x, int y, Vector2f delta);
	public void mouseScroll(Mouse m, float dx, float dy);
	public void mouseDelta(Mouse m, int dx, int dy);
	public void mouseButtonPressed(Mouse m, MouseButton button);
	public void mouseButtonReleased(Mouse m, MouseButton button);
	
	public void mouseEntered(Mouse m);
	public void mouseExited(Mouse m);
}
