package gltools.transform;

import gltools.vector.Matrix3f;
import gltools.vector.MatrixFactory;

public class Rotation2D extends MatOp3f {
	private float m_degrees;
	
	public Rotation2D() {}
	public Rotation2D(float degrees) {
		setDegrees(degrees);
	}
	
	public float getDegrees() { return m_degrees; }
	public void setDegrees(float degrees) { m_degrees = degrees; }
	
	public Matrix3f getTransform() {
		return MatrixFactory.create2DRotationMatrix((float) Math.toRadians(m_degrees));
	}
}
