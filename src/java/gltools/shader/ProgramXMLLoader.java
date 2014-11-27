package gltools.shader;

import glcommon.util.ResourceLocator;
import gltools.ffp.FFPTexCoordAttribute;
import gltools.ffp.FFPVertexAttribute;
import gltools.gl.GL;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.Shader.ShaderCompileException;
import gltools.util.FileUtils;
import gltools.util.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class ProgramXMLLoader {
	private static final boolean DEBUG = false;
	
	public static List<Program> s_load(String resource, ResourceLocator resources, GL gl) throws IOException, ShaderCompileException, ProgramLinkException {
		return s_load(resources.getResource(resource), resources, gl);
	}
	public static List<Program> s_load(InputStream input, ResourceLocator resources, GL gl) throws IOException, ShaderCompileException, ProgramLinkException {
		String file = FileUtils.s_readAll(input);
		Document doc = Jsoup.parse(file, "", Parser.xmlParser());
		
		Elements pgms = doc.getElementsByTag("program");
		
		List<Program> programs = new ArrayList<Program>();
		for (Element p : pgms) {
			programs.add(s_parseProgram(p, resources, gl));
		}
		return programs;
	}
	private static Program s_parseProgram(Element p, ResourceLocator resources, GL gl) throws ShaderCompileException, ProgramLinkException, IOException {
		Program program = new Program();
		program.init(gl.getGL2());
		
		Element shaders = p.getElementsByTag("shaders").get(0);
		for (Element s : shaders.children()) { 
			Shader shader = s_parseShader(s, resources);
			shader.init(gl.getGL2());
			if (DEBUG) System.out.println("Compiling shader: " + shader);
			shader.compile(gl.getGL2());
			if (DEBUG) System.out.println("Done compiling shader, attaching");
			program.attachShader(shader, gl.getGL2());
		}
		if (DEBUG) System.out.println("Linking program...");
		program.link(gl.getGL2());
		if (p.getElementsByTag("uniforms").size() != 0) {
			Element uniforms  = p.getElementsByTag("uniforms").get(0);
			for (Element uni : uniforms.children()) {
				Uniform uniform = s_parseUniform(uni, program);
				uniform.init(gl.getGL2());
				program.getInputs().addInput(uniform);
			}
		}
		if (p.getElementsByTag("attributes").size() != 0) {
			Element attributes = p.getElementsByTag("attributes").get(0);
			for (Element attr : attributes.children()) {
				Attribute attrib = s_parseAttribute(attr, program);
				attrib.init(gl.getGL2());
				program.getInputs().addInput(attrib);
			}
		}
		if (DEBUG) System.out.println("Program Creation Error State: " + GLUtils.getError(gl.getGL1()));
		return program;
	}
	private static Uniform s_parseUniform(Element u, Program p) {
		String name = u.attr("name");
		DataType type = DataType.s_getType(u.attr("type"));
		if (type == null) throw new RuntimeException("Could not find DataType: " + type);
		InputUsage usage = new InputUsage(u.attr("usage"), type, Uniform.class);
		Uniform uni = new Uniform(p, name);
		//The usage includes the datatype
		uni.setUsage(usage);
		return uni;
	}
	private static Attribute s_parseAttribute(Element a, Program p) {
		//TODO:Move to some config file?
		if (a.tagName().equals("ffp-vert-attribute")) {
			String type = a.attr("type");
			DataType dataType = DataType.s_getType(type);
			if (dataType == null) return new FFPVertexAttribute(InputUsage.VERTEX_POSITION_3D);
			switch(dataType) {
			case VEC2: return new FFPVertexAttribute(InputUsage.VERTEX_POSITION_2D);
			case VEC3: return new FFPVertexAttribute(InputUsage.VERTEX_POSITION_3D);
			default: throw new IllegalArgumentException("Unrecognized dataType: " + dataType);
			}
		} else if (a.tagName().equals("ffp-texcoord-attribute")) {
			return new FFPTexCoordAttribute();
		} else if (!a.tagName().equals("attribute")) {
			throw new RuntimeException("Unknown attribute input type");
		}
		
		String name = a.attr("name");
		DataType type = DataType.s_getType(a.attr("type"));
		if (type == null) throw new RuntimeException("Could not find DataType: " + type);
		InputUsage usage = new InputUsage(a.attr("usage"), type, Attribute.class);
		Attribute attr = new Attribute(p, name);
		attr.setUsage(usage);
		return attr;
	}
	private static Shader s_parseShader(Element s, ResourceLocator resources) throws IOException {	
		Shader shader = null;
		if (s.tagName().equals("fragment") || s.tagName().equals("fragment-shader"))  
			shader = new FragmentShader();
		else if (s.tagName().equals("vertex") || s.tagName().equals("vertex-shader")) 
			shader = new VertexShader();
		
		String resource = s.attr("resource");
		ShaderSource source = null;
		if (!resource.equals("") && resource != null) {
			InputStream input = resources.getResource(resource);
			if (input == null) throw new RuntimeException("Could not find resource at: " + resource);
			source = new ShaderSource(input);
		} else {
			StringBuilder builder = new StringBuilder();
			for (TextNode node : s.textNodes()) {
				builder.append(node.getWholeText());
			}
			source = new ShaderSource(builder.toString().trim()); 
		}
		shader.setSource(source);
		if (DEBUG) System.out.println("Shader source: " + source);
		
		if (DEBUG) System.out.println("Retrieved shader: " + shader);
		return shader; 
	}
}
