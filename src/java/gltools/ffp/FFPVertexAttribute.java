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

public class FFPVertexAttribute extends Attribute {
	/**
	 * Usage should either be InputUsage.VERTEX_POSITION_3D
	 * or InputUsage.VERTEX_POSITION_2D, depending on what is being used
	 */
	public FFPVertexAttribute(InputUsage usage) {
		setUsage(usage);
	}
	public FFPVertexAttribute(boolean is3D) {
		if (is3D) {
			setUsage(InputUsage.VERTEX_POSITION_3D);
		} else {
			setUsage(InputUsage.VERTEX_POSITION_2D);
		}
	}
	@Override
	public void enableArray(GL2 gl) {
		gl.glEnableClientState(GL1.GL_VERTEX_ARRAY);
	}
	@Override
	public void disableArray(GL2 gl) {
		gl.glDisableClientState(GL1.GL_VERTEX_ARRAY);
	}
	@Override
	public void point(int stride, long offset, GL2 gl) {
		gl.glVertexPointer(getUsage().getDataType().getComponents(), getUsage().getDataType().getID(), stride, offset);
	}

	@Override
	public void setValue(GL2 gl, float val) {}
	
	@Override
	public void setValue(GL2 gl, Vector2f val) {
		gl.glVertex2f(val.getX(), val.getY());
	}
	@Override
	public void setValue(GL2 gl, Vector3f val) {
		gl.glVertex3f(val.getX(), val.getY(), val.getZ());
	}
	@Override
	public void setValue(GL2 gl, Vector4f val) {
		gl.glVertex4f(val.getX(), val.getY(), val.getZ(), val.getW());
	}
	
	@Override
	public void setValue(GL2 gl, Matrix2f val) {}
	@Override
	public void setValue(GL2 gl, Matrix3f val) {}
	@Override
	public void setValue(GL2 gl, Matrix4f val) {}
}
