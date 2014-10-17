package glcommon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class BufferUtils {
		public static ByteBuffer createByteBuffer(int size) {
			return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
		}
		public static ShortBuffer createShortBuffer(int size) {
			return createByteBuffer(size << 1).asShortBuffer();
		}
		public static CharBuffer createCharBuffer(int size) {
			return createByteBuffer(size << 1).asCharBuffer();
		}
		public static IntBuffer createIntBuffer(int size) {
			return createByteBuffer(size << 2).asIntBuffer();
		}
		public static LongBuffer createLongBuffer(int size) {
			return createByteBuffer(size << 3).asLongBuffer();
		}
		public static FloatBuffer createFloatBuffer(int size) {
			return createByteBuffer(size << 2).asFloatBuffer();
		}
		public static DoubleBuffer createDoubleBuffer(int size) {
			return createByteBuffer(size << 3).asDoubleBuffer();
		}
		public static String asString(FloatBuffer buffer) { 
			int position = buffer.position();
			buffer.rewind();
			float[] dest = new float[buffer.capacity()];
			buffer.get(dest);
			buffer.position(position);
			return Arrays.toString(dest);
		}
		public static String asString(IntBuffer buffer) { 
			int position = buffer.position();
			buffer.rewind();
			int[] dest = new int[buffer.capacity()];
			buffer.get(dest);
			buffer.position(position);
			return Arrays.toString(dest);
		}
		public static void main(String[] args) {
			FloatBuffer buffer = BufferUtils.createFloatBuffer(3);
			buffer.put(10f).put(15f).put(20f);
			System.out.println(asString(buffer));
		}
}