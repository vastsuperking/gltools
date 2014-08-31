package gltools.buffer;

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

	public void enable() {
		InputList<Attribute> attributes = Program.s_getCurrent().getInputs(Attribute.class, m_usage);
		for (Attribute a : attributes) {
			a.enable();
		}
	}
	public void disable() {
		InputList<Attribute> attributes = Program.s_getCurrent().getInputs(Attribute.class, m_usage);
		for (Attribute a : attributes) {
			a.disable();
		}
	}
	public void bind() {
		if (m_vbo != null) m_vbo.bind();
	}
	public void unbind() {
		if (m_vbo != null) m_vbo.unbind();
	}
	public void point() {
		bind();
		InputList<Attribute> attributes = Program.s_getCurrent().getInputs(Attribute.class, m_usage);
		for (Attribute a : attributes) {
			a.point(m_stride, m_offset);
		}
		unbind();
	}
}
