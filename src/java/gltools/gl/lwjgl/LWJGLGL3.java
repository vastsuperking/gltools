package gltools.gl.lwjgl;

import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;
import gltools.gl.GLSync;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL33;

public class LWJGLGL3 {
	public void glBeginConditionalRender(int id, int mode) {
		GL30.glBeginConditionalRender(id, mode);
	}
	public void glBeginTransformFeedback(int primitiveMode) {
		GL30.glBeginTransformFeedback(primitiveMode);
	}
	public void glBindBufferBase(int target, int index, int buffer) {
		GL30.glBindBufferBase(target, index, buffer);
	}
	public void glBindBufferRange(int target, int index, int buffer, long offset, long size) {
		GL30.glBindBufferRange(target, index, buffer, offset, size);
	}
	public void glBindFragDataLocation(int program, int colorNumber, java.nio.ByteBuffer name) {
		GL30.glBindFragDataLocation(program, colorNumber, name);
	}
	public void glBindFragDataLocation(int program, int colorNumber, java.lang.CharSequence name) {
		GL30.glBindFragDataLocation(program, colorNumber, name);
	}
	public void glBindFramebuffer(int target, int framebuffer) {
		GL30.glBindFramebuffer(target, framebuffer);
	}
	public void glBindRenderbuffer(int target, int renderbuffer) {
		GL30.glBindRenderbuffer(target, renderbuffer);
	}
	public void glBindVertexArray(int array) {
		GL30.glBindVertexArray(array);
	}
	public void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
		GL30.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
	}
	public int glCheckFramebufferStatus(int target) {
		return GL30.glCheckFramebufferStatus(target);
	}
	public void glClampColor(int target, int clamp) {
		GL30.glClampColor(target, clamp);
	}
	public void glClearBuffer(int buffer, int drawbuffer, java.nio.FloatBuffer value) {
		GL30.glClearBuffer(buffer, drawbuffer, value);
	}
	public void glClearBuffer(int buffer, int drawbuffer, java.nio.IntBuffer value) {
		GL30.glClearBuffer(buffer, drawbuffer, value);
	}
	public void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil) {
		GL30.glClearBufferfi(buffer, drawbuffer, depth, stencil);
	}
	public void glClearBufferu(int buffer, int drawbuffer, java.nio.IntBuffer value) {
		GL30.glClearBufferu(buffer, drawbuffer, value);
	}
	public void glColorMaski(int buf, boolean r, boolean g, boolean b, boolean a) {
		GL30.glColorMaski(buf, r, g, b, a);
	}
	public void glDeleteFramebuffers(int framebuffer) {
		GL30.glDeleteFramebuffers(framebuffer);
	}
	public void glDeleteFramebuffers(java.nio.IntBuffer framebuffers) {
		GL30.glDeleteFramebuffers(framebuffers);
	}
	public void glDeleteRenderbuffers(int renderbuffer) {
		GL30.glDeleteRenderbuffers(renderbuffer);
	}
	public void glDeleteRenderbuffers(java.nio.IntBuffer renderbuffers) {
		GL30.glDeleteRenderbuffers(renderbuffers);
	}
	public void glDeleteVertexArrays(int array) {
		GL30.glDeleteVertexArrays(array);
	}
	public void glDeleteVertexArrays(java.nio.IntBuffer arrays) {
		GL30.glDeleteVertexArrays(arrays);
	}
	public void glDisablei(int target, int index) {
		GL30.glDisablei(target, index);
	}
	public void glEnablei(int target, int index) {
		GL30.glEnablei(target, index);
	}
	public void glEndConditionalRender() {
		GL30.glEndConditionalRender();
	}
	public void glEndTransformFeedback() {
		GL30.glEndTransformFeedback();
	}
	public void glFlushMappedBufferRange(int target, long offset, long length) {
		GL30.glFlushMappedBufferRange(target, offset, length);
	}
	public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
		GL30.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
	}
	public void glFramebufferTexture1D(int target, int attachment, int textarget, int texture, int level) {
		GL30.glFramebufferTexture1D(target, attachment, textarget, texture, level);
	}
	public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
		GL30.glFramebufferTexture2D(target, attachment, textarget, texture, level);
	}
	public void glFramebufferTexture3D(int target, int attachment, int textarget, int texture, int level, int zoffset) {
		GL30.glFramebufferTexture3D(target, attachment, textarget, texture, level, zoffset);
	}
	public void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
		GL30.glFramebufferTextureLayer(target, attachment, texture, level, layer);
	}
	public void glGenerateMipmap(int target) {
		GL30.glGenerateMipmap(target);
	}
	public int glGenFramebuffers() {
		return GL30.glGenFramebuffers();
	}
	public void glGenFramebuffers(java.nio.IntBuffer framebuffers) {
		GL30.glGenFramebuffers(framebuffers);
	}
	public int glGenRenderbuffers() {
		return GL30.glGenRenderbuffers();
	}
	public void glGenRenderbuffers(java.nio.IntBuffer renderbuffers) {
		GL30.glGenRenderbuffers(renderbuffers);
	}
	public int glGenVertexArrays() {
		return GL30.glGenVertexArrays();
	}
	public void glGenVertexArrays(java.nio.IntBuffer arrays) {
		GL30.glGenVertexArrays(arrays);
	}
	public boolean glGetBoolean(int value, int index) {
		return GL30.glGetBoolean(value, index);
	}
	public void glGetBoolean(int value, int index, java.nio.ByteBuffer data) {
		GL30.glGetBoolean(value, index, data);
	}
	public int glGetFragDataLocation(int program, java.nio.ByteBuffer name) {
		return GL30.glGetFragDataLocation(program, name);
	}
	public int glGetFragDataLocation(int program, java.lang.CharSequence name) {
		return GL30.glGetFragDataLocation(program, name);
	}
	public int glGetFramebufferAttachmentParameter(int target, int attachment, int pname) {
		return GL30.glGetFramebufferAttachmentParameter(target, attachment, pname);
	}
	public void glGetFramebufferAttachmentParameter(int target, int attachment, int pname, java.nio.IntBuffer params) {
		GL30.glGetFramebufferAttachmentParameter(target, attachment, pname, params);
	}
	public int glGetFramebufferAttachmentParameteri(int target, int attachment, int pname) {
		return GL30.glGetFramebufferAttachmentParameteri(target, attachment, pname);
	}
	public int glGetInteger(int value, int index) {
		return GL30.glGetInteger(value, index);
	}
	public void glGetInteger(int value, int index, java.nio.IntBuffer data) {
		GL30.glGetInteger(value, index, data);
	}
	public int glGetRenderbufferParameter(int target, int pname) {
		return GL30.glGetRenderbufferParameter(target, pname);
	}
	public void glGetRenderbufferParameter(int target, int pname, java.nio.IntBuffer params) {
		GL30.glGetRenderbufferParameter(target, pname, params);
	}
	public int glGetRenderbufferParameteri(int target, int pname) {
		return GL30.glGetRenderbufferParameteri(target, pname);
	}
	public java.lang.String glGetStringi(int name, int index) {
		return GL30.glGetStringi(name, index);
	}
	public void glGetTexParameterI(int target, int pname, java.nio.IntBuffer params) {
		GL30.glGetTexParameterI(target, pname, params);
	}
	public int glGetTexParameterIi(int target, int pname) {
		return GL30.glGetTexParameterIi(target, pname);
	}
	public void glGetTexParameterIu(int target, int pname, java.nio.IntBuffer params) {
		GL30.glGetTexParameterIu(target, pname, params);
	}
	public int glGetTexParameterIui(int target, int pname) {
		return GL30.glGetTexParameterIui(target, pname);
	}
	public void glGetTransformFeedbackVarying(int program, int index, java.nio.IntBuffer length, java.nio.IntBuffer size, java.nio.IntBuffer type, java.nio.ByteBuffer name) {
		GL30.glGetTransformFeedbackVarying(program, index, length, size, type, name);
	}
	public java.lang.String glGetTransformFeedbackVarying(int program, int index, int bufSize, java.nio.IntBuffer size, java.nio.IntBuffer type) {
		return GL30.glGetTransformFeedbackVarying(program, index, bufSize, size, type);
	}
	public void glGetUniformu(int program, int location, java.nio.IntBuffer params) {
		GL30.glGetUniformu(program, location, params);
	}
	public void glGetVertexAttribI(int index, int pname, java.nio.IntBuffer params) {
		GL30.glGetVertexAttribI(index, pname, params);
	}
	public void glGetVertexAttribIu(int index, int pname, java.nio.IntBuffer params) {
		GL30.glGetVertexAttribIu(index, pname, params);
	}
	public boolean glIsEnabledi(int target, int index) {
		return GL30.glIsEnabledi(target, index);
	}
	public boolean glIsFramebuffer(int framebuffer) {
		return GL30.glIsFramebuffer(framebuffer);
	}
	public boolean glIsRenderbuffer(int renderbuffer) {
		return GL30.glIsRenderbuffer(renderbuffer);
	}
	public boolean glIsVertexArray(int array) {
		return GL30.glIsVertexArray(array);
	}
	public java.nio.ByteBuffer glMapBufferRange(int target, long offset, long length, int access, java.nio.ByteBuffer old_buffer) {
		return GL30.glMapBufferRange(target, offset, length, access, old_buffer);
	}
	public void glRenderbufferStorage(int target, int internalformat, int width, int height) {
		GL30.glRenderbufferStorage(target, internalformat, width, height);
	}
	public void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height) {
		GL30.glRenderbufferStorageMultisample(target, samples, internalformat, width, height);
	}
	public void glTexParameterI(int target, int pname, java.nio.IntBuffer params) {
		GL30.glTexParameterI(target, pname, params);
	}
	public void glTexParameterIi(int target, int pname, int param) {
		GL30.glTexParameterIi(target, pname, param);
	}
	public void glTexParameterIu(int target, int pname, java.nio.IntBuffer params) {
		GL30.glTexParameterIu(target, pname, params);
	}
	public void glTexParameterIui(int target, int pname, int param) {
		GL30.glTexParameterIui(target, pname, param);
	}
	public void glTransformFeedbackVaryings(int program, java.lang.CharSequence[] varyings, int bufferMode) {
		GL30.glTransformFeedbackVaryings(program, varyings, bufferMode);
	}
	public void glTransformFeedbackVaryings(int program, int count, java.nio.ByteBuffer varyings, int bufferMode) {
		GL30.glTransformFeedbackVaryings(program, count, varyings, bufferMode);
	}
	public void glUniform1u(int location, java.nio.IntBuffer value) {
		GL30.glUniform1u(location, value);
	}
	public void glUniform1ui(int location, int v0) {
		GL30.glUniform1ui(location, v0);
	}
	public void glUniform2u(int location, java.nio.IntBuffer value) {
		GL30.glUniform2u(location, value);
	}
	public void glUniform2ui(int location, int v0, int v1) {
		GL30.glUniform2ui(location, v0, v1);
	}
	public void glUniform3u(int location, java.nio.IntBuffer value) {
		GL30.glUniform3u(location, value);
	}
	public void glUniform3ui(int location, int v0, int v1, int v2) {
		GL30.glUniform3ui(location, v0, v1, v2);
	}
	public void glUniform4u(int location, java.nio.IntBuffer value) {
		GL30.glUniform4u(location, value);
	}
	public void glUniform4ui(int location, int v0, int v1, int v2, int v3) {
		GL30.glUniform4ui(location, v0, v1, v2, v3);
	}
	public void glVertexAttribI1(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI1(index, v);
	}
	public void glVertexAttribI1i(int index, int x) {
		GL30.glVertexAttribI1i(index, x);
	}
	public void glVertexAttribI1u(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI1u(index, v);
	}
	public void glVertexAttribI1ui(int index, int x) {
		GL30.glVertexAttribI1ui(index, x);
	}
	public void glVertexAttribI2(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI2(index, v);
	}
	public void glVertexAttribI2i(int index, int x, int y) {
		GL30.glVertexAttribI2i(index, x, y);
	}
	public void glVertexAttribI2u(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI2u(index, v);
	}
	public void glVertexAttribI2ui(int index, int x, int y) {
		GL30.glVertexAttribI2ui(index, x, y);
	}
	public void glVertexAttribI3(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI3(index, v);
	}
	public void glVertexAttribI3i(int index, int x, int y, int z) {
		GL30.glVertexAttribI3i(index, x, y, z);
	}
	public void glVertexAttribI3u(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI3u(index, v);
	}
	public void glVertexAttribI3ui(int index, int x, int y, int z) {
		GL30.glVertexAttribI3ui(index, x, y, z);
	}
	public void glVertexAttribI4(int index, java.nio.ByteBuffer v) {
		GL30.glVertexAttribI4(index, v);
	}
	public void glVertexAttribI4(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI4(index, v);
	}
	public void glVertexAttribI4(int index, java.nio.ShortBuffer v) {
		GL30.glVertexAttribI4(index, v);
	}
	public void glVertexAttribI4i(int index, int x, int y, int z, int w) {
		GL30.glVertexAttribI4i(index, x, y, z, w);
	}
	public void glVertexAttribI4u(int index, java.nio.ByteBuffer v) {
		GL30.glVertexAttribI4u(index, v);
	}
	public void glVertexAttribI4u(int index, java.nio.IntBuffer v) {
		GL30.glVertexAttribI4u(index, v);
	}
	public void glVertexAttribI4u(int index, java.nio.ShortBuffer v) {
		GL30.glVertexAttribI4u(index, v);
	}
	public void glVertexAttribI4ui(int index, int x, int y, int z, int w) {
		GL30.glVertexAttribI4ui(index, x, y, z, w);
	}
	public void glVertexAttribIPointer(int index, int size, int type, int stride, java.nio.ByteBuffer buffer) {
		GL30.glVertexAttribIPointer(index, size, type, stride, buffer);
	}
	public void glVertexAttribIPointer(int index, int size, int type, int stride, java.nio.IntBuffer buffer) {
		GL30.glVertexAttribIPointer(index, size, type, stride, buffer);
	}
	public void glVertexAttribIPointer(int index, int size, int type, int stride, long buffer_buffer_offset) {
		GL30.glVertexAttribIPointer(index, size, type, stride, buffer_buffer_offset);
	}
	public void glVertexAttribIPointer(int index, int size, int type, int stride, java.nio.ShortBuffer buffer) {
		GL30.glVertexAttribIPointer(index, size, type, stride, buffer);
	}
	public void glCopyBufferSubData(int readtarget, int writetarget, long readoffset, long writeoffset, long size) {
		GL31.glCopyBufferSubData(readtarget, writetarget, readoffset, writeoffset, size);
	}
	public void glDrawArraysInstanced(int mode, int first, int count, int primcount) {
		GL31.glDrawArraysInstanced(mode, first, count, primcount);
	}
	public void glDrawElementsInstanced(int mode, java.nio.ByteBuffer indices, int primcount) {
		GL31.glDrawElementsInstanced(mode, indices, primcount);
	}
	public void glDrawElementsInstanced(int mode, java.nio.IntBuffer indices, int primcount) {
		GL31.glDrawElementsInstanced(mode, indices, primcount);
	}
	public void glDrawElementsInstanced(int mode, int indices_count, int type, long indices_buffer_offset, int primcount) {
		GL31.glDrawElementsInstanced(mode, indices_count, type, indices_buffer_offset, primcount);
	}
	public void glDrawElementsInstanced(int mode, java.nio.ShortBuffer indices, int primcount) {
		GL31.glDrawElementsInstanced(mode, indices, primcount);
	}
	public int glGetActiveUniformBlock(int program, int uniformBlockIndex, int pname) {
		return GL31.glGetActiveUniformBlock(program, uniformBlockIndex, pname);
	}
	public void glGetActiveUniformBlock(int program, int uniformBlockIndex, int pname, java.nio.IntBuffer params) {
		GL31.glGetActiveUniformBlock(program, uniformBlockIndex, pname, params);
	}
	public int glGetActiveUniformBlocki(int program, int uniformBlockIndex, int pname) {
		return GL31.glGetActiveUniformBlocki(program, uniformBlockIndex, pname);
	}
	public java.lang.String glGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize) {
		return GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, bufSize);
	}
	public void glGetActiveUniformBlockName(int program, int uniformBlockIndex, java.nio.IntBuffer length, java.nio.ByteBuffer uniformBlockName) {
		GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, length, uniformBlockName);
	}
	public java.lang.String glGetActiveUniformName(int program, int uniformIndex, int bufSize) {
		return GL31.glGetActiveUniformName(program, uniformIndex, bufSize);
	}
	public void glGetActiveUniformName(int program, int uniformIndex, java.nio.IntBuffer length, java.nio.ByteBuffer uniformName) {
		GL31.glGetActiveUniformName(program, uniformIndex, length, uniformName);
	}
	public void glGetActiveUniforms(int program, java.nio.IntBuffer uniformIndices, int pname, java.nio.IntBuffer params) {
		GL31.glGetActiveUniforms(program, uniformIndices, pname, params);
	}
	public int glGetActiveUniforms(int program, int uniformIndex, int pname) {
		return GL31.glGetActiveUniforms(program, uniformIndex, pname);
	}
	public int glGetActiveUniformsi(int program, int uniformIndex, int pname) {
		return GL31.glGetActiveUniformsi(program, uniformIndex, pname);
	}
	public int glGetUniformBlockIndex(int program, java.nio.ByteBuffer uniformBlockName) {
		return GL31.glGetUniformBlockIndex(program, uniformBlockName);
	}
	public int glGetUniformBlockIndex(int program, java.lang.CharSequence uniformBlockName) {
		return GL31.glGetUniformBlockIndex(program, uniformBlockName);
	}
	public void glGetUniformIndices(int program, java.nio.ByteBuffer uniformNames, java.nio.IntBuffer uniformIndices) {
		GL31.glGetUniformIndices(program, uniformNames, uniformIndices);
	}
	public void glGetUniformIndices(int program, java.lang.CharSequence[] uniformNames, java.nio.IntBuffer uniformIndices) {
		GL31.glGetUniformIndices(program, uniformNames, uniformIndices);
	}
	public void glPrimitiveRestartIndex(int index) {
		GL31.glPrimitiveRestartIndex(index);
	}
	public void glTexBuffer(int target, int internalformat, int buffer) {
		GL31.glTexBuffer(target, internalformat, buffer);
	}
	public void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding) {
		GL31.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding);
	}
	public int glClientWaitSync(GLSync sync, int flags, long timeout) {
		return GL32.glClientWaitSync(LWJGLUtils.s_wrapGLSync(sync), flags, timeout);
	}
	public void glDeleteSync(GLSync sync) {
		GL32.glDeleteSync(LWJGLUtils.s_wrapGLSync(sync));
	}
	public void glDrawElementsBaseVertex(int mode, java.nio.ByteBuffer indices, int basevertex) {
		GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
	}
	public void glDrawElementsBaseVertex(int mode, java.nio.IntBuffer indices, int basevertex) {
		GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
	}
	public void glDrawElementsBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int basevertex) {
		GL32.glDrawElementsBaseVertex(mode, indices_count, type, indices_buffer_offset, basevertex);
	}
	public void glDrawElementsBaseVertex(int mode, java.nio.ShortBuffer indices, int basevertex) {
		GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
	}
	public void glDrawElementsInstancedBaseVertex(int mode, java.nio.ByteBuffer indices, int primcount, int basevertex) {
		GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
	}
	public void glDrawElementsInstancedBaseVertex(int mode, java.nio.IntBuffer indices, int primcount, int basevertex) {
		GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
	}
	public void glDrawElementsInstancedBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex) {
		GL32.glDrawElementsInstancedBaseVertex(mode, indices_count, type, indices_buffer_offset, primcount, basevertex);
	}
	public void glDrawElementsInstancedBaseVertex(int mode, java.nio.ShortBuffer indices, int primcount, int basevertex) {
		GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
	}
	public void glDrawRangeElementsBaseVertex(int mode, int start, int end, java.nio.ByteBuffer indices, int basevertex) {
		GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
	}
	public void glDrawRangeElementsBaseVertex(int mode, int start, int end, java.nio.IntBuffer indices, int basevertex) {
		GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
	}
	public void glDrawRangeElementsBaseVertex(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset, int basevertex) {
		GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices_count, type, indices_buffer_offset, basevertex);
	}
	public void glDrawRangeElementsBaseVertex(int mode, int start, int end, java.nio.ShortBuffer indices, int basevertex) {
		GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
	}
	public GLSync glFenceSync(int condition, int flags) {
		return LWJGLUtils.s_unwrapGLSync(GL32.glFenceSync(condition, flags));
	}
	public void glFramebufferTexture(int target, int attachment, int texture, int level) {
		GL32.glFramebufferTexture(target, attachment, texture, level);
	}
	public long glGetBufferParameter(int target, int pname) {
		return GL32.glGetBufferParameter(target, pname);
	}
	public void glGetBufferParameter(int target, int pname, java.nio.LongBuffer params) {
		GL32.glGetBufferParameter(target, pname, params);
	}
	public long glGetBufferParameteri64(int target, int pname) {
		return GL32.glGetBufferParameteri64(target, pname);
	}
	public long glGetInteger64(int pname) {
		return GL32.glGetInteger64(pname);
	}
	public long glGetInteger64(int value, int index) {
		return GL32.glGetInteger64(value, index);
	}
	public void glGetInteger64(int value, int index, java.nio.LongBuffer data) {
		GL32.glGetInteger64(value, index, data);
	}
	public void glGetInteger64(int pname, java.nio.LongBuffer data) {
		GL32.glGetInteger64(pname, data);
	}
	public void glGetMultisample(int pname, int index, java.nio.FloatBuffer val) {
		GL32.glGetMultisample(pname, index, val);
	}
	public int glGetSync(GLSync sync, int pname) {
		return GL32.glGetSync(LWJGLUtils.s_wrapGLSync(sync), pname);
	}
	public void glGetSync(GLSync sync, int pname, java.nio.IntBuffer length, java.nio.IntBuffer values) {
		GL32.glGetSync(LWJGLUtils.s_wrapGLSync(sync), pname, length, values);
	}
	public int glGetSynci(GLSync sync, int pname) {
		return GL32.glGetSynci(LWJGLUtils.s_wrapGLSync(sync), pname);
	}
	public boolean glIsSync(GLSync sync) {
		return GL32.glIsSync(LWJGLUtils.s_wrapGLSync(sync));
	}
	public void glProvokingVertex(int mode) {
		GL32.glProvokingVertex(mode);
	}
	public void glSampleMaski(int index, int mask) {
		GL32.glSampleMaski(index, mask);
	}
	public void glTexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
		GL32.glTexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
	}
	public void glTexImage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
		GL32.glTexImage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
	}
	public void glWaitSync(GLSync sync, int flags, long timeout) {
		GL32.glWaitSync(LWJGLUtils.s_wrapGLSync(sync), flags, timeout);
	}
	public void glBindFragDataLocationIndexed(int program, int colorNumber, int index, java.nio.ByteBuffer name) {
		GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
	}
	public void glBindFragDataLocationIndexed(int program, int colorNumber, int index, java.lang.CharSequence name) {
		GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
	}
	public void glBindSampler(int unit, int sampler) {
		GL33.glBindSampler(unit, sampler);
	}
	public void glColorP3u(int type, java.nio.IntBuffer color) {
		GL33.glColorP3u(type, color);
	}
	public void glColorP3ui(int type, int color) {
		GL33.glColorP3ui(type, color);
	}
	public void glColorP4u(int type, java.nio.IntBuffer color) {
		GL33.glColorP4u(type, color);
	}
	public void glColorP4ui(int type, int color) {
		GL33.glColorP4ui(type, color);
	}
	public void glDeleteSamplers(int sampler) {
		GL33.glDeleteSamplers(sampler);
	}
	public void glDeleteSamplers(java.nio.IntBuffer samplers) {
		GL33.glDeleteSamplers(samplers);
	}
	public int glGenSamplers() {
		return GL33.glGenSamplers();
	}
	public void glGenSamplers(java.nio.IntBuffer samplers) {
		GL33.glGenSamplers(samplers);
	}
	public int glGetFragDataIndex(int program, java.nio.ByteBuffer name) {
		return GL33.glGetFragDataIndex(program, name);
	}
	public int glGetFragDataIndex(int program, java.lang.CharSequence name) {
		return GL33.glGetFragDataIndex(program, name);
	}
	public long glGetQueryObject(int id, int pname) {
		return GL33.glGetQueryObject(id, pname);
	}
	public void glGetQueryObject(int id, int pname, java.nio.LongBuffer params) {
		GL33.glGetQueryObject(id, pname, params);
	}
	public long glGetQueryObjecti64(int id, int pname) {
		return GL33.glGetQueryObjecti64(id, pname);
	}
	public long glGetQueryObjectu(int id, int pname) {
		return GL33.glGetQueryObjectu(id, pname);
	}
	public void glGetQueryObjectu(int id, int pname, java.nio.LongBuffer params) {
		GL33.glGetQueryObjectu(id, pname, params);
	}
	public long glGetQueryObjectui64(int id, int pname) {
		return GL33.glGetQueryObjectui64(id, pname);
	}
	public void glGetSamplerParameter(int sampler, int pname, java.nio.FloatBuffer params) {
		GL33.glGetSamplerParameter(sampler, pname, params);
	}
	public void glGetSamplerParameter(int sampler, int pname, java.nio.IntBuffer params) {
		GL33.glGetSamplerParameter(sampler, pname, params);
	}
	public float glGetSamplerParameterf(int sampler, int pname) {
		return GL33.glGetSamplerParameterf(sampler, pname);
	}
	public int glGetSamplerParameteri(int sampler, int pname) {
		return GL33.glGetSamplerParameteri(sampler, pname);
	}
	public void glGetSamplerParameterI(int sampler, int pname, java.nio.IntBuffer params) {
		GL33.glGetSamplerParameterI(sampler, pname, params);
	}
	public int glGetSamplerParameterIi(int sampler, int pname) {
		return GL33.glGetSamplerParameterIi(sampler, pname);
	}
	public void glGetSamplerParameterIu(int sampler, int pname, java.nio.IntBuffer params) {
		GL33.glGetSamplerParameterIu(sampler, pname, params);
	}
	public int glGetSamplerParameterIui(int sampler, int pname) {
		return GL33.glGetSamplerParameterIui(sampler, pname);
	}
	public boolean glIsSampler(int sampler) {
		return GL33.glIsSampler(sampler);
	}
	public void glMultiTexCoordP1u(int texture, int type, java.nio.IntBuffer coords) {
		GL33.glMultiTexCoordP1u(texture, type, coords);
	}
	public void glMultiTexCoordP1ui(int texture, int type, int coords) {
		GL33.glMultiTexCoordP1ui(texture, type, coords);
	}
	public void glMultiTexCoordP2u(int texture, int type, java.nio.IntBuffer coords) {
		GL33.glMultiTexCoordP2u(texture, type, coords);
	}
	public void glMultiTexCoordP2ui(int texture, int type, int coords) {
		GL33.glMultiTexCoordP2ui(texture, type, coords);
	}
	public void glMultiTexCoordP3u(int texture, int type, java.nio.IntBuffer coords) {
		GL33.glMultiTexCoordP3u(texture, type, coords);
	}
	public void glMultiTexCoordP3ui(int texture, int type, int coords) {
		GL33.glMultiTexCoordP3ui(texture, type, coords);
	}
	public void glMultiTexCoordP4u(int texture, int type, java.nio.IntBuffer coords) {
		GL33.glMultiTexCoordP4u(texture, type, coords);
	}
	public void glMultiTexCoordP4ui(int texture, int type, int coords) {
		GL33.glMultiTexCoordP4ui(texture, type, coords);
	}
	public void glNormalP3u(int type, java.nio.IntBuffer coords) {
		GL33.glNormalP3u(type, coords);
	}
	public void glNormalP3ui(int type, int coords) {
		GL33.glNormalP3ui(type, coords);
	}
	public void glQueryCounter(int id, int target) {
		GL33.glQueryCounter(id, target);
	}
	public void glSamplerParameter(int sampler, int pname, java.nio.FloatBuffer params) {
		GL33.glSamplerParameter(sampler, pname, params);
	}
	public void glSamplerParameter(int sampler, int pname, java.nio.IntBuffer params) {
		GL33.glSamplerParameter(sampler, pname, params);
	}
	public void glSamplerParameterf(int sampler, int pname, float param) {
		GL33.glSamplerParameterf(sampler, pname, param);
	}
	public void glSamplerParameteri(int sampler, int pname, int param) {
		GL33.glSamplerParameteri(sampler, pname, param);
	}
	public void glSamplerParameterI(int sampler, int pname, java.nio.IntBuffer params) {
		GL33.glSamplerParameterI(sampler, pname, params);
	}
	public void glSamplerParameterIu(int sampler, int pname, java.nio.IntBuffer params) {
		GL33.glSamplerParameterIu(sampler, pname, params);
	}
	public void glSecondaryColorP3u(int type, java.nio.IntBuffer color) {
		GL33.glSecondaryColorP3u(type, color);
	}
	public void glSecondaryColorP3ui(int type, int color) {
		GL33.glSecondaryColorP3ui(type, color);
	}
	public void glTexCoordP1u(int type, java.nio.IntBuffer coords) {
		GL33.glTexCoordP1u(type, coords);
	}
	public void glTexCoordP1ui(int type, int coords) {
		GL33.glTexCoordP1ui(type, coords);
	}
	public void glTexCoordP2u(int type, java.nio.IntBuffer coords) {
		GL33.glTexCoordP2u(type, coords);
	}
	public void glTexCoordP2ui(int type, int coords) {
		GL33.glTexCoordP2ui(type, coords);
	}
	public void glTexCoordP3u(int type, java.nio.IntBuffer coords) {
		GL33.glTexCoordP3u(type, coords);
	}
	public void glTexCoordP3ui(int type, int coords) {
		GL33.glTexCoordP3ui(type, coords);
	}
	public void glTexCoordP4u(int type, java.nio.IntBuffer coords) {
		GL33.glTexCoordP4u(type, coords);
	}
	public void glTexCoordP4ui(int type, int coords) {
		GL33.glTexCoordP4ui(type, coords);
	}
	public void glVertexAttribDivisor(int index, int divisor) {
		GL33.glVertexAttribDivisor(index, divisor);
	}
	public void glVertexAttribP1u(int index, int type, boolean normalized, java.nio.IntBuffer value) {
		GL33.glVertexAttribP1u(index, type, normalized, value);
	}
	public void glVertexAttribP1ui(int index, int type, boolean normalized, int value) {
		GL33.glVertexAttribP1ui(index, type, normalized, value);
	}
	public void glVertexAttribP2u(int index, int type, boolean normalized, java.nio.IntBuffer value) {
		GL33.glVertexAttribP2u(index, type, normalized, value);
	}
	public void glVertexAttribP2ui(int index, int type, boolean normalized, int value) {
		GL33.glVertexAttribP2ui(index, type, normalized, value);
	}
	public void glVertexAttribP3u(int index, int type, boolean normalized, java.nio.IntBuffer value) {
		GL33.glVertexAttribP3u(index, type, normalized, value);
	}
	public void glVertexAttribP3ui(int index, int type, boolean normalized, int value) {
		GL33.glVertexAttribP3ui(index, type, normalized, value);
	}
	public void glVertexAttribP4u(int index, int type, boolean normalized, java.nio.IntBuffer value) {
		GL33.glVertexAttribP4u(index, type, normalized, value);
	}
	public void glVertexAttribP4ui(int index, int type, boolean normalized, int value) {
		GL33.glVertexAttribP4ui(index, type, normalized, value);
	}
	public void glVertexP2u(int type, java.nio.IntBuffer value) {
		GL33.glVertexP2u(type, value);
	}
	public void glVertexP2ui(int type, int value) {
		GL33.glVertexP2ui(type, value);
	}
	public void glVertexP3u(int type, java.nio.IntBuffer value) {
		GL33.glVertexP3u(type, value);
	}
	public void glVertexP3ui(int type, int value) {
		GL33.glVertexP3ui(type, value);
	}
	public void glVertexP4u(int type, java.nio.IntBuffer value) {
		GL33.glVertexP4u(type, value);
	}
	public void glVertexP4ui(int type, int value) {
		GL33.glVertexP4ui(type, value);
	}
}
