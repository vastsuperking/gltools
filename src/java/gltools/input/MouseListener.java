package gltools.input;

import gltools.input.Mouse.MouseButton;
import gltools.vector.Vector2f;

public interface MouseListener {
	public void mouseMoved(Mouse m, int x, int y, Vector2f delta);
	public void mouseWheelMoved(Mouse m, int dm);
	//Delta might not be the same as moved if the mouse is at the edge of the screen/grabbed!!!
	public void mouseDelta(Mouse m, int dx, int dy);
	public void mouseButtonPressed(Mouse m, MouseButton button);
	public void mouseButtonReleased(Mouse m, MouseButton button);
}
