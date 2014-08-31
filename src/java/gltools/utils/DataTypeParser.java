package gltools.utils;

import gltools.shader.DataType;
import gltools.vector.Matrix2f;
import gltools.vector.Matrix4f;
import gltools.vector.Vector2f;
import gltools.vector.Vector3f;
import gltools.vector.Vector4f;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class DataTypeParser {
	@SuppressWarnings("unused")
	private static Object s_parseDataType(String data) {
		String[] firstParenthesis = data.split("(");
		
		String type = firstParenthesis[0];
		String value = firstParenthesis[1].split(")")[0];
		
		DataType dataType = DataType.s_getType(type.toUpperCase());
		switch(dataType) {
		case FLOAT: return Float.parseFloat(value);
		case INT: return Integer.parseInt(value);
		case VEC2: return s_parseVec2f(value);
		case VEC3: return s_parseVec3f(value);
		case VEC4: return s_parseVec4f(value);
		case MAT2: return s_parseMat2f(value);
		case MAT3: break;
		case MAT4: return s_parseMat4f(value);
		default: return null;
		}
		return null;
	}
	private static Vector2f s_parseVec2f(String value) {
		String[] coords = value.split(",");
		return new Vector2f(Float.parseFloat(coords[0].trim()), 
							 Float.parseFloat(coords[1].trim()));
	}
	private static Vector3f s_parseVec3f(String value) {
		String[] coords = value.split(",");
		return new Vector3f(Float.parseFloat(coords[0].trim()),
							 Float.parseFloat(coords[1].trim()),
							 Float.parseFloat(coords[2].trim()));
	}
	private static Vector4f s_parseVec4f(String value) {
		String[] coords = value.split(",");
		return new Vector4f(Float.parseFloat(coords[0].trim()),
							 Float.parseFloat(coords[1].trim()),
							 Float.parseFloat(coords[2].trim()),
							 Float.parseFloat(coords[3].trim()));
	}
	private static Matrix4f s_parseMat4f(String value) {	
		String[] coords = value.split(",");
		float[] floats = s_parseFloats(coords);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
		buffer.put(floats);
		buffer.flip();
		Matrix4f mat = new Matrix4f();
		mat.load(buffer);
		return mat;
	}
	private static Matrix2f s_parseMat2f(String value) {	
		String[] coords = value.split(",");
		float[] floats = s_parseFloats(coords);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
		buffer.put(floats);
		buffer.flip();
		Matrix2f mat = new Matrix2f();
		mat.load(buffer);
		return mat;
	}
	private static float[] s_parseFloats(String[] coords) {
		float[] floats = new float[coords.length];
		for (int i = 0; i < coords.length; i++) {
			floats[i] = Float.parseFloat(coords[i].trim());
		}
		return floats;
	}
}
