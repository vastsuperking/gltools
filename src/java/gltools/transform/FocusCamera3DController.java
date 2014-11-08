package gltools.transform;

import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.input.Mouse;
import gltools.input.Mouse.MouseButton;
import gltools.input.MouseListener;

public class FocusCamera3DController implements Camera3DController, MouseListener {
	private Vector3f m_focalPoint = new Vector3f();
	private float m_distance = 1f;
	private Mouse m_mouse = null;
	private boolean m_zoomEnabled = true;
	private float m_minZoom;
	
	private float m_mouseSensitivity;
	private float m_scrollSensitivity;
	
	private float m_pitchDelta = 0f;
	private float m_yawDelta = 0f;
	
	private boolean m_updatePos = false;
	private boolean m_dragging = false;
	
	public FocusCamera3DController(Vector3f point, float distance, Mouse mouse, boolean zoom, float minZoom, float mouseSensitivity, float scrollSensitivity) {
		m_focalPoint = point;
		m_distance = distance;
		m_mouse = mouse;
		m_minZoom = minZoom;
		m_zoomEnabled = zoom;
		
		m_scrollSensitivity = scrollSensitivity;
		m_mouseSensitivity = mouseSensitivity;
		
		m_updatePos = true;
		m_mouse.addListener(this);
	}
	public void setDistance(float dist) {
		m_distance = dist;
		m_updatePos = true;
	}
	public void setFocalPoint(Vector3f pt) {
		m_focalPoint = pt;
		m_updatePos = true;
	}
	
	@Override
	public void update(Camera3D camera, long deltaMillis) {
		if (m_pitchDelta != 0) {
			camera.setPitch(camera.getPitch() + m_pitchDelta);
			m_pitchDelta = 0;
		}
		if (m_yawDelta != 0) {
			camera.setYaw(camera.getYaw() + m_yawDelta);
			m_yawDelta = 0;
		}
		
		if (m_updatePos) {
			//Update the position of the camera
			Vector3f pos = new Vector3f(m_focalPoint);
			Vector3f view = new Vector3f(0, 0, -m_distance);
			//Transform view by our cam_rotate matrix
			Vector4f viewRot = camera.getRotateMatrix().mul(new Vector4f(view, 0f), null);
			Vector3f viewRot3 = new Vector3f(viewRot.getX(), viewRot.getY(), -viewRot.getZ());
			//viewRot3.normalise().scale(m_distance);
			//Add the view to pos // pos <-- pos + view
			pos.add(viewRot3);
			camera.setPosition(pos);
			//Set update pos to false
			m_updatePos = false;
		}
	}
	@Override
	public void mouseMoved(Mouse m, int x, int y, Vector2f delta) {
		
	}
	@Override
	public void mouseWheelMoved(Mouse m, int dm) {
		if (m_zoomEnabled) {
			float nd = m_distance - dm * m_scrollSensitivity;
			if (nd < m_minZoom) nd = m_minZoom;
			m_distance = nd;
			m_updatePos = true;
		}
	}
	@Override
	public void mouseDelta(Mouse m, int dx, int dy) {
		if (m_dragging) {
			m_pitchDelta -= dy * m_mouseSensitivity;
			m_yawDelta += dx * m_mouseSensitivity;
			m_updatePos = true;
		}
	}
	@Override
	public void mouseButtonPressed(Mouse m, MouseButton button) {
		if (button.getName().equals(MouseButton.RIGHT_BUTTON_NAME)) m_dragging = true;
	}
	@Override
	public void mouseButtonReleased(Mouse m, MouseButton button) {
		m_dragging = false;
	}
}
