package gltools.display;

import glcommon.input.Keyboard;
import glcommon.input.Mouse;
import gltools.gl.GL;

public abstract class GLApplication {	
	public abstract void init(GL gl, Mouse m, Keyboard k, int width, int height);
	public abstract void destroy(GL gl);
	
	public abstract void resize(GL gl, int width, int height);
	
	public abstract void render(GL gl);
	
	public abstract void update(long delta);	
}
