package gltools.texture;

import glcommon.image.ImageWrapMode;
import gltools.gl.GL1;

public enum TextureWrapMode {
	REPEAT(GL1.GL_REPEAT),
	CLAMP_TO_BORDER(GL1.GL_CLAMP_TO_BORDER),
	CLAMP_TO_EDGE(GL1.GL_CLAMP_TO_EDGE);
	private int m_id;
	
	TextureWrapMode(int id) {
		m_id = id;
	}
	
	public int getID() { return m_id; }
	
	public static TextureWrapMode s_get(ImageWrapMode mode) {
		switch (mode) {
		case REPEAT: return TextureWrapMode.REPEAT;
		case CLAMP_TO_BORDER: return TextureWrapMode.CLAMP_TO_BORDER;
		case CLAMP_TO_EDGE: return TextureWrapMode.CLAMP_TO_EDGE;
		default: throw new RuntimeException("Cannot handle: " + mode);
		}
	}
}
