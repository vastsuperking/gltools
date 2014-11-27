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

	public abstract void setValue(int val, GL2 gl);
	public abstract void setValue(float val, GL2 gl);
	public abstract void setValue(boolean val, GL2 gl);
	public abstract void setValue(Vector2f val, GL2 gl);
	public abstract void setValue(Vector3f val, GL2 gl);
	public abstract void setValue(Vector4f val, GL2 gl);
	public abstract void setValue(Matrix2f val, GL2 gl);
	public abstract void setValue(Matrix3f val, GL2 gl);
	public abstract void setValue(Matrix4f val, GL2 gl);
	
	public void setValue(DataType type, Vector v, GL2 gl) {
		switch (type) {
		case VEC2: setValue((Vector2f) v, gl);
		case VEC3: setValue((Vector3f) v, gl);
		case VEC4: setValue((Vector4f) v, gl);
		default: throw new IllegalArgumentException("Unrecognized dataType:  " + type);
		}
	}
	public void setValue(DataType type, Object o, GL2 gl) {
		switch (type) {
		case INT: setValue((Integer) o, gl); break;
		case BOOL: setValue((Boolean) o, gl); break;
		case FLOAT: setValue((Float) o, gl); break;
		case VEC2: setValue((Vector2f) o, gl); break;
		case VEC3: setValue((Vector3f) o, gl); break;
		case VEC4: setValue((Vector4f) o, gl); break;
		case MAT2: setValue((Matrix2f) o, gl); break;
		case MAT3: setValue((Matrix3f) o, gl); break;
		case MAT4: setValue((Matrix4f) o, gl); break;
		default: throw new IllegalArgumentException("Unrecognized dataType:  " + type);
		}
	}
}
