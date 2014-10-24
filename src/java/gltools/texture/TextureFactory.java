package gltools.texture;

import glcommon.image.Image1D;
import glcommon.image.Image2D;
import glcommon.image.ImageIO;
import glcommon.util.ResourceLocator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class TextureFactory {
	public static Texture2D s_loadTexture(String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture(locator.getResource(resource));
	}
	public static Texture2D s_loadTexture(File file) throws IOException {
		return s_loadTexture(ImageIO.s_read(file));
	}
	public static Texture2D s_loadTexture(InputStream input) throws IOException {
		return s_loadTexture(ImageIO.s_read(input));
	}
	public static Texture2D s_loadTexture(Image2D image) {
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
		return s_loadTexture1D(ImageIO.s_readImage1D(input));
	}
	public static Texture1D s_loadTexture1D(Image1D image) {
		Texture1D tex = new Texture1DBuilder().setImage(image).build();
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
	public static Texture1D s_loadTexture1DGreyscale(String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture1DGreyscale(locator.getResource(resource));
	}
	public static Texture1D s_loadTexture1DGreyscale(File file) throws IOException {
		return s_loadTexture1DGreyscale(ImageIO.s_readImage1D(file));
	}
	public static Texture1D s_loadTexture1DGreyscale(InputStream input) throws IOException {
		return s_loadTexture1DGreyscale(ImageIO.s_readImage1D(input));
	}
	public static Texture1D s_loadTexture1DGreyscale(Image1D image) {
		Texture1DBuilder builder = new Texture1DBuilder();
		builder.setImage(image);
		builder.setFormat(TextureFormat.RED8);
		Texture1D tex = builder.build();
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
	public static Texture1D s_createBlank(int length) {
		Texture1D tex = new Texture1DBuilder().setLength(length).build();
		tex.bind();
		tex.load();
		tex.unbind();
		return tex;
	}
}
