package gltools.gl;

public interface GL {
	public void glClear(boolean color, boolean depth, boolean stencil, boolean accum);
	public void glClearAccum(boolean red, boolean green, boolean blue, boolean alpha);
}
