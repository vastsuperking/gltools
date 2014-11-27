package gltools.util;

import glcommon.vector.Matrix3f;
import gltools.gl.GL;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Uniform;

import java.util.Stack;

//Kind of like glMatrixMode
/**
 * Combines Matrix4f and InputUsage...
 * Also contains a matrix stack for pushing and popping
 */
public class GLMatrix3f implements Loadable {
	/*
	//Temporarily in here....
	public static GLMatrix4f s_model 		= new GLMatrix4f(InputUsage.MODEL_MATRIX); 
	public static GLMatrix4f s_view 		= new GLMatrix4f(InputUsage.VIEW_MATRIX); 
	public static GLMatrix4f s_projection	= new GLMatrix4f(InputUsage.PROJECTION_MATRIX); 
	//TODO: Should be 3f, but matrix3f not yet implemented
	public static GLMatrix4f s_normal 		= new GLMatrix4f(InputUsage.NORMAL_MATRIX);
	*/
	private Matrix3f m_matrix = new Matrix3f();
	private Stack<Matrix3f> m_stack = new Stack<Matrix3f>();
	
	//Default usage if none is specified with load()
	private InputUsage m_fallbackUsage = null;

	public GLMatrix3f() { m_fallbackUsage = null; }
	public GLMatrix3f(InputUsage usage) {
		m_fallbackUsage = usage;
	}

	public Matrix3f getCurrentMatrix() { return m_matrix; }
	public InputUsage getUsage() { return m_fallbackUsage; }
	public void setCurrentMatrix(Matrix3f mat) { m_matrix = mat; }
	public void setUsage(InputUsage usage) { m_fallbackUsage = usage; }
	 
	public void push() {
		m_stack.push(new Matrix3f(m_matrix));
	}
	public void pop() {
		m_matrix = m_stack.pop();
	}
	
	public void setIdentity() {
		m_matrix = new Matrix3f();
	}
	 
	/**
	 * Will update the matrix so the openGL and local matrices are in sync,
	 * using the default usage
	 */
	public void load(GL gl) {
		if (m_fallbackUsage == null) throw new RuntimeException("No default usage!");
		load(m_fallbackUsage, gl);
	}
	@Override
	public void load(InputUsage usage, GL gl) {
		if (usage.getDataType() != DataType.MAT3) throw new RuntimeException("Not a mat3!");
		if (Program.s_getCurrent() == null) {
			System.err.println("Warning! No current program, matrix value not set!");
			return;
		}
		Program.s_getCurrent().getInputs(Uniform.class, usage).setValue(m_matrix, gl.getGL2());
	}
}
