package gltools.transform;

import gltools.GLMatrix4f;
import gltools.vector.Matrix4f;
import gltools.vector.MatrixFactory;
import gltools.vector.Vector3f;

public class Translation {
	private Vector3f m_translate = new Vector3f();
	
	public Translation() {}
	public Translation(Vector3f vec) {
		m_translate = vec;
	}
	public Translation(float x, float y, float z) {
		m_translate = new Vector3f(x, y, z);
	}
	
	public void setTranslation(Vector3f translation) { m_translate = translation; }
	public Vector3f getTranslation() { return m_translate; }
	
	public Matrix4f getTransform() {
		return MatrixFactory.createTranslationMatrix(m_translate);
	}
	
	public void apply() {
		GLMatrix4f.s_model.getMatrix().mul(getTransform());
	}
}
