package gltools.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
	public static String s_readAll(File in) throws IOException {
		return s_readAll(new FileInputStream(in));
	}
	public static String s_readAll(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		StringBuilder text = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			text.append(line).append('\n');
		}
		reader.close();
		return text.toString();
	}
}
