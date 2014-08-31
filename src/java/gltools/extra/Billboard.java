package gltools.extra;

import gltools.display.Camera;
import gltools.vector.Vector3f;

public class Billboard {
	public enum BillboardMode{ MATCH_ALL, MATCH_YAW }
	
	private Vector3f m_pos;
	private BillboardMode m_mode;
	private float m_width;
	private float m_height;
	
	public Billboard(Vector3f pos, float width, float height, BillboardMode mode) {
		m_pos = pos;
		m_width = width;
		m_height = height;
	}
	
	public Vector3f[] generate(Camera cam) {
		switch(m_mode) {
		case MATCH_ALL: return s_generateBillboardMatchAll(cam, m_pos, m_width, m_height);
		default: return null;
		}
	}
	
	public static Vector3f[] s_generateBillboardMatchAll(Camera cam, Vector3f pos, float width, float height) {
		
		Vector3f camUp = cam.getUpDirection();
		Vector3f camRight = cam.getRightDirection();
		
		Vector3f posTemp = new Vector3f(pos);
		Vector3f heightEdge = camUp.scale(height, null);
		Vector3f widthEdge = camRight.scale(width, null);
		
		Vector3f bl = posTemp.sub(camUp.scale(0.5f * height, null)).sub(camRight.scale(0.5f * width, null));
		Vector3f br = bl.add(widthEdge, null);
		Vector3f tl = bl.add(heightEdge, null);
		
		Vector3f tr = tl.add(widthEdge, null);
		return new Vector3f[] {tr, tl, bl, br};
	}
}
