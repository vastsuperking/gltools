package glcommon.font;

import glcommon.image.Image2D;

import java.awt.Rectangle;

public interface Font {
	
	public int getAscent();
	public int getDescent();
	public int getHeight();
	
	public String getName();
	public int getSize();
	
	public boolean isItalic();
	public boolean isBold();
	
	public Glyph getGlyph(char c);
	
	public Rectangle getBounds(String text);
	
	public interface Glyph {
		public char getChar();
		//MAYBE remove these? Maybe later
		
		/**
		 * Returns the xOffset at the beginning of the character
		 * @return the xOffset
		 */
		public int getXOff();
		/**
		 * Returns the yoffset where the yoffset is
		 * + if offset is below
		 * - if offset is above
		 * this is equivalent to the descending length of the char
		 * to render this, just add it to the y location
		 * @return
		 */
		public int getYOff();
		
		/**
		 * How many pixels to advance the cursor after
		 * drawing the char
		 * @return the xAdvance
		 */
		public int getXAdvance();
		
		public int getHeight();
		public int getWidth();
		
		public Image2D getImage();
		
		/**
		 * Renders the char but does not move the cursor
		 * @param r the Renderer to use
		 * @param mat the Material which will be used, must have glyphTexture param
		 */
//		public void render(Renderer2D r, float scale, Material mat);
		/**
		 * Renders the char and translates to the next char's position
		 * Equivalent to:
		 * <code>
		 * render(r);
		 * r.translate(getXAdvance(), 0);
		 * </code>
		 * 
		 * @param r the Renderer to use
		 * @param mat the Material which will be used, must have glyphTexture param
		 */
//		public void renderAndTranslate(Renderer2D r, float scale, Material mat);
	}
}
