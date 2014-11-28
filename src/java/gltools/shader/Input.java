package gltools.shader;

import glcommon.vector.Matrix2f;
import glcommon.vector.Matrix3f;
import glcommon.vector.Matrix4f;
import glcommon.vector.Vector;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.gl.GL2;

public abstract class Input {
	private InputUsage m_usage = null;
	private String m_name = "";
	private boolean m_active = true;
	public Input() {}
	
	public String getName() { return m_name; }
	public InputUsage getUsage() { return m_usage; }
	public boolean isActive() { return m_active; }
	public void setName(String name) { m_name = name; }
	public void setUsage(InputUsage usage) { m_usage = usage; }
	public void setActive(boolean active) { m_active = active; }

	public abstract void setValue(GL2 gl, int val);
	public abstract void setValue(GL2 gl, float val);
	public abstract void setValue(GL2 gl, boolean val);
	public abstract void setValue(GL2 gl, Vector2f val);
	public abstract void setValue(GL2 gl, Vector3f val);
	public abstract void setValue(GL2 gl, Vector4f val);
	public abstract void setValue(GL2 gl, Matrix2f val);
	public abstract void setValue(GL2 gl, Matrix3f val);
	public abstract void setValue(GL2 gl, Matrix4f val);
	
	public void setValue(GL2 gl, DataType type, Vector v) {
		switch (type) {
		case VEC2: setValue(gl, (Vector2f) v);
		case VEC3: setValue(gl, (Vector3f) v);
		case VEC4: setValue(gl, (Vector4f) v);
		default: throw new IllegalArgumentException("Unrecognized dataType:  " + type);
		}
	}
	public void setValue(GL2 gl, DataType type, Object o) {
		switch (type) {
		case INT: setValue(gl, (Integer) o); break;
		case BOOL: setValue(gl, (Boolean) o); break;
		case FLOAT: setValue(gl, (Float) o); break;
		case VEC2: setValue(gl, (Vector2f) o); break;
		case VEC3: setValue(gl, (Vector3f) o); break;
		case VEC4: setValue(gl, (Vector4f) o); break;
		case MAT2: setValue(gl, (Matrix2f) o); break;
		case MAT3: setValue(gl, (Matrix3f) o); break;
		case MAT4: setValue(gl, (Matrix4f) o); break;
		default: throw new IllegalArgumentException("Unrecognized dataType:  " + type);
		}
	}
}
