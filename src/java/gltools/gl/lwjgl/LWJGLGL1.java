package gltools.gl.lwjgl;

import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

public class LWJGLGL1 {
	public void glAccum(int op, float value) {
		GL11.glAccum(op, value);
	}
	public void glAlphaFunc(int func, float ref) {
		GL11.glAlphaFunc(func, ref);
	}
	public boolean glAreTexturesResident(java.nio.IntBuffer textures, java.nio.ByteBuffer residences) {
		return GL11.glAreTexturesResident(textures, residences);
	}
	public void glArrayElement(int i) {
		GL11.glArrayElement(i);
	}
	public void glBegin(int mode) {
		GL11.glBegin(mode);
	}
	public void glBindTexture(int target, int texture) {
		GL11.glBindTexture(target, texture);
	}
	public void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, java.nio.ByteBuffer bitmap) {
		GL11.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap);
	}
	public void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, long bitmap_buffer_offset) {
		GL11.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap_buffer_offset);
	}
	public void glBlendFunc(int sfactor, int dfactor) {
		GL11.glBlendFunc(sfactor, dfactor);
	}
	public void glCallList(int list) {
		GL11.glCallList(list);
	}
	public void glCallLists(java.nio.ByteBuffer lists) {
		GL11.glCallLists(lists);
	}
	public void glCallLists(java.nio.IntBuffer lists) {
		GL11.glCallLists(lists);
	}
	public void glCallLists(java.nio.ShortBuffer lists) {
		GL11.glCallLists(lists);
	}
	public void glClear(int mask) {
		GL11.glClear(mask);
	}
	public void glClearAccum(float red, float green, float blue, float alpha) {
		GL11.glClearAccum(red, green, blue, alpha);
	}
	public void glClearColor(float red, float green, float blue, float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
	}
	public void glClearDepth(double depth) {
		GL11.glClearDepth(depth);
	}
	public void glClearStencil(int s) {
		GL11.glClearStencil(s);
	}
	public void glClipPlane(int plane, java.nio.DoubleBuffer equation) {
		GL11.glClipPlane(plane, equation);
	}
	public void glColor3b(byte red, byte green, byte blue) {
		GL11.glColor3b(red, green, blue);
	}
	public void glColor3d(double red, double green, double blue) {
		GL11.glColor3d(red, green, blue);
	}
	public void glColor3f(float red, float green, float blue) {
		GL11.glColor3f(red, green, blue);
	}
	public void glColor3ub(byte red, byte green, byte blue) {
		GL11.glColor3ub(red, green, blue);
	}
	public void glColor4b(byte red, byte green, byte blue, byte alpha) {
		GL11.glColor4b(red, green, blue, alpha);
	}
	public void glColor4d(double red, double green, double blue, double alpha) {
		GL11.glColor4d(red, green, blue, alpha);
	}
	public void glColor4f(float red, float green, float blue, float alpha) {
		GL11.glColor4f(red, green, blue, alpha);
	}
	public void glColor4ub(byte red, byte green, byte blue, byte alpha) {
		GL11.glColor4ub(red, green, blue, alpha);
	}
	public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GL11.glColorMask(red, green, blue, alpha);
	}
	public void glColorMaterial(int face, int mode) {
		GL11.glColorMaterial(face, mode);
	}
	public void glColorPointer(int size, boolean unsigned, int stride, java.nio.ByteBuffer pointer) {
		GL11.glColorPointer(size, unsigned, stride, pointer);
	}
	public void glColorPointer(int size, int stride, java.nio.DoubleBuffer pointer) {
		GL11.glColorPointer(size, stride, pointer);
	}
	public void glColorPointer(int size, int stride, java.nio.FloatBuffer pointer) {
		GL11.glColorPointer(size, stride, pointer);
	}
	public void glColorPointer(int size, int type, int stride, java.nio.ByteBuffer pointer) {
		GL11.glColorPointer(size, type, stride, pointer);
	}
	public void glColorPointer(int size, int type, int stride, long pointer_buffer_offset) {
		GL11.glColorPointer(size, type, stride, pointer_buffer_offset);
	}
	public void glCopyPixels(int x, int y, int width, int height, int type) {
		GL11.glCopyPixels(x, y, width, height, type);
	}
	public void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) {
		GL11.glCopyTexImage1D(target, level, internalFormat, x, y, width, border);
	}
	public void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) {
		GL11.glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
	}
	public void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) {
		GL11.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
	}
	public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
		GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
	}
	public void glCullFace(int mode) {
		GL11.glCullFace(mode);
	}
	public void glDeleteLists(int list, int range) {
		GL11.glDeleteLists(list, range);
	}
	public void glDeleteTextures(int texture) {
		GL11.glDeleteTextures(texture);
	}
	public void glDeleteTextures(java.nio.IntBuffer textures) {
		GL11.glDeleteTextures(textures);
	}
	public void glDepthFunc(int func) {
		GL11.glDepthFunc(func);
	}
	public void glDepthMask(boolean flag) {
		GL11.glDepthMask(flag);
	}
	public void glDepthRange(double zNear, double zFar) {
		GL11.glDepthRange(zNear, zFar);
	}
	public void glDisable(int cap) {
		GL11.glDisable(cap);
	}
	public void glDisableClientState(int cap) {
		GL11.glDisableClientState(cap);
	}
	public void glDrawArrays(int mode, int first, int count) {
		GL11.glDrawArrays(mode, first, count);
	}
	public void glDrawBuffer(int mode) {
		GL11.glDrawBuffer(mode);
	}
	public void glDrawElements(int mode, java.nio.ByteBuffer indices) {
		GL11.glDrawElements(mode, indices);
	}
	public void glDrawElements(int mode, java.nio.IntBuffer indices) {
		GL11.glDrawElements(mode, indices);
	}
	public void glDrawElements(int mode, int count, int type, java.nio.ByteBuffer indices) {
		GL11.glDrawElements(mode, count, type, indices);
	}
	public void glDrawElements(int mode, int indices_count, int type, long indices_buffer_offset) {
		GL11.glDrawElements(mode, indices_count, type, indices_buffer_offset);
	}
	public void glDrawElements(int mode, java.nio.ShortBuffer indices) {
		GL11.glDrawElements(mode, indices);
	}
	public void glDrawPixels(int width, int height, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}
	public void glDrawPixels(int width, int height, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}
	public void glDrawPixels(int width, int height, int format, int type, long pixels_buffer_offset) {
		GL11.glDrawPixels(width, height, format, type, pixels_buffer_offset);
	}
	public void glDrawPixels(int width, int height, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}
	public void glEdgeFlag(boolean flag) {
		GL11.glEdgeFlag(flag);
	}
	public void glEdgeFlagPointer(int stride, java.nio.ByteBuffer pointer) {
		GL11.glEdgeFlagPointer(stride, pointer);
	}
	public void glEdgeFlagPointer(int stride, long pointer_buffer_offset) {
		GL11.glEdgeFlagPointer(stride, pointer_buffer_offset);
	}
	public void glEnable(int cap) {
		GL11.glEnable(cap);
	}
	public void glEnableClientState(int cap) {
		GL11.glEnableClientState(cap);
	}
	public void glEnd() {
		GL11.glEnd();
	}
	public void glEndList() {
		GL11.glEndList();
	}
	public void glEvalCoord1d(double u) {
		GL11.glEvalCoord1d(u);
	}
	public void glEvalCoord1f(float u) {
		GL11.glEvalCoord1f(u);
	}
	public void glEvalCoord2d(double u, double v) {
		GL11.glEvalCoord2d(u, v);
	}
	public void glEvalCoord2f(float u, float v) {
		GL11.glEvalCoord2f(u, v);
	}
	public void glEvalMesh1(int mode, int i1, int i2) {
		GL11.glEvalMesh1(mode, i1, i2);
	}
	public void glEvalMesh2(int mode, int i1, int i2, int j1, int j2) {
		GL11.glEvalMesh2(mode, i1, i2, j1, j2);
	}
	public void glEvalPoint1(int i) {
		GL11.glEvalPoint1(i);
	}
	public void glEvalPoint2(int i, int j) {
		GL11.glEvalPoint2(i, j);
	}
	public void glFeedbackBuffer(int type, java.nio.FloatBuffer buffer) {
		GL11.glFeedbackBuffer(type, buffer);
	}
	public void glFinish() {
		GL11.glFinish();
	}
	public void glFlush() {
		GL11.glFlush();
	}
	public void glFog(int pname, java.nio.FloatBuffer params) {
		GL11.glFog(pname, params);
	}
	public void glFog(int pname, java.nio.IntBuffer params) {
		GL11.glFog(pname, params);
	}
	public void glFogf(int pname, float param) {
		GL11.glFogf(pname, param);
	}
	public void glFogi(int pname, int param) {
		GL11.glFogi(pname, param);
	}
	public void glFrontFace(int mode) {
		GL11.glFrontFace(mode);
	}
	public void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL11.glFrustum(left, right, bottom, top, zNear, zFar);
	}
	public int glGenLists(int range) {
		return GL11.glGenLists(range);
	}
	public int glGenTextures() {
		return GL11.glGenTextures();
	}
	public void glGenTextures(java.nio.IntBuffer textures) {
		GL11.glGenTextures(textures);
	}
	public boolean glGetBoolean(int pname) {
		return GL11.glGetBoolean(pname);
	}
	public void glGetBoolean(int pname, java.nio.ByteBuffer params) {
		GL11.glGetBoolean(pname, params);
	}
	public void glGetClipPlane(int plane, java.nio.DoubleBuffer equation) {
		GL11.glGetClipPlane(plane, equation);
	}
	public double glGetDouble(int pname) {
		return GL11.glGetDouble(pname);
	}
	public void glGetDouble(int pname, java.nio.DoubleBuffer params) {
		GL11.glGetDouble(pname, params);
	}
	public int glGetError() {
		return GL11.glGetError();
	}
	public float glGetFloat(int pname) {
		return GL11.glGetFloat(pname);
	}
	public void glGetFloat(int pname, java.nio.FloatBuffer params) {
		GL11.glGetFloat(pname, params);
	}
	public int glGetInteger(int pname) {
		return GL11.glGetInteger(pname);
	}
	public void glGetInteger(int pname, java.nio.IntBuffer params) {
		GL11.glGetInteger(pname, params);
	}
	public void glGetLight(int light, int pname, java.nio.FloatBuffer params) {
		GL11.glGetLight(light, pname, params);
	}
	public void glGetLight(int light, int pname, java.nio.IntBuffer params) {
		GL11.glGetLight(light, pname, params);
	}
	public void glGetMap(int target, int query, java.nio.DoubleBuffer v) {
		GL11.glGetMap(target, query, v);
	}
	public void glGetMap(int target, int query, java.nio.FloatBuffer v) {
		GL11.glGetMap(target, query, v);
	}
	public void glGetMap(int target, int query, java.nio.IntBuffer v) {
		GL11.glGetMap(target, query, v);
	}
	public void glGetMaterial(int face, int pname, java.nio.FloatBuffer params) {
		GL11.glGetMaterial(face, pname, params);
	}
	public void glGetMaterial(int face, int pname, java.nio.IntBuffer params) {
		GL11.glGetMaterial(face, pname, params);
	}
	public void glGetPixelMap(int map, java.nio.FloatBuffer values) {
		GL11.glGetPixelMap(map, values);
	}
	public void glGetPixelMapfv(int map, long values_buffer_offset) {
		GL11.glGetPixelMapfv(map, values_buffer_offset);
	}
	public void glGetPixelMapu(int map, java.nio.IntBuffer values) {
		GL11.glGetPixelMapu(map, values);
	}
	public void glGetPixelMapu(int map, java.nio.ShortBuffer values) {
		GL11.glGetPixelMapu(map, values);
	}
	public void glGetPixelMapuiv(int map, long values_buffer_offset) {
		GL11.glGetPixelMapuiv(map, values_buffer_offset);
	}
	public void glGetPixelMapusv(int map, long values_buffer_offset) {
		GL11.glGetPixelMapusv(map, values_buffer_offset);
	}
	public java.nio.ByteBuffer glGetPointer(int pname, long result_size) {
		return GL11.glGetPointer(pname, result_size);
	}
	public void glGetPolygonStipple(java.nio.ByteBuffer mask) {
		GL11.glGetPolygonStipple(mask);
	}
	public void glGetPolygonStipple(long mask_buffer_offset) {
		GL11.glGetPolygonStipple(mask_buffer_offset);
	}
	public java.lang.String glGetString(int name) {
		return GL11.glGetString(name);
	}
	public void glGetTexEnv(int coord, int pname, java.nio.FloatBuffer params) {
		GL11.glGetTexEnv(coord, pname, params);
	}
	public void glGetTexEnv(int coord, int pname, java.nio.IntBuffer params) {
		GL11.glGetTexEnv(coord, pname, params);
	}
	public float glGetTexEnvf(int coord, int pname) {
		return GL11.glGetTexEnvf(coord, pname);
	}
	public int glGetTexEnvi(int coord, int pname) {
		return GL11.glGetTexEnvi(coord, pname);
	}
	public void glGetTexGen(int coord, int pname, java.nio.DoubleBuffer params) {
		GL11.glGetTexGen(coord, pname, params);
	}
	public void glGetTexGen(int coord, int pname, java.nio.FloatBuffer params) {
		GL11.glGetTexGen(coord, pname, params);
	}
	public void glGetTexGen(int coord, int pname, java.nio.IntBuffer params) {
		GL11.glGetTexGen(coord, pname, params);
	}
	public double glGetTexGend(int coord, int pname) {
		return GL11.glGetTexGend(coord, pname);
	}
	public float glGetTexGenf(int coord, int pname) {
		return GL11.glGetTexGenf(coord, pname);
	}
	public int glGetTexGeni(int coord, int pname) {
		return GL11.glGetTexGeni(coord, pname);
	}
	public void glGetTexImage(int target, int level, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	public void glGetTexImage(int target, int level, int format, int type, java.nio.DoubleBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	public void glGetTexImage(int target, int level, int format, int type, java.nio.FloatBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	public void glGetTexImage(int target, int level, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	public void glGetTexImage(int target, int level, int format, int type, long pixels_buffer_offset) {
		GL11.glGetTexImage(target, level, format, type, pixels_buffer_offset);
	}
	public void glGetTexImage(int target, int level, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	public void glGetTexLevelParameter(int target, int level, int pname, java.nio.FloatBuffer params) {
		GL11.glGetTexLevelParameter(target, level, pname, params);
	}
	public void glGetTexLevelParameter(int target, int level, int pname, java.nio.IntBuffer params) {
		GL11.glGetTexLevelParameter(target, level, pname, params);
	}
	public float glGetTexLevelParameterf(int target, int level, int pname) {
		return GL11.glGetTexLevelParameterf(target, level, pname);
	}
	public int glGetTexLevelParameteri(int target, int level, int pname) {
		return GL11.glGetTexLevelParameteri(target, level, pname);
	}
	public void glGetTexParameter(int target, int pname, java.nio.FloatBuffer params) {
		GL11.glGetTexParameter(target, pname, params);
	}
	public void glGetTexParameter(int target, int pname, java.nio.IntBuffer params) {
		GL11.glGetTexParameter(target, pname, params);
	}
	public float glGetTexParameterf(int target, int pname) {
		return GL11.glGetTexParameterf(target, pname);
	}
	public int glGetTexParameteri(int target, int pname) {
		return GL11.glGetTexParameteri(target, pname);
	}
	public void glHint(int target, int mode) {
		GL11.glHint(target, mode);
	}
	public void glInitNames() {
		GL11.glInitNames();
	}
	public void glInterleavedArrays(int format, int stride, java.nio.ByteBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	public void glInterleavedArrays(int format, int stride, java.nio.DoubleBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	public void glInterleavedArrays(int format, int stride, java.nio.FloatBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	public void glInterleavedArrays(int format, int stride, java.nio.IntBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	public void glInterleavedArrays(int format, int stride, long pointer_buffer_offset) {
		GL11.glInterleavedArrays(format, stride, pointer_buffer_offset);
	}
	public void glInterleavedArrays(int format, int stride, java.nio.ShortBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	public boolean glIsEnabled(int cap) {
		return GL11.glIsEnabled(cap);
	}
	public boolean glIsList(int list) {
		return GL11.glIsList(list);
	}
	public boolean glIsTexture(int texture) {
		return GL11.glIsTexture(texture);
	}
	public void glLight(int light, int pname, java.nio.FloatBuffer params) {
		GL11.glLight(light, pname, params);
	}
	public void glLight(int light, int pname, java.nio.IntBuffer params) {
		GL11.glLight(light, pname, params);
	}
	public void glLightf(int light, int pname, float param) {
		GL11.glLightf(light, pname, param);
	}
	public void glLighti(int light, int pname, int param) {
		GL11.glLighti(light, pname, param);
	}
	public void glLightModel(int pname, java.nio.FloatBuffer params) {
		GL11.glLightModel(pname, params);
	}
	public void glLightModel(int pname, java.nio.IntBuffer params) {
		GL11.glLightModel(pname, params);
	}
	public void glLightModelf(int pname, float param) {
		GL11.glLightModelf(pname, param);
	}
	public void glLightModeli(int pname, int param) {
		GL11.glLightModeli(pname, param);
	}
	public void glLineStipple(int factor, short pattern) {
		GL11.glLineStipple(factor, pattern);
	}
	public void glLineWidth(float width) {
		GL11.glLineWidth(width);
	}
	public void glListBase(int base) {
		GL11.glListBase(base);
	}
	public void glLoadIdentity() {
		GL11.glLoadIdentity();
	}
	public void glLoadMatrix(java.nio.DoubleBuffer m) {
		GL11.glLoadMatrix(m);
	}
	public void glLoadMatrix(java.nio.FloatBuffer m) {
		GL11.glLoadMatrix(m);
	}
	public void glLoadName(int name) {
		GL11.glLoadName(name);
	}
	public void glLogicOp(int opcode) {
		GL11.glLogicOp(opcode);
	}
	public void glMap1d(int target, double u1, double u2, int stride, int order, java.nio.DoubleBuffer points) {
		GL11.glMap1d(target, u1, u2, stride, order, points);
	}
	public void glMap1f(int target, float u1, float u2, int stride, int order, java.nio.FloatBuffer points) {
		GL11.glMap1f(target, u1, u2, stride, order, points);
	}
	public void glMap2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, java.nio.DoubleBuffer points) {
		GL11.glMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
	}
	public void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, java.nio.FloatBuffer points) {
		GL11.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
	}
	public void glMapGrid1d(int un, double u1, double u2) {
		GL11.glMapGrid1d(un, u1, u2);
	}
	public void glMapGrid1f(int un, float u1, float u2) {
		GL11.glMapGrid1f(un, u1, u2);
	}
	public void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2) {
		GL11.glMapGrid2d(un, u1, u2, vn, v1, v2);
	}
	public void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) {
		GL11.glMapGrid2f(un, u1, u2, vn, v1, v2);
	}
	public void glMaterial(int face, int pname, java.nio.FloatBuffer params) {
		GL11.glMaterial(face, pname, params);
	}
	public void glMaterial(int face, int pname, java.nio.IntBuffer params) {
		GL11.glMaterial(face, pname, params);
	}
	public void glMaterialf(int face, int pname, float param) {
		GL11.glMaterialf(face, pname, param);
	}
	public void glMateriali(int face, int pname, int param) {
		GL11.glMateriali(face, pname, param);
	}
	public void glMatrixMode(int mode) {
		GL11.glMatrixMode(mode);
	}
	public void glMultMatrix(java.nio.DoubleBuffer m) {
		GL11.glMultMatrix(m);
	}
	public void glMultMatrix(java.nio.FloatBuffer m) {
		GL11.glMultMatrix(m);
	}
	public void glNewList(int list, int mode) {
		GL11.glNewList(list, mode);
	}
	public void glNormal3b(byte nx, byte ny, byte nz) {
		GL11.glNormal3b(nx, ny, nz);
	}
	public void glNormal3d(double nx, double ny, double nz) {
		GL11.glNormal3d(nx, ny, nz);
	}
	public void glNormal3f(float nx, float ny, float nz) {
		GL11.glNormal3f(nx, ny, nz);
	}
	public void glNormal3i(int nx, int ny, int nz) {
		GL11.glNormal3i(nx, ny, nz);
	}
	public void glNormalPointer(int stride, java.nio.ByteBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}
	public void glNormalPointer(int stride, java.nio.DoubleBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}
	public void glNormalPointer(int stride, java.nio.FloatBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}
	public void glNormalPointer(int stride, java.nio.IntBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}
	public void glNormalPointer(int type, int stride, java.nio.ByteBuffer pointer) {
		GL11.glNormalPointer(type, stride, pointer);
	}
	public void glNormalPointer(int type, int stride, long pointer_buffer_offset) {
		GL11.glNormalPointer(type, stride, pointer_buffer_offset);
	}
	public void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL11.glOrtho(left, right, bottom, top, zNear, zFar);
	}
	public void glPassThrough(float token) {
		GL11.glPassThrough(token);
	}
	public void glPixelMap(int map, java.nio.FloatBuffer values) {
		GL11.glPixelMap(map, values);
	}
	public void glPixelMapfv(int map, int values_mapsize, long values_buffer_offset) {
		GL11.glPixelMapfv(map, values_mapsize, values_buffer_offset);
	}
	public void glPixelMapu(int map, java.nio.IntBuffer values) {
		GL11.glPixelMapu(map, values);
	}
	public void glPixelMapu(int map, java.nio.ShortBuffer values) {
		GL11.glPixelMapu(map, values);
	}
	public void glPixelMapuiv(int map, int values_mapsize, long values_buffer_offset) {
		GL11.glPixelMapuiv(map, values_mapsize, values_buffer_offset);
	}
	public void glPixelMapusv(int map, int values_mapsize, long values_buffer_offset) {
		GL11.glPixelMapusv(map, values_mapsize, values_buffer_offset);
	}
	public void glPixelStoref(int pname, float param) {
		GL11.glPixelStoref(pname, param);
	}
	public void glPixelStorei(int pname, int param) {
		GL11.glPixelStorei(pname, param);
	}
	public void glPixelTransferf(int pname, float param) {
		GL11.glPixelTransferf(pname, param);
	}
	public void glPixelTransferi(int pname, int param) {
		GL11.glPixelTransferi(pname, param);
	}
	public void glPixelZoom(float xfactor, float yfactor) {
		GL11.glPixelZoom(xfactor, yfactor);
	}
	public void glPointSize(float size) {
		GL11.glPointSize(size);
	}
	public void glPolygonMode(int face, int mode) {
		GL11.glPolygonMode(face, mode);
	}
	public void glPolygonOffset(float factor, float units) {
		GL11.glPolygonOffset(factor, units);
	}
	public void glPolygonStipple(java.nio.ByteBuffer mask) {
		GL11.glPolygonStipple(mask);
	}
	public void glPolygonStipple(long mask_buffer_offset) {
		GL11.glPolygonStipple(mask_buffer_offset);
	}
	public void glPopAttrib() {
		GL11.glPopAttrib();
	}
	public void glPopClientAttrib() {
		GL11.glPopClientAttrib();
	}
	public void glPopMatrix() {
		GL11.glPopMatrix();
	}
	public void glPopName() {
		GL11.glPopName();
	}
	public void glPrioritizeTextures(java.nio.IntBuffer textures, java.nio.FloatBuffer priorities) {
		GL11.glPrioritizeTextures(textures, priorities);
	}
	public void glPushAttrib(int mask) {
		GL11.glPushAttrib(mask);
	}
	public void glPushClientAttrib(int mask) {
		GL11.glPushClientAttrib(mask);
	}
	public void glPushMatrix() {
		GL11.glPushMatrix();
	}
	public void glPushName(int name) {
		GL11.glPushName(name);
	}
	public void glRasterPos2d(double x, double y) {
		GL11.glRasterPos2d(x, y);
	}
	public void glRasterPos2f(float x, float y) {
		GL11.glRasterPos2f(x, y);
	}
	public void glRasterPos2i(int x, int y) {
		GL11.glRasterPos2i(x, y);
	}
	public void glRasterPos3d(double x, double y, double z) {
		GL11.glRasterPos3d(x, y, z);
	}
	public void glRasterPos3f(float x, float y, float z) {
		GL11.glRasterPos3f(x, y, z);
	}
	public void glRasterPos3i(int x, int y, int z) {
		GL11.glRasterPos3i(x, y, z);
	}
	public void glRasterPos4d(double x, double y, double z, double w) {
		GL11.glRasterPos4d(x, y, z, w);
	}
	public void glRasterPos4f(float x, float y, float z, float w) {
		GL11.glRasterPos4f(x, y, z, w);
	}
	public void glRasterPos4i(int x, int y, int z, int w) {
		GL11.glRasterPos4i(x, y, z, w);
	}
	public void glReadBuffer(int mode) {
		GL11.glReadBuffer(mode);
	}
	public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}
	public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.DoubleBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}
	public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.FloatBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}
	public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}
	public void glReadPixels(int x, int y, int width, int height, int format, int type, long pixels_buffer_offset) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels_buffer_offset);
	}
	public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}
	public void glRectd(double x1, double y1, double x2, double y2) {
		GL11.glRectd(x1, y1, x2, y2);
	}
	public void glRectf(float x1, float y1, float x2, float y2) {
		GL11.glRectf(x1, y1, x2, y2);
	}
	public void glRecti(int x1, int y1, int x2, int y2) {
		GL11.glRecti(x1, y1, x2, y2);
	}
	public int glRenderMode(int mode) {
		return GL11.glRenderMode(mode);
	}
	public void glRotated(double angle, double x, double y, double z) {
		GL11.glRotated(angle, x, y, z);
	}
	public void glRotatef(float angle, float x, float y, float z) {
		GL11.glRotatef(angle, x, y, z);
	}
	public void glScaled(double x, double y, double z) {
		GL11.glScaled(x, y, z);
	}
	public void glScalef(float x, float y, float z) {
		GL11.glScalef(x, y, z);
	}
	public void glScissor(int x, int y, int width, int height) {
		GL11.glScissor(x, y, width, height);
	}
	public void glSelectBuffer(java.nio.IntBuffer buffer) {
		GL11.glSelectBuffer(buffer);
	}
	public void glShadeModel(int mode) {
		GL11.glShadeModel(mode);
	}
	public void glStencilFunc(int func, int ref, int mask) {
		GL11.glStencilFunc(func, ref, mask);
	}
	public void glStencilMask(int mask) {
		GL11.glStencilMask(mask);
	}
	public void glStencilOp(int fail, int zfail, int zpass) {
		GL11.glStencilOp(fail, zfail, zpass);
	}
	public void glTexCoord1d(double s) {
		GL11.glTexCoord1d(s);
	}
	public void glTexCoord1f(float s) {
		GL11.glTexCoord1f(s);
	}
	public void glTexCoord2d(double s, double t) {
		GL11.glTexCoord2d(s, t);
	}
	public void glTexCoord2f(float s, float t) {
		GL11.glTexCoord2f(s, t);
	}
	public void glTexCoord3d(double s, double t, double r) {
		GL11.glTexCoord3d(s, t, r);
	}
	public void glTexCoord3f(float s, float t, float r) {
		GL11.glTexCoord3f(s, t, r);
	}
	public void glTexCoord4d(double s, double t, double r, double q) {
		GL11.glTexCoord4d(s, t, r, q);
	}
	public void glTexCoord4f(float s, float t, float r, float q) {
		GL11.glTexCoord4f(s, t, r, q);
	}
	public void glTexCoordPointer(int size, int stride, java.nio.DoubleBuffer pointer) {
		GL11.glTexCoordPointer(size, stride, pointer);
	}
	public void glTexCoordPointer(int size, int stride, java.nio.FloatBuffer pointer) {
		GL11.glTexCoordPointer(size, stride, pointer);
	}
	public void glTexCoordPointer(int size, int stride, java.nio.IntBuffer pointer) {
		GL11.glTexCoordPointer(size, stride, pointer);
	}
	public void glTexCoordPointer(int size, int type, int stride, java.nio.ByteBuffer pointer) {
		GL11.glTexCoordPointer(size, type, stride, pointer);
	}
	public void glTexCoordPointer(int size, int type, int stride, long pointer_buffer_offset) {
		GL11.glTexCoordPointer(size, type, stride, pointer_buffer_offset);
	}
	public void glTexCoordPointer(int size, int stride, java.nio.ShortBuffer pointer) {
		GL11.glTexCoordPointer(size, stride, pointer);
	}
	public void glTexEnv(int target, int pname, java.nio.FloatBuffer params) {
		GL11.glTexEnv(target, pname, params);
	}
	public void glTexEnv(int target, int pname, java.nio.IntBuffer params) {
		GL11.glTexEnv(target, pname, params);
	}
	public void glTexEnvf(int target, int pname, float param) {
		GL11.glTexEnvf(target, pname, param);
	}
	public void glTexEnvi(int target, int pname, int param) {
		GL11.glTexEnvi(target, pname, param);
	}
	public void glTexGen(int coord, int pname, java.nio.DoubleBuffer params) {
		GL11.glTexGen(coord, pname, params);
	}
	public void glTexGen(int coord, int pname, java.nio.FloatBuffer params) {
		GL11.glTexGen(coord, pname, params);
	}
	public void glTexGen(int coord, int pname, java.nio.IntBuffer params) {
		GL11.glTexGen(coord, pname, params);
	}
	public void glTexGend(int coord, int pname, double param) {
		GL11.glTexGend(coord, pname, param);
	}
	public void glTexGenf(int coord, int pname, float param) {
		GL11.glTexGenf(coord, pname, param);
	}
	public void glTexGeni(int coord, int pname, int param) {
		GL11.glTexGeni(coord, pname, param);
	}
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, java.nio.DoubleBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, java.nio.FloatBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels_buffer_offset);
	}
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.DoubleBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.FloatBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels_buffer_offset);
	}
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}
	public void glTexParameter(int target, int pname, java.nio.FloatBuffer param) {
		GL11.glTexParameter(target, pname, param);
	}
	public void glTexParameter(int target, int pname, java.nio.IntBuffer param) {
		GL11.glTexParameter(target, pname, param);
	}
	public void glTexParameterf(int target, int pname, float param) {
		GL11.glTexParameterf(target, pname, param);
	}
	public void glTexParameteri(int target, int pname, int param) {
		GL11.glTexParameteri(target, pname, param);
	}
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, java.nio.DoubleBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, java.nio.FloatBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels_buffer_offset);
	}
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.ByteBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.DoubleBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.FloatBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.IntBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset);
	}
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.ShortBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}
	public void glTranslated(double x, double y, double z) {
		GL11.glTranslated(x, y, z);
	}
	public void glTranslatef(float x, float y, float z) {
		GL11.glTranslatef(x, y, z);
	}
	public void glVertex2d(double x, double y) {
		GL11.glVertex2d(x, y);
	}
	public void glVertex2f(float x, float y) {
		GL11.glVertex2f(x, y);
	}
	public void glVertex2i(int x, int y) {
		GL11.glVertex2i(x, y);
	}
	public void glVertex3d(double x, double y, double z) {
		GL11.glVertex3d(x, y, z);
	}
	public void glVertex3f(float x, float y, float z) {
		GL11.glVertex3f(x, y, z);
	}
	public void glVertex3i(int x, int y, int z) {
		GL11.glVertex3i(x, y, z);
	}
	public void glVertex4d(double x, double y, double z, double w) {
		GL11.glVertex4d(x, y, z, w);
	}
	public void glVertex4f(float x, float y, float z, float w) {
		GL11.glVertex4f(x, y, z, w);
	}
	public void glVertex4i(int x, int y, int z, int w) {
		GL11.glVertex4i(x, y, z, w);
	}
	public void glVertexPointer(int size, int stride, java.nio.DoubleBuffer pointer) {
		GL11.glVertexPointer(size, stride, pointer);
	}
	public void glVertexPointer(int size, int stride, java.nio.FloatBuffer pointer) {
		GL11.glVertexPointer(size, stride, pointer);
	}
	public void glVertexPointer(int size, int stride, java.nio.IntBuffer pointer) {
		GL11.glVertexPointer(size, stride, pointer);
	}
	public void glVertexPointer(int size, int type, int stride, java.nio.ByteBuffer pointer) {
		GL11.glVertexPointer(size, type, stride, pointer);
	}
	public void glVertexPointer(int size, int type, int stride, long pointer_buffer_offset) {
		GL11.glVertexPointer(size, type, stride, pointer_buffer_offset);
	}
	public void glVertexPointer(int size, int stride, java.nio.ShortBuffer pointer) {
		GL11.glVertexPointer(size, stride, pointer);
	}
	public void glViewport(int x, int y, int width, int height) {
		GL11.glViewport(x, y, width, height);
	}
	public void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
		GL12.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	}
	public void glDrawRangeElements(int mode, int start, int end, java.nio.ByteBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}
	public void glDrawRangeElements(int mode, int start, int end, java.nio.IntBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}
	public void glDrawRangeElements(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset) {
		GL12.glDrawRangeElements(mode, start, end, indices_count, type, indices_buffer_offset);
	}
	public void glDrawRangeElements(int mode, int start, int end, java.nio.ShortBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, java.nio.ByteBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, java.nio.DoubleBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, java.nio.FloatBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, java.nio.IntBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels_buffer_offset);
	}
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, java.nio.ShortBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.ByteBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.DoubleBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.FloatBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.IntBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset);
	}
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, java.nio.ShortBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}
	public void glActiveTexture(int texture) {
		GL13.glActiveTexture(texture);
	}
	public void glClientActiveTexture(int texture) {
		GL13.glClientActiveTexture(texture);
	}
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, java.nio.ByteBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, data);
	}
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, data_imageSize, data_buffer_offset);
	}
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, java.nio.ByteBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, data);
	}
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset);
	}
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, java.nio.ByteBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, data);
	}
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset);
	}
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, java.nio.ByteBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, data);
	}
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, data_imageSize, data_buffer_offset);
	}
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, java.nio.ByteBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data);
	}
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset);
	}
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, java.nio.ByteBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data);
	}
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset);
	}
	public void glGetCompressedTexImage(int target, int lod, java.nio.ByteBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}
	public void glGetCompressedTexImage(int target, int lod, java.nio.IntBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}
	public void glGetCompressedTexImage(int target, int lod, long img_buffer_offset) {
		GL13.glGetCompressedTexImage(target, lod, img_buffer_offset);
	}
	public void glGetCompressedTexImage(int target, int lod, java.nio.ShortBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}
	public void glLoadTransposeMatrix(java.nio.DoubleBuffer m) {
		GL13.glLoadTransposeMatrix(m);
	}
	public void glLoadTransposeMatrix(java.nio.FloatBuffer m) {
		GL13.glLoadTransposeMatrix(m);
	}
	public void glMultiTexCoord1d(int target, double s) {
		GL13.glMultiTexCoord1d(target, s);
	}
	public void glMultiTexCoord1f(int target, float s) {
		GL13.glMultiTexCoord1f(target, s);
	}
	public void glMultiTexCoord2d(int target, double s, double t) {
		GL13.glMultiTexCoord2d(target, s, t);
	}
	public void glMultiTexCoord2f(int target, float s, float t) {
		GL13.glMultiTexCoord2f(target, s, t);
	}
	public void glMultiTexCoord3d(int target, double s, double t, double r) {
		GL13.glMultiTexCoord3d(target, s, t, r);
	}
	public void glMultiTexCoord3f(int target, float s, float t, float r) {
		GL13.glMultiTexCoord3f(target, s, t, r);
	}
	public void glMultiTexCoord4d(int target, double s, double t, double r, double q) {
		GL13.glMultiTexCoord4d(target, s, t, r, q);
	}
	public void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
		GL13.glMultiTexCoord4f(target, s, t, r, q);
	}
	public void glMultTransposeMatrix(java.nio.DoubleBuffer m) {
		GL13.glMultTransposeMatrix(m);
	}
	public void glMultTransposeMatrix(java.nio.FloatBuffer m) {
		GL13.glMultTransposeMatrix(m);
	}
	public void glSampleCoverage(float value, boolean invert) {
		GL13.glSampleCoverage(value, invert);
	}
	public void glBlendColor(float red, float green, float blue, float alpha) {
		GL14.glBlendColor(red, green, blue, alpha);
	}
	public void glBlendEquation(int mode) {
		GL14.glBlendEquation(mode);
	}
	public void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		GL14.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
	}
	public void glFogCoordd(double coord) {
		GL14.glFogCoordd(coord);
	}
	public void glFogCoordf(float coord) {
		GL14.glFogCoordf(coord);
	}
	public void glFogCoordPointer(int stride, java.nio.DoubleBuffer data) {
		GL14.glFogCoordPointer(stride, data);
	}
	public void glFogCoordPointer(int stride, java.nio.FloatBuffer data) {
		GL14.glFogCoordPointer(stride, data);
	}
	public void glFogCoordPointer(int type, int stride, long data_buffer_offset) {
		GL14.glFogCoordPointer(type, stride, data_buffer_offset);
	}
	public void glMultiDrawArrays(int mode, java.nio.IntBuffer piFirst, java.nio.IntBuffer piCount) {
		GL14.glMultiDrawArrays(mode, piFirst, piCount);
	}
	public void glPointParameter(int pname, java.nio.FloatBuffer params) {
		GL14.glPointParameter(pname, params);
	}
	public void glPointParameter(int pname, java.nio.IntBuffer params) {
		GL14.glPointParameter(pname, params);
	}
	public void glPointParameterf(int pname, float param) {
		GL14.glPointParameterf(pname, param);
	}
	public void glPointParameteri(int pname, int param) {
		GL14.glPointParameteri(pname, param);
	}
	public void glSecondaryColor3b(byte red, byte green, byte blue) {
		GL14.glSecondaryColor3b(red, green, blue);
	}
	public void glSecondaryColor3d(double red, double green, double blue) {
		GL14.glSecondaryColor3d(red, green, blue);
	}
	public void glSecondaryColor3f(float red, float green, float blue) {
		GL14.glSecondaryColor3f(red, green, blue);
	}
	public void glSecondaryColor3ub(byte red, byte green, byte blue) {
		GL14.glSecondaryColor3ub(red, green, blue);
	}
	public void glSecondaryColorPointer(int size, boolean unsigned, int stride, java.nio.ByteBuffer data) {
		GL14.glSecondaryColorPointer(size, unsigned, stride, data);
	}
	public void glSecondaryColorPointer(int size, int stride, java.nio.DoubleBuffer data) {
		GL14.glSecondaryColorPointer(size, stride, data);
	}
	public void glSecondaryColorPointer(int size, int stride, java.nio.FloatBuffer data) {
		GL14.glSecondaryColorPointer(size, stride, data);
	}
	public void glSecondaryColorPointer(int size, int type, int stride, long data_buffer_offset) {
		GL14.glSecondaryColorPointer(size, type, stride, data_buffer_offset);
	}
	public void glWindowPos2d(double x, double y) {
		GL14.glWindowPos2d(x, y);
	}
	public void glWindowPos2f(float x, float y) {
		GL14.glWindowPos2f(x, y);
	}
	public void glWindowPos2i(int x, int y) {
		GL14.glWindowPos2i(x, y);
	}
	public void glWindowPos3d(double x, double y, double z) {
		GL14.glWindowPos3d(x, y, z);
	}
	public void glWindowPos3f(float x, float y, float z) {
		GL14.glWindowPos3f(x, y, z);
	}
	public void glWindowPos3i(int x, int y, int z) {
		GL14.glWindowPos3i(x, y, z);
	}
	public void glBeginQuery(int target, int id) {
		GL15.glBeginQuery(target, id);
	}
	public void glBindBuffer(int target, int buffer) {
		GL15.glBindBuffer(target, buffer);
	}
	public void glBufferData(int target, java.nio.ByteBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}
	public void glBufferData(int target, java.nio.DoubleBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}
	public void glBufferData(int target, java.nio.FloatBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}
	public void glBufferData(int target, java.nio.IntBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}
	public void glBufferData(int target, long data_size, int usage) {
		GL15.glBufferData(target, data_size, usage);
	}
	public void glBufferData(int target, java.nio.ShortBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}
	public void glBufferSubData(int target, long offset, java.nio.ByteBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}
	public void glBufferSubData(int target, long offset, java.nio.DoubleBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}
	public void glBufferSubData(int target, long offset, java.nio.FloatBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}
	public void glBufferSubData(int target, long offset, java.nio.IntBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}
	public void glBufferSubData(int target, long offset, java.nio.ShortBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}
	public void glDeleteBuffers(int buffer) {
		GL15.glDeleteBuffers(buffer);
	}
	public void glDeleteBuffers(java.nio.IntBuffer buffers) {
		GL15.glDeleteBuffers(buffers);
	}
	public void glDeleteQueries(int id) {
		GL15.glDeleteQueries(id);
	}
	public void glDeleteQueries(java.nio.IntBuffer ids) {
		GL15.glDeleteQueries(ids);
	}
	public void glEndQuery(int target) {
		GL15.glEndQuery(target);
	}
	public int glGenBuffers() {
		return GL15.glGenBuffers();
	}
	public void glGenBuffers(java.nio.IntBuffer buffers) {
		GL15.glGenBuffers(buffers);
	}
	public int glGenQueries() {
		return GL15.glGenQueries();
	}
	public void glGenQueries(java.nio.IntBuffer ids) {
		GL15.glGenQueries(ids);
	}
	public int glGetBufferParameter(int target, int pname) {
		return GL15.glGetBufferParameter(target, pname);
	}
	public void glGetBufferParameter(int target, int pname, java.nio.IntBuffer params) {
		GL15.glGetBufferParameter(target, pname, params);
	}
	public int glGetBufferParameteri(int target, int pname) {
		return GL15.glGetBufferParameteri(target, pname);
	}
	public java.nio.ByteBuffer glGetBufferPointer(int target, int pname) {
		return GL15.glGetBufferPointer(target, pname);
	}
	public void glGetBufferSubData(int target, long offset, java.nio.ByteBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}
	public void glGetBufferSubData(int target, long offset, java.nio.DoubleBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}
	public void glGetBufferSubData(int target, long offset, java.nio.FloatBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}
	public void glGetBufferSubData(int target, long offset, java.nio.IntBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}
	public void glGetBufferSubData(int target, long offset, java.nio.ShortBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}
	public int glGetQuery(int target, int pname) {
		return GL15.glGetQuery(target, pname);
	}
	public void glGetQuery(int target, int pname, java.nio.IntBuffer params) {
		GL15.glGetQuery(target, pname, params);
	}
	public int glGetQueryi(int target, int pname) {
		return GL15.glGetQueryi(target, pname);
	}
	public void glGetQueryObject(int id, int pname, java.nio.IntBuffer params) {
		GL15.glGetQueryObject(id, pname, params);
	}
	public int glGetQueryObjecti(int id, int pname) {
		return GL15.glGetQueryObjecti(id, pname);
	}
	public void glGetQueryObjectu(int id, int pname, java.nio.IntBuffer params) {
		GL15.glGetQueryObjectu(id, pname, params);
	}
	public int glGetQueryObjectui(int id, int pname) {
		return GL15.glGetQueryObjectui(id, pname);
	}
	public boolean glIsBuffer(int buffer) {
		return GL15.glIsBuffer(buffer);
	}
	public boolean glIsQuery(int id) {
		return GL15.glIsQuery(id);
	}
	public java.nio.ByteBuffer glMapBuffer(int target, int access, java.nio.ByteBuffer old_buffer) {
		return GL15.glMapBuffer(target, access, old_buffer);
	}
	public java.nio.ByteBuffer glMapBuffer(int target, int access, long length, java.nio.ByteBuffer old_buffer) {
		return GL15.glMapBuffer(target, access, length, old_buffer);
	}
	public boolean glUnmapBuffer(int target) {
		return GL15.glUnmapBuffer(target);
	}
}
