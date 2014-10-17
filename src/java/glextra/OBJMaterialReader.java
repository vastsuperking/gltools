package glextra;

import glcommon.Color;
import glextra.material.Material;
import gltools.ResourceLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class OBJMaterialReader {
	public static HashMap<String, Material> s_readMaterials(String resource, ResourceLocator locator) throws IOException {
		HashMap<String, Material> mats = new HashMap<String, Material>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(locator.getResource(resource)));
		
		Material current;
		
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("newmtl ")) {
				String name = line.substring(7);
				current = new Material();
				mats.put(name, current);
			} else if (line.startsWith("Ka ")) {
				s_parseColor(line.substring(3));
			}
		}
		return mats;
	}
	private static Color s_parseColor(String string) {
		String[] parts = string.split("\\s+");
		float red = Float.parseFloat(parts[0]);
		float green = Float.parseFloat(parts[1]);
		float blue = Float.parseFloat(parts[2]);
		return new Color(red, green, blue);
	}
}
