package gltools.util;

import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Uniform;
import gltools.vector.Matrix4f;

import java.util.Stack;

//Kind of like glMatrixMode
/**
 * Combines Matrix4f and InputUsage...
 * Also contains a matrix stack for pushing and popping
 */
public class GLMatrix4f implements Loadable {
	private Matrix4f m_matrix = new Matrix4f();
	private Stack<Matrix4f> m_stack = new Stack<Matrix4f>();
	private InputUsage m_usage = null;
	

	public GLMatrix4f(InputUsage usage) {
		m_usage = usage;
	}

	public Matrix4f getCurrentMatrix() { return m_matrix; }
	public InputUsage getUsage() { return m_usage; }
	public void setCurrentMatrix(Matrix4f mat) { m_matrix = mat; }
	public void setUsage(InputUsage usage) { m_usage = usage; }
	 
	public void push() {
		m_stack.push(new Matrix4f(m_matrix));
	}
	public void pop() {
		m_matrix = m_stack.pop();
	}
	 
	/**
	 * Will update the matrix so the openGL and local matrices are in sync,
	 * using the default usage
	 */
	public void load() {
		if (m_usage == null) throw new RuntimeException("Default usage is null!");
		load(m_usage);
	}
	public void load(InputUsage usage) {
		if (usage.getDataType() != DataType.MAT4) throw new RuntimeException("Not a mat4!");
		if (Program.s_getCurrent() == null) {
			System.err.println("Warning! No current program, matrix value not set!");
			return;
		}
		Program.s_getCurrent().getInputs(Uniform.class, usage).setValue(m_matrix);
	}
}
