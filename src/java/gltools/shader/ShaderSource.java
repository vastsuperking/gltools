package gltools.shader;

import gltools.ResourceLocator;
import gltools.utils.FileUtils;

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
		for (String d : m_defines) {
			builder.append("#define " + d).append('\n');
		}
		builder.append(m_source);
		return builder.toString();
	}
}
