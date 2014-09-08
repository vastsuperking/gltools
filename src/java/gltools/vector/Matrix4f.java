package gltools.vector;

import java.nio.FloatBuffer;

public class Matrix4f extends Matrix {

	public float m00, m01, m02, m03,
		         m10, m11, m12, m13,
		         m20, m21, m22, m23,
		         m30, m31, m32, m33;

	public Matrix4f() {
		super();
		setIdentity();
	}
	public Matrix4f(Matrix4f src) {
		super();
		load(src);
	}
	public Matrix4f(float[][] floats) {
		super();
		load(floats);
	} 
	public Matrix4f transpose() {
		return transpose(this, this);
	}
	public Matrix4f transpose(Matrix4f dest) {
		return transpose(this, dest);
	}

	public Matrix4f negate() {
		return negate(this, this);
	}
	public Matrix4f negate(Matrix4f dest) {
		return negate(this, dest);
	}
	public Matrix4f add(Matrix4f right) {
		return add(this, right, this);
	}
	public Matrix4f add(Matrix4f right, Matrix4f dest) {
		return add(this, right, dest);
	}
	public Matrix4f sub(Matrix4f right) {
		return sub(this, right, this);
	}
	public Matrix4f sub(Matrix4f right, Matrix4f dest) {
		return sub(this, right, dest);
	}
	public Matrix4f mul(Matrix4f right) {
		return mul(this, right, this);
	}
	public Matrix4f mul(Matrix4f right, Matrix4f dest) {
		return mul(this, right, dest);
	}
	public Vector4f mul(Vector4f vec, Vector4f dest) {
		return mul(this, vec, dest);
	}
	public Vector4f mul(Vector4f vec) {
		return mul(this, vec, vec);
	}
	public Matrix3f getInnerMat3f() {
		float[][] inner = {{m00, m01, m02},
							{m10, m11, m12},
							{m20, m21, m22}};
		return new Matrix3f(inner);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(m00).append(", ").append(m01).append(", ")
			   .append(m02).append(", ").append(m03).append("]").append('\n');
			   
		builder.append("[").append(m10).append(", ").append(m11).append(", ")
			   .append(m12).append(", ").append(m13).append("]").append('\n');
			   
		builder.append("[").append(m20).append(", ").append(m21).append(", ")
			   .append(m22).append(", ").append(m23).append("]").append('\n');
			   
		builder.append("[").append(m30).append(", ").append(m31).append(", ")
			   .append(m32).append(", ").append(m33).append("]").append('\n');
			   
		return builder.toString();
	}
  
	public Matrix4f setIdentity() {
		m00 = 1f; m01 = 0f; m02 = 0f; m03 = 0f;
		m10 = 0f; m11 = 1f; m12 = 0f; m13 = 0f;
		m20 = 0f; m21 = 0f; m22 = 1f; m23 = 0f;
		m30 = 0f; m31 = 0f; m32 = 0f; m33 = 1f;
		return this;
	}
	public Matrix4f setZero() {
		m00 = 0f; m01 = 0f; m02 = 0f; m03 = 0f;
		m10 = 0f; m11 = 0f; m12 = 0f; m13 = 0f;
		m20 = 0f; m21 = 0f; m22 = 0f; m23 = 0f;
		m30 = 0f; m31 = 0f; m32 = 0f; m33 = 0f;
		return this;
	}
	public Matrix4f load(float[][] floats) {
		m00 = floats[0][0];	m01 = floats[0][1];	m02 = floats[0][2]; m03 = floats[0][3];
		m10 = floats[1][0];	m11 = floats[1][1];	m12 = floats[1][2];	m13 = floats[1][3];
		m20 = floats[2][0];	m21 = floats[2][1];	m22 = floats[2][2];	m23 = floats[2][3];
		m30 = floats[3][0];	m31 = floats[3][1];	m32 = floats[3][2]; m33 = floats[3][3];
		return this;
	}
	public float[][] toFloats() {
		float[][] floats = {{m00, m01, m02, m03},
							{m10, m11, m12, m13},
							{m20, m21, m22, m23},
							{m30, m31, m32, m33}};
		return floats;
	}
	
	public Matrix4f  load(Matrix4f src) {
		load(src.toFloats());
		return this;
	}
	public Matrix4f load(FloatBuffer buf) {
		m00 = buf.get(); m01 = buf.get(); m02 = buf.get(); m03 = buf.get();
		m10 = buf.get(); m11 = buf.get(); m12 = buf.get(); m13 = buf.get();
		m20 = buf.get(); m21 = buf.get(); m22 = buf.get(); m23 = buf.get();
		m30 = buf.get(); m31 = buf.get(); m32 = buf.get(); m33 = buf.get();
		return this;
	}
	public Matrix loadTranspose(FloatBuffer buf) {
		Matrix4f mat = new Matrix4f();
		mat.load(buf);
		mat.transpose(this);//create a new matrix from the buf
		//and transpose it into this matrix(transpose of transpose = matrix)
		return this;
	}
	public Matrix4f store(FloatBuffer buf) {
		buf.put(m00).put(m01).put(m02).put(m03);
		buf.put(m10).put(m11).put(m12).put(m13);
		buf.put(m20).put(m21).put(m22).put(m23);
		buf.put(m30).put(m31).put(m32).put(m33);
		return this;
	}
	public Matrix4f storeTranspose(FloatBuffer buf) {
		Matrix4f mat = new Matrix4f(this);
		mat.transpose();
		mat.store(buf);
		//create a new matrix from this one, transpose it and store it in the buf
		return this;
	}
	
