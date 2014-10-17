package glcommon;

import glcommon.vector.Vector4f;

public class Color {
	public static Color BLACK = new Color(0f, 0f, 0f, 1f);
	public static Color RED = new Color(1f, 0f, 0f, 1f);
	public static Color BLUE = new Color(0f, 0f, 1f, 1f);
	public static Color GREEN = new Color(0f, 1f, 0f, 1f);
	public static Color ORANGE = new Color(1f, 0.50f, 0f, 1f);
	public static Color MAGENTA = new Color(1f, 0.50f, 1f, 1f);
	public static Color WHITE = new Color(1f, 1f, 1f, 1f);
	

	private float m_red = 1f;
	private float m_green = 1f;
	private float m_blue = 1f;
	private float m_alpha = 1f;
	
	public Color(float red, float green, float blue, float alpha) {
		set(red, green, blue, alpha);
	}
	public Color(Color c) {
		set(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}
	
	public Color(float r, float g, float b) {
		set(r, g, b, 1f);
	}
	public void set(float red, float green, float blue, float alpha) {
		m_red = red;
		m_green = green;
		m_blue = blue;
		m_alpha = alpha;
	}
	
	public float getRed() { return m_red; }
	public float getGreen() { return m_green; }
	public float getBlue() { return m_blue; }
	public float getAlpha() { return m_alpha; }
	public void setRed(float red) { m_red = red; }
	public void setGreen(float green) { m_green = green; }
	public void setBlue(float blue) { m_blue = blue; }
	public void setAlpha(float alpha) { m_alpha = alpha; }

	public float[] toFloats() {
		return new float[] { m_red, m_green, m_blue, m_alpha};
	}
	
	public Vector4f toVector4f() {
		return new Vector4f(m_red, m_green, m_blue, m_alpha);
	}

	public Color clone() { return new Color(m_red, m_green, m_blue, m_alpha); }
	
	
	public java.awt.Color toJava() {
		return new java.awt.Color((int) (m_red * 225), (int) (m_blue * 255), (int) (m_green * 255), (int) (m_alpha * 255));
	}
}
