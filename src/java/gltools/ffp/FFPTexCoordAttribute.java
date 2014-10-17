package gltools.ffp;

import glcommon.vector.Matrix4f;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.shader.Attribute;
import gltools.shader.InputUsage;

import org.lwjgl.opengl.GL11;

public class FFPTexCoordAttribute extends Attribute {
	public FFPTexCoordAttribute() {
		setUsage(InputUsage.VERTEX_TEX_COORD);
	}
	
	@Override
	public void enable() {
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
	}
	@Override
	public void disable() {
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
	}
	@Override
	public void point(int stride, long offset) {
		GL11.glTexCoordPointer(getUsage().getDataType().getComponents(), getUsage().getDataType().getComponentType().getID(), stride, offset);
	}
	
	
	@Override
	public void setValue(float val) {
		GL11.glTexCoord1f(val);
	}
	
	@Override
	public void setValue(Vector2f val) {
		GL11.glTexCoord2f(val.getX(), val.getY());
	}
	@Override
	public void setValue(Vector3f val) {
		GL11.glTexCoord3f(val.getX(), val.getY(), val.getZ());
	}
	@Override
	public void setValue(Vector4f val) {
		GL11.glTexCoord4f(val.getX(), val.getY(), val.getZ(), val.getW());
	}
	
	@Override
	public void setValue(Matrix4f val) {}
}
