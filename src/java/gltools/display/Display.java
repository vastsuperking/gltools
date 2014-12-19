package gltools.display;

import glcommon.input.Keyboard;
import glcommon.input.Mouse;
import gltools.gl.GL;

public interface Display {
	public int getWidth();
	public int getHeight();
	
	public GL getGL();
	
	public Mouse getMouse();
	public Keyboard getKeyboard();

	
	public void init();
	public void destroy();
	public void update();
}
