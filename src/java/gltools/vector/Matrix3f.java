package gltools.vector;

import java.nio.FloatBuffer;

public class Matrix3f extends Matrix {
	public float 	m00, m01, m02,
				 	m10, m11, m12,
				 	m20, m21, m22;
	
	public Matrix3f() {
		super();
		setIdentity();
	}
	public Matrix3f(Matrix3f src) {
		super();
		load(src);
	}
	public Matrix3f(float[][] floats) {
		super();
		load(floats);
	}
	
	public Matrix3f setIdentity() {
		return setIdentity(this);
	}
	public Matrix3f setZero() {
		return setZero(this);
	}
	
	public Matrix3f load(Matrix3f src) {
		return load(src, this);
	}
	public Matrix3f load(FloatBuffer buf) {
		return load(buf, this);
	}
	public Matrix3f loadTranspose(FloatBuffer buf) {
		return loadTranspose(buf, this);
	}
	public Matrix3f load(float[][] floats) {
		m00 = floats[0][0];	m01 = floats[0][1];	m02 = floats[0][2];
		m10 = floats[1][0];	m11 = floats[1][1];	m12 = floats[1][2];
		m20 = floats[2][0];	m21 = floats[2][1];	m22 = floats[2][2];
		return this;
	}
	
	public Matrix3f store(FloatBuffer buf) {
		return store(this, buf);
	}
	public Matrix3f storeTranspose(FloatBuffer buf) {
		return storeTranspose(this, buf);
	}
	
	public Matrix3f invert() {
		return invert(this, this);
	}
	public Matrix3f invert(Matrix3f dest) {
		return invert(this, dest);
	}
	public Matrix3f transpose() {
		return transpose(this, this);
	}
	public Matrix3f transpose(Matrix3f dest) {
		return transpose(this, dest);
	}
	public Matrix3f negate() {
		return negate(this, this);
	}
	public Matrix3f negate(Matrix3f dest) {
		return negate(this, dest);
	}
	
	public Matrix3f mul(Matrix3f right) {
		return mul(this, right, this);
	}
	public Matrix3f mul(Matrix3f right, Matrix3f dest) {
		return mul(this, right, dest);
	}
	
	public float determinant() {
		float d = m00 * (m11 * m22 - m12 * m21)
					+ m01 * (m12 * m20 - m10 * m22)
					+ m02 * (m10 * m21 - m11 * m20);
		return d;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append('[').append(m00).append(", ").append(m10).append(", ").append(m20).append(']').append('\n');
		buf.append('[').append(m01).append(", ").append(m11).append(", ").append(m21).append(']').append('\n');
		buf.append('[').append(m02).append(", ").append(m12).append(", ").append(m22).append(']').append('\n');
		return buf.toString();
	}
	public float[][] toFloats() {
		return new float[][] { {m00, m01, m02}, 
								 {m10, m11, m12},
								 {m20, m21, m22} };
	}
	
	public Matrix3f clone() {
		return new Matrix3f(this);
	}
	
	//---------------Static Functions------------------
	
	public static Matrix3f setIdentity(Matrix3f m) {
		m.m00 = 1.0f; m.m01 = 0.0f; m.m02 = 0.0f;
		m.m10 = 0.0f; m.m11 = 1.0f; m.m12 = 0.0f;
		m.m20 = 0.0f; m.m21 = 0.0f; m.m22 = 1.0f;
		return m;
	}
	public static Matrix3f setZero(Matrix3f m) {
		m.m00 = 0.0f; m.m01 = 0.0f; m.m02 = 0.0f;
		m.m10 = 0.0f; m.m11 = 0.0f; m.m12 = 0.0f;
		m.m20 = 0.0f; m.m21 = 0.0f; m.m22 = 0.0f;
		return m;
	}
	
	public static Matrix3f load(Matrix3f src, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		dest.m00 = src.m00;
		dest.m10 = src.m10;
		dest.m20 = src.m20;
		dest.m01 = src.m01;
		dest.m11 = src.m11;
		dest.m21 = src.m21;
		dest.m02 = src.m02;
		dest.m12 = src.m12;
		dest.m22 = src.m22;
		return dest;
	}
	public static Matrix3f load(FloatBuffer buf, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		dest.m00 = buf.get();
		dest.m01 = buf.get();
		dest.m02 = buf.get();
		dest.m10 = buf.get();
		dest.m11 = buf.get();
		dest.m12 = buf.get();
		dest.m20 = buf.get();
		dest.m21 = buf.get();
		dest.m22 = buf.get();
		return dest;
	}
	public static Matrix3f loadTranspose(FloatBuffer buf, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		
		dest.m00 = buf.get();
		dest.m10 = buf.get();
		dest.m20 = buf.get();
		dest.m01 = buf.get();
		dest.m11 = buf.get();
		dest.m21 = buf.get();
		dest.m02 = buf.get();
		dest.m12 = buf.get();
		dest.m22 = buf.get();
		
		return dest;
	}
	public static Matrix3f store(Matrix3f mat, FloatBuffer buf) {
		buf.put(mat.m00);
		buf.put(mat.m01);
		buf.put(mat.m02);
		buf.put(mat.m10);
		buf.put(mat.m11);
		buf.put(mat.m12);
		buf.put(mat.m20);
		buf.put(mat.m21);
		buf.put(mat.m22);
		return mat;
	}
	public static Matrix3f storeTranspose(Matrix3f mat, FloatBuffer buf) {
		buf.put(mat.m00);
		buf.put(mat.m10);
		buf.put(mat.m20);
		buf.put(mat.m01);
		buf.put(mat.m11);
		buf.put(mat.m21);
		buf.put(mat.m02);
		buf.put(mat.m12);
		buf.put(mat.m22);
		return mat;
	}
	public static Matrix3f negate(Matrix3f src, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		dest.m00 = -src.m00;
		dest.m01 = -src.m02;
		dest.m02 = -src.m01;
		dest.m10 = -src.m10;
		dest.m11 = -src.m12;
		dest.m12 = -src.m11;
		dest.m20 = -src.m20;
		dest.m21 = -src.m22;
		dest.m22 = -src.m21;
		return dest;
	}
	public static Matrix3f transpose(Matrix3f src, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		float m00 = src.m00; float m01 = src.m10; float m02 = src.m20;
		float m10 = src.m01; float m11 = src.m11; float m12 = src.m21;
		float m20 = src.m02; float m21 = src.m12; float m22 = src.m22;

		dest.m00 = m00; dest.m01 = m01; dest.m02 = m02;
		dest.m10 = m10; dest.m11 = m11; dest.m12 = m12;
		dest.m20 = m20; dest.m21 = m21; dest.m22 = m22;
		return dest;
	}
	
