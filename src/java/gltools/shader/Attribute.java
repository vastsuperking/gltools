package gltools.shader;

import gltools.vector.Matrix2f;
import gltools.vector.Matrix3f;
import gltools.vector.Matrix4f;
import gltools.vector.Vector2f;
import gltools.vector.Vector3f;
import gltools.vector.Vector4f;

import org.lwjgl.opengl.GL20;

public class Attribute extends Input {
	private int m_id;
	private Program m_program;
	
	public Attribute(Program program, String name) {
		setName(name);
		setProgram(program);
		updateID(true);
	}
	public Attribute(Attribute attribute) {
		setName(attribute.getName());
		setProgram(attribute.getProgram());
		setID(attribute.getID());
	}
	protected Attribute() {}
	
	public Program getProgram() { return m_program; }
	public int getID() {return m_id;}
	public int getIndex() { return m_id; }//index and id are the same, I hope

	public void setProgram(Program p) { m_program = p; }
	public void setID(int id) {m_id = id;}

	/**
	 * All attribute setValue() functions are deprecated
	 * Usage of VAO and VBOs are preferred
	 * These will most likely not be removed in the future, but their usage should be avoided
	 */
	@Deprecated
	public void setValue(int val) {
		throw new RuntimeException("Cannot set attribute to int!!");
	}
	@Deprecated
	public void setValue(boolean val) {
		throw new RuntimeException("Cannot set attribute to bool!!");
	}
	@Deprecated
	public void setValue(float val) {
		if (!isActive()) return;
		checkBound();
		GL20.glVertexAttrib1f(getID(), val);
	}
	@Deprecated
	public void setValue(Vector2f vec) {
		if (!isActive()) return;
		checkBound();
		GL20.glVertexAttrib2f(getID(), vec.getX(), vec.getY());
	}
	@Deprecated
	public void setValue(Vector3f vec) {
		if (!isActive()) return;
		checkBound();
		GL20.glVertexAttrib3f(getID(), vec.getX(), vec.getY(), vec.getZ());
	}
	@Deprecated
	public void setValue(Vector4f vec) {
		if (!isActive()) return;
		checkBound();
		GL20.glVertexAttrib4f(getID(), vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	public void setValue(Matrix2f mat) { throw new RuntimeException("Cannot set an attribute to a mat2"); }
	public void setValue(Matrix3f mat) { throw new RuntimeException("Cannot set an attribute to a mat3"); }
	public void setValue(Matrix4f mat) { throw new RuntimeException("Cannot set an attribute to a mat4"); }
	
	public void enable() {
		GL20.glEnableVertexAttribArray(getID());
	}
	public void disable() {
		GL20.glDisableVertexAttribArray(getID());
	}
	
	public void point(int stride, long offset) {
		DataType type = getUsage().getDataType();
		if (type == DataType.BOOL) throw new RuntimeException("Cannot point to boolean array!");
		GL20.glVertexAttribPointer(getID(), type.getComponents(), type.getComponentType().getID(), false, stride, offset);
	}
	/**
	 * If the attribute ID(or location) has changed(due to recompilation or other),
	 * then this function will update the ID
	 * If the new id is invalid(-1) and the autoActivate flag is set, the uniform will be deactived, 
	 * if it is valid and the flag is set, the attribute will be activated if it is not already active
	 * @param autoActivate If set, the Uniform will automatically activate or deactivate itself depending on the new id 
	 */
	public void updateID(boolean autoActivate) {
		int id = s_getAttributeID(m_program, getName());
		if (autoActivate && id == -1) {
			if (isActive()) System.out.println("Deactivating Attribute: " + this + " due to invalid id(-1)");
			setActive(false);
		} else if (autoActivate) {
			if (!isActive()) System.out.println("Reactivating Attribute: " + this + " due to valid id: " + id);
			setActive(true);
		}
		m_id = id;		
	}
	
	@Override
	public String toString() {
		return "Attribute(" + getName() + ", " + m_id + ", Active: " + isActive() + ")";
	}
	
	//-------------------
	
	private void checkBound() {
		if (Program.s_getCurrent() != m_program) throw new RuntimeException("program.bind() must be called before attribute.setValue()");
	}
	
	public static int s_getAttributeID(Program p, String name) {
		return GL20.glGetAttribLocation(p.getID(), name);
	}
}