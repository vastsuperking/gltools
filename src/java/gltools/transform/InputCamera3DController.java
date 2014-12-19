package gltools.transform;

import glcommon.input.Keyboard;
import glcommon.input.Mouse;
import glcommon.input.MouseListener;
import glcommon.input.Mouse.MouseButton;
import glcommon.vector.Vector2f;


/**
 * A camera controller that uses the keyboard as input
 * (Essentially make the camera a "Flycam")
 * TODO: Rewrite using InputManager
 */
public class InputCamera3DController implements Camera3DController {
	private Keyboard m_keyboard;
	private Mouse m_mouse;
	private int m_dx;
	private int m_dy;
	private float m_moveSpeed;
	private float m_mouseSensitivity;
	
	
	public InputCamera3DController(Keyboard keyboard, Mouse m, float speed, float mouseSensitivity) {
		m_keyboard = keyboard;
		m_mouse = m;
		m_moveSpeed = speed;
		m_mouseSensitivity = mouseSensitivity;
		m_mouse.addListener(new MouseListener() {
			@Override
			public void mouseMoved(Mouse m, int x, int y, Vector2f delta) {}
			@Override
			public void mouseScroll(Mouse m, float dx, float dy) {}

			@Override
			public void mouseDelta(Mouse m, int x, int y) {
				m_dx += x;
				m_dy += y;
			}

			@Override
			public void mouseButtonPressed(Mouse m, MouseButton button) {}
			@Override
			public void mouseButtonReleased(Mouse m, MouseButton button) {}
			@Override
			public void mouseEntered(Mouse m) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(Mouse m) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * @return the keyboard
	 */
	public Keyboard getKeyboard() {
		return m_keyboard;
	}

	/**
	 * @param keyboard the keyboard to use
	 */
	public void setKeyboard(Keyboard keyboard) {
		m_keyboard = keyboard;
	}
	private void up(Camera3D cam, float amount) {
		cam.getPosition().y += amount;
	}
	private void down(Camera3D cam, float amount) {
		cam.getPosition().y -= amount;
	}
	private void yaw(Camera3D cam, float amount) {
		cam.setYaw(cam.getYaw() + amount);
	}
	private void pitch(Camera3D cam, float amount) {
		cam.setPitch(cam.getPitch() + amount);
	}
	private void walkForward(Camera3D cam, float distance) {
		cam.getPosition().x += distance * (float)Math.sin(Math.toRadians(cam.getYaw()));
		cam.getPosition().z -= distance * (float)Math.cos(Math.toRadians(cam.getYaw()));
	}
	private void walkBackwards(Camera3D cam, float distance) {
		cam.getPosition().x -= distance * (float)Math.sin(Math.toRadians(cam.getYaw()));
		cam.getPosition().z += distance * (float)Math.cos(Math.toRadians(cam.getYaw()));
	}

	private void strafeLeft(Camera3D cam, float distance) {
		cam.getPosition().x += distance * (float)Math.sin(Math.toRadians(cam.getYaw() - 90));
		cam.getPosition().z -= distance * (float)Math.cos(Math.toRadians(cam.getYaw() - 90));
	}

	private void strafeRight(Camera3D cam, float distance) {
		cam.getPosition().x += distance * (float)Math.sin(Math.toRadians(cam.getYaw() + 90));
		cam.getPosition().z -= distance * (float)Math.cos(Math.toRadians(cam.getYaw() + 90));
	}
	
	public void update(Camera3D camera, long deltaMillis) {
		//TODO: Find a way to use the Mouse class to encompass this feature
		
		yaw(camera, m_dx * m_mouseSensitivity);
		pitch(camera, -m_dy * m_mouseSensitivity); 
		
		m_dx = 0;
		m_dy = 0;
		
		if (m_keyboard.isKeyPressed(m_keyboard.getKey("W"))) {
			walkForward(camera, (float) (deltaMillis * 0.001) * m_moveSpeed);
		}
		if (m_keyboard.isKeyPressed(m_keyboard.getKey("A"))) {
			strafeLeft(camera, (float) (deltaMillis * 0.001) * m_moveSpeed);
		}
		if (m_keyboard.isKeyPressed(m_keyboard.getKey("S"))) {
			walkBackwards(camera, (float) (deltaMillis * 0.001) * m_moveSpeed);
		}
		if (m_keyboard.isKeyPressed(m_keyboard.getKey("D"))) {
			strafeRight(camera, (float) (deltaMillis * 0.001) * m_moveSpeed);
		}
		if (m_keyboard.isKeyPressed(m_keyboard.getKey("LEFT_SHIFT")))  {
			down(camera, (float) (deltaMillis * 0.001) * m_moveSpeed);
		}
		if (m_keyboard.isKeyPressed(m_keyboard.getKey("SPACE")))  {
			up(camera, (float) (deltaMillis * 0.001) * m_moveSpeed);
		}
		
	}
}

