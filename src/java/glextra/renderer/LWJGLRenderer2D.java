package glextra.renderer;

import glextra.material.Material;


public class LWJGLRenderer2D implements Renderer2D {
	private static LWJGLRenderer2D INSTANCE = null;
	private LWJGLRenderer2D() {}
	
	@Override
	public void init() {
		
	}

	@Override
	public void setMaterial(Material mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	public LWJGLRenderer2D getInstance() {
		if (INSTANCE == null) INSTANCE = new LWJGLRenderer2D();
		return INSTANCE;
	}
}
