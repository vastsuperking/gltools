package gltools.shader;

import gltools.BufferUtils;
import gltools.vector.Matrix2f;
import gltools.vector.Matrix3f;
import gltools.vector.Matrix4f;
import gltools.vector.Vector2f;
import gltools.vector.Vector3f;
import gltools.vector.Vector4f;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL20;


public class Uniform extends Input {
	private int m_id;
	private Program m_program;
	
	public Uniform(Program program, String name) {
		setProgram(program);
		setName(name);
		updateID(true);
	}
	public Uniform(Uniform uniform) {
		setID(uniform.getID());
	}
	public Uniform() {}

	public int getID() {return m_id;}
	public Program getProgram() { return m_program; }
	
	public void setID(int id) { m_id = id; }
	public void setProgram(Program prog) { m_program = prog; }
	
	public void setValue(int val) {
		if (!isActive()) return;
		checkProgBound();
		GL20.glUniform1i(getID(), val);
	}
	public void setValue(boolean val) {
		if (!isActive()) return;
		checkProgBound();
		GL20.glUniform1i(getID(), val ? 1 : 0);
	}
	public void setValue(float val) {
		if (!isActive()) return;
		checkProgBound();
		GL20.glUniform1f(getID(), val);
	}
	public void setValue(Vector2f vec) {
		if (!isActive()) return;
		checkProgBound();
		GL20.glUniform2f(getID(), vec.getX(), vec.getY());
	}
	public void setValue(Vector3f vec) {
		if (!isActive()) return;
		checkProgBound();
		GL20.glUniform3f(getID(), vec.getX(), vec.getY(), vec.getZ());
	}
	public void setValue(Vector4f vec) {
		if (!isActive()) return;
		checkProgBound();
		GL20.glUniform4f(getID(), vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	public void setValue(Matrix2f mat) {
		if (!isActive()) return;
		checkProgBound();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(9);
		mat.storeTranspose(buffer);
		buffer.flip();
		GL20.glUniformMatrix2(getID(), false, buffer);
	}
	public void setValue(Matrix3f mat) {
		if (!isActive()) return;
		checkProgBound();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(9);
		mat.storeTranspose(buffer);
		buffer.flip();
		GL20.glUniformMatrix3(getID(), false, buffer);
	}
	public void setValue(Matrix4f mat) {
		if (!isActive()) return;
		checkProgBound();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		mat.store(buffer);
		/*System.out.println("\n\n\n------------------Storing Matrix----------------\n\n\n");
		System.out.println("Original: " + mat);
		System.out.println("In buffer:");
		System.out.println(BufferUtils.asString(buffer));*/
		buffer.flip();
		GL20.glUniformMatrix4(getID(), true, buffer);
	}
	/**
	 * If the uniform ID(or location) has changed(due to recompilation or other),
	 * then this function will update the ID
	 * If the new id is invalid(-1) and the autoActivate flag is set, the uniform will be deactived, 
	 * if it is valid and the flag is set, the uniform will be activated if it is not already active
	 * @param autoActivate If set, the Uniform will automatically activate or deactivate itself depending on the new id 
	 */
	public void updateID(boolean autoActivate) {
		int id = s_getUniformID(m_program, getName());
		if (autoActivate && id == -1) {
			if (isActive()) System.out.println("Deactivating Uniform: " + this + " due to invalid id(-1)");
			setActive(false);
		} else if (autoActivate) {
			if (!isActive()) System.out.println("Reactivating Uniform " + this + " due to valid id: " + id);
			setActive(true);
		}
		m_id = id;
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
	public static int s_getUniformID(Program p, String name) {
		return GL20.glGetUniformLocation(p.getID(), name);
	}
}