package gltools.transform;

import gltools.GLMatrix4f;
import gltools.vector.Matrix4f;
import gltools.vector.MatrixFactory;
import gltools.vector.Vector3f;

public class Rotation {
	private float m_degrees;
	private Vector3f m_axis;
	
	public Rotation() {}
	public Rotation(float degrees, Vector3f axis) {
		setDegrees(degrees);
		setAxis(axis);
	}
	
	public float getDegrees() { return m_degrees; }
	public Vector3f getAxis() { return m_axis; }
	public void setDegrees(float degrees) { m_degrees = degrees; }
	public void setAxis(Vector3f axis) { m_axis = axis; }
	
	public Matrix4f getTransform() {
		return MatrixFactory.createRotationMatrix((float) Math.toRadians(m_degrees), m_axis);
	}
	
	public void apply() {
		GLMatrix4f.s_model.getMatrix().mul(getTransform());
		GLMatrix4f.s_normal.getMatrix().mul(getTransform());
	}
}
