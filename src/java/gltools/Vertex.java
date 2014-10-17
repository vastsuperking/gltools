package gltools;

import glcommon.Color;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import gltools.shader.Attribute;
import gltools.shader.InputUsage;
import gltools.shader.Program;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;

public class Vertex {
	private HashMap<InputUsage, VertexAttribute> m_attributes = new HashMap<InputUsage, VertexAttribute>();
	
	public Vertex() {}
	public Vertex(Vertex v) {
		//Go through attributes and clone them to this vertex
		for (VertexAttribute va : v.getAttributes()) {
			addAttribute(va.clone());
		}
	}
	public Vertex(Vector3f position) {
		setPosition(position);
	}
	public Vertex(Vector2f position) {
		setPosition(position);
	}
	public void setPosition(Vector3f pos) { 
		m_attributes.put(InputUsage.VERTEX_POSITION_3D, new Position3D(pos));
		if (m_attributes.containsKey(InputUsage.VERTEX_POSITION_2D))
			m_attributes.remove(InputUsage.VERTEX_POSITION_2D);
	}
	public void setPosition(Vector2f pos) { 
		m_attributes.put(InputUsage.VERTEX_POSITION_2D, new Position2D(pos));
		if (m_attributes.containsKey(InputUsage.VERTEX_POSITION_3D))
			m_attributes.remove(InputUsage.VERTEX_POSITION_3D);
	}
	public Vector3f getPosition3D() {
		Position3D loc = (Position3D) m_attributes.get(InputUsage.VERTEX_POSITION_3D);
		return loc.getPosition();
	}
	public Vector2f getPosition2D() {
		Position2D loc = (Position2D) m_attributes.get(InputUsage.VERTEX_POSITION_2D);
		return loc.getPosition();
	}
	public boolean is3D() {
		return m_attributes.containsKey(InputUsage.VERTEX_POSITION_3D);
	}
	@Override
	public String toString() {
		return "Vertex" + getPosition3D(); 
	}
	
	public Collection<VertexAttribute> getAttributes() { return m_attributes.values(); }
	public VertexAttribute getAttribute(InputUsage usage) { return m_attributes.get(usage); }
	public void addAttribute(VertexAttribute e) {
		m_attributes.put(e.getUsage(), e);
	}
	
	/**
	 * Note this functions uses legacy OGL calls
	 * and might be removed in the future
	 */
	@Deprecated
	public void render() {
		VertexAttribute pos = null;
		for (VertexAttribute c : m_attributes.values()) {
			if (!(c instanceof Position3D || c instanceof Position2D)) c.apply();
			else pos = c;
		}
		//Make sure the vertex is called last
		pos.apply();
	}
	
	
	@Override
	public Object clone() {
		return new Vertex(this);
	}
	
	/**
	 * Vertex attributes contain attributes about a certain vertex
	 * Unlike the shader.Attribute class, this holds the actual information about the vertex
	 */
	public interface VertexAttribute {
		public InputUsage getUsage();
		public void addTo(ByteBuffer buffer);
		/**
		 * Uses legacy OGL calls
		 * Might be removed in the future
		 */
		@Deprecated
		public void apply();
		
		public VertexAttribute clone();
	}
	public static class Position3D implements VertexAttribute {
		private final Vector3f m_pos;
		
		public Position3D(Vector3f pos) {
			m_pos = pos;
		}
		@Override
		public InputUsage getUsage() {
			return InputUsage.VERTEX_POSITION_3D;
		}

		public Vector3f getPosition() { return m_pos; }

