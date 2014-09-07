package glextra.renderer;

import glextra.material.Material;

public interface Renderer2D {
	public void init(float left, float right, float top, float bottom);
	
	public void setMaterial(Material mat);
	public Material getMaterial();
		
	public void viewTrans(int x, int y);
	public void viewScale(int x, int y);
	
	public void translate(int x, int y);
	public void scale(int x, int y);
	public void rotate(float radians);
	
	public void pushView();
	public void popView();
	
	public void pushModel();
	public void popModel();
	
	public void startGeometry();
	public void fillRect(int x, int y, int width, int height);
	public void finishGeometry();
}

