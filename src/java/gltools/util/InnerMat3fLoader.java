package gltools.util;

import gltools.gl.GL;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;

public class InnerMat3fLoader implements Loadable {
	private GLMatrix4f m_matrix;
	
	//Default usage if none is specified in load()
	private InputUsage m_fallbackUsage;
	
	public InnerMat3fLoader(GLMatrix4f matrix) {
		m_matrix = matrix;
	}
	public InnerMat3fLoader(GLMatrix4f matrix, InputUsage usage) {
		m_matrix = matrix;
		m_fallbackUsage = usage;
	}
	
	public void load(GL gl) {
		if (m_fallbackUsage == null) throw new RuntimeException("No fallback usage specified");
		load(m_fallbackUsage, gl);
	}
	
	public void load(InputUsage usage, GL gl) {
		if (usage.getDataType() != DataType.MAT3) throw new IllegalArgumentException(usage + " not of type MAT3!");
		if (Program.s_getCurrent() == null) {
			System.err.println("Warning! No current program, mat3 value not set!");
			return;
		}
		Program.s_getCurrent().getInputs(usage.getInputType(), usage).setValue(
				m_matrix.getCurrentMatrix().getInnerMat3f(), gl.getGL2());
	}
}
