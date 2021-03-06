package glcommon.image;

import glcommon.BufferUtils;
import glcommon.util.Pair;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.nio.ByteBuffer;

public class ImageUtils {
	public static final int BYTES_PER_PIXEL_RGBA = 4; //4 for RGBA, 3 for RGB
	//DO NOT USE, texture buffer must be power of 2
	public static final int BYTES_PER_PIXEL_RGB = 3; //4 for RGBA, 3 for RGB
	public static final int BYTES_PER_PIXEL_GREYSCALE = 1; //1 channel


	public static Pair<ImageFormat, ByteBuffer> s_imageToByteBuffer(BufferedImage img) {
		int fmtInt = img.getType();
		switch(fmtInt) {
		case BufferedImage.TYPE_BYTE_GRAY: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.R, s_imageGreyscaleToByteBuffer(img));

		case BufferedImage.TYPE_4BYTE_ABGR: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img)); 
		case BufferedImage.TYPE_4BYTE_ABGR_PRE: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img)); 
		case BufferedImage.TYPE_3BYTE_BGR: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img));
		case BufferedImage.TYPE_INT_ARGB: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img));
		case BufferedImage.TYPE_INT_RGB: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img));
		case BufferedImage.TYPE_CUSTOM: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img));
		case BufferedImage.TYPE_BYTE_INDEXED: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img));
		case BufferedImage.TYPE_BYTE_BINARY: return new Pair<ImageFormat, ByteBuffer>(ImageFormat.RGBA, s_imageColorToByteBufferRGBA(img));
		default: throw new RuntimeException(s_intFormatToString(fmtInt) + " is not supported!");
		}
	}
	public static ByteBuffer s_imageColorToByteBufferRGBA(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight());
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage image = op.filter(img, null);
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		//Makes pixels always in RGBA
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL_RGBA); //4 for RGBA, 3 for RGB
		
		//Do row by row
		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				byte red = (byte) ((pixel >> 16) & 0xFF);
				byte green = (byte) ((pixel >> 8) & 0xFF);
				byte blue = (byte) (pixel & 0xFF);
				byte alpha = (byte) ((pixel >> 24) & 0xFF);

				buffer.put(red);     // Red component
				buffer.put(green);      // Green component
				buffer.put(blue);               // Blue component
				buffer.put(alpha);    // Alpha component. Only for RGBA
			}
		}

		buffer.flip(); //DO NOT FORGET THIS
		return buffer;
	}
	public static ByteBuffer s_imageGreyscaleToByteBuffer(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight());
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage image = op.filter(img, null);

		Raster raster = image.getData();
		//Greyscale ... a little different from rgba
		int[] pixels = raster.getPixels(0, 0, image.getWidth(), image.getHeight(), new int[image.getWidth() * image.getHeight()]);

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL_GREYSCALE);

		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) (pixel & 0xFF));     // Black component
			}
		}
		buffer.flip();
		return buffer;
	}
	public static String s_intFormatToString(int fmt) {
		switch(fmt) {
		case BufferedImage.TYPE_4BYTE_ABGR: return "4BYTE_ABGR";
		case BufferedImage.TYPE_4BYTE_ABGR_PRE: return "4BYTE_ABGR_PRE";
		case BufferedImage.TYPE_3BYTE_BGR: return "3BYTE_BGR";	
		case BufferedImage.TYPE_BYTE_GRAY: return "BYTE_GRAY";
		case BufferedImage.TYPE_INT_ARGB: return "INT_ARGB";
		case BufferedImage.TYPE_INT_RGB: return "INT_RGB";
		case BufferedImage.TYPE_CUSTOM: return "CUSTOM";
		case BufferedImage.TYPE_BYTE_INDEXED: return "BYTE_INDEXED";
		case BufferedImage.TYPE_BYTE_BINARY: return "BYTE_BINARY";
		default: return "UNKNOWN_TYPE";
		}
	}
}
