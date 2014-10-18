package gltools.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.codegen.GConstruct;
import tools.codegen.GField;
import tools.codegen.GInterface;


public class GenerateGLTemplates {
	private static final String OUTPUT_DIR = "/Templates/GL/";
	private static final String PACKAGE_SUMMARY_URL = "http://www.lwjgl.org/javadoc/org/lwjgl/opengl/package-summary.html";
	private static final String CONSTANT_VALUES_URL = "http://www.lwjgl.org/javadoc/constant-values.html";
	
	private static final String LWJGL_CLASS_URL_PREFIX = "http://lwjgl.org/javadoc/org/lwjgl/opengl/";
	private static final String LWJGL_CLASS_URL_SUFFIX = ".html";
	private static final String[] LWJGL_CLASSES = {"GL11", "GL12", "GL13", "GL14", "GL15",
													 "GL20", "GL21",
													 "GL30", "GL31", "GL32", "GL33",
													 "GL40", "GL41", "GL42", "GL43", "GL44"};
	
	private HashMap<String, GField> m_constantFields = new HashMap<String, GField>();
	
	public void run() throws IOException {
		parseConstants(CONSTANT_VALUES_URL);
		HashMap<String, String> classURLs = parseClassListURLs();
		parseClasses(classURLs);
	}
	private List<GConstruct> parseClasses(HashMap<String, String> classURLs) {
		List<GConstruct> constructs = new ArrayList<GConstruct>();
		
		for (int i = 1; i <= 4; i++) {
			GInterface interfaze = new GInterface("GL" + i);
			Set<OnlineClass> validClasses = s_getClassesNameStarts(classURLs, "GL" + i);
			for (OnlineClass onlineClass : validClasses) {
				parseClass(onlineClass.getName(), onlineClass.getURL(), interfaze);
			}
		}
		return constructs;
	}
	private void parseClass(String name, String url, GInterface glInterface) {
		System.out.println("Parsing: " + name + " for " + glInterface.getName());
	}
	private HashMap<String, String> parseClassListURLs() {
		HashMap<String, String> classURLs = new HashMap<String, String>();
		for (String clazz : LWJGL_CLASSES) {
			String url = LWJGL_CLASS_URL_PREFIX + clazz + LWJGL_CLASS_URL_SUFFIX;
			classURLs.put(clazz, url);
		}
		return classURLs;
	}
	private void parseConstants(String constantsURL) throws IOException {
		Document doc = s_get(constantsURL);
		Element constantsContainer = doc.getElementsByClass("constantValuesContainer").first();
		Elements tables = constantsContainer.getElementsByTag("table");
		for (Element t : tables) {
			//Get main table body(3rd element, 2nd index)
			Element tBody = t.child(2);
			//Get all table rows from table body
			Elements rows = tBody.getElementsByTag("tr");
			for (Element row : rows) processConstant(row);
		}
	}
	private void processConstant(Element row) {
		String modsAndType = s_clean(row.getAllElements().get(0).text());
		String name = s_clean(row.child(1).text());
		String value = s_clean(row.child(2).text());
		String[] modsParts = modsAndType.split(" ");
		String visibility = modsParts[0];
		String type = modsParts[modsParts.length - 1];
		boolean isStatic = modsAndType.contains("static");
		boolean isFinal = modsAndType.contains("final");		if (s_isInt(value)) {
			int intVal = Integer.parseInt(value);
			String formattedValue = "0x" + String.format("%05X", intVal & 0xFFFFFF);
			GField field = new GField(isStatic, isFinal, type, name, formattedValue);
			m_constantFields.put(name, field);
		}
	}
	public static Set<OnlineClass> s_getClassesNameStarts(HashMap<String, String> map, String start) {
		Set<OnlineClass> set = new TreeSet<OnlineClass>();
		for (Entry<String, String> e : map.entrySet()) {
			if (e.getKey().startsWith(start)) set.add(new OnlineClass(e.getKey(), e.getValue()));
		}
		return set;
	}
	public static Document s_get(String url) throws IOException {
		return Jsoup.connect(url).get();
    }
	public static String s_clean(String input) {
		return input.replace('\u00A0', ' ');
	}
	public static boolean s_isInt(String str) {
	    try { 
	        Integer.parseInt(str); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public static void s_write(String output, File file) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(file));
		writer.print(output);
		writer.close();
	}
	public static void main(String[] args) throws IOException, URISyntaxException {
		GenerateGLTemplates gen = new GenerateGLTemplates();
		gen.run();
	}
	public static class OnlineClass implements Comparable<OnlineClass> {
		private String m_name;
		private String m_url;
		
		public OnlineClass(String name, String url) {
			m_name = name;
			m_url = url;
		}
		public String getName() { return m_name; }
		public String getURL() { return m_url; }
		@Override
		public int compareTo(OnlineClass o) {
			return m_name.compareTo(o.getName());
		}

	}
}
