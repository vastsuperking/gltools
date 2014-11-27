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
	public static Texture2D s_loadTexture(GL1 gl, String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture(gl, locator.getResource(resource));
	}
	public static Texture2D s_loadTexture(GL1 gl, File file) throws IOException {
		return s_loadTexture(gl, ImageIO.s_read(file));
	}
	public static Texture2D s_loadTexture(GL1 gl, InputStream input) throws IOException {
		return s_loadTexture(gl, ImageIO.s_read(input));
	}
	public static Texture2D s_loadTexture(GL1 gl, Image2D image) {
		Texture2D tex = new Texture2DBuilder().setImage(image).build();
		tex.init(gl);
		tex.bind(gl);
		tex.load(gl);
		tex.unbind(gl);
		return tex;
	}
	public static Texture1D s_loadTexture1D(GL1 gl, String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture1D(gl, locator.getResource(resource));
	}
	public static Texture1D s_loadTexture1D(GL1 gl, File file) throws IOException {
		return s_loadTexture1D(gl, new FileInputStream(file));
	}
	public static Texture1D s_loadTexture1D(GL1 gl, InputStream input) throws IOException {
		return s_loadTexture1D(gl, ImageIO.s_readImage1D(input));
	}
	public static Texture1D s_loadTexture1D(GL1 gl, Image1D image) {
		Texture1D tex = new Texture1DBuilder().setImage(image).build();
		tex.init(gl);
		tex.bind(gl);
		tex.load(gl);
		tex.unbind(gl);
		return tex;
	}
	public static Texture1D s_loadTexture1DGreyscale(GL1 gl, String resource, ResourceLocator locator) throws IOException {
		return s_loadTexture1DGreyscale(gl, locator.getResource(resource));
	}
	public static Texture1D s_loadTexture1DGreyscale(GL1 gl, File file) throws IOException {
		return s_loadTexture1DGreyscale(gl, ImageIO.s_readImage1D(file));
	}
	public static Texture1D s_loadTexture1DGreyscale(GL1 gl, InputStream input) throws IOException {
		return s_loadTexture1DGreyscale(gl, ImageIO.s_readImage1D(input));
	}
	public static Texture1D s_loadTexture1DGreyscale(GL1 gl, Image1D image) {
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
