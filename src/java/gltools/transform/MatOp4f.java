package gltools.transform;

import gltools.utils.GLMatrix4f;
import gltools.vector.Matrix4f;

//Matrix Operation
public abstract class MatOp4f {
	public abstract Matrix4f getTransform();
	
	public void apply(Matrix4f mat) {
		Matrix4f.mul(mat, getTransform(), mat);
	}
	public void apply(GLMatrix4f mat) {
		//mat.getCurrentMatrix().mul(getTransform());
		//Matrix4f.mul(getTransform(), mat.getCurrentMatrix(), mat.getCurrentMatrix());
		Matrix4f.mul(mat.getCurrentMatrix(), getTransform(), mat.getCurrentMatrix());
	}
}
