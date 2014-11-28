package gltools.transform;

import glcommon.vector.Matrix3f;
import glcommon.vector.MatrixFactory;
import glcommon.vector.Vector2f;

public class Translation2D extends MatOp3f {
	private Vector2f m_translate = new Vector2f();
	
	public Translation2D() {}
	public Translation2D(Vector2f vec) {
		m_translate = vec;
	}
	public Translation2D(float x, float y) {
		m_translate = new Vector2f(x, y);
	}
	
	public void setTranslation(Vector2f translation) { m_translate = translation; }
	public Vector2f getTranslation() { return m_translate; }
	
	public Matrix3f getTransform() {
		return MatrixFactory.createAffineTranslationMatrix(m_translate);
	}
}
