package gltools.texture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class Texture1D extends Texture {
	private int m_length;

	public Texture1D(int handle) {
		super(handle, TextureTarget.TEXTURE_1D);
	}
	public Texture1D() {
		this(GL11.glGenTextures());
	}
	
	public int getLength() { return m_length; }
	public void setLength(int length) { m_length = length; }
	
	@Override
	public void setTWrapMode(TextureWrapMode mode) { throw new RuntimeException("No T_WRAP_MODE for Texture1D!");}
	
	@Override
	public void load() {
		checkBound();
		loadParams();

		GL11.glTexImage1D(getTarget().getID(), 0, getFormat().getInternal(), m_length, 0, getFormat().getBase(), GL11.GL_UNSIGNED_BYTE, getData());
	}
	@Override
	public void loadParams() {
		checkBound();
		//Setup wrap mode
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_WRAP_S, getSWrapMode().getID());
		
		//Setup texture filtering
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_MIN_FILTER, getMinFilterMode().getID());
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_MAG_FILTER, getMaxFilterMode().getID());
	
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, 0);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 0);
	}

}
