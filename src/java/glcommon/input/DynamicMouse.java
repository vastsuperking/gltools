package glcommon.input;



public class DynamicMouse extends Mouse {
	public DynamicMouse() {
		//There are so few buttons, we can add them all here
		//rather than building them dynamically
		add(new MouseButton(0, MouseButton.LEFT_BUTTON_NAME));
		add(new MouseButton(1, MouseButton.RIGHT_BUTTON_NAME));
		add(new MouseButton(2, MouseButton.MIDDLE_BUTTON_NAME));
	}
	
	@Override
	public void poll() {}
	
	public void onMousePress(MouseButton mb) {
		updateButtonState(getButton(mb.getName()), true);
	}
	public void onMouseRelease(MouseButton mb) {
		updateButtonState(getButton(mb.getName()), false);		
	}
	public void onMouseMove(int x, int y) {
		if (x != getX() && y != getY()) {
			updateDelta(x - getX(), y - getY());
			updateMousePosition(x, y);
		}		
	}
	public void onMouseEnter() {
		mouseEntered();
	}
	public void onMouseExit() {
		mouseExited();
	}
	public void onMouseWheel(float dx, float dy) {
		mouseWheelMoved(dx, dy);
	}
}
