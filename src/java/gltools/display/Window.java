package gltools.display;

public interface Window extends Display {
	public int getX();
	public int getY();
	public String getTitle();
	public MonitorProvider getMonitorProvider();
	
	public boolean isResizable();
	public boolean isFullscreen();
	public boolean isVisible();
	public boolean isInitialized();	
	
	public void useFullscreen(Monitor monitor);
	public void setResizable(boolean resizable);
	public void setVisible(boolean visible);
	public void setX(int x);
	public void setY(int y);
	public void setPosition(int x, int y);
	public void setTitle(String title);
	
	public void addResizedListener(ResizeListener rl);
	public void addFileDropListener(FileDropListener fl);
	public void addMoveListener(MoveListener ml);
	public void addStateListener(WindowStateListener sl);
	
	public boolean closeRequested();
}
