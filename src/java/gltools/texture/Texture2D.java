package gltools.texture;

import gltools.gl.GL1;

public class Texture2D extends Texture {
	private int m_width = 0;
	private int m_height = 0;
	
	public Texture2D() {
		super(TextureTarget.TEXTURE_2D);
	}
	public int  getWidth() 		{ return m_width; 	}
	public int  getHeight() 		{ return m_height; 	}

	public void setWidth(int width) 	{ m_width 	= width;  }
	public void setHeight(int height) 	{ m_height 	= height; }

	@Override
	public void load(GL1 gl) {
		checkBound();
		loadParams(gl);
        //Send texel data to OpenGL
        gl.glTexImage2D(getTarget().getID(), 0, getFormat().getInternal(), m_width, m_height, 0, getFormat().getBase(), GL1.GL_UNSIGNED_BYTE, getData());
	}
	@Override
	public void loadParams(GL1 gl) {
		checkBound();
		//Setup wrap mode
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_WRAP_S, getSWrapMode().getID());
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_WRAP_T, getTWrapMode().getID());

		//Setup texture filtering
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_MIN_FILTER, getMinFilterMode().getID());
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_MAG_FILTER, getMaxFilterMode().getID());
	}
}
