package gltools.texture;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class TextureUtils {
	public static final int BYTES_PER_PIXEL_RGBA = 4; //4 for RGBA, 3 for RGB
	public static final int BYTES_PER_PIXEL_GREYSCALE = 1; //1 channel
	
	@Deprecated
	public static ByteBuffer s_imageToByteBuffer(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight());
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage image = op.filter(img, null);

		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL_RGBA); //4 for RGBA, 3 for RGB

		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
				buffer.put((byte) (pixel & 0xFF));               // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
			}
		}

		buffer.flip(); //DO NOT FORGET THIS
		return buffer;
	}
	@Deprecated
	public static ByteBuffer s_imageGreyscaleToByteBuffer(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight());
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage image = op.filter(img, null);
		
		Raster raster = image.getData();
		
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
}
