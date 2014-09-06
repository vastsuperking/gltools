package gltools.utils;

/**
 * Represents and object that can be loaded into GPU memory via the load() function
 * can update a value or do something, bind a shader, etc.
 */
public interface Loadable {
	public void load();
}
