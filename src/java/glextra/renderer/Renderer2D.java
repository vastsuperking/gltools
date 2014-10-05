package glextra.renderer;

import glextra.material.GlobalParamProvider;
import glextra.material.Material;

public interface Renderer2D {
	public void init(int displayWidth, int displayHeight, float left, float right, float top, float bottom);
	
	public void setMaterial(Material mat);
	public Material getMaterial();
	public GlobalParamProvider getGlobalParams();
		
	public void viewTrans(float x, float y);
	public void viewScale(float x, float y);
	
	public void translate(float x, float y);
	public void scale(float x, float y);
	public void rotate(float radians);
	
	public void pushView();
	public void popView();
	
	public void pushModel();
	public void popModel();
	
	public void startGeometry();
	public void fillRect(float x, float y, float width, float height);
	public void finishGeometry();
	
	public void startLighted();
	public void finishLighted();
	public void renderLight(Light light);
	public void doLightingComputations();
	
	public void clear();
}

