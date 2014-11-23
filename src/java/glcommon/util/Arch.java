package glcommon.util;

public enum Arch {
	x86("x86"),
	x64("x64"),
	OTHER("Other");
	
	String m_name;
	Arch(String name) { m_name = name; }
	
	public String toString() {
		return m_name;
	}
	
	public static Arch s_get() {
		String arch = System.getProperty("os.arch");
		if (arch.contains("64")) return x64;
		else if (arch.contains("86")) return x86;
		else return OTHER;
	}
}
