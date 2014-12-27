package glextra.network;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import glcommon.BufferUtils;
import glcommon.util.StringUtils;
import glextra.network.Field.ByteField;
import glextra.network.Field.FloatField;
import glextra.network.Field.IntField;
import glextra.network.Field.StringField;

public class Msg {
	private String m_typeName;
	private byte m_typeByte;
	
	private ArrayList<Field> m_fields = new ArrayList<Field>();
	private HashMap<String, Field> m_fieldNames = new HashMap<String, Field>();

	public Msg() {}

	public void setTypeName(String name) { m_typeName = name; }
	public void setTypeByte(byte type) { m_typeByte = type; }
	
	public String getTypeName() { return m_typeName; }
	public byte getTypeByte() { return m_typeByte; }

	public List<Field> getFields() { return m_fields; }
	
	public void addField(Field f) {
		if (m_fieldNames.containsKey(f.getName()))
			throw new RuntimeException("Msg already has field with name: " + f.getName());
		m_fields.add(f);
		m_fieldNames.put(f.getName(), f);
	}
	
	public int getLength() {
		//Start with 1 for type byte
		int len = 1;
		for (Field f : m_fields) len += f.getSize();
		return len;
	}
	public void setByte(String field, byte b) {
		Field f = m_fieldNames.get(field);
		if (f == null) throw new RuntimeException("Could not find field: " + field);
		if (!(f instanceof ByteField)) throw new RuntimeException("Field(" + f + "is not a byte field!");
		((ByteField) f).set(b);
	}
	public void setInt(String field, int i) {
		Field f = m_fieldNames.get(field);
		if (f == null) throw new RuntimeException("Could not find field: " + field);
		if (!(f instanceof IntField)) throw new RuntimeException("Field(" + f + "is not a int field!");
		((IntField) f).set(i);
	}
	public void setFloat(String field, float f) {
		Field fd = m_fieldNames.get(field);
		if (fd == null) throw new RuntimeException("Could not find field: " + fd);
		if (!(fd instanceof FloatField)) throw new RuntimeException("Field(" + fd + "is not a byte field!");
		((FloatField) fd).set(f);
	}
	public void setString(String field, String s) {
		Field f = m_fieldNames.get(field);
		if (f == null) throw new RuntimeException("Could not find field: " + field);
		if (!(f instanceof StringField)) throw new RuntimeException("Field(" + f + "is not a byte field!");
		((StringField) f).set(s);
	}

	public void read(ByteBuffer buffer) {
		read(buffer, null);
	}
	
	public void read(InputStream in) throws IOException {
		read(in, null);
	}
	
	public void read(ByteBuffer buffer, MsgDefines defines) {
		byte type = buffer.get();
		if (type != m_typeByte) {
			if (m_fields.size() > 0) {
				throw new RuntimeException("Msg(" + this + ") is of wrong type, should be: " + 
											StringUtils.s_toHex(m_typeByte));
			}
			if (defines == null)
				throw new RuntimeException("Unknown message type and no defines set!");
			Msg def = defines.getDefinition(type);
			if (def == null)
				throw new RuntimeException("Unknown msg type: " + type);
			inherit(def);
		}
		for (Field f : m_fields) {
			f.read(buffer);
		}
	}
	public void read(InputStream in, MsgDefines defines) throws IOException {
		int t = in.read();
		if (t == -1) throw new EOFException();
		byte type = (byte) t;
		if (type != m_typeByte) {
			if (m_fields.size() > 0) {
				throw new RuntimeException("Msg(" + this + ") is of wrong type, should be: " + 
											StringUtils.s_toHex(m_typeByte));
			}
			if (defines == null)
				throw new RuntimeException("No defines set!");
			Msg def = defines.getDefinition(type);
			if (def == null)
				throw new RuntimeException("Unknown msg type: " + type);
			inherit(def);
		}
		for (Field f : m_fields) {
			f.read(in);
		}
	}
	
	public void write(OutputStream out) throws IOException {
		out.write(m_typeByte);
		for (Field f : m_fields) {
			f.write(out);
		}
	}
	
	public ByteBuffer create() {
		ByteBuffer buffer = BufferUtils.createByteBuffer(getLength());
		
		buffer.put(m_typeByte);
		
		for (Field f : m_fields) {
			f.addTo(buffer);
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	//Inherit msg's type and fields
	public void inherit(Msg msg) {
		m_typeName = msg.getTypeName();
		m_typeByte = msg.getTypeByte();
		m_fields.clear();
		m_fieldNames.clear();
		
		for (Field f : msg.getFields()) {
			addField(f);
		}
	}
	
	@Override
	public String toString() {
		return "[" + (m_typeName != null ? m_typeName : "?") + ":" + StringUtils.s_toHex(m_typeByte) + "]" + StringUtils.s_join("|", m_fields);
	}
}
