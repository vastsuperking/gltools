package gltools.ffp;

import glcommon.vector.Matrix4f;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.shader.Attribute;
import gltools.shader.InputUsage;

import org.lwjgl.opengl.GL11;

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
	public void enable() {
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
	}
	@Override
	public void disable() {
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	}
	@Override
	public void point(int stride, long offset) {
		GL11.glVertexPointer(getUsage().getDataType().getComponents(), getUsage().getDataType().getID(), stride, offset);
	}

	@Override
	public void setValue(float val) {}
	
	@Override
	public void setValue(Vector2f val) {
		GL11.glVertex2f(val.getX(), val.getY());
	}
	@Override
	public void setValue(Vector3f val) {
		GL11.glVertex3f(val.getX(), val.getY(), val.getZ());
	}
	@Override
	public void setValue(Vector4f val) {
		GL11.glVertex4f(val.getX(), val.getY(), val.getZ(), val.getW());
	}
	
	@Override
	public void setValue(Matrix4f val) {}
}
