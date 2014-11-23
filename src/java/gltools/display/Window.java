package gltools.display;

import gltools.input.Keyboard;
import gltools.input.Mouse;


public interface Window {
	public int getWidth();
	public int getHeight();
	
	public void addResizedListener(ResizeListener rl);
	
	public Mouse getMouse();
	public Keyboard getKeyboard();
	public boolean initialized();
	
	
	public void init(Monitor monitor);
	public void destroy();
	public boolean closeRequested();
	public void update(int fps);
}
