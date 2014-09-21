package gltools.utils;

import gltools.shader.InputUsage;

/**
 * Represents and object that can be loaded into GPU memory via the load() function
 * can update a value or do something, bind a shader, etc.
 */
public interface Loadable {
	public void load(InputUsage usage);
}
