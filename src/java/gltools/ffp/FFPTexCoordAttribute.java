package gltools.ffp;

import glcommon.vector.Matrix2f;
import glcommon.vector.Matrix3f;
import glcommon.vector.Matrix4f;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.gl.GL1;
import gltools.gl.GL2;
import gltools.shader.Attribute;
import gltools.shader.InputUsage;

public class FFPTexCoordAttribute extends Attribute {
	public FFPTexCoordAttribute() {
		setUsage(InputUsage.VERTEX_TEX_COORD);
	}
	
	@Override
	public void enableArray(GL2 gl) {
		gl.glEnableClientState(GL1.GL_TEXTURE_COORD_ARRAY);
	}
	@Override
	public void disableArray(GL2 gl) {
		gl.glDisableClientState(GL1.GL_TEXTURE_COORD_ARRAY);
	}
	@Override
	public void point(int stride, long offset, GL2 gl) {
		gl.glTexCoordPointer(getUsage().getDataType().getComponents(), getUsage().getDataType().getComponentType().getID(), stride, offset);
	}
	
	
	@Override
	public void setValue(GL2 gl, float val) {
		gl.glTexCoord1f(val);
	}
	
	@Override
	public void setValue(GL2 gl, Vector2f val) {
		gl.glTexCoord2f(val.getX(), val.getY());
	}
	@Override
	public void setValue(GL2 gl, Vector3f val) {
		gl.glTexCoord3f(val.getX(), val.getY(), val.getZ());
	}
	@Override
	public void setValue(GL2 gl, Vector4f val) {
		gl.glTexCoord4f(val.getX(), val.getY(), val.getZ(), val.getW());
	}
	
	@Override
	public void setValue(GL2 gl, Matrix2f val) {}
	@Override
	public void setValue(GL2 gl, Matrix3f val) {}
	@Override
	public void setValue(GL2 gl, Matrix4f val) {}
}
