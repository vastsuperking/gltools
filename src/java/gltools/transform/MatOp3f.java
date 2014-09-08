package gltools.transform;

import gltools.utils.GLMatrix3f;
import gltools.vector.Matrix3f;

public abstract class MatOp3f {
	public abstract Matrix3f getTransform();
	
	public void apply(Matrix3f mat) {
		Matrix3f.mul(mat, getTransform(), mat);
	}
	public void apply(GLMatrix3f mat) {
		Matrix3f.mul(mat.getCurrentMatrix(), getTransform(), mat.getCurrentMatrix());
	}
}
