package glcommon.image;

public enum ImageFormat {
	R(1),
	RGB(3),
	RGBA(4);
	
	private int m_size; //Pixel size in bytes
	
	private ImageFormat(int size) {
		m_size = size;
	}
	
	public int getSize() { return m_size; }
}
