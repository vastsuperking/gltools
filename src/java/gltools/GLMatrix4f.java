package gltools;

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
public class GLMatrix4f {
	//Temporarily in here....
	public static GLMatrix4f s_model 		= new GLMatrix4f(InputUsage.MODEL_MATRIX); 
	public static GLMatrix4f s_view 		= new GLMatrix4f(InputUsage.VIEW_MATRIX); 
	public static GLMatrix4f s_projection	= new GLMatrix4f(InputUsage.PROJECTION_MATRIX); 
	//TODO: Should be 3f, but matrix3f not yet implemented
	public static GLMatrix4f s_normal 		= new GLMatrix4f(InputUsage.NORMAL_MATRIX);
	
	private Matrix4f m_matrix = new Matrix4f();
	private Stack<Matrix4f> m_stack = new Stack<Matrix4f>();
	private InputUsage m_usage = null;
	

	public GLMatrix4f(InputUsage usage) {
		m_usage = usage;
	}

	public Matrix4f getMatrix() { return m_matrix; }
	public InputUsage getUsage() { return m_usage; }
	public void setMatrix(Matrix4f mat) { m_matrix = mat; }
	public void setUsage(InputUsage usage) { m_usage = usage; }
	 
	public void push() {
		m_stack.push(new Matrix4f(m_matrix));
	}
	public void pop() {
		m_matrix = m_stack.pop();
	}
	 
	/**
	 * Will update the matrix so the openGL and local matrices are in sync
	 */
	public void load() {
		if (Program.s_getCurrent() == null) {
			System.err.println("Warning! No current program, matrix value not set!");
			return;
		}
		Program.s_getCurrent().getInputs(Uniform.class, m_usage).setValue(m_matrix);
	}
}
