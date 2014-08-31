package gltools.display;

import gltools.input.Keyboard;
import gltools.input.Mouse;

public interface Display {
	public int getWidth();
	public int getHeight();
	
	public void addResizedListener(ResizeListener rl);
	
	public Mouse getMouse();
	public Keyboard getKeyboard();
	
	public void init();
	public void destroy();
	public boolean closeRequested();
	public void update(int fps);
}
