package gltools.shader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeometryShader extends Shader {
	
	public GeometryShader() {
		super(ShaderType.GEOMETRY_SHADER);
	}
	public GeometryShader(GeometryShader src) {
		super(src);
	}
	public GeometryShader(String src) {
		super(src, ShaderType.GEOMETRY_SHADER);
	}
	public GeometryShader(InputStream in) throws IOException {
		super(in, ShaderType.GEOMETRY_SHADER);
	}
	public GeometryShader(File src) throws IOException {
		super(src, ShaderType.GEOMETRY_SHADER);
	}
}