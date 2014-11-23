package glextra.renderer;

import glcommon.vector.Matrix3f;
import glcommon.vector.Vector2f;
import glextra.font.Font;
import glextra.material.Material;
import gltools.display.Window;

public interface Renderer2D {
	public void init(float left, float right, float top, float bottom, Window display);
	
	public void setMaterial(Material mat);
	public Material getMaterial();
	
	public void setFont(Font font);
	public Font getFont();
	
	public float getCSTop();
	public float getCSBottom();
	public float getCSRight();
	public float getCSLeft();
	
	public float getCSWidth();
	public float getCSHeight();
	
	public Window getDisplay();
	
	public void updateProjection(float left, float right, float top, float bottom);
	
	public void viewTrans(float x, float y);
	public void viewScale(float x, float y);
	
	public void translate(float x, float y);
	public void scale(float x, float y);
	public void rotate(float radians);
	
	public Vector2f getViewTranslation();
	public Vector2f getModelTranslation();
	
	public Vector2f getViewScale();
	public Vector2f getModelScale();
	
	public float getViewRotation();
	public float getModelRotation();
	
	public Matrix3f getModelMatrix();
	public Matrix3f getViewMatrix();
	
	public void pushView();
	public void popView();
	
	public void pushModel();
	public void popModel();
	
	
	public void fillRect(float x, float y, float width, float height);
	public void fillRect(float x, float y, float width, float height, float texCoordWidth, float texCoordHeight);
	
	public void drawString(float x, float y, float scale, String string);
	
	public void renderLight(Light light);
	
	public void startGeometry();
	public void finishGeometry();
	
	public void startLighted();
	public void finishLighted();

	public void doLightingComputations();
	
	public void clear();
}