	public static Matrix3f add(Matrix3f left, Matrix3f right, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		dest.m00 = left.m00 + right.m00;
		dest.m01 = left.m01 + right.m01;
		dest.m02 = left.m02 + right.m02;
		dest.m10 = left.m10 + right.m10;
		dest.m11 = left.m11 + right.m11;
		dest.m12 = left.m12 + right.m12;
		dest.m20 = left.m20 + right.m20;
		dest.m21 = left.m21 + right.m21;
		dest.m22 = left.m22 + right.m22;
		return dest;
	}
	public static Matrix3f sub(Matrix3f left, Matrix3f right, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();
		dest.m00 = left.m00 - right.m00;
		dest.m01 = left.m01 - right.m01;
		dest.m02 = left.m02 - right.m02;
		dest.m10 = left.m10 - right.m10;
		dest.m11 = left.m11 - right.m11;
		dest.m12 = left.m12 - right.m12;
		dest.m20 = left.m20 - right.m20;
		dest.m21 = left.m21 - right.m21;
		dest.m22 = left.m22 - right.m22;
		return dest;
	}
	public static Matrix3f mul(Matrix3f left, Matrix3f right, Matrix3f dest) {
		if (dest == null) dest = new Matrix3f();

		float m00 =	left.m00 * right.m00 + 
					left.m10 * right.m01 + 
					left.m20 * right.m02;
		float m01 =	left.m01 * right.m00 +
					left.m11 * right.m01 + 
					left.m21 * right.m02;
		float m02 =	left.m02 * right.m00 +
					left.m12 * right.m01 +
					left.m22 * right.m02;
		float m10 =	left.m00 * right.m10 +
					left.m10 * right.m11 +
					left.m20 * right.m12;
		float m11 =	left.m01 * right.m10 +
					left.m11 * right.m11 +
					left.m21 * right.m12;
		float m12 =	left.m02 * right.m10 +
					left.m12 * right.m11 +
					left.m22 * right.m12;
		float m20 =	left.m00 * right.m20 +
					left.m10 * right.m21 +
					left.m20 * right.m22;
		float m21 =	left.m01 * right.m20 +
					left.m11 * right.m21 +
					left.m21 * right.m22;
		float m22 =	left.m02 * right.m20 +
					left.m12 * right.m21 +
					left.m22 * right.m22;

		dest.m00 = m00;
		dest.m01 = m01;
		dest.m02 = m02;
		dest.m10 = m10;
		dest.m11 = m11;
		dest.m12 = m12;
		dest.m20 = m20;
		dest.m21 = m21;
		dest.m22 = m22;

		return dest;
	}
	public static Matrix3f invert(Matrix3f src, Matrix3f dest) {
		float determinant = src.determinant();

		if (determinant != 0) {
			if (dest == null) dest = new Matrix3f();
			 float determinant_inv = 1f/determinant;

			 float t00 = src.m11 * src.m22 - src.m12* src.m21;
			 float t01 = - src.m10 * src.m22 + src.m12 * src.m20;
			 float t02 = src.m10 * src.m21 - src.m11 * src.m20;
			 float t10 = - src.m01 * src.m22 + src.m02 * src.m21;
			 float t11 = src.m00 * src.m22 - src.m02 * src.m20;
			 float t12 = - src.m00 * src.m21 + src.m01 * src.m20;
			 float t20 = src.m01 * src.m12 - src.m02 * src.m11;
			 float t21 = -src.m00 * src.m12 + src.m02 * src.m10;
			 float t22 = src.m00 * src.m11 - src.m01 * src.m10;

			 dest.m00 = t00*determinant_inv;
			 dest.m11 = t11*determinant_inv;
			 dest.m22 = t22*determinant_inv;
			 dest.m01 = t10*determinant_inv;
			 dest.m10 = t01*determinant_inv;
			 dest.m20 = t02*determinant_inv;
			 dest.m02 = t20*determinant_inv;
			 dest.m12 = t21*determinant_inv;
			 dest.m21 = t12*determinant_inv;
			 return dest;
		} else return null;
	}
}