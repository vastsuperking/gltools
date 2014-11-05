package gltools.transform;

import glcommon.vector.Matrix4f;
import glcommon.vector.MatrixFactory;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.util.GLMatrix4f;

public class Camera3D {
	Vector3f m_position = new Vector3f(0f, 0f, 0f);

	float m_pitch = 0f;
	float m_yaw = 0f;
	float m_roll = 0f;

	public static Vector3f PITCH_NORM = new Vector3f(1.0f, 0.0f, 0.0f);
	public static Vector3f YAW_NORM = new Vector3f(0.0f, 1.0f, 0.0f);
	public static Vector3f ROLL_NORM = new Vector3f(1.0f, 0.0f, 1.0f);
	

	public Camera3D(Vector3f position, float pitch, float yaw, float roll) {
		m_position = position;
		m_pitch = pitch;
		m_yaw = yaw;
		m_roll = roll;
	}
	public Camera3D() {}
	
	public void setX(float x) {m_position.setX(x);}
	public void setY(float y) {m_position.setY(y);}
	public void setZ(float z) {m_position.setZ(z);}
	public void setPosition(Vector3f pos) { m_position.set(pos); } 
	public void setPitch(float a) {m_pitch = a;}
	public void setYaw(float a) {m_yaw = a;}
	public void setRoll(float a) {m_roll = a;}
	public float getX() {return m_position.x;}
	public float getY() {return m_position.y;}
	public float getZ() {return m_position.z;}
	public float getPitch() {return m_pitch;}
	public float getYaw() {return m_yaw;}
	public float getRoll() {return m_roll;}
	public Vector3f getPosition() {return m_position;}

	public void apply(GLMatrix4f matrix) {
		apply(matrix.getCurrentMatrix());
	}
	public void apply(Matrix4f matrix) {


		Matrix4f yawRot = MatrixFactory.create3DRotationMatrix((float) Math.toRadians(m_yaw), new Vector3f(0f, 1f, 0f));
		Matrix4f pitchRot = MatrixFactory.create3DRotationMatrix((float) Math.toRadians(m_pitch), new Vector3f(1f, 0f, 0f));

		matrix.mul(pitchRot);
		matrix.mul(yawRot);

		matrix.mul(MatrixFactory.create3DTranslationMatrix(m_position.negate(null)));

		//Create a translation matrix from the position negated into a new vector
		//Update the view matrix
		//GLMatrix4f.s_view.load();
	}
	public Matrix4f getRotateMatrix() {
		Matrix4f mat = new Matrix4f().setIdentity();
		mat.mul(MatrixFactory.create3DRotationMatrix((float) Math.toRadians(m_yaw), new Vector3f(0f, 1f, 0f)));
		mat.mul(MatrixFactory.create3DRotationMatrix((float) Math.toRadians(m_pitch), new Vector3f(1f, 0f, 0f)));
		return mat;
	}
	public Vector3f getViewDirection() {
		//transform a straight looking vector by the rotation matrix and place the value into itself
		Vector4f vec4 = getRotateMatrix().mul(new Vector4f(0f, 0f, -1f, 1f));
		Vector3f vec = new Vector3f(vec4);
		return vec;
	}
	public Vector3f getRightDirection() {
		Vector4f vec = getRotateMatrix().mul(new Vector4f(1f, 0f, 0f, 1f));
		return new Vector3f(vec);
	}
	public Vector3f getUpDirection() {
		//transform a up vector by the rotation matrix and place the value into itself
		Vector4f vec = getRotateMatrix().mul(new Vector4f(0f, 1f, 0f, 1f));
		return new Vector3f(vec.getX(), vec.getY(), vec.getZ());
	}


	public String toString() {
		return m_position.toString();
	}
}