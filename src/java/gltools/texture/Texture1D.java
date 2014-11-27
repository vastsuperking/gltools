package gltools.texture;

import gltools.gl.GL1;


public class Texture1D extends Texture {
	private int m_length;

	public Texture1D() {
		super(TextureTarget.TEXTURE_1D);
	}
	
	public int getLength() { return m_length; }
	public void setLength(int length) { m_length = length; }
	
	@Override
	public void setTWrapMode(TextureWrapMode mode) { throw new RuntimeException("No T_WRAP_MODE for Texture1D!");}
	
	@Override
	public void load(GL1 gl) {
		checkBound();
		loadParams(gl);

		gl.glTexImage1D(getTarget().getID(), 0, getFormat().getInternal(), m_length, 0, getFormat().getBase(), GL1.GL_UNSIGNED_BYTE, getData());
	}
	@Override
	public void loadParams(GL1 gl) {
		checkBound();
		//Setup wrap mode
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_WRAP_S, getSWrapMode().getID());
		
		//Setup texture filtering
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_MIN_FILTER, getMinFilterMode().getID());
		gl.glTexParameteri(getTarget().getID(), GL1.GL_TEXTURE_MAG_FILTER, getMaxFilterMode().getID());
	
	    gl.glTexParameteri(GL1.GL_TEXTURE_1D, GL1.GL_TEXTURE_BASE_LEVEL, 0);
	    gl.glTexParameteri(GL1.GL_TEXTURE_1D, GL1.GL_TEXTURE_MAX_LEVEL, 0);
	}

}