		@Override
		public void addTo(ByteBuffer buffer) {
			buffer.asFloatBuffer().put(m_pos.x).put(m_pos.y).put(m_pos.z);
		}
		@Override
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_pos);
		}
		
		public VertexAttribute clone() {
			return new Position3D(new Vector3f(m_pos));
		}
	}
	public static class Position2D implements VertexAttribute {
		private final Vector2f m_pos;
		
		public Position2D(Vector2f pos) {
			m_pos = pos;
		}
		@Override
		public InputUsage getUsage() {
			return InputUsage.VERTEX_POSITION_2D;
		}

		public Vector2f getPosition() { return m_pos; }

		@Override
		public void addTo(ByteBuffer buffer) {
			buffer.asFloatBuffer().put(m_pos.x).put(m_pos.y);
		}
		@Override
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_pos);
		}
		
		public VertexAttribute clone() {
			return new Position2D(new Vector2f(m_pos));
		}
	}
	public static class VertexNormal implements VertexAttribute {
		private final Vector3f m_normal;
		
		public VertexNormal(Vector3f normal) { m_normal = normal; }
		
		@Override
		public InputUsage getUsage() { return InputUsage.VERTEX_NORMAL; }
		
		@Override
		public void addTo(ByteBuffer buf) {
			buf.asFloatBuffer().put(m_normal.x).put(m_normal.y).put(m_normal.z);
		}
		
		@Override
		@Deprecated
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_normal);
		}
		@Override
		public VertexAttribute clone() {
			return new VertexNormal(new Vector3f(m_normal));
		}
	}
	public static class VertexTangent implements VertexAttribute {
		private final Vector3f m_tangent;
		
		public VertexTangent(Vector3f tangent) { m_tangent = tangent; }
		
		@Override
		public InputUsage getUsage() { return InputUsage.VERTEX_TANGENT; }
		
		@Override
		public void addTo(ByteBuffer buf) {
			buf.asFloatBuffer().put(m_tangent.x).put(m_tangent.y).put(m_tangent.z);
		}
		
		@Override
		@Deprecated
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_tangent);
		}
		@Override
		public VertexAttribute clone() {
			return new VertexNormal(new Vector3f(m_tangent));
		}
	}
	public static class VertexBitangent implements VertexAttribute {
		private final Vector3f m_bitangent;
		
		public VertexBitangent(Vector3f bitangent) { m_bitangent = bitangent; }
		
		@Override
		public InputUsage getUsage() { return InputUsage.VERTEX_BITANGENT; }
		
		@Override
		public void addTo(ByteBuffer buf) {
			buf.asFloatBuffer().put(m_bitangent.x).put(m_bitangent.y).put(m_bitangent.z);
		}
		
		@Override
		@Deprecated
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_bitangent);
		}
		@Override
		public VertexAttribute clone() {
			return new VertexNormal(new Vector3f(m_bitangent));
		}
	}
	public static class VertexColor implements VertexAttribute {
		private final Color m_color;
		
		public VertexColor(Color c) {
			m_color = c;
		}
		@Override
		public InputUsage getUsage() { return InputUsage.DIFFUSE_COLOR; }
		
		@Override
		public void addTo(ByteBuffer buf) {
			buf.asFloatBuffer().put(m_color.getRed()).put(m_color.getGreen()).put(m_color.getBlue()).put(m_color.getAlpha());
		}
		
		@Override
		@Deprecated
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_color.toVector4f());
		}
		
		public VertexAttribute clone() {
			return new VertexColor(new Color(m_color));
		}
	}
	public static class TexCoord implements VertexAttribute {
		private final Vector2f m_coord;
		
		public TexCoord(Vector2f coord) {
			m_coord = coord;
		}
		
		@Override
		public InputUsage getUsage() { return InputUsage.VERTEX_TEX_COORD; }
		
		@Override
		public void addTo(ByteBuffer buffer) {
			buffer.asFloatBuffer().put(m_coord.getX()).put(m_coord.getY());
		}
		
		@Override
		@Deprecated
		public void apply() {
			Program.s_getCurrent().getInputs().getInputs(Attribute.class, getUsage()).setValue(m_coord);
		}
		@Override
		public String toString() {
			return "TexCoord[" + m_coord.getX() + ", " + m_coord.getY() + "]";
		}
		
		public VertexAttribute clone() {
			return new TexCoord(new Vector2f(m_coord));
		}
	}
}
