package gltools.utils;

import gltools.shader.InputUsage;
import gltools.shader.Program;

public class InnerMat3fLoader implements Loadable {
	private GLMatrix4f m_matrix;
	private InputUsage m_usage;
	public InnerMat3fLoader(GLMatrix4f matrix, InputUsage usage) {
		m_matrix = matrix;
		m_usage = usage;
	}
	
	@Override
	public void load() {
		Program.s_getCurrent().getInputs(m_usage.getInputType(), 
										 m_usage).setValue(
									m_matrix.getCurrentMatrix().getInnerMat3f());
	}
}
