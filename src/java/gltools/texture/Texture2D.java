package gltools.texture;

import org.lwjgl.opengl.GL11;

public class Texture2D extends Texture {
	private int m_width = 0;
	private int m_height = 0;
	
	public Texture2D(int handle) {
		super(handle, TextureTarget.TEXTURE_2D);
	}
	public Texture2D() {
		this(GL11.glGenTextures());
	}
	public int  getWidth() 		{ return m_width; 	}
	public int  getHeight() 		{ return m_height; 	}

	public void setWidth(int width) 	{ m_width 	= width;  }
	public void setHeight(int height) 	{ m_height 	= height; }

	@Override
	public void load() {
		checkBound();
		loadParams();
        //Send texel data to OpenGL
        GL11.glTexImage2D(getTarget().getID(), 0, getFormat().getSized(), m_width, m_height, 0, getFormat().getBase(), GL11.GL_UNSIGNED_BYTE, getData());
	}
	@Override
	public void loadParams() {
		checkBound();
		//Setup wrap mode
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_WRAP_S, getSWrapMode().getID());
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_WRAP_T, getTWrapMode().getID());

		//Setup texture filtering
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_MIN_FILTER, getMinFilterMode().getID());
		GL11.glTexParameteri(getTarget().getID(), GL11.GL_TEXTURE_MAG_FILTER, getMaxFilterMode().getID());
	}
}
