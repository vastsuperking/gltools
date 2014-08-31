package gltools.texture;

import gltools.ResourceLocator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class TextureFactory {
	public static Texture2D s_loadTexture(String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture(locator.getResource(resource));
	}
	public static Texture2D s_loadTexture(File file) throws IOException {
		return s_loadTexture(new FileInputStream(file));
	}
	public static Texture2D s_loadTexture(InputStream input) throws IOException {
		BufferedImage image = ImageIO.read(input);
		return s_loadTexture(image);
	}
	public static Texture2D s_loadTexture(BufferedImage image) {
		Texture2D tex = new Texture2DBuilder().setImage(image).build();
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
	public static Texture1D s_loadTexture1D(String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture1D(locator.getResource(resource));
	}
	public static Texture1D s_loadTexture1D(File file) throws IOException {
		return s_loadTexture1D(new FileInputStream(file));
	}
	public static Texture1D s_loadTexture1D(InputStream input) throws IOException {
		BufferedImage image = ImageIO.read(input);
		return s_loadTexture1D(image);
	}
	public static Texture1D s_loadTexture1D(BufferedImage image) {
		Texture1D tex = new Texture1D();
		//Determine the way the picture is: horizontal or vertical
		int width = image.getHeight() > image.getWidth() ? image.getHeight() : image.getWidth();
		tex.setWidth(width);
		tex.setData(TextureUtils.s_imageToByteBuffer(image));
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
	public static Texture1D s_loadTexture1DGreyscale(String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture1DGreyscale(locator.getResource(resource));
	}
	public static Texture1D s_loadTexture1DGreyscale(File file) throws IOException {
		return s_loadTexture1DGreyscale(new FileInputStream(file));
	}
	public static Texture1D s_loadTexture1DGreyscale(InputStream input) throws IOException {
		BufferedImage image = ImageIO.read(input);
		return s_loadTexture1DGreyscale(image);
	}
	public static Texture1D s_loadTexture1DGreyscale(BufferedImage image) {
		Texture1D tex = new Texture1D();
		//Determine the way the picture is: horizontal or vertical
		int width = image.getHeight() > image.getWidth() ? image.getHeight() : image.getWidth();
		tex.setWidth(width);
		tex.setFormat(TextureFormat.RED8);
		tex.setData(TextureUtils.s_imageGreyscaleToByteBuffer(image));
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
	
	public static Texture2D s_createBlank(int width, int height) {
		Texture2D tex = new Texture2DBuilder().setWidth(width).setHeight(height).build();
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
}
