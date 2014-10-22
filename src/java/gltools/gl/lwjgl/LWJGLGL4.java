package gltools.gl.lwjgl;

import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GL42;
import org.lwjgl.opengl.GL41;
import org.lwjgl.opengl.GL40;
import gltools.gl.Pointer;
import org.lwjgl.opengl.GL44;
import gltools.gl.PointerBuffer;
import gltools.gl.DebugCallback;

public class LWJGLGL4 {
	public void glBeginQueryIndexed(int target, int index, int id) {
		GL40.glBeginQueryIndexed(target, index, id);
	}
	public void glBindTransformFeedback(int target, int id) {
		GL40.glBindTransformFeedback(target, id);
	}
	public void glBlendEquationi(int buf, int mode) {
		GL40.glBlendEquationi(buf, mode);
	}
	public void glBlendEquationSeparatei(int buf, int modeRGB, int modeAlpha) {
		GL40.glBlendEquationSeparatei(buf, modeRGB, modeAlpha);
	}
	public void glBlendFunci(int buf, int src, int dst) {
		GL40.glBlendFunci(buf, src, dst);
	}
	public void glBlendFuncSeparatei(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
		GL40.glBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha);
	}
	public void glDeleteTransformFeedbacks(int id) {
		GL40.glDeleteTransformFeedbacks(id);
	}
	public void glDeleteTransformFeedbacks(java.nio.IntBuffer ids) {
		GL40.glDeleteTransformFeedbacks(ids);
	}
	public void glDrawArraysIndirect(int mode, java.nio.ByteBuffer indirect) {
		GL40.glDrawArraysIndirect(mode, indirect);
	}
	public void glDrawArraysIndirect(int mode, java.nio.IntBuffer indirect) {
		GL40.glDrawArraysIndirect(mode, indirect);
	}
	public void glDrawArraysIndirect(int mode, long indirect_buffer_offset) {
		GL40.glDrawArraysIndirect(mode, indirect_buffer_offset);
	}
	public void glDrawElementsIndirect(int mode, int type, java.nio.ByteBuffer indirect) {
		GL40.glDrawElementsIndirect(mode, type, indirect);
	}
	public void glDrawElementsIndirect(int mode, int type, java.nio.IntBuffer indirect) {
		GL40.glDrawElementsIndirect(mode, type, indirect);
	}
	public void glDrawElementsIndirect(int mode, int type, long indirect_buffer_offset) {
		GL40.glDrawElementsIndirect(mode, type, indirect_buffer_offset);
	}
	public void glDrawTransformFeedback(int mode, int id) {
		GL40.glDrawTransformFeedback(mode, id);
	}
	public void glDrawTransformFeedbackStream(int mode, int id, int stream) {
		GL40.glDrawTransformFeedbackStream(mode, id, stream);
	}
	public void glEndQueryIndexed(int target, int index) {
		GL40.glEndQueryIndexed(target, index);
	}
	public int glGenTransformFeedbacks() {
		return GL40.glGenTransformFeedbacks();
	}
	public void glGenTransformFeedbacks(java.nio.IntBuffer ids) {
		GL40.glGenTransformFeedbacks(ids);
	}
	public java.lang.String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize) {
		return GL40.glGetActiveSubroutineName(program, shadertype, index, bufsize);
	}
	public void glGetActiveSubroutineName(int program, int shadertype, int index, java.nio.IntBuffer length, java.nio.ByteBuffer name) {
		GL40.glGetActiveSubroutineName(program, shadertype, index, length, name);
	}
	public int glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname) {
		return GL40.glGetActiveSubroutineUniform(program, shadertype, index, pname);
	}
	public void glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname, java.nio.IntBuffer values) {
		GL40.glGetActiveSubroutineUniform(program, shadertype, index, pname, values);
	}
	public int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname) {
		return GL40.glGetActiveSubroutineUniformi(program, shadertype, index, pname);
	}
	public java.lang.String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize) {
		return GL40.glGetActiveSubroutineUniformName(program, shadertype, index, bufsize);
	}
	public void glGetActiveSubroutineUniformName(int program, int shadertype, int index, java.nio.IntBuffer length, java.nio.ByteBuffer name) {
		GL40.glGetActiveSubroutineUniformName(program, shadertype, index, length, name);
	}
	public int glGetProgramStage(int program, int shadertype, int pname) {
		return GL40.glGetProgramStage(program, shadertype, pname);
	}
	public void glGetProgramStage(int program, int shadertype, int pname, java.nio.IntBuffer values) {
		GL40.glGetProgramStage(program, shadertype, pname, values);
	}
	public int glGetProgramStagei(int program, int shadertype, int pname) {
		return GL40.glGetProgramStagei(program, shadertype, pname);
	}
	public int glGetQueryIndexed(int target, int index, int pname) {
		return GL40.glGetQueryIndexed(target, index, pname);
	}
	public void glGetQueryIndexed(int target, int index, int pname, java.nio.IntBuffer params) {
		GL40.glGetQueryIndexed(target, index, pname, params);
	}
	public int glGetQueryIndexedi(int target, int index, int pname) {
		return GL40.glGetQueryIndexedi(target, index, pname);
	}
	public int glGetSubroutineIndex(int program, int shadertype, java.nio.ByteBuffer name) {
		return GL40.glGetSubroutineIndex(program, shadertype, name);
	}
	public int glGetSubroutineIndex(int program, int shadertype, java.lang.CharSequence name) {
		return GL40.glGetSubroutineIndex(program, shadertype, name);
	}
	public int glGetSubroutineUniformLocation(int program, int shadertype, java.nio.ByteBuffer name) {
		return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
	}
	public int glGetSubroutineUniformLocation(int program, int shadertype, java.lang.CharSequence name) {
		return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
	}
	public void glGetUniform(int program, int location, java.nio.DoubleBuffer params) {
		GL40.glGetUniform(program, location, params);
	}
	public int glGetUniformSubroutineu(int shadertype, int location) {
		return GL40.glGetUniformSubroutineu(shadertype, location);
	}
	public void glGetUniformSubroutineu(int shadertype, int location, java.nio.IntBuffer params) {
		GL40.glGetUniformSubroutineu(shadertype, location, params);
	}
	public int glGetUniformSubroutineui(int shadertype, int location) {
		return GL40.glGetUniformSubroutineui(shadertype, location);
	}
	public boolean glIsTransformFeedback(int id) {
		return GL40.glIsTransformFeedback(id);
	}
	public void glMinSampleShading(float value) {
		GL40.glMinSampleShading(value);
	}
	public void glPatchParameter(int pname, java.nio.FloatBuffer values) {
		GL40.glPatchParameter(pname, values);
	}
	public void glPatchParameteri(int pname, int value) {
		GL40.glPatchParameteri(pname, value);
	}
	public void glPauseTransformFeedback() {
		GL40.glPauseTransformFeedback();
	}
	public void glResumeTransformFeedback() {
		GL40.glResumeTransformFeedback();
	}
	public void glUniform1(int location, java.nio.DoubleBuffer value) {
		GL40.glUniform1(location, value);
	}
	public void glUniform1d(int location, double x) {
		GL40.glUniform1d(location, x);
	}
	public void glUniform2(int location, java.nio.DoubleBuffer value) {
		GL40.glUniform2(location, value);
	}
	public void glUniform2d(int location, double x, double y) {
		GL40.glUniform2d(location, x, y);
	}
	public void glUniform3(int location, java.nio.DoubleBuffer value) {
		GL40.glUniform3(location, value);
	}
	public void glUniform3d(int location, double x, double y, double z) {
		GL40.glUniform3d(location, x, y, z);
	}
	public void glUniform4(int location, java.nio.DoubleBuffer value) {
		GL40.glUniform4(location, value);
	}
	public void glUniform4d(int location, double x, double y, double z, double w) {
		GL40.glUniform4d(location, x, y, z, w);
	}
	public void glUniformMatrix2(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix2(location, transpose, value);
	}
	public void glUniformMatrix2x3(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix2x3(location, transpose, value);
	}
	public void glUniformMatrix2x4(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix2x4(location, transpose, value);
	}
	public void glUniformMatrix3(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix3(location, transpose, value);
	}
	public void glUniformMatrix3x2(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix3x2(location, transpose, value);
	}
	public void glUniformMatrix3x4(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix3x4(location, transpose, value);
	}
	public void glUniformMatrix4(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix4(location, transpose, value);
	}
	public void glUniformMatrix4x2(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix4x2(location, transpose, value);
	}
	public void glUniformMatrix4x3(int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL40.glUniformMatrix4x3(location, transpose, value);
	}
	public void glUniformSubroutinesu(int shadertype, java.nio.IntBuffer indices) {
		GL40.glUniformSubroutinesu(shadertype, indices);
	}
	public void glActiveShaderProgram(int pipeline, int program) {
		GL41.glActiveShaderProgram(pipeline, program);
	}
	public void glBindProgramPipeline(int pipeline) {
		GL41.glBindProgramPipeline(pipeline);
	}
	public void glClearDepthf(float d) {
		GL41.glClearDepthf(d);
	}
	public int glCreateShaderProgram(int type, java.nio.ByteBuffer string) {
		return GL41.glCreateShaderProgram(type, string);
	}
	public int glCreateShaderProgram(int type, java.nio.ByteBuffer[] strings) {
		return GL41.glCreateShaderProgram(type, strings);
	}
	public int glCreateShaderProgram(int type, java.lang.CharSequence string) {
		return GL41.glCreateShaderProgram(type, string);
	}
	public int glCreateShaderProgram(int type, java.lang.CharSequence[] strings) {
		return GL41.glCreateShaderProgram(type, strings);
	}
	public int glCreateShaderProgram(int type, int count, java.nio.ByteBuffer strings) {
		return GL41.glCreateShaderProgram(type, count, strings);
	}
	public void glDeleteProgramPipelines(int pipeline) {
		GL41.glDeleteProgramPipelines(pipeline);
	}
	public void glDeleteProgramPipelines(java.nio.IntBuffer pipelines) {
		GL41.glDeleteProgramPipelines(pipelines);
	}
	public void glDepthRangeArray(int first, java.nio.DoubleBuffer v) {
		GL41.glDepthRangeArray(first, v);
	}
	public void glDepthRangef(float n, float f) {
		GL41.glDepthRangef(n, f);
	}
	public void glDepthRangeIndexed(int index, double n, double f) {
		GL41.glDepthRangeIndexed(index, n, f);
	}
	public int glGenProgramPipelines() {
		return GL41.glGenProgramPipelines();
	}
	public void glGenProgramPipelines(java.nio.IntBuffer pipelines) {
		GL41.glGenProgramPipelines(pipelines);
	}
	public double glGetDouble(int target, int index) {
		return GL41.glGetDouble(target, index);
	}
	public void glGetDouble(int target, int index, java.nio.DoubleBuffer data) {
		GL41.glGetDouble(target, index, data);
	}
	public float glGetFloat(int target, int index) {
		return GL41.glGetFloat(target, index);
	}
	public void glGetFloat(int target, int index, java.nio.FloatBuffer data) {
		GL41.glGetFloat(target, index, data);
	}
	public void glGetProgramBinary(int program, java.nio.IntBuffer length, java.nio.IntBuffer binaryFormat, java.nio.ByteBuffer binary) {
		GL41.glGetProgramBinary(program, length, binaryFormat, binary);
	}
	public void glGetProgramPipeline(int pipeline, int pname, java.nio.IntBuffer params) {
		GL41.glGetProgramPipeline(pipeline, pname, params);
	}
	public int glGetProgramPipelinei(int pipeline, int pname) {
		return GL41.glGetProgramPipelinei(pipeline, pname);
	}
	public java.lang.String glGetProgramPipelineInfoLog(int pipeline, int bufSize) {
		return GL41.glGetProgramPipelineInfoLog(pipeline, bufSize);
	}
	public void glGetProgramPipelineInfoLog(int pipeline, java.nio.IntBuffer length, java.nio.ByteBuffer infoLog) {
		GL41.glGetProgramPipelineInfoLog(pipeline, length, infoLog);
	}
	public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, java.nio.IntBuffer range, java.nio.IntBuffer precision) {
		GL41.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
	}
	public void glGetVertexAttribL(int index, int pname, java.nio.DoubleBuffer params) {
		GL41.glGetVertexAttribL(index, pname, params);
	}
	public boolean glIsProgramPipeline(int pipeline) {
		return GL41.glIsProgramPipeline(pipeline);
	}
	public void glProgramBinary(int program, int binaryFormat, java.nio.ByteBuffer binary) {
		GL41.glProgramBinary(program, binaryFormat, binary);
	}
	public void glProgramParameteri(int program, int pname, int value) {
		GL41.glProgramParameteri(program, pname, value);
	}
	public void glProgramUniform1(int program, int location, java.nio.DoubleBuffer value) {
		GL41.glProgramUniform1(program, location, value);
	}
	public void glProgramUniform1(int program, int location, java.nio.FloatBuffer value) {
		GL41.glProgramUniform1(program, location, value);
	}
	public void glProgramUniform1(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform1(program, location, value);
	}
	public void glProgramUniform1d(int program, int location, double v0) {
		GL41.glProgramUniform1d(program, location, v0);
	}
	public void glProgramUniform1f(int program, int location, float v0) {
		GL41.glProgramUniform1f(program, location, v0);
	}
	public void glProgramUniform1i(int program, int location, int v0) {
		GL41.glProgramUniform1i(program, location, v0);
	}
	public void glProgramUniform1u(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform1u(program, location, value);
	}
	public void glProgramUniform1ui(int program, int location, int v0) {
		GL41.glProgramUniform1ui(program, location, v0);
	}
	public void glProgramUniform2(int program, int location, java.nio.DoubleBuffer value) {
		GL41.glProgramUniform2(program, location, value);
	}
	public void glProgramUniform2(int program, int location, java.nio.FloatBuffer value) {
		GL41.glProgramUniform2(program, location, value);
	}
	public void glProgramUniform2(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform2(program, location, value);
	}
	public void glProgramUniform2d(int program, int location, double v0, double v1) {
		GL41.glProgramUniform2d(program, location, v0, v1);
	}
	public void glProgramUniform2f(int program, int location, float v0, float v1) {
		GL41.glProgramUniform2f(program, location, v0, v1);
	}
	public void glProgramUniform2i(int program, int location, int v0, int v1) {
		GL41.glProgramUniform2i(program, location, v0, v1);
	}
	public void glProgramUniform2u(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform2u(program, location, value);
	}
	public void glProgramUniform2ui(int program, int location, int v0, int v1) {
		GL41.glProgramUniform2ui(program, location, v0, v1);
	}
	public void glProgramUniform3(int program, int location, java.nio.DoubleBuffer value) {
		GL41.glProgramUniform3(program, location, value);
	}
	public void glProgramUniform3(int program, int location, java.nio.FloatBuffer value) {
		GL41.glProgramUniform3(program, location, value);
	}
	public void glProgramUniform3(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform3(program, location, value);
	}
	public void glProgramUniform3d(int program, int location, double v0, double v1, double v2) {
		GL41.glProgramUniform3d(program, location, v0, v1, v2);
	}
	public void glProgramUniform3f(int program, int location, float v0, float v1, float v2) {
		GL41.glProgramUniform3f(program, location, v0, v1, v2);
	}
	public void glProgramUniform3i(int program, int location, int v0, int v1, int v2) {
		GL41.glProgramUniform3i(program, location, v0, v1, v2);
	}
	public void glProgramUniform3u(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform3u(program, location, value);
	}
	public void glProgramUniform3ui(int program, int location, int v0, int v1, int v2) {
		GL41.glProgramUniform3ui(program, location, v0, v1, v2);
	}
	public void glProgramUniform4(int program, int location, java.nio.DoubleBuffer value) {
		GL41.glProgramUniform4(program, location, value);
	}
	public void glProgramUniform4(int program, int location, java.nio.FloatBuffer value) {
		GL41.glProgramUniform4(program, location, value);
	}
	public void glProgramUniform4(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform4(program, location, value);
	}
	public void glProgramUniform4d(int program, int location, double v0, double v1, double v2, double v3) {
		GL41.glProgramUniform4d(program, location, v0, v1, v2, v3);
	}
	public void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3) {
		GL41.glProgramUniform4f(program, location, v0, v1, v2, v3);
	}
	public void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3) {
		GL41.glProgramUniform4i(program, location, v0, v1, v2, v3);
	}
	public void glProgramUniform4u(int program, int location, java.nio.IntBuffer value) {
		GL41.glProgramUniform4u(program, location, value);
	}
	public void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3) {
		GL41.glProgramUniform4ui(program, location, v0, v1, v2, v3);
	}
	public void glProgramUniformMatrix2(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix2(program, location, transpose, value);
	}
	public void glProgramUniformMatrix2(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix2(program, location, transpose, value);
	}
	public void glProgramUniformMatrix2x3(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix2x3(program, location, transpose, value);
	}
	public void glProgramUniformMatrix2x3(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix2x3(program, location, transpose, value);
	}
	public void glProgramUniformMatrix2x4(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix2x4(program, location, transpose, value);
	}
	public void glProgramUniformMatrix2x4(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix2x4(program, location, transpose, value);
	}
	public void glProgramUniformMatrix3(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix3(program, location, transpose, value);
	}
	public void glProgramUniformMatrix3(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix3(program, location, transpose, value);
	}
	public void glProgramUniformMatrix3x2(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix3x2(program, location, transpose, value);
	}
	public void glProgramUniformMatrix3x2(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix3x2(program, location, transpose, value);
	}
	public void glProgramUniformMatrix3x4(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix3x4(program, location, transpose, value);
	}
	public void glProgramUniformMatrix3x4(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix3x4(program, location, transpose, value);
	}
	public void glProgramUniformMatrix4(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix4(program, location, transpose, value);
	}
	public void glProgramUniformMatrix4(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix4(program, location, transpose, value);
	}
	public void glProgramUniformMatrix4x2(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix4x2(program, location, transpose, value);
	}
	public void glProgramUniformMatrix4x2(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix4x2(program, location, transpose, value);
	}
	public void glProgramUniformMatrix4x3(int program, int location, boolean transpose, java.nio.DoubleBuffer value) {
		GL41.glProgramUniformMatrix4x3(program, location, transpose, value);
	}
	public void glProgramUniformMatrix4x3(int program, int location, boolean transpose, java.nio.FloatBuffer value) {
		GL41.glProgramUniformMatrix4x3(program, location, transpose, value);
	}
	public void glReleaseShaderCompiler() {
		GL41.glReleaseShaderCompiler();
	}
	public void glScissorArray(int first, java.nio.IntBuffer v) {
		GL41.glScissorArray(first, v);
	}
	public void glScissorIndexed(int index, java.nio.IntBuffer v) {
		GL41.glScissorIndexed(index, v);
	}
	public void glScissorIndexed(int index, int left, int bottom, int width, int height) {
		GL41.glScissorIndexed(index, left, bottom, width, height);
	}
	public void glShaderBinary(java.nio.IntBuffer shaders, int binaryformat, java.nio.ByteBuffer binary) {
		GL41.glShaderBinary(shaders, binaryformat, binary);
	}
	public void glUseProgramStages(int pipeline, int stages, int program) {
		GL41.glUseProgramStages(pipeline, stages, program);
	}
	public void glValidateProgramPipeline(int pipeline) {
		GL41.glValidateProgramPipeline(pipeline);
	}
	public void glVertexAttribL1(int index, java.nio.DoubleBuffer v) {
		GL41.glVertexAttribL1(index, v);
	}
	public void glVertexAttribL1d(int index, double x) {
		GL41.glVertexAttribL1d(index, x);
	}
	public void glVertexAttribL2(int index, java.nio.DoubleBuffer v) {
		GL41.glVertexAttribL2(index, v);
	}
	public void glVertexAttribL2d(int index, double x, double y) {
		GL41.glVertexAttribL2d(index, x, y);
	}
	public void glVertexAttribL3(int index, java.nio.DoubleBuffer v) {
		GL41.glVertexAttribL3(index, v);
	}
	public void glVertexAttribL3d(int index, double x, double y, double z) {
		GL41.glVertexAttribL3d(index, x, y, z);
	}
	public void glVertexAttribL4(int index, java.nio.DoubleBuffer v) {
		GL41.glVertexAttribL4(index, v);
	}
	public void glVertexAttribL4d(int index, double x, double y, double z, double w) {
		GL41.glVertexAttribL4d(index, x, y, z, w);
	}
	public void glVertexAttribLPointer(int index, int size, int stride, java.nio.DoubleBuffer pointer) {
		GL41.glVertexAttribLPointer(index, size, stride, pointer);
	}
	public void glVertexAttribLPointer(int index, int size, int stride, long pointer_buffer_offset) {
		GL41.glVertexAttribLPointer(index, size, stride, pointer_buffer_offset);
	}
	public void glViewportArray(int first, java.nio.FloatBuffer v) {
		GL41.glViewportArray(first, v);
	}
	public void glViewportIndexed(int index, java.nio.FloatBuffer v) {
		GL41.glViewportIndexed(index, v);
	}
	public void glViewportIndexedf(int index, float x, float y, float w, float h) {
		GL41.glViewportIndexedf(index, x, y, w, h);
	}
	public void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer, int access, int format) {
		GL42.glBindImageTexture(unit, texture, level, layered, layer, access, format);
	}
	public void glDrawArraysInstancedBaseInstance(int mode, int first, int count, int primcount, int baseinstance) {
		GL42.glDrawArraysInstancedBaseInstance(mode, first, count, primcount, baseinstance);
	}
	public void glDrawElementsInstancedBaseInstance(int mode, java.nio.ByteBuffer indices, int primcount, int baseinstance) {
		GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
	}
	public void glDrawElementsInstancedBaseInstance(int mode, java.nio.IntBuffer indices, int primcount, int baseinstance) {
		GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
	}
	public void glDrawElementsInstancedBaseInstance(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int baseinstance) {
		GL42.glDrawElementsInstancedBaseInstance(mode, indices_count, type, indices_buffer_offset, primcount, baseinstance);
	}
	public void glDrawElementsInstancedBaseInstance(int mode, java.nio.ShortBuffer indices, int primcount, int baseinstance) {
		GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
	}
	public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, java.nio.ByteBuffer indices, int primcount, int basevertex, int baseinstance) {
		GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
	}
	public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, java.nio.IntBuffer indices, int primcount, int basevertex, int baseinstance) {
		GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
	}
	public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex, int baseinstance) {
		GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices_count, type, indices_buffer_offset, primcount, basevertex, baseinstance);
	}
	public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, java.nio.ShortBuffer indices, int primcount, int basevertex, int baseinstance) {
		GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
	}
	public void glDrawTransformFeedbackInstanced(int mode, int id, int primcount) {
		GL42.glDrawTransformFeedbackInstanced(mode, id, primcount);
	}
	public void glDrawTransformFeedbackStreamInstanced(int mode, int id, int stream, int primcount) {
		GL42.glDrawTransformFeedbackStreamInstanced(mode, id, stream, primcount);
	}
	public int glGetActiveAtomicCounterBuffer(int program, int bufferIndex, int pname) {
		return GL42.glGetActiveAtomicCounterBuffer(program, bufferIndex, pname);
	}
	public void glGetActiveAtomicCounterBuffer(int program, int bufferIndex, int pname, java.nio.IntBuffer params) {
		GL42.glGetActiveAtomicCounterBuffer(program, bufferIndex, pname, params);
	}
	public int glGetInternalformat(int target, int internalformat, int pname) {
		return GL42.glGetInternalformat(target, internalformat, pname);
	}
	public void glGetInternalformat(int target, int internalformat, int pname, java.nio.IntBuffer params) {
		GL42.glGetInternalformat(target, internalformat, pname, params);
	}
	public void glMemoryBarrier(int barriers) {
		GL42.glMemoryBarrier(barriers);
	}
	public void glTexStorage1D(int target, int levels, int internalformat, int width) {
		GL42.glTexStorage1D(target, levels, internalformat, width);
	}
	public void glTexStorage2D(int target, int levels, int internalformat, int width, int height) {
		GL42.glTexStorage2D(target, levels, internalformat, width, height);
	}
	public void glTexStorage3D(int target, int levels, int internalformat, int width, int height, int depth) {
		GL42.glTexStorage3D(target, levels, internalformat, width, height, depth);
	}
	public void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride) {
		GL43.glBindVertexBuffer(bindingindex, buffer, offset, stride);
	}
	public void glClearBufferData(int target, int internalformat, int format, int type, java.nio.ByteBuffer data) {
		GL43.glClearBufferData(target, internalformat, format, type, data);
	}
	public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, java.nio.ByteBuffer data) {
		GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
	}
	public void glCopyImageSubData(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth) {
		GL43.glCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth, srcHeight, srcDepth);
	}
	public void glDebugMessageCallback(DebugCallback callback) {
		GL43.glDebugMessageCallback(LWJGLUtils.s_wrapDebugCallback(callback));
	}
	public void glDebugMessageControl(int source, int type, int severity, java.nio.IntBuffer ids, boolean enabled) {
		GL43.glDebugMessageControl(source, type, severity, ids, enabled);
	}
	public void glDebugMessageInsert(int source, int type, int id, int severity, java.nio.ByteBuffer buf) {
		GL43.glDebugMessageInsert(source, type, id, severity, buf);
	}
	public void glDebugMessageInsert(int source, int type, int id, int severity, java.lang.CharSequence buf) {
		GL43.glDebugMessageInsert(source, type, id, severity, buf);
	}
	public void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z) {
		GL43.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
	}
	public void glDispatchComputeIndirect(long indirect) {
		GL43.glDispatchComputeIndirect(indirect);
	}
	public void glFramebufferParameteri(int target, int pname, int param) {
		GL43.glFramebufferParameteri(target, pname, param);
	}
	public int glGetDebugMessageLog(int count, java.nio.IntBuffer sources, java.nio.IntBuffer types, java.nio.IntBuffer ids, java.nio.IntBuffer severities, java.nio.IntBuffer lengths, java.nio.ByteBuffer messageLog) {
		return GL43.glGetDebugMessageLog(count, sources, types, ids, severities, lengths, messageLog);
	}
	public void glGetFramebufferParameter(int target, int pname, java.nio.IntBuffer params) {
		GL43.glGetFramebufferParameter(target, pname, params);
	}
	public int glGetFramebufferParameteri(int target, int pname) {
		return GL43.glGetFramebufferParameteri(target, pname);
	}
	public void glGetInternalformat(int target, int internalformat, int pname, java.nio.LongBuffer params) {
		GL43.glGetInternalformat(target, internalformat, pname, params);
	}
	public long glGetInternalformati64(int target, int internalformat, int pname) {
		return GL43.glGetInternalformati64(target, internalformat, pname);
	}
	public java.lang.String glGetObjectLabel(int identifier, int name, int bufSize) {
		return GL43.glGetObjectLabel(identifier, name, bufSize);
	}
	public void glGetObjectLabel(int identifier, int name, java.nio.IntBuffer length, java.nio.ByteBuffer label) {
		GL43.glGetObjectLabel(identifier, name, length, label);
	}
	public java.lang.String glGetObjectPtrLabel(Pointer ptr, int bufSize) {
		return GL43.glGetObjectPtrLabel(LWJGLUtils.s_wrapPointer(ptr), bufSize);
	}
	public void glGetObjectPtrLabel(Pointer ptr, java.nio.IntBuffer length, java.nio.ByteBuffer label) {
		GL43.glGetObjectPtrLabel(LWJGLUtils.s_wrapPointer(ptr), length, label);
	}
	public void glGetProgramInterface(int program, int programInterface, int pname, java.nio.IntBuffer params) {
		GL43.glGetProgramInterface(program, programInterface, pname, params);
	}
	public int glGetProgramInterfacei(int program, int programInterface, int pname) {
		return GL43.glGetProgramInterfacei(program, programInterface, pname);
	}
	public void glGetProgramResource(int program, int programInterface, int index, java.nio.IntBuffer props, java.nio.IntBuffer length, java.nio.IntBuffer params) {
		GL43.glGetProgramResource(program, programInterface, index, props, length, params);
	}
	public int glGetProgramResourceIndex(int program, int programInterface, java.nio.ByteBuffer name) {
		return GL43.glGetProgramResourceIndex(program, programInterface, name);
	}
	public int glGetProgramResourceIndex(int program, int programInterface, java.lang.CharSequence name) {
		return GL43.glGetProgramResourceIndex(program, programInterface, name);
	}
	public int glGetProgramResourceLocation(int program, int programInterface, java.nio.ByteBuffer name) {
		return GL43.glGetProgramResourceLocation(program, programInterface, name);
	}
	public int glGetProgramResourceLocation(int program, int programInterface, java.lang.CharSequence name) {
		return GL43.glGetProgramResourceLocation(program, programInterface, name);
	}
	public int glGetProgramResourceLocationIndex(int program, int programInterface, java.nio.ByteBuffer name) {
		return GL43.glGetProgramResourceLocationIndex(program, programInterface, name);
	}
	public int glGetProgramResourceLocationIndex(int program, int programInterface, java.lang.CharSequence name) {
		return GL43.glGetProgramResourceLocationIndex(program, programInterface, name);
	}
	public java.lang.String glGetProgramResourceName(int program, int programInterface, int index, int bufSize) {
		return GL43.glGetProgramResourceName(program, programInterface, index, bufSize);
	}
	public void glGetProgramResourceName(int program, int programInterface, int index, java.nio.IntBuffer length, java.nio.ByteBuffer name) {
		GL43.glGetProgramResourceName(program, programInterface, index, length, name);
	}
	public void glInvalidateBufferData(int buffer) {
		GL43.glInvalidateBufferData(buffer);
	}
	public void glInvalidateBufferSubData(int buffer, long offset, long length) {
		GL43.glInvalidateBufferSubData(buffer, offset, length);
	}
	public void glInvalidateFramebuffer(int target, java.nio.IntBuffer attachments) {
		GL43.glInvalidateFramebuffer(target, attachments);
	}
	public void glInvalidateSubFramebuffer(int target, java.nio.IntBuffer attachments, int x, int y, int width, int height) {
		GL43.glInvalidateSubFramebuffer(target, attachments, x, y, width, height);
	}
	public void glInvalidateTexImage(int texture, int level) {
		GL43.glInvalidateTexImage(texture, level);
	}
	public void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth) {
		GL43.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth);
	}
	public void glMultiDrawArraysIndirect(int mode, java.nio.ByteBuffer indirect, int primcount, int stride) {
		GL43.glMultiDrawArraysIndirect(mode, indirect, primcount, stride);
	}
	public void glMultiDrawArraysIndirect(int mode, java.nio.IntBuffer indirect, int primcount, int stride) {
		GL43.glMultiDrawArraysIndirect(mode, indirect, primcount, stride);
	}
	public void glMultiDrawArraysIndirect(int mode, long indirect_buffer_offset, int primcount, int stride) {
		GL43.glMultiDrawArraysIndirect(mode, indirect_buffer_offset, primcount, stride);
	}
	public void glMultiDrawElementsIndirect(int mode, int type, java.nio.ByteBuffer indirect, int primcount, int stride) {
		GL43.glMultiDrawElementsIndirect(mode, type, indirect, primcount, stride);
	}
	public void glMultiDrawElementsIndirect(int mode, int type, java.nio.IntBuffer indirect, int primcount, int stride) {
		GL43.glMultiDrawElementsIndirect(mode, type, indirect, primcount, stride);
	}
	public void glMultiDrawElementsIndirect(int mode, int type, long indirect_buffer_offset, int primcount, int stride) {
		GL43.glMultiDrawElementsIndirect(mode, type, indirect_buffer_offset, primcount, stride);
	}
	public void glObjectLabel(int identifier, int name, java.nio.ByteBuffer label) {
		GL43.glObjectLabel(identifier, name, label);
	}
	public void glObjectLabel(int identifier, int name, java.lang.CharSequence label) {
		GL43.glObjectLabel(identifier, name, label);
	}
	public void glObjectPtrLabel(Pointer ptr, java.nio.ByteBuffer label) {
		GL43.glObjectPtrLabel(LWJGLUtils.s_wrapPointer(ptr), label);
	}
	public void glObjectPtrLabel(Pointer ptr, java.lang.CharSequence label) {
		GL43.glObjectPtrLabel(LWJGLUtils.s_wrapPointer(ptr), label);
	}
	public void glPopDebugGroup() {
		GL43.glPopDebugGroup();
	}
	public void glPushDebugGroup(int source, int id, java.nio.ByteBuffer message) {
		GL43.glPushDebugGroup(source, id, message);
	}
	public void glPushDebugGroup(int source, int id, java.lang.CharSequence message) {
		GL43.glPushDebugGroup(source, id, message);
	}
	public void glShaderStorageBlockBinding(int program, int storageBlockIndex, int storageBlockBinding) {
		GL43.glShaderStorageBlockBinding(program, storageBlockIndex, storageBlockBinding);
	}
	public void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size) {
		GL43.glTexBufferRange(target, internalformat, buffer, offset, size);
	}
	public void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
		GL43.glTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
	}
	public void glTexStorage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
		GL43.glTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
	}
	public void glTextureView(int texture, int target, int origtexture, int internalformat, int minlevel, int numlevels, int minlayer, int numlayers) {
		GL43.glTextureView(texture, target, origtexture, internalformat, minlevel, numlevels, minlayer, numlayers);
	}
	public void glVertexAttribBinding(int attribindex, int bindingindex) {
		GL43.glVertexAttribBinding(attribindex, bindingindex);
	}
	public void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized, int relativeoffset) {
		GL43.glVertexAttribFormat(attribindex, size, type, normalized, relativeoffset);
	}
	public void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) {
		GL43.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
	}
	public void glVertexAttribLFormat(int attribindex, int size, int type, int relativeoffset) {
		GL43.glVertexAttribLFormat(attribindex, size, type, relativeoffset);
	}
	public void glVertexBindingDivisor(int bindingindex, int divisor) {
		GL43.glVertexBindingDivisor(bindingindex, divisor);
	}
	public void glBindBuffersBase(int target, int first, int count, java.nio.IntBuffer buffers) {
		GL44.glBindBuffersBase(target, first, count, buffers);
	}
	public void glBindBuffersRange(int target, int first, int count, java.nio.IntBuffer buffers, PointerBuffer offsets, PointerBuffer sizes) {
		GL44.glBindBuffersRange(target, first, count, buffers, LWJGLUtils.s_wrapPointerBuffer(offsets), LWJGLUtils.s_wrapPointerBuffer(sizes));
	}
	public void glBindImageTextures(int first, int count, java.nio.IntBuffer textures) {
		GL44.glBindImageTextures(first, count, textures);
	}
	public void glBindSamplers(int first, int count, java.nio.IntBuffer samplers) {
		GL44.glBindSamplers(first, count, samplers);
	}
	public void glBindTextures(int first, int count, java.nio.IntBuffer textures) {
		GL44.glBindTextures(first, count, textures);
	}
	public void glBindVertexBuffers(int first, int count, java.nio.IntBuffer buffers, PointerBuffer offsets, java.nio.IntBuffer strides) {
		GL44.glBindVertexBuffers(first, count, buffers, LWJGLUtils.s_wrapPointerBuffer(offsets), strides);
	}
	public void glBufferStorage(int target, java.nio.ByteBuffer data, int flags) {
		GL44.glBufferStorage(target, data, flags);
	}
	public void glBufferStorage(int target, java.nio.DoubleBuffer data, int flags) {
		GL44.glBufferStorage(target, data, flags);
	}
	public void glBufferStorage(int target, java.nio.FloatBuffer data, int flags) {
		GL44.glBufferStorage(target, data, flags);
	}
	public void glBufferStorage(int target, java.nio.IntBuffer data, int flags) {
		GL44.glBufferStorage(target, data, flags);
	}
	public void glBufferStorage(int target, java.nio.LongBuffer data, int flags) {
		GL44.glBufferStorage(target, data, flags);
	}
	public void glBufferStorage(int target, long size, int flags) {
		GL44.glBufferStorage(target, size, flags);
	}
	public void glBufferStorage(int target, java.nio.ShortBuffer data, int flags) {
		GL44.glBufferStorage(target, data, flags);
	}
	public void glClearTexImage(int texture, int level, int format, int type, java.nio.ByteBuffer data) {
		GL44.glClearTexImage(texture, level, format, type, data);
	}
	public void glClearTexImage(int texture, int level, int format, int type, java.nio.DoubleBuffer data) {
		GL44.glClearTexImage(texture, level, format, type, data);
	}
	public void glClearTexImage(int texture, int level, int format, int type, java.nio.FloatBuffer data) {
		GL44.glClearTexImage(texture, level, format, type, data);
	}
	public void glClearTexImage(int texture, int level, int format, int type, java.nio.IntBuffer data) {
		GL44.glClearTexImage(texture, level, format, type, data);
	}
	public void glClearTexImage(int texture, int level, int format, int type, java.nio.LongBuffer data) {
		GL44.glClearTexImage(texture, level, format, type, data);
	}
	public void glClearTexImage(int texture, int level, int format, int type, java.nio.ShortBuffer data) {
		GL44.glClearTexImage(texture, level, format, type, data);
	}
	public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.ByteBuffer data) {
		GL44.glClearTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data);
	}
	public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.DoubleBuffer data) {
		GL44.glClearTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data);
	}
	public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.FloatBuffer data) {
		GL44.glClearTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data);
	}
	public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.IntBuffer data) {
		GL44.glClearTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data);
	}
	public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.LongBuffer data) {
		GL44.glClearTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data);
	}
	public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.ShortBuffer data) {
		GL44.glClearTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data);
	}
}
