package gltools.shader;

import gltools.ResourceLocator;
import gltools.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShaderSource {
	private List<String> m_defines = new ArrayList<String>();
	private String m_source = "";
	
	public ShaderSource(String resource, ResourceLocator manager) throws IOException {
		this(manager.getResource(resource));
	}
	public ShaderSource(File source) throws IOException {
		m_source = FileUtils.s_readAll(source);
	}
	public ShaderSource(InputStream source) throws IOException {
		m_source = FileUtils.s_readAll(source);
	}
	public ShaderSource(String source) {
		m_source = source;
	}
	public void setSource(String source) { m_source = source; }
	public void setDefines(List<String> defines) { m_defines = defines; }
	public String getSource() { return m_source; }
	
	public void addDefine(String define) { m_defines.add(define); }
	
	public void clearDefines() { m_defines.clear(); }
	
	public String getFullSource() {
		StringBuilder builder = new StringBuilder();

		//First append version statement
		int versionIndex = m_source.indexOf("#version");
		int endVersionIndex = m_source.indexOf('\n', versionIndex) + 1;
		builder.append(m_source.substring(versionIndex, endVersionIndex));
		
		//Newline inbetween version and defines
		builder.append('\n');
		
		//Then append defines
		for (String d : m_defines) {
			builder.append("#define " + d).append('\n');
		}
		
		//Newline inbetween defines and main source code
		builder.append('\n');
		
		builder.append(m_source.substring(endVersionIndex));
		return builder.toString();
	}
}
