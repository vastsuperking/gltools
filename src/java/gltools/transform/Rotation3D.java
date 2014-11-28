package gltools.transform;

import glcommon.vector.Matrix4f;
import glcommon.vector.MatrixFactory;
import glcommon.vector.Vector3f;
import gltools.util.GLMatrix3f;

public class Rotation3D extends MatOp4f {
	private float m_degrees;
	private Vector3f m_axis;
	
	public Rotation3D() {}
	public Rotation3D(float degrees, Vector3f axis) {
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
	
	public void applyToNormal(GLMatrix3f normal) {
		normal.getCurrentMatrix().mul(MatrixFactory.createRotationMatrix3f((float) Math.toRadians(m_degrees), m_axis));
	}
}
