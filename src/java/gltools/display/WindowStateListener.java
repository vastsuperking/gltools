package gltools.display;

public interface WindowStateListener {
	public void windowClosed(Window window);
	public void windowRefresh(Window window);
	public void windowFocused(Window window);
	public void windowUnfocused(Window window);
	public void windowMinimized(Window window);
	public void windowMaximized(Window window);
}
