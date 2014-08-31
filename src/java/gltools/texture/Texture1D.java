package gltools.texture;

import org.lwjgl.opengl.GL11;

public class Texture1D extends Texture {
	private int m_width;

	public Texture1D(int handle) {
		super(handle, TextureTarget.TEXTURE_1D);
	}
	public Texture1D() {
		this(GL11.glGenTextures());
	}
	
	public int getWidth() { return m_width; }
	public void setWidth(int width) { m_width = width; }
	
	@Override
	public void load() {
		checkBound();
		loadParams();

		GL11.glTexImage1D(getTarget().getID(), 0, getFormat().getSized(), m_width, 0, getFormat().getBase(), GL11.GL_UNSIGNED_BYTE, getData());
	}
	@Override
	public void loadParams() {
		checkBound();
		//Setup wrap mode
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_WRAP_S, getSWrapMode().getID());
		
		//Setup texture filtering
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_MIN_FILTER, getMinFilterMode().getID());
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_MAG_FILTER, getMaxFilterMode().getID());
	}

}
