package gltools.shader;

import gltools.gl.GL1;
import gltools.gl.GL2;

public enum DataType {
	BYTE(GL1.GL_BYTE, 1),
	SHORT(GL1.GL_SHORT, 2),
	FLOAT(GL1.GL_FLOAT, 4),
	DOUBLE(GL1.GL_DOUBLE, 8),
	INT(GL1.GL_INT, 4),
	BOOL(GL2.GL_BOOL, 4),
	
	VEC2(FLOAT, 2),
	VEC3(FLOAT, 3),
	VEC4(FLOAT, 4),
	MAT2(FLOAT, 16),
	MAT3(FLOAT, 9),
	MAT4(FLOAT, 16),
	//Not really datatypes, but they are glsl datatype
	//Only uniforms can have this datatype
	SAMPLER1D(GL1.GL_INT, 4),
	SAMPLER2D(GL1.GL_INT, 4);
	
	private final int m_id;
	private final int m_size;
	private final int m_components;
	private final DataType m_componentType;
	
	DataType(int id, int size) {
		m_id = id;
		m_size = size;
		m_components = 1;
		m_componentType = this;
	}
	DataType(DataType type, int components) {
		m_id = type.getID();
		m_size = type.getSize() * components;
		m_componentType = type;
		m_components = components;
	}
	
	public int getID() {
		return m_id;
	}
	public int getSize() { return m_size; }
	
	public int getComponents() { return m_components; }
	public DataType getComponentType() { return m_componentType; }
	
	public static DataType s_getType(String type) {
		//Make it upper case
		type = type.toUpperCase();
		for (DataType dt : values()) {
			if (dt.toString().equals(type)) return dt;
		}
		return null;
	}
}
