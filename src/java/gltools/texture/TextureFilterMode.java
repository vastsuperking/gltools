package gltools.texture;

import glcommon.image.ImageFilterMode;
import gltools.gl.GL1;

public enum TextureFilterMode {
	NEAREST(GL1.GL_NEAREST),
	LINEAR(GL1.GL_LINEAR),
	NEAREST_MIPMAP_NEAREST(GL1.GL_NEAREST_MIPMAP_NEAREST),
	NEAREST_MIPMAP_LINEAR(GL1.GL_NEAREST_MIPMAP_LINEAR),
	LINEAR_MIPMAP_NEAREST(GL1.GL_LINEAR_MIPMAP_NEAREST),
	LINEAR_MIPMAP_LINEAR(GL1.GL_LINEAR_MIPMAP_LINEAR);
	
	private int m_id;
	
	TextureFilterMode(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
	
	public static TextureFilterMode s_get(ImageFilterMode mode) {
		switch(mode) {
		case NEAREST: return TextureFilterMode.NEAREST;
		case LINEAR: return TextureFilterMode.LINEAR;
		case NEAREST_MIPMAP_NEAREST: return TextureFilterMode.NEAREST_MIPMAP_NEAREST;
		case NEAREST_MIPMAP_LINEAR: return TextureFilterMode.NEAREST_MIPMAP_LINEAR;
		case LINEAR_MIPMAP_NEAREST: return TextureFilterMode.LINEAR_MIPMAP_NEAREST;
		case LINEAR_MIPMAP_LINEAR: return TextureFilterMode.LINEAR_MIPMAP_LINEAR;
		default: throw new RuntimeException("Cannot handle: " + mode);
		}
	}
}
