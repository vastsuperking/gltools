package glextra.font;

import glcommon.Color;
import glcommon.image.Image2D;
import glextra.font.BMFont.BMGlyph;
import gltools.texture.Texture2D;
import gltools.texture.TextureFactory;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class JavaFontConverter {
	private static final float SPACE_WIDTH_PERCENTAGE = 0.25f;
	private static final float TAB_WIDTH_PERCENTAGE = 1f;

	private static final String ALL_CHARS_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ -1234567890+=!@#$%^&*()_|'\"/?><[]{},.`~:;\t\\";
	private static final char[] ALL_CHARS = ALL_CHARS_STRING.toCharArray();
	
	public static BMFont s_convert(Font font) {
		return s_convert(font, s_getAllChars(), Color.WHITE);
	}
	public static BMFont s_convert(Font font, char[] chars) {
		return s_convert(font, chars, Color.WHITE);
	}
	//Don't fool around with the color unless you know what you are doing!!!1
	public static BMFont s_convert(Font font, Color color) {
		return s_convert(font, s_getAllChars(), color);
	}
	//Don't fool around with the color unless you know what you are doing!!!1
	public static BMFont s_convert(Font font, char[] chars, Color color) {
		BMFont bmfont = new BMFont();
		bmfont.setSize(font.getSize());
		bmfont.setItalic(font.isItalic());
		bmfont.setBold(font.isBold());
		bmfont.setName(font.getFontName());
		
		
		FontMetrics metrics = new Canvas().getFontMetrics(font);
		java.awt.Color jcolor = color.toJava();
		
		bmfont.setAscent(metrics.getAscent());
		bmfont.setDescent(metrics.getDescent());
		for(char c : chars) {
			int width = s_getCharWidth(c, metrics);
			int height = s_getCharHeight(c, metrics);
			
			BufferedImage image  =new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2d = (Graphics2D) image.getGraphics();
			g2d.setColor(jcolor);
			g2d.setFont(font);
			g2d.drawString(Character.toString(c), 0, height - metrics.getDescent());
			g2d.dispose();
			
			Texture2D texture = TextureFactory.s_loadTexture(new Image2D(image));
			
			//Add a one pixel extra offset to the xadvance
			BMGlyph glyph = new BMGlyph(bmfont, c, texture, 0, 0, width + 1);
			bmfont.addGlyph(c, glyph);
		}
		return bmfont;
	}
	public static char[] s_getAllChars() {
		return ALL_CHARS;
	}
	private static int s_getCharWidth(char c, FontMetrics metrics) {
		int width = metrics.charWidth(c);
		if (c == ' ') width = (int) (SPACE_WIDTH_PERCENTAGE * metrics.getHeight());
		if (c == '\t') width = (int) TAB_WIDTH_PERCENTAGE * metrics.getHeight();
		return width;
	}
	private static int s_getCharHeight(char c, FontMetrics metrics) {
		return metrics.getHeight();
	}
}
