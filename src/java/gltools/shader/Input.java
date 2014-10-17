package gltools.shader;

import glcommon.vector.Matrix2f;
import glcommon.vector.Matrix3f;
import glcommon.vector.Matrix4f;
import glcommon.vector.Vector;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;

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

	public abstract void setValue(int val);
	public abstract void setValue(float val);
	public abstract void setValue(boolean val);
	public abstract void setValue(Vector2f val);
	public abstract void setValue(Vector3f val);
	public abstract void setValue(Vector4f val);
	public abstract void setValue(Matrix2f val);
	public abstract void setValue(Matrix3f val);
	public abstract void setValue(Matrix4f val);
	
	public void setValue(DataType type, Vector v) {
		switch (type) {
		case VEC2: setValue((Vector2f) v);
		case VEC3: setValue((Vector3f) v);
		case VEC4: setValue((Vector4f) v);
		default: throw new IllegalArgumentException("Unrecognized dataType:  " + type);
		}
	}
	public void setValue(DataType type, Object o) {
		switch (type) {
		case INT: setValue((Integer) o); break;
		case BOOL: setValue((Boolean) o); break;
		case FLOAT: setValue((Float) o); break;
		case VEC2: setValue((Vector2f) o); break;
		case VEC3: setValue((Vector3f) o); break;
		case VEC4: setValue((Vector4f) o); break;
		case MAT2: setValue((Matrix2f) o); break;
		case MAT3: setValue((Matrix3f) o); break;
		case MAT4: setValue((Matrix4f) o); break;
		default: throw new IllegalArgumentException("Unrecognized dataType:  " + type);
		}
	}
}