	@Override
	public Matrix4f clone() {
		return new Matrix4f(this);
	}

	public static Matrix4f negate(Matrix4f src, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();

		dest.m00 = -src.m00;
		dest.m01 = -src.m01;
		dest.m02 = -src.m02;
		dest.m03 = -src.m03;
		dest.m10 = -src.m10;
		dest.m11 = -src.m11;
		dest.m12 = -src.m12;
		dest.m13 = -src.m13;
		dest.m20 = -src.m20;
		dest.m21 = -src.m21;
		dest.m22 = -src.m22;
		dest.m23 = -src.m23;
		dest.m30 = -src.m30;
		dest.m31 = -src.m31;
		dest.m32 = -src.m32;
		dest.m33 = -src.m33;

		return dest;
	}	
	public static Matrix4f transpose(Matrix4f src, Matrix4f dest) {
		if (dest == null)
		   dest = new Matrix4f();
		float m00 = src.m00;
		float m01 = src.m10;
		float m02 = src.m20;
		float m03 = src.m30;
		float m10 = src.m01;
		float m11 = src.m11;
		float m12 = src.m21;
		float m13 = src.m31;
		float m20 = src.m02;
		float m21 = src.m12;
		float m22 = src.m22;
		float m23 = src.m32;
		float m30 = src.m03;
		float m31 = src.m13;
		float m32 = src.m23;
		float m33 = src.m33;

		dest.m00 = m00;
		dest.m01 = m01;
		dest.m02 = m02;
		dest.m03 = m03;
		dest.m10 = m10;
		dest.m11 = m11;
		dest.m12 = m12;
		dest.m13 = m13;
		dest.m20 = m20;
		dest.m21 = m21;
		dest.m22 = m22;
		dest.m23 = m23;
		dest.m30 = m30;
		dest.m31 = m31;
		dest.m32 = m32;
		dest.m33 = m33;

		return dest;
	}
	public static Matrix4f sub(Matrix4f left, Matrix4f right, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();

		dest.m00 = left.m00 - right.m00;
		dest.m01 = left.m01 - right.m01;
		dest.m02 = left.m02 - right.m02;
		dest.m03 = left.m03 - right.m03;
		dest.m10 = left.m10 - right.m10;
		dest.m11 = left.m11 - right.m11;
		dest.m12 = left.m12 - right.m12;
		dest.m13 = left.m13 - right.m13;
		dest.m20 = left.m20 - right.m20;
		dest.m21 = left.m21 - right.m21;
		dest.m22 = left.m22 - right.m22;
		dest.m23 = left.m23 - right.m23;
		dest.m30 = left.m30 - right.m30;
		dest.m31 = left.m31 - right.m31;
		dest.m32 = left.m32 - right.m32;
		dest.m33 = left.m33 - right.m33;

		return dest;
	}
	public static Matrix4f add(Matrix4f left, Matrix4f right, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();

		dest.m00 = left.m00 + right.m00;
		dest.m01 = left.m01 + right.m01;
		dest.m02 = left.m02 + right.m02;
		dest.m03 = left.m03 + right.m03;
		dest.m10 = left.m10 + right.m10;
		dest.m11 = left.m11 + right.m11;
		dest.m12 = left.m12 + right.m12;
		dest.m13 = left.m13 + right.m13;
		dest.m20 = left.m20 + right.m20;
		dest.m21 = left.m21 + right.m21;
		dest.m22 = left.m22 + right.m22;
		dest.m23 = left.m23 + right.m23;
		dest.m30 = left.m30 + right.m30;
		dest.m31 = left.m31 + right.m31;
		dest.m32 = left.m32 + right.m32;
		dest.m33 = left.m33 + right.m33;

		return dest;
	}
	public static Matrix4f mul(Matrix4f left, Matrix4f right, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();

		float m00 =	left.m00 * right.m00 +
					left.m01 * right.m10 +
					left.m02 * right.m20 +
					left.m03 * right.m30;
		
		float m01 =	left.m00 * right.m01 +
					left.m01 * right.m11 +
					left.m02 * right.m21 +
					left.m03 * right.m31;
		
		float m02 =	left.m00 * right.m02 +
					left.m01 * right.m12 +
					left.m02 * right.m22 +
					left.m03 * right.m32;
		
		float m03 =	left.m00 * right.m03 +
					left.m01 * right.m13 +
					left.m02 * right.m23 +
					left.m03 * right.m33;
		
		//--Do next row--//
		
		float m10 =	left.m10 * right.m00 +
					left.m11 * right.m10 +
					left.m12 * right.m20 +
					left.m13 * right.m30;
		
		float m11 =	left.m10 * right.m01 +
					left.m11 * right.m11 +
					left.m12 * right.m21 +
					left.m13 * right.m31;
		
		float m12 =	left.m10 * right.m02 +
					left.m11 * right.m12 +
					left.m12 * right.m22 +
					left.m13 * right.m32;
		
		float m13 =	left.m10 * right.m03 +
					left.m11 * right.m13 +
					left.m12 * right.m23 +
					left.m13 * right.m33;
		
		//--Do next row--//
		
		float m20 =	left.m20 * right.m00 +
					left.m21 * right.m10 +
					left.m22 * right.m20 +
					left.m23 * right.m30;
		
		float m21 =	left.m20 * right.m01 +
					left.m21 * right.m11 +
					left.m22 * right.m21 +
					left.m23 * right.m31;
		
		float m22 =	left.m20 * right.m02 +
					left.m21 * right.m12 +
					left.m22 * right.m22 +
					left.m23 * right.m32;
		
		float m23 =	left.m20 * right.m03 +
					left.m21 * right.m13 +
					left.m22 * right.m23 +
					left.m23 * right.m33;
		
		//--Do next row--//
		
		float m30 =	left.m30 * right.m00 +
					left.m31 * right.m10 +
					left.m32 * right.m20 +
					left.m33 * right.m30;
		
		float m31 =	left.m30 * right.m01 +
					left.m31 * right.m11 +
					left.m32 * right.m21 +
					left.m33 * right.m31;
		
		float m32 =	left.m30 * right.m02 +
					left.m31 * right.m12 +
					left.m32 * right.m22 +
					left.m33 * right.m32;
		
		float m33 =	left.m30 * right.m03 +
					left.m31 * right.m13 +
					left.m32 * right.m23 +
					left.m33 * right.m33;

		dest.m00 = m00;
		dest.m01 = m01;
		dest.m02 = m02;
		dest.m03 = m03;
		dest.m10 = m10;
		dest.m11 = m11;
		dest.m12 = m12;
		dest.m13 = m13;
		dest.m20 = m20;
		dest.m21 = m21;
		dest.m22 = m22;
		dest.m23 = m23;
		dest.m30 = m30;
		dest.m31 = m31;
		dest.m32 = m32;
		dest.m33 = m33;

		return dest;
	}
	public static void main(String[] args) {
		/*float[][] m1 = {{1f, 0f, 0f, 5f},
						 {0f, 1f, 0f, 0f},
						 {0f, 0f, 1f, -5f},
						 {0f, 0f, 0f, 1f}};
		float[][] m2 = {{0.9934f, 0f, 0.115f, 0f},
						{0f, 1f, 0f, 0f},
						{-0.115f, 0f, 0.993f, 0f},
						{0f, 0f, 0f, 1f}};*/
		//Matrix4f mat1 = new Matrix4f(m1);
		//Matrix4f mat2 = new Matrix4f(m2);
		Matrix4f mat1 = MatrixFactory.create3DRotationMatrix((float) (0.5 * Math.PI), new Vector3f(0f, 0f, 1f));
		Matrix4f mat2 = MatrixFactory.create3DTranslationMatrix(new Vector3f(1, 1, 5));
		
		Matrix4f mat3 = new Matrix4f();
		Matrix4f.mul(mat1, mat2, mat3);
		System.out.println(mat1);
		System.out.println(mat2);
		System.out.println(mat3);
		Vector4f vec = new Vector4f(1, 1, 1, 1);
		Vector4f trans = new Vector4f();
		Matrix4f.mul(mat3, vec, trans);
		System.out.println(trans);
	}
	public static Vector4f mul(Matrix4f left, Vector4f right, Vector4f dest) {
		if (dest == null)
			dest = new Vector4f();

		float x = 	left.m00 * right.x +
					left.m01 * right.y + 
					left.m02 * right.z +
					left.m03 * right.w;
		float y = 	left.m10 * right.x +
					left.m11 * right.y + 
					left.m12 * right.z +
					left.m13 * right.w;
		float z = 	left.m20 * right.x + 
					left.m21 * right.y + 
					left.m22 * right.z + 
					left.m23 * right.w;
		float w = 	left.m30 * right.x +
					left.m31 * right.y + 
					left.m32 * right.z + 
					left.m33 * right.w;

		dest.x = x;
		dest.y = y;
		dest.z = z;
		dest.w = w;

		return dest;
	}
}