package gltools.transform;

import gltools.vector.Matrix4f;
import gltools.vector.MatrixFactory;
import gltools.vector.Vector3f;

public class Translation3D extends MatOp4f {
	private Vector3f m_translate = new Vector3f();
	
	public Translation3D() {}
	public Translation3D(Vector3f vec) {
		m_translate = vec;
	}
	public Translation3D(float x, float y, float z) {
		m_translate = new Vector3f(x, y, z);
	}
	
	public void setTranslation(Vector3f translation) { m_translate = translation; }
	public Vector3f getTranslation() { return m_translate; }
	
	public Matrix4f getTransform() {
		return MatrixFactory.create3DTranslationMatrix(m_translate);
	}
}