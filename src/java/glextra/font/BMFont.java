package glextra.font;

import glcommon.image.Image2D;

import java.awt.Rectangle;
import java.util.HashMap;

public class BMFont implements Font {
	private int m_descent;
	private int m_ascent;
	private HashMap<Character, Glyph> m_glyphs = new HashMap<Character, Glyph>();
	
	private String m_name;
	private int m_size;
	private boolean m_italic;
	private boolean m_bold;
	
	@Override
	public int getAscent() { return m_ascent; }
	@Override
	public int getDescent() { return m_descent; }
	@Override
	public int getHeight() { return m_ascent + m_descent; }
	
	@Override
	public String getName() { return m_name; }
	@Override
	public int getSize() { return m_size; }
	
	@Override
	public boolean isItalic() { return m_italic; }
	@Override
	public boolean isBold() { return m_bold; }
	
	public void setAscent(int ascending) { m_ascent = ascending; }
	public void setDescent(int descending) { m_descent = descending; }
	public void setName(String name) { m_name = name; }
	public void setSize(int size) { m_size = size; }
	public void setItalic(boolean i) { m_italic = i; }
	public void setBold(boolean b) { m_bold = b; } 
	
	public void addGlyph(char c, Glyph g) {
		m_glyphs.put(c, g);
	}
	
	public Glyph getGlyph(char c) {
		return m_glyphs.get(c);
	}
	/**
	 * Retrieves the minimum size of a string of text
	 */
	@Override
	public Rectangle getBounds(String text) {
		int height = 0;
		int width = 0;
		//Initialize y to the maximum int
		int y = Integer.MAX_VALUE;
		
		char[] chars = text.toCharArray();
		//First, find the lowest yoffset
		for (char c : chars) {
			Glyph g = getGlyph(c);
			if (g != null) y = Math.min(g.getYOff(), y);
		}
		for (char c : chars) {
			Glyph g = getGlyph(c);
			if (g != null) {
				height = Math.max(g.getHeight() + (g.getYOff() - y), height);
				width += g.getXAdvance();
			}
		}
		return new Rectangle(0, y, width, height);
	}
	/**
	 * A glyph represents a character in white, as well
	 * and holds important info about that character
	 */
	public static class BMGlyph implements Glyph {
		private final char m_char;
		private final Image2D m_glyph;
		

		private final int m_xoffset;
		//Describes the y offset from the top line of the font (y coord + ascent)
		//+ is below, - is above so the y is 
		//- getAscending() (to move the cursor to the top of the line) + yoffset
		private final int m_yoffset;
		private final int m_xadvance;
		
		private final Font m_font;
		
		public BMGlyph(Font f, char c, Image2D image, int xoff, int yoff, int xadv) {
			m_font = f;
			m_char = c;
			m_glyph = image;
			m_xoffset = xoff;
			m_yoffset = yoff;
			m_xadvance = xadv;
		}
		public Font getFont() { return m_font; }
		public char getChar() { return m_char; }
		
		public int getXOff() { return m_xoffset; }
		public int getYOff() { return m_yoffset; }
		public int getXAdvance() { return m_xadvance; }
		
		public int getWidth() { return m_glyph.getWidth(); }
		public int getHeight() { return m_glyph.getHeight(); }
		
		public Image2D getImage() { return m_glyph; }
	}
}
