package glcommon.vector;

public class MatrixUtils {
	public static Vector2f getTranslation(Matrix3f mat) {
		return new Vector2f(mat.m02, mat.m12);
	}
	public static Vector2f getScale(Matrix3f mat) {
		float a = mat.m00;
		float b = mat.m01;
		float c = mat.m10;
		float d = mat.m11;
		
		float sX = (float) (sign(a) * Math.sqrt(a*a + b*b));
		float sY = (float) (sign(d) * Math.sqrt(c*c + d*d));
		return new Vector2f(sX, sY);
	}
	public static float getRotation(Matrix3f mat) {
		float a = mat.m00;
		float b = mat.m01;
		return (float) Math.atan2(-b, a);
	}
	
	private static int sign(double a) {
		return a > 0 ? 1 : (a < 0 ? -1 : 0);
	}
}
