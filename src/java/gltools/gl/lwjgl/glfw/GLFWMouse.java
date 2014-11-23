package gltools.gl.lwjgl.glfw;

import gltools.input.Mouse;

import org.lwjgl.system.glfw.GLFW;

public class GLFWMouse extends Mouse {
	protected GLFWMouse() {
		add(new MouseButton(0, MouseButton.LEFT_BUTTON_NAME));
		add(new MouseButton(1, MouseButton.RIGHT_BUTTON_NAME));
		add(new MouseButton(2, MouseButton.MIDDLE_BUTTON_NAME));
	}

	@Override
	public void poll() {
		//Polling is done in the display
	}

	//Functions to be called from window
	
	protected void mouseButton(GLFWWindow window, int button, int action, int mods) {
		boolean state;
		switch ( action ) {
			case GLFW.GLFW_RELEASE: state = false; break;
			case GLFW.GLFW_PRESS: state = true;	break;
			default:
				throw new IllegalArgumentException(
				   String.format("Unsupported mouse button action: 0x%X", action));
		}
		updateButtonState(getButton(button), state);
	}
	protected void cursorPos(GLFWWindow window, float x, float y) {
		//Invert y (GLFW is from top left)
		y = window.getHeight() - y;
		
		if (x != getX() && y != getY()) {
			updateDelta((int) x - getX(), (int) y - getY());
			updateMousePosition((int) x, (int) y);
		}
	}
	protected void cursorEnter(GLFWWindow window, boolean entered) {
		if (entered) mouseEntered();
		else mouseExited();
	}
	
	protected void scroll(GLFWWindow window, float x, float y) {
		mouseScrolled(x, y);
	}
}
