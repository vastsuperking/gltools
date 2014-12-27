package glcommon.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class StringUtils {
	
	public static String s_toHex(byte b) {
		return String.format("%02x", b);
	}
	public static byte s_parseHexByte(String s) {
		if (s.startsWith("0x")) {
			return (byte) Integer.parseInt(s.substring(2), 16);
		} else {
			return (byte) Integer.parseInt(s, 16);
		}
	}
	
	public static String s_joinObjects(String del, Object... objs) {
		return s_join(del, Arrays.asList(objs));
	}
	public static String s_join(String del, Collection<?> col) {
		StringBuilder builder = new StringBuilder();
		Iterator<?> it = col.iterator();
		while(it.hasNext()) {
			Object o = it.next();
			builder.append(o.toString());
			if (it.hasNext()) builder.append(del);
		}
		return builder.toString();
	}
}
