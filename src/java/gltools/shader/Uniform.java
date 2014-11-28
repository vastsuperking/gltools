package gltools.shader;

import glcommon.BufferUtils;
import glcommon.vector.Matrix2f;
import glcommon.vector.Matrix3f;
import glcommon.vector.Matrix4f;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.gl.GL2;

import java.nio.FloatBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Uniform extends Input {
	public static final Logger logger = LoggerFactory.getLogger(Uniform.class);
	
	private int m_id;
	private Program m_program;
	
	public Uniform(Program program, String name) {
		setProgram(program);
		setName(name);
	}
	
	public Uniform(Uniform uniform) {
		setID(uniform.getID());
	}
	public Uniform() {}

	public int getID() {return m_id;}
	public Program getProgram() { return m_program; }
	
	protected void setID(int id) { m_id = id; }
	public void setProgram(Program prog) { m_program = prog; }

	
	public void setValue(GL2 gl, int val) {
		if (!isActive()) return;
		checkProgBound();
		gl.glUniform1i(getID(), val);
	}
	public void setValue(GL2 gl, boolean val) {
		if (!isActive()) return;
		checkProgBound();
		gl.glUniform1i(getID(), val ? 1 : 0);
	}
	public void setValue(GL2 gl, float val) {
		if (!isActive()) return;
		checkProgBound();
		gl.glUniform1f(getID(), val);
	}
	public void setValue(GL2 gl, Vector2f vec) {
		if (!isActive()) return;
		checkProgBound();
		gl.glUniform2f(getID(), vec.getX(), vec.getY());
	}
	public void setValue(GL2 gl, Vector3f vec) {
		if (!isActive()) return;
		checkProgBound();
		gl.glUniform3f(getID(), vec.getX(), vec.getY(), vec.getZ());
	}
	public void setValue(GL2 gl, Vector4f vec) {
		if (!isActive()) return;
		//System.out.println("Setting " + getName() + " to " + vec);
		//Thread.dumpStack();
		checkProgBound();
		gl.glUniform4f(getID(), vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	public void setValue(GL2 gl, Matrix2f mat) {
		if (!isActive()) return;
		checkProgBound();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(9);
		mat.storeTranspose(buffer);
		buffer.flip();
		gl.glUniformMatrix2(getID(), false, buffer);
	}
	public void setValue(GL2 gl, Matrix3f mat) {
		if (!isActive()) return;
		checkProgBound();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(9);
		mat.storeTranspose(buffer);
		buffer.flip();
		gl.glUniformMatrix3(getID(), false, buffer);
	}
	public void setValue(GL2 gl, Matrix4f mat) {
		if (!isActive()) return;
		checkProgBound();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		mat.store(buffer);
		buffer.flip();
		gl.glUniformMatrix4(getID(), true, buffer);
	}
	
	public void init(GL2 gl) {
		updateID(gl);
	}
	
	public void updateID(GL2 gl) {
		updateID(true, gl);
	}
	
	/**
	 * If the uniform ID(or location) has changed(due to recompilation or other),
	 * then this function will update the ID
	 * If the new id is invalid(-1) and the autoActivate flag is set, the uniform will be deactived, 
	 * if it is valid and the flag is set, the uniform will be activated if it is not already active
	 * @param autoActivate If set, the Uniform will automatically activate or deactivate itself depending on the new id 
	 */
	public void updateID(boolean autoActivate, GL2 gl) {
		int id = gl.glGetUniformLocation(m_program.getID(), getName());

		setID(id);
		if (autoActivate && id == -1) {
			if (isActive()) { 
				logger.debug("Deactivating Uniform: " + this + " due to invalid id(-1)");
				setActive(false);
			}
		} else if (autoActivate) {
			if (!isActive()) {
				logger.debug("Activating Uniform " + this + " due to valid id: " + id);
				setActive(true);
			}
		}
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + getID();
		hash = hash * 31 + getProgram().getID();
		return hash;
	}
	
	@Override
	public String toString() {
		return "Uniform(" + getName() + ", " + m_id + ", Active: " + isActive() + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		//If the object is a uniform and the uniform id and program id match, they are the same
		return o instanceof Uniform && ((Uniform) o).getID() == getID() 
				&& ((Uniform) o).getProgram().getID() == getProgram().getID();
	}
	
	//------------------------
	private void checkProgBound() {
		//System.out.println("Current program: " + Program.s_getCurrent() + " looking for: " + m_program);
		if (Program.s_getCurrent() != m_program) throw new RuntimeException("Program must be bound before setValue()!");
	}
}