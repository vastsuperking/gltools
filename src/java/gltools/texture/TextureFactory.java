package gltools.texture;

import glcommon.image.Image1D;
import glcommon.image.Image2D;
import glcommon.image.ImageIO;
import glcommon.util.ResourceLocator;
import gltools.gl.GL1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class TextureFactory {
	public static Texture2D s_loadTexture(String resource, ResourceLocator locator, GL1 gl) throws IOException {
		return s_loadTexture(locator.getResource(resource), gl);
	}
	public static Texture2D s_loadTexture(File file, GL1 gl) throws IOException {
		return s_loadTexture(ImageIO.s_read(file), gl);
	}
	public static Texture2D s_loadTexture(InputStream input, GL1 gl) throws IOException {
		return s_loadTexture(ImageIO.s_read(input), gl);
	}
	public static Texture2D s_loadTexture(Image2D image, GL1 gl) {
		Texture2D tex = new Texture2DBuilder().setImage(image).build();
		tex.init(gl);
		tex.bind(gl);
		tex.load(gl);
		tex.unbind(gl);
		return tex;
	}
	public static Texture1D s_loadTexture1D(String resource, ResourceLocator locator, GL1 gl) throws IOException {
		return s_loadTexture1D(locator.getResource(resource), gl);
	}
	public static Texture1D s_loadTexture1D(File file, GL1 gl) throws IOException {
		return s_loadTexture1D(new FileInputStream(file), gl);
	}
	public static Texture1D s_loadTexture1D(InputStream input, GL1 gl) throws IOException {
		return s_loadTexture1D(ImageIO.s_readImage1D(input), gl);
	}
	public static Texture1D s_loadTexture1D(Image1D image, GL1 gl) {
		Texture1D tex = new Texture1DBuilder().setImage(image).build();
		tex.init(gl);
		tex.bind(gl);
		tex.load(gl);
		tex.unbind(gl);
		return tex;
	}
	public static Texture1D s_loadTexture1DGreyscale(String resource, ResourceLocator locator, GL1 gl) throws IOException {
		return s_loadTexture1DGreyscale(locator.getResource(resource), gl);
	}
	public static Texture1D s_loadTexture1DGreyscale(File file, GL1 gl) throws IOException {
		return s_loadTexture1DGreyscale(ImageIO.s_readImage1D(file), gl);
	}
	public static Texture1D s_loadTexture1DGreyscale(InputStream input, GL1 gl) throws IOException {
		return s_loadTexture1DGreyscale(ImageIO.s_readImage1D(input), gl);
	}
	public static Texture1D s_loadTexture1DGreyscale(Image1D image, GL1 gl) {
		Texture1DBuilder builder = new Texture1DBuilder();
		builder.setImage(image);
		builder.setFormat(TextureFormat.RED8);
		Texture1D tex = builder.build();
		tex.init(gl);
		tex.bind(gl);
		tex.load(gl);
		tex.unbind(gl);
		
		return tex;
	}
	

	public static Texture2D s_createBlank(int width, int height) {
		Texture2D tex = new Texture2DBuilder().setWidth(width).setHeight(height).build();
		return tex;
	}
	public static Texture1D s_createBlank(int length) {
		Texture1D tex = new Texture1DBuilder().setLength(length).build();
		return tex;
	}
}
