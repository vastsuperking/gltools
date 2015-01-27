package glcommon.image;

import glcommon.util.ResourceLocator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageIO {
	public static Image2D s_read(String resource, ResourceLocator locator) throws IOException {
		return s_read(locator.getResource(resource));
	}
	public static Image1D s_readImage1D(String resource, ResourceLocator locator) throws IOException {
		return s_readImage1D(locator.getResource(resource));
	}
	
	public static Image2D s_read(File file) throws IOException {
		FileInputStream is = new FileInputStream(file);
		try {
			return s_read(is);
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}
	}
	public static Image1D s_readImage1D(File file) throws IOException {
		FileInputStream is = new FileInputStream(file);
		try {
			return s_readImage1D(is);
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}
	}
	
	public static Image2D s_read(InputStream input) throws IOException {
		return new Image2D(javax.imageio.ImageIO.read(input));
	}
	public static Image1D s_readImage1D(InputStream input) throws IOException {
		return new Image1D(javax.imageio.ImageIO.read(input));
	}
}
