package glcommon.image;

/**
 * An image instance is tied to a certain image
 * and represents that image in a different system
 * eg. opengl texture
 */
public interface ImageInstance {
	public void delete();
	public void update(Image image);
	public interface Key<T extends ImageInstance> {}
}
