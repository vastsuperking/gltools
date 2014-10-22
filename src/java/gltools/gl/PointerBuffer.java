package gltools.gl;

import glcommon.BufferUtils;

import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ReadOnlyBufferException;

public class PointerBuffer implements Comparable<PointerBuffer> {
	private static final boolean s_is64Bit;
	
	static {
		s_is64Bit = System.getProperty("os.arch").endsWith("64");
	}
	
	protected final ByteBuffer m_pointers;
	
	protected final Buffer m_view;
	protected final IntBuffer m_32View;
	protected final LongBuffer m_64View;
	
	public PointerBuffer(int capacity) {
		this(BufferUtils.createByteBuffer(capacity * s_getPointerSize()));
	}
	public PointerBuffer(ByteBuffer source) {
		m_pointers = source.slice().order(source.order());
		if (s_is64Bit()) {
			m_32View = null;
			m_view = m_64View = m_pointers.asLongBuffer();
		} else {
			m_view = m_32View = m_pointers.asIntBuffer();			
			m_64View = null;
		}
	}
	public ByteBuffer getPointerBuffer() { return m_pointers; }
	public Buffer getView() { return m_view; }
	public IntBuffer get32View() { return m_32View; }
	public LongBuffer get64View() { return m_64View; }
	
	public final int capacity() {
		return m_view.capacity();
	}
	
	public final int position() {
		return m_view.position();
	}
	public final int positionByte() {
		return position() * s_getPointerSize();
	}
	
	public final PointerBuffer position(int newPosition) {
		m_view.position(newPosition);
		return this;
	}
	
	public boolean isReadOnly() {
		return false;
	}
	
	public final int limit() {
		return m_view.limit();
	}
	
	public final PointerBuffer limit(int newLimit) {
		m_view.limit(newLimit);
		return this;
	}
	public final PointerBuffer mark() {
		m_view.mark();
		return this;
	}
	public final PointerBuffer reset() {
		m_view.reset();
		return this;
	}
	public final PointerBuffer clear() {
		m_view.clear();
		return this;
	}
	public final PointerBuffer flip() {
		m_view.flip();
		return this;
	}	
	public final PointerBuffer rewind() {
		m_view.rewind();
		return this;
	}
	public final int remaining() {
		return m_view.remaining();
	}
	public final int remainingByte() {
		return remaining() * s_getPointerSize();
	}
	public final boolean hasRemaining() {
		return m_view.hasRemaining();
	}
	
	protected PointerBuffer newInstance(final ByteBuffer source) {
		return new PointerBuffer(source);
	}
	
	public PointerBuffer slice() {
		m_pointers.position(m_view.position() * s_getPointerSize());
		m_pointers.limit(m_view.limit() * s_getPointerSize());
		
		try {
			return newInstance(m_pointers);
		} finally {
			m_pointers.clear();
		}
	}
	public PointerBuffer duplicate() {
		final PointerBuffer buffer = newInstance(m_pointers);
		buffer.position(m_view.position());
		buffer.limit(m_view.limit());
		
		return buffer;
	}
	public PointerBuffer asReadOnlyBuffer() {
		final PointerBuffer buffer = new PointerBufferR(m_pointers);
		buffer.position(position());
		buffer.limit(limit());
		
		return buffer;
	}
	
	public PointerBuffer put(long l) {
		if (s_is64Bit()) 	m_64View.put(l);
		else				m_32View.put((int) l);
		return this;
	}
	public PointerBuffer put(int index, long l) {
		if (s_is64Bit()) 	m_64View.put(index, l);
		else				m_32View.put(index, (int) l);
		return this;
	}
	
	public PointerBuffer put(Pointer pointer) {
		return put(pointer.getPointer());
	}
	public PointerBuffer put(int index, Pointer pointer) {
		return put(index, pointer.getPointer());
	}
	
	public PointerBuffer put(PointerBuffer src) {
		if (s_is64Bit()) 	m_64View.put(src.m_64View);
		else 				m_32View.put(src.m_32View);
		return this;
	}
	
