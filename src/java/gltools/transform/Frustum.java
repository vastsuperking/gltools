package gltools.transform;

public class Frustum {
	private float m_left;
	private float m_right;
	private float m_bottom;
	private float m_top;
	private float m_near;
	private float m_far;
	
	public Frustum() {}
	public Frustum(float left, float right,
					float bottom, float top,
					float near, float far) {
		m_left = left;
		m_right = right;
		m_bottom = bottom;
		m_top = top;
		m_near = near;
		m_far = far;
	}

	public float getLeft() { return m_left; }
	public float getRight() { return m_right; }
	public float getBottom() { return m_bottom; }
	public float getTop() { return m_top; }
	public float getNear() { return m_near; }
	public float getFar() { return m_far; }
	
	public void setLeft(float left) {	m_left = left; }
	public void setRight(float right) { m_right = right; }
	public void setBottom(float bottom) { m_bottom = bottom; }
	public void setTop(float top) { m_top = top; }
	public void setNear(float near) {	m_near = near; }
	public void setFar(float far) { m_far = far; }
}
