package gltools.buffer;

import gltools.gl.GL1;
import gltools.gl.GL2;
import gltools.shader.Attribute;
import gltools.shader.InputList;
import gltools.shader.InputUsage;
import gltools.shader.Program;

/**
 * Describes a particular array of data 
 */
public class AttribArray {
	private final VertexBuffer m_vbo;
	private final InputUsage m_usage;
	private final int m_stride;
	private final long m_offset;
	
	/**
	 * Defines an attribute array
	 * @param vbo the buffer to fetch values from
	 * @param usage the usage of this array
	 * @param stride the number of bytes between the beginning of one attribute to the beginning of the next
	 */
	public AttribArray(VertexBuffer vbo, InputUsage usage, int stride, long offset) {
		if (!usage.getInputType().equals(Attribute.class))
			throw new IllegalArgumentException("Usage(" + usage + ") must be an attribute");
		m_vbo = vbo;
		m_usage = usage;
		m_stride = stride;
		m_offset = offset;
	}
	
	public VertexBuffer getVertexBuffer() { return m_vbo; }
	public InputUsage getUsage() { return m_usage; }
	public int getStride() { return m_stride; }
	public long getOffset() { return m_offset; }

	public String toString() {
		return "AttribArray(" + m_vbo + ", " + m_usage + ", Stride: " + m_stride + " offset: " + m_offset + ")";
	}

	public void enable(GL2 gl) {
		if (Program.s_getCurrent() == null)
			throw new RuntimeException("No program bound!");
		InputList<Attribute> attributes = Program.s_getCurrent().getInputs(Attribute.class, m_usage);
		for (Attribute a : attributes) {
			a.enableArray(gl);
		}
	}
	public void disable(GL2 gl) {
		InputList<Attribute> attributes = Program.s_getCurrent().getInputs(Attribute.class, m_usage);
		for (Attribute a : attributes) {
			a.disableArray(gl);
		}
	}
	public void bind(GL1 gl) {
		if (m_vbo != null) m_vbo.bind(gl);
	}
	public void unbind(GL1 gl) {
		if (m_vbo != null) m_vbo.unbind(gl);
	}
	public void point(GL2 gl) {
		bind(gl);
		InputList<Attribute> attributes = Program.s_getCurrent().getInputs(Attribute.class, m_usage);
		for (Attribute a : attributes) {
			a.point(m_stride, m_offset, gl);
		}
		unbind(gl);
	}
}
