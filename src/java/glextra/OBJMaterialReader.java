package glextra;

import glcommon.Color;
import glcommon.util.ResourceLocator;
import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glextra.material.Material;
import glextra.material.MaterialXMLLoader;
import gltools.shader.Program.ProgramLinkException;
import gltools.shader.Shader.ShaderCompileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class OBJMaterialReader {
	public static HashMap<String, Material> s_readMaterials(String resource, ResourceLocator locator) throws IOException {
		HashMap<String, Material> mats = new HashMap<String, Material>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(locator.getResource(resource)));
		
		Material current = null;
		
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("newmtl ")) {
				if (current != null) current.ready();
				String name = line.substring(7);
				current = s_newMTL();
				mats.put(name, current);
			} else if (line.startsWith("Ka ")) {
				current.setColor("ambientColor", s_parseColor(line.substring(3)));
			} else if (line.startsWith("Kd ")) {
				current.setColor("diffuseColor", s_parseColor(line.substring(3)));
			}
		}
		if (current != null) current.ready();
		return mats;
	}
	private static Color s_parseColor(String string) {
		String[] parts = string.split("\\s+");
		float red = Float.parseFloat(parts[0]);
		float green = Float.parseFloat(parts[1]);
		float blue = Float.parseFloat(parts[2]);
		return new Color(red, green, blue);
	}
	
	public static Material s_newMTL() throws IOException {
		try {
			Material m = MaterialXMLLoader.s_load("Materials/M3D/obj.mat", new ClasspathResourceLocator()).get(0);
			return m;
		} catch (ShaderCompileException e) {
			e.printStackTrace();
		} catch (ProgramLinkException e) {
			e.printStackTrace();
		}
		return null;
	}
}
