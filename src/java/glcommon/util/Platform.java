package glcommon.util;

public enum Platform {
	OTHER,
	OSX,
	WINDOWS,
	LINUX;
	
	public static Platform s_get() {
		String os = System.getProperty("os.name");
		if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0)) {
			return OSX; 
		} else if (os.indexOf("win") >= 0) {
			return WINDOWS;
		} else if (os.indexOf("nux") >= 0) {
			return LINUX;
		} else {
			return OTHER;
		}
	}
}
