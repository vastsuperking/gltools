package gltools.display;

import gltools.gl.GL;
import gltools.input.Keyboard;
import gltools.input.Mouse;

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
