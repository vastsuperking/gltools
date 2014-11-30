package glextra.renderer;

import glcommon.image.Image2D;
import glcommon.image.ImageInstance;
import gltools.gl.GL1;
import gltools.texture.Texture2D;

public class GLTextureManager implements ImageInstance.Key<GLImageInstance> {
	public Texture2D getTexture(GL1 gl, Image2D img) {
		GLImageInstance texture = null;
		
		if (img.hasInstance(this)) {
			texture = img.getInstance(this);
		} else {
			texture = new GLImageInstance(gl, img);
			img.addInstance(this, texture);
		}
		
		return texture.getGLTexture();
	}
}
