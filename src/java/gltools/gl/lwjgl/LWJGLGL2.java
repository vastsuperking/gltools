package gltools.gl.lwjgl;

import org.lwjgl.opengl.GL21;
import org.lwjgl.opengl.GL20;

public class LWJGLGL2 {
	public void glAttachShader(int program, int shader) {
		GL20.glAttachShader(program, shader);
	}
	public void glBindAttribLocation(int program, int index, java.nio.ByteBuffer name) {
		GL20.glBindAttribLocation(program, index, name);
	}
	public void glBindAttribLocation(int program, int index, java.lang.CharSequence name) {
		GL20.glBindAttribLocation(program, index, name);
	}
	public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
		GL20.glBlendEquationSeparate(modeRGB, modeAlpha);
	}
	public void glCompileShader(int shader) {
		GL20.glCompileShader(shader);
	}
	public int glCreateProgram() {
		return GL20.glCreateProgram();
	}
	public int glCreateShader(int type) {
		return GL20.glCreateShader(type);
	}
	public void glDeleteProgram(int program) {
		GL20.glDeleteProgram(program);
	}
	public void glDeleteShader(int shader) {
		GL20.glDeleteShader(shader);
	}
	public void glDetachShader(int program, int shader) {
		GL20.glDetachShader(program, shader);
	}
	public void glDisableVertexAttribArray(int index) {
		GL20.glDisableVertexAttribArray(index);
	}
	public void glDrawBuffers(int buffer) {
		GL20.glDrawBuffers(buffer);
	}
	public void glDrawBuffers(java.nio.IntBuffer buffers) {
		GL20.glDrawBuffers(buffers);
	}
	public void glEnableVertexAttribArray(int index) {
		GL20.glEnableVertexAttribArray(index);
	}
	public java.lang.String glGetActiveAttrib(int program, int index, int maxLength) {
		return GL20.glGetActiveAttrib(program, index, maxLength);
	}
	public void glGetActiveAttrib(int program, int index, java.nio.IntBuffer length, java.nio.IntBuffer size, java.nio.IntBuffer type, java.nio.ByteBuffer name) {
		GL20.glGetActiveAttrib(program, index, length, size, type, name);
	}
	public java.lang.String glGetActiveAttrib(int program, int index, int maxLength, java.nio.IntBuffer sizeType) {
		return GL20.glGetActiveAttrib(program, index, maxLength, sizeType);
	}
	public int glGetActiveAttribSize(int program, int index) {
		return GL20.glGetActiveAttribSize(program, index);
	}
	public int glGetActiveAttribType(int program, int index) {
		return GL20.glGetActiveAttribType(program, index);
	}
	public java.lang.String glGetActiveUniform(int program, int index, int maxLength) {
		return GL20.glGetActiveUniform(program, index, maxLength);
	}
	public void glGetActiveUniform(int program, int index, java.nio.IntBuffer length, java.nio.IntBuffer size, java.nio.IntBuffer type, java.nio.ByteBuffer name) {
		GL20.glGetActiveUniform(program, index, length, size, type, name);
	}
	public java.lang.String glGetActiveUniform(int program, int index, int maxLength, java.nio.IntBuffer sizeType) {
		return GL20.glGetActiveUniform(program, index, maxLength, sizeType);
	}
	public int glGetActiveUniformSize(int program, int index) {
		return GL20.glGetActiveUniformSize(program, index);
	}
	public int glGetActiveUniformType(int program, int index) {
		return GL20.glGetActiveUniformType(program, index);
	}
	public void glGetAttachedShaders(int program, java.nio.IntBuffer count, java.nio.IntBuffer shaders) {
		GL20.glGetAttachedShaders(program, count, shaders);
	}
	public int glGetAttribLocation(int program, java.nio.ByteBuffer name) {
		return GL20.glGetAttribLocation(program, name);
	}
	public int glGetAttribLocation(int program, java.lang.CharSequence name) {
		return GL20.glGetAttribLocation(program, name);
	}
	public int glGetProgram(int program, int pname) {
		return GL20.glGetProgram(program, pname);
	}
	public void glGetProgram(int program, int pname, java.nio.IntBuffer params) {
		GL20.glGetProgram(program, pname, params);
	}
	public int glGetProgrami(int program, int pname) {
		return GL20.glGetProgrami(program, pname);
	}
	public java.lang.String glGetProgramInfoLog(int program, int maxLength) {
		return GL20.glGetProgramInfoLog(program, maxLength);
	}
	public void glGetProgramInfoLog(int program, java.nio.IntBuffer length, java.nio.ByteBuffer infoLog) {
		GL20.glGetProgramInfoLog(program, length, infoLog);
	}
	public int glGetShader(int shader, int pname) {
		return GL20.glGetShader(shader, pname);
	}
	public void glGetShader(int shader, int pname, java.nio.IntBuffer params) {
		GL20.glGetShader(shader, pname, params);
	}
	public int glGetShaderi(int shader, int pname) {
		return GL20.glGetShaderi(shader, pname);
	}
	public java.lang.String glGetShaderInfoLog(int shader, int maxLength) {
		return GL20.glGetShaderInfoLog(shader, maxLength);
	}
	public void glGetShaderInfoLog(int shader, java.nio.IntBuffer length, java.nio.ByteBuffer infoLog) {
		GL20.glGetShaderInfoLog(shader, length, infoLog);
	}
	public java.lang.String glGetShaderSource(int shader, int maxLength) {
		return GL20.glGetShaderSource(shader, maxLength);
	}
	public void glGetShaderSource(int shader, java.nio.IntBuffer length, java.nio.ByteBuffer source) {
		GL20.glGetShaderSource(shader, length, source);
	}
	public void glGetUniform(int program, int location, java.nio.FloatBuffer params) {
		GL20.glGetUniform(program, location, params);
	}
	public void glGetUniform(int program, int location, java.nio.IntBuffer params) {
		GL20.glGetUniform(program, location, params);
	}
	public int glGetUniformLocation(int program, java.nio.ByteBuffer name) {
		return GL20.glGetUniformLocation(program, name);
	}
	public int glGetUniformLocation(int program, java.lang.CharSequence name) {
		return GL20.glGetUniformLocation(program, name);
	}
	public void glGetVertexAttrib(int index, int pname, java.nio.DoubleBuffer params) {
		GL20.glGetVertexAttrib(index, pname, params);
	}
	public void glGetVertexAttrib(int index, int pname, java.nio.FloatBuffer params) {
		GL20.glGetVertexAttrib(index, pname, params);
	}
	public void glGetVertexAttrib(int index, int pname, java.nio.IntBuffer params) {
		GL20.glGetVertexAttrib(index, pname, params);
	}
	public void glGetVertexAttribPointer(int index, int pname, java.nio.ByteBuffer pointer) {
		GL20.glGetVertexAttribPointer(index, pname, pointer);
	}
	public java.nio.ByteBuffer glGetVertexAttribPointer(int index, int pname, long result_size) {
		return GL20.glGetVertexAttribPointer(index, pname, result_size);
	}
	public boolean glIsProgram(int program) {
		return GL20.glIsProgram(program);
	}
	public boolean glIsShader(int shader) {
		return GL20.glIsShader(shader);
	}
	public void glLinkProgram(int program) {
		GL20.glLinkProgram(program);
	}
	public void glShaderSource(int shader, java.nio.ByteBuffer string) {
		GL20.glShaderSource(shader, string);
	}
	public void glShaderSource(int shader, java.lang.CharSequence string) {
		GL20.glShaderSource(shader, string);
	}
	public void glShaderSource(int shader, java.lang.CharSequence[] strings) {
		GL20.glShaderSource(shader, strings);
	}
	public void glStencilFuncSeparate(int face, int func, int ref, int mask) {
		GL20.glStencilFuncSeparate(face, func, ref, mask);
	}
	public void glStencilMaskSeparate(int face, int mask) {
		GL20.glStencilMaskSeparate(face, mask);
	}
	public void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		GL20.glStencilOpSeparate(face, sfail, dpfail, dppass);
	}
	public void glUniform1(int location, java.nio.FloatBuffer values) {
		GL20.glUniform1(location, values);
	}
	public void glUniform1(int location, java.nio.IntBuffer values) {
		GL20.glUniform1(location, values);
	}
	public void glUniform1f(int location, float v0) {
		GL20.glUniform1f(location, v0);
	}
	public void glUniform1i(int location, int v0) {
		GL20.glUniform1i(location, v0);
	}
	public void glUniform2(int location, java.nio.FloatBuffer values) {
		GL20.glUniform2(location, values);
	}
	public void glUniform2(int location, java.nio.IntBuffer values) {
		GL20.glUniform2(location, values);
	}
	public void glUniform2f(int location, float v0, float v1) {
		GL20.glUniform2f(location, v0, v1);
	}
	public void glUniform2i(int location, int v0, int v1) {
		GL20.glUniform2i(location, v0, v1);
	}
	public void glUniform3(int location, java.nio.FloatBuffer values) {
		GL20.glUniform3(location, values);
	}
	public void glUniform3(int location, java.nio.IntBuffer values) {
		GL20.glUniform3(location, values);
	}
	public void glUniform3f(int location, float v0, float v1, float v2) {
		GL20.glUniform3f(location, v0, v1, v2);
	}
	public void glUniform3i(int location, int v0, int v1, int v2) {
		GL20.glUniform3i(location, v0, v1, v2);
	}
	public void glUniform4(int location, java.nio.FloatBuffer values) {
		GL20.glUniform4(location, values);
	}
	public void glUniform4(int location, java.nio.IntBuffer values) {
		GL20.glUniform4(location, values);
	}
	public void glUniform4f(int location, float v0, float v1, float v2, float v3) {
		GL20.glUniform4f(location, v0, v1, v2, v3);
	}
	public void glUniform4i(int location, int v0, int v1, int v2, int v3) {
		GL20.glUniform4i(location, v0, v1, v2, v3);
	}
	public void glUniformMatrix2(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL20.glUniformMatrix2(location, transpose, matrices);
	}
	public void glUniformMatrix3(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL20.glUniformMatrix3(location, transpose, matrices);
	}
	public void glUniformMatrix4(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL20.glUniformMatrix4(location, transpose, matrices);
	}
	public void glUseProgram(int program) {
		GL20.glUseProgram(program);
	}
	public void glValidateProgram(int program) {
		GL20.glValidateProgram(program);
	}
	public void glVertexAttrib1d(int index, double x) {
		GL20.glVertexAttrib1d(index, x);
	}
	public void glVertexAttrib1f(int index, float x) {
		GL20.glVertexAttrib1f(index, x);
	}
	public void glVertexAttrib1s(int index, short x) {
		GL20.glVertexAttrib1s(index, x);
	}
	public void glVertexAttrib2d(int index, double x, double y) {
		GL20.glVertexAttrib2d(index, x, y);
	}
	public void glVertexAttrib2f(int index, float x, float y) {
		GL20.glVertexAttrib2f(index, x, y);
	}
	public void glVertexAttrib2s(int index, short x, short y) {
		GL20.glVertexAttrib2s(index, x, y);
	}
	public void glVertexAttrib3d(int index, double x, double y, double z) {
		GL20.glVertexAttrib3d(index, x, y, z);
	}
	public void glVertexAttrib3f(int index, float x, float y, float z) {
		GL20.glVertexAttrib3f(index, x, y, z);
	}
	public void glVertexAttrib3s(int index, short x, short y, short z) {
		GL20.glVertexAttrib3s(index, x, y, z);
	}
	public void glVertexAttrib4d(int index, double x, double y, double z, double w) {
		GL20.glVertexAttrib4d(index, x, y, z, w);
	}
	public void glVertexAttrib4f(int index, float x, float y, float z, float w) {
		GL20.glVertexAttrib4f(index, x, y, z, w);
	}
	public void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) {
		GL20.glVertexAttrib4Nub(index, x, y, z, w);
	}
	public void glVertexAttrib4s(int index, short x, short y, short z, short w) {
		GL20.glVertexAttrib4s(index, x, y, z, w);
	}
	public void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, java.nio.ByteBuffer buffer) {
		GL20.glVertexAttribPointer(index, size, unsigned, normalized, stride, buffer);
	}
	public void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, java.nio.IntBuffer buffer) {
		GL20.glVertexAttribPointer(index, size, unsigned, normalized, stride, buffer);
	}
	public void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, java.nio.ShortBuffer buffer) {
		GL20.glVertexAttribPointer(index, size, unsigned, normalized, stride, buffer);
	}
	public void glVertexAttribPointer(int index, int size, boolean normalized, int stride, java.nio.DoubleBuffer buffer) {
		GL20.glVertexAttribPointer(index, size, normalized, stride, buffer);
	}
	public void glVertexAttribPointer(int index, int size, boolean normalized, int stride, java.nio.FloatBuffer buffer) {
		GL20.glVertexAttribPointer(index, size, normalized, stride, buffer);
	}
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, java.nio.ByteBuffer buffer) {
		GL20.glVertexAttribPointer(index, size, type, normalized, stride, buffer);
	}
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) {
		GL20.glVertexAttribPointer(index, size, type, normalized, stride, buffer_buffer_offset);
	}
	public void glUniformMatrix2x3(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL21.glUniformMatrix2x3(location, transpose, matrices);
	}
	public void glUniformMatrix2x4(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL21.glUniformMatrix2x4(location, transpose, matrices);
	}
	public void glUniformMatrix3x2(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL21.glUniformMatrix3x2(location, transpose, matrices);
	}
	public void glUniformMatrix3x4(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL21.glUniformMatrix3x4(location, transpose, matrices);
	}
	public void glUniformMatrix4x2(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL21.glUniformMatrix4x2(location, transpose, matrices);
	}
	public void glUniformMatrix4x3(int location, boolean transpose, java.nio.FloatBuffer matrices) {
		GL21.glUniformMatrix4x3(location, transpose, matrices);
	}
}
