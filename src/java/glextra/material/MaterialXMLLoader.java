package glextra.material;

import glcommon.util.ResourceLocator;
import glcommon.vector.Vector2f;
import glcommon.vector.Vector3f;
import glcommon.vector.Vector4f;
import gltools.gl.GL;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Shader.ShaderCompileException;
import gltools.shader.Uniform;
import gltools.texture.Texture;
import gltools.texture.Texture1D;
import gltools.texture.Texture2D;
import gltools.texture.TextureFactory;
import gltools.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class MaterialXMLLoader {
	private static final boolean DEBUG = false;
	public static List<Material> s_load(String resource, ResourceLocator locator, GL gl) throws IOException, ShaderCompileException, ProgramLinkException {
		return s_load(locator.getResource(resource), locator, gl);
	}
	public static List<Material> s_load(InputStream in, ResourceLocator locator, GL gl) throws IOException, ShaderCompileException, ProgramLinkException {
		String content = FileUtils.s_readAll(in);
		Document doc = Jsoup.parse(content, "", Parser.xmlParser());
		Elements mats = doc.getElementsByTag("material");
		
		List<Material> materials = new ArrayList<Material>();
		for (Element m : mats) {
			materials.add(s_parseMaterial(m, locator, gl));
		}
		return materials;
	}
	private static Material s_parseMaterial(Element m, ResourceLocator locator, GL gl) throws IOException, ShaderCompileException, ProgramLinkException {
		Material mat = new Material(m.attr("name"));
		
		Elements paramsElements = m.getElementsByTag("parameters");
		if (paramsElements.size() < 1) throw new RuntimeException("Unable to find parameters tag");
		Element paramsElement = paramsElements.first();
		Elements params = paramsElement.children();
		for (Element param : params) {
			MatParam p = s_parseParam(param, locator, gl);
			if (DEBUG) System.out.println("Parsed Param: " + p);
			mat.addParameter(p);
		}
		
		Elements techniques = m.getElementsByTag("technique");
		for (Element t : techniques) {
			boolean def = Boolean.parseBoolean(t.attr("default"));
			Technique technique = s_parseTechnique(t, locator, gl);
			if (def) mat.setDefaultTechnique(technique);
			else mat.addTechnique(technique);
		}
		
		//Just in case ready doesn't select the technique
		mat.selectTechnique();

		//Will do all the compiling
		//and select the technique
		mat.ready(gl);
		return mat;
	}
	private static MatParam s_parseParam(Element p, ResourceLocator locator, GL gl) throws IOException {
		DataType type = s_parseParamType(p.tagName());
		String valueString = p.attr("value");
		Object value = null;
		if (valueString != null && !valueString.equals("")) value = s_parseParamValue(type, valueString, locator, gl);
		String name = p.attr("name");
		//The usage is always a uniform
		InputUsage usage = new InputUsage(p.attr("usage"), type, Uniform.class);
		if ((type == DataType.SAMPLER1D || type == DataType.SAMPLER2D) && 
				(value instanceof Texture || value == null)) {
			int unit = Integer.parseInt(p.attr("unit"));
			return new MatTexParam(type, name, usage, (Texture) value, unit);
		} else return new MatParam(type, name, usage, value);
	}
	private static Technique s_parseTechnique(Element t, ResourceLocator locator, GL gl) throws IOException, ShaderCompileException, ProgramLinkException {
		String name = t.attr("name");
		String[] modes = t.attr("modes").split(",");
		
		Elements programs = t.getElementsByTag("program");
		if (programs.size() < 1) throw new RuntimeException("Unable to find program tag");
		Element programElement = programs.first();
		String programLocation = programElement.attr("resource");
		Program program = ProgramXMLLoader.s_load(programLocation, locator, gl).get(0);
		Technique technique = new Technique(name, program);
		Elements definesElements = t.getElementsByTag("defines");
		if (definesElements.size() > 0) s_parseDefines(definesElements.first(), technique);
		Elements globalParamsElements = t.getElementsByTag("globalParams");
		if (globalParamsElements.size() > 0) s_parseGlobalParams(globalParamsElements.first().children(), technique);
		
		technique.addAllRenderModes(Arrays.asList(modes));
		
		return technique;
	}
	
	private static void s_parseDefines(Element d, Technique t) {
		Elements defines = d.children();
		for (Element defineElement : defines) {
			if (defineElement.tagName().equals("define")) {
				String paramName = defineElement.attr("param");
				String define = defineElement.text();
				t.addDefine(paramName, define);
			}
		}
	}
	
	private static void s_parseGlobalParams(Elements params, Technique technique) {
		for (Element e : params) {
			DataType type = s_parseGlobalParamType(e.tagName());
			
			InputUsage usage = new InputUsage(e.attr("usage"), type, Uniform.class);
			technique.addGlobalParam(usage);
		}
	}
	
	/**
	 * Returns the parameter type given a string
	 * textures are represented by their corresponding simplerXX datatypes
	 */
	private static DataType s_parseParamType(String type) {
		type = type.toLowerCase();
		if (type.equals("float")) return DataType.FLOAT;
		else if (type.equals("boolean") || type.equals("bool")) return DataType.BOOL;
		else if (type.equals("vec2")) return DataType.VEC2;
		else if (type.equals("vec3")) return DataType.VEC3;
		else if (type.equals("color") || type.equals("vec4")) return DataType.VEC4;
		else if (type.equals("tex2d")) return DataType.SAMPLER2D;
		else if (type.equals("tex1d")) return DataType.SAMPLER1D;
		//else if (type.equals("mat2")) return DataType.MAT3;
		//else if (type.equals("mat3")) return DataType.MAT3;
		//else if (type.equals("mat4")) return DataType.MAT3;
		else throw new RuntimeException("Unknown ParamType: " + type);
	}
	
	private static DataType s_parseGlobalParamType(String type) {
		type = type.toLowerCase();
		if (type.equals("float")) return DataType.FLOAT;
		else if (type.equals("boolean") || type.equals("bool")) return DataType.BOOL;
		else if (type.equals("vec2")) return DataType.VEC2;
		else if (type.equals("vec3")) return DataType.VEC3;
		else if (type.equals("tex2d")) return DataType.SAMPLER2D;
		else if (type.equals("tex1d")) return DataType.SAMPLER1D;
		else if (type.equals("mat2")) return DataType.MAT2;
		else if (type.equals("mat3")) return DataType.MAT3;
		else if (type.equals("mat4")) return DataType.MAT4;
		else throw new RuntimeException("Unknown ParamType: " + type);
	}
	
	
	/**
	 * Sampler2D corresponds to Texture2D
	 * and Sampler1D corresponds to Texture1D 
	 */
	 private static Object s_parseParamValue(DataType type, String value, ResourceLocator locator, GL gl) throws IOException {
		 switch(type) {
		 case BOOL: return s_parseBoolean(value);
		 case VEC4: return s_parseVector4f(value);
		 case VEC3: return s_parseVector3f(value);
		 case VEC2: return s_parseVector2f(value);
		 case FLOAT: return s_parseFloat(value);
		 case SAMPLER1D: return s_parseTex1D(value, locator, gl);
		 case SAMPLER2D: return s_parseTex2D(value, locator, gl);
		 default: throw new RuntimeException("Unable to parse value for ParamType: " + type);
		 }
	 }
	 private static Texture2D s_parseTex2D(String value, ResourceLocator locator, GL gl) throws IOException {
		 if (value == null || value.equals("")) return null;
		 return TextureFactory.s_loadTexture(value, locator, gl.getGL1());
	 }
	 private static Texture1D s_parseTex1D(String value, ResourceLocator locator, GL gl) throws IOException {
		 if (value == null || value.equals("")) return null;
		 return TextureFactory.s_loadTexture1D(value, locator, gl.getGL1());
	 }
	 private static boolean s_parseBoolean(String value) {
		 if (value == null || value.equals("")) return false;
		 else return Boolean.parseBoolean(value);
	 }
	 private static float s_parseFloat(String value) {
		 if (value == null || value.equals("")) return 0;
		 else return Float.parseFloat(value);
	 }
	 private static Vector4f s_parseVector4f(String value) {
		 if (value == null || value.equals("")) return new Vector4f();
		 try {
			 value = value.trim();
			 if (value.indexOf('(') != -1 && value.indexOf(')') != -1) {
				 value = value.split("\\(")[1].split("\\)")[0].replaceAll(",", " ");
			 }
			 String[] floats = value.split("\\s+");
			 if (floats.length > 4) throw new RuntimeException("Too many arguments!");
			 return new Vector4f(Integer.parseInt(floats[0]), Integer.parseInt(floats[1]),
					 			  Integer.parseInt(floats[2]), Integer.parseInt(floats[3]));
		 } catch (Exception e) {
			 throw new RuntimeException("Unable to parse: " + value, e);
		 }
	 }
	 private static Vector3f s_parseVector3f(String value) {
		 if (value == null || value.equals("")) return new Vector3f();
		 try {
			 value = value.trim();
			 if (value.indexOf('(') != -1 && value.indexOf(')') != -1) {
				 value = value.split("\\(")[1].split("\\)")[0].replaceAll(",", " ");
			 }
			 String[] floats = value.split("\\s+");
			 if (floats.length > 3) throw new RuntimeException("Too many arguments!");
			 return new Vector3f(Integer.parseInt(floats[0]), Integer.parseInt(floats[1]),
					 			  Integer.parseInt(floats[2]));
		 } catch (Exception e) {
			 throw new RuntimeException("Unable to parse: " + value, e);
		 }
	 }
	 private static Vector2f s_parseVector2f(String value) {
		 if (value == null || value.equals("")) return new Vector2f();
		 try {
			 value = value.trim();
			 if (value.indexOf('(') != -1 && value.indexOf(')') != -1) {
				 value = value.split("\\(")[1].split("\\)")[0].replaceAll(",", " ");
			 }
			 String[] floats = value.split("\\s+");
			 if (floats.length > 2) throw new RuntimeException("Too many arguments!");
			 return new Vector2f(Integer.parseInt(floats[0]), Integer.parseInt(floats[1]));
		 } catch (Exception e) {
			 throw new RuntimeException("Unable to parse: " + value, e);
		 }
	 }
}
