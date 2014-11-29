package gltools.gl;


public interface GL extends GL4 {
	public GLVersion getGLVersion();
	
	public void init();
	public void destroy();
	
	public void makeCurrent();
	public void releaseCurrent();
	
	public boolean isCurrent();
	
	public GL1 getGL1();
	public GL2 getGL2();
	public GL3 getGL3();
	public GL4 getGL4();
}
