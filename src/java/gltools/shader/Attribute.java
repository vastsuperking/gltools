package gltools.shader;

import glcommon.vector.Matrix2f;
import glcommon.vector.Matrix3f;
import glcommon.vector.Matrix4f;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.gl.GL2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Attribute extends Input {
	private static final Logger logger = LoggerFactory.getLogger(Attribute.class);
	
	private int m_id;
	private Program m_program;

	public Attribute(Program program, String name) {
		setName(name);
		setProgram(program);
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
	protected void setID(int id) {m_id = id;}

	/**
	 * All attribute setValue() functions are deprecated
	 * Usage of VAO and VBOs are preferred
	 * These will most likely not be removed in the future, but their usage should be avoided
	 */
	@Deprecated
	public void setValue(GL2 gl, int val) {
		throw new RuntimeException("Cannot set attribute to int!!");
	}
	@Deprecated
	public void setValue(GL2 gl, boolean val) {
		throw new RuntimeException("Cannot set attribute to bool!!");
	}
	@Deprecated
	public void setValue(GL2 gl, float val) {
		if (!isActive()) return;
		checkBound(gl);
		gl.glVertexAttrib1f(getID(), val);
	}
	@Deprecated
	public void setValue(GL2 gl, Vector2f vec) {
		if (!isActive()) return;
		checkBound(gl);
		gl.glVertexAttrib2f(getID(), vec.getX(), vec.getY());
	}
	@Deprecated
	public void setValue(GL2 gl, Vector3f vec) {
		if (!isActive()) return;
		checkBound(gl);
		gl.glVertexAttrib3f(getID(), vec.getX(), vec.getY(), vec.getZ());
	}
	@Deprecated
	public void setValue(GL2 gl, Vector4f vec) {
		if (!isActive()) return;
		checkBound(gl);
		gl.glVertexAttrib4f(getID(), vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	public void setValue(GL2 gl, Matrix2f mat) { throw new RuntimeException("Cannot set an attribute to a mat2"); }
	public void setValue(GL2 gl, Matrix3f mat) { throw new RuntimeException("Cannot set an attribute to a mat3"); }
	public void setValue(GL2 gl, Matrix4f mat) { throw new RuntimeException("Cannot set an attribute to a mat4"); }
	
	public void init(GL2 gl) {
		if (m_program != null) updateID(gl);
	}

	public void enableArray(GL2 gl) {
		gl.glEnableVertexAttribArray(getID());
	}
	public void disableArray(GL2 gl) {
		gl.glDisableVertexAttribArray(getID());
	}
	
	public void point(int stride, long offset, GL2 gl) {
		DataType type = getUsage().getDataType();
		if (type == DataType.BOOL) throw new RuntimeException("Cannot point to boolean array!");
		gl.glVertexAttribPointer(getID(), type.getComponents(), type.getComponentType().getID(), false, stride, offset);
	}
	
	public void updateID(GL2 gl) {
		updateID(true, gl);
	}
	/**
	 * If the attribute ID(or location) has changed(due to recompilation or other),
	 * then this function will update the ID
	 * If the new id is invalid(-1) and the autoActivate flag is set, the uniform will be deactived, 
	 * if it is valid and the flag is set, the attribute will be activated if it is not already active
	 * @param autoActivate If set, the Uniform will automatically activate or deactivate itself depending on the new id 
	 */
	public void updateID(boolean autoActivate, GL2 gl) {
		int id = gl.glGetAttribLocation(m_program.getID(), getName());
		setID(id);
		if (autoActivate && id == -1) {
			if (isActive()) {
				logger.debug("Deactivating Attribute: " + this + " due to invalid id(-1)");
				setActive(false);
			}
		} else if (autoActivate) {
			if (!isActive()) {
				logger.debug("Activating Attribute: " + this + " due to valid id: " + id);
				setActive(true);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Attribute(" + getName() + ", " + m_id + ", Active: " + isActive() + ")";
	}
	
	//-------------------
	
	private void checkBound(GL2 gl) {
		int id = gl.glGetInteger(GL2.GL_CURRENT_PROGRAM);
		if (id != m_program.getID()) throw new RuntimeException("program.bind() must be called before attribute.setValue()");
	}
}