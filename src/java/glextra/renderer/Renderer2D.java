package glextra.renderer;

import glextra.material.Material;

public interface Renderer2D {
	public void init();
	
	public void setMaterial(Material mat);
	public void fillRect(int x, int y, int width, int height);
}