	public PointerBuffer put(long[] src, int offset, int length) {
		if (s_is64Bit()) {
			m_64View.put(src, offset, length);
		} else {
			checkBounds(offset, length, src.length);
			if (length > m_32View.remaining()) {
				throw new BufferOverflowException();
			}
			int end = offset + length;
			for (int i = offset; i < end; i++) m_32View.put((int) src[i]);
		}
		return this;
	}
	public PointerBuffer put(long[] src) {
		return put(src, 0, src.length);
	}
	
	public long get() {
		if (s_is64Bit()) return m_64View.get();
		else return m_32View.get() & 0x00000000FFFFFFFF;
	}
	public long get(int index) {
		if (s_is64Bit()) return m_64View.get(index);
		else return m_32View.get(index) & 0x00000000FFFFFFFF;		
	}
	
	public PointerBuffer get(long[] dst, int offset, int length) {
		if (s_is64Bit()) {
			m_64View.get(dst, offset, length);
		} else {
			checkBounds(offset, length, dst.length);
			if (length > m_32View.remaining()) throw new BufferUnderflowException();
			int end = offset + length;
			for (int i = offset; i < end; i++) {
				dst[i] = m_32View.get() & 0x00000000FFFFFFFF;
			}
		}
		return this;
	}
	public PointerBuffer get(long[] dst) {
		return get(dst, 0, dst.length);
	}
	
	public PointerBuffer compact() {
		if (s_is64Bit()) 	m_64View.compact();
		else				m_32View.compact();
		return this;
	}
	
	public ByteOrder order() {
		if (s_is64Bit()) {
			return m_64View.order();
		} else {
			return m_32View.order();
		}
	}
	
	@Override
	public int hashCode() { 
		int hash = 1;
		int p = position();
		for (int i = limit() - 1; i >=p; i--) {
			hash = 31 * hash + (int) get(i);
		}
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PointerBuffer)) return false;
		PointerBuffer that = (PointerBuffer) o;
		
		if (remaining() != that.remaining()) return false;
		
		int p = position();
		for (int i = limit() - 1, j = that.limit() - 1; i >= p; i--, j--) {
			long v1 = get(i);
			long v2 = that.get(j);
			if (v1 != v2) return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(PointerBuffer p) {
		final PointerBuffer that = p;
		int n = position() + Math.min(remaining(), that.remaining());
		for (int i = this.position(), j = that.position(); i < n; i++, j++ ) {
			long v1 = this.get(i);
			long v2 = that.get(j);
			if ( v1 == v2 )
				continue;
			if ( v1 < v2 )
				return -1;
			return +1;
		}
		return remaining() - that.remaining();
	}
	
	private void checkBounds(int offset, int len, int tgtSize) {
		if ( (offset | len | (offset + len) | (tgtSize - (offset + len))) < 0)
			throw new IndexOutOfBoundsException();
	}
	
	public static PointerBuffer s_allocateDirect(int capacity) {
		return new PointerBuffer(capacity);
	}
	public static int s_getPointerSize() {
		return s_is64Bit() ? 8 : 4;
	}
	
	public static boolean s_is64Bit() { return s_is64Bit; }
	
	
	
	//---------------------Read-Only PointerBuffer-----------------------
	
	
	private static final class PointerBufferR extends PointerBuffer {
		public PointerBufferR(final ByteBuffer source) {
			super(source);
		}
		@Override
		public boolean isReadOnly() {
			return true;
		}
		@Override
		protected PointerBuffer newInstance(final ByteBuffer source) {
			return new PointerBufferR(source);
		}
		@Override
		public PointerBuffer asReadOnlyBuffer() {
			return duplicate();
		}
		@Override
		public PointerBuffer put(final long l) {
			throw new ReadOnlyBufferException();
		}
		@Override
		public PointerBuffer put(final int index, final long l) {
			throw new ReadOnlyBufferException();
		}
		@Override
		public PointerBuffer put(final PointerBuffer src) {
			throw new ReadOnlyBufferException();
		}
		@Override
		public PointerBuffer put(final long[] src, final int offset, final int length) {
			throw new ReadOnlyBufferException();
		}
		@Override
		public PointerBuffer compact() {
			throw new ReadOnlyBufferException();
		}
	}
}
