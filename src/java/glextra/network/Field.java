package glextra.network;

import glcommon.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public abstract class Field {
	private String m_name;
	
	public Field(String name){
		m_name = name;
	}
	
	public String getName() { return m_name; }
	//Size in bytes
	public abstract int getSize();
	
	public abstract void addTo(ByteBuffer buffer);
	
	public abstract void read(ByteBuffer buffer);
	
	public abstract void read(InputStream in) throws IOException;
	public abstract void write(OutputStream out) throws IOException;
	
	public abstract Field copy();
	
	public static class ByteField extends Field {
		private byte m_value = 0x00;
		
		public ByteField(String name) {
			super(name);
		}
		public ByteField(String name, byte val) {
			super(name);
			m_value = val;
		}
		
		public void set(byte value) { m_value = value; }
		public byte get() { return m_value; }
		
		public int getSize() { return 1; }
		
		public void addTo(ByteBuffer buffer) {
			buffer.put(m_value);
		}
		public void write(OutputStream out) throws IOException {
			out.write(m_value);
		}
		
		public void read(InputStream in) throws IOException {
			m_value = (byte) in.read();
		}
		public void read(ByteBuffer buffer) {
			m_value = buffer.get();
		}
		
		@Override
		public String toString() {
			return getName() + ":0x" + StringUtils.s_toHex(m_value);
		}
		
		public ByteField copy() { return new ByteField(getName(), m_value); }
	}
	public static class IntField extends Field {
		private int m_value = 0;
		
		public IntField(String name) {
			super(name);
		}
		public IntField(String name, int val) {
			super(name);
			m_value = val;
		}
		
		
		public void set(int value) { m_value = value; }
		public int get() { return m_value; }
		
		public int getSize() { return 4; }
		
		public void addTo(ByteBuffer buffer) {
			buffer.putInt(m_value);
		}
		public void read(ByteBuffer buffer) {
			m_value = buffer.getInt();
		}
		
		public void read(InputStream in) throws IOException {
			m_value = s_readInt(in);
		}
		public void write(OutputStream out) throws IOException {
			s_writeInt(out, m_value);
		}
		
		@Override
		public String toString() {
			return getName() + ":" + m_value;
		}
		
		public IntField copy() { return new IntField(getName(), m_value); }
	}
	public static class LongField extends Field {
		private long m_value = 0;
		
		public LongField(String name) {
			super(name);
		}
		public LongField(String name, long val) {
			super(name);
			m_value = val;
		}
		
		
		public void set(long f) { m_value = f; }
		public long get() { return m_value; }
		
		public int getSize() { return 4; }
		
		public void addTo(ByteBuffer buffer) {
			buffer.putLong(m_value);
		}
		public void read(ByteBuffer buffer) {
			m_value = buffer.getInt();
		}
		
		public void read(InputStream in) throws IOException {
			m_value = s_readLong(in);
		}
		public void write(OutputStream out) throws IOException {
			s_writeLong(out, m_value);
		}
		
		@Override
		public String toString() {
			return getName() + ":" + m_value;
		}
		
		public LongField copy() { return new LongField(getName(), m_value); }
	}
	public static class FloatField extends Field {
		private float m_value = 0;
		
		public FloatField(String name) {
			super(name);
		}
		public FloatField(String name, float val) {
			super(name);
			m_value = val;
		}

		public void set(float value) { m_value = value; }
		public float get() { return m_value; }
		
		public int getSize() { return 4; }
		
		public void addTo(ByteBuffer buffer) {
			buffer.putFloat(m_value);
		}
		public void read(ByteBuffer buffer) {
			m_value = buffer.getFloat();
		}

		public void read(InputStream in) throws IOException {
			m_value = s_readFloat(in);
		}
		
		public void write(OutputStream out) throws IOException {
			s_writeFloat(out, m_value);
		}
		
		@Override
		public String toString() {
			return getName() + ":" + m_value;
		}

		public FloatField copy() { return new FloatField(getName(), m_value); }
	}
	public static class StringField extends Field {
		private String m_value = "";
		
		public StringField(String name) {
			super(name);
		}
		public StringField(String name, String val) {
			super(name);
			m_value = val;
		}
		
		public void set(String value) { m_value = value; }
		public String get() { return m_value; }
		
		public int getSize() { return m_value.length() + 1; }
		
		public void addTo(ByteBuffer buffer) {
			byte[] bytes = m_value.getBytes(Charset.forName("UTF-8"));
			buffer.put(bytes);
			buffer.put((byte) 0x00);
		}
		public void read(ByteBuffer buffer) {
			buffer.mark();
			int len = 0;
			for (byte b = buffer.get(); b != 0x00; len++, b = buffer.get());
			buffer.reset();
			byte[] bytes = new byte[len];
			buffer.get(bytes);
			m_value = new String(bytes, Charset.forName("UTF-8"));		
		}
		
		public void read(InputStream in) throws IOException {
			m_value = s_readString(in);
		}
		public void write(OutputStream out) throws IOException {
			s_writeString(out, m_value);
		}
		
		@Override
		public String toString() {
			return getName() + ":" + m_value;
		}
		
		public StringField copy() { return new StringField(getName(), m_value); }
	}
	private static void s_writeInt(OutputStream out, int value) throws IOException {
		out.write((value >>> 24) & 0xFF);
		out.write((value >>> 16) & 0xFF);
		out.write((value >>> 8) & 0xFF);
		out.write((value >>> 0) & 0xFF);
	}
	private static int s_readInt(InputStream in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0)
			throw new EOFException();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}
	
	private static void s_writeLong(OutputStream out, long value) throws IOException {
		out.write((byte) (value >>> 56));
		out.write((byte) (value >>> 48));
		out.write((byte) (value >>> 40));
		out.write((byte) (value >>> 32));
		out.write((byte) (value >>> 24));
		out.write((byte) (value >>> 16));
		out.write((byte) (value >>> 8));
		out.write((byte) (value >>> 0));
	}
	private static long s_readLong(InputStream in) throws IOException {
		long l = ((in.read() & 0xFF) << 56) +
				 ((in.read() & 0xFF) << 48) +
				 ((in.read() & 0xFF) << 40) +
				 ((in.read() & 0xFF) << 32) +
				 ((in.read() & 0xFF) << 24) +
				 ((in.read() & 0xFF) << 16) +
				 ((in.read() & 0xFF) << 8) +
				 ((in.read() & 0xFF) << 0);
		return l;
	}
	
	private static void s_writeFloat(OutputStream out, float value) throws IOException {
		s_writeInt(out, Float.floatToIntBits(value));
	}
	private static float s_readFloat(InputStream in) throws IOException {
		return Float.intBitsToFloat(s_readInt(in));
	}
	private static String s_readString(InputStream in) throws IOException {
		ByteArrayOutputStream tmp = new ByteArrayOutputStream();
		int b;
		while ((b = in.read()) != 0x00) tmp.write(b);
		return new String(tmp.toByteArray(), Charset.forName("UTF-8"));
	}
	private static void s_writeString(OutputStream out, String s) throws IOException {
		if (s != null) out.write(s.getBytes(Charset.forName("UTF-8")));
		out.write(0x00);
	}
}
