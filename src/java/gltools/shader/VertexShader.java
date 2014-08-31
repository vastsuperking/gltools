package gltools.shader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class VertexShader extends Shader {
	public static final ShaderType TYPE = ShaderType.VERTEX_SHADER;

	public VertexShader() {
		super(TYPE);
	}
	public VertexShader(VertexShader src) {
		super(src);
	}
	public VertexShader(String src) {
		super(src, TYPE);
	}
	public VertexShader(InputStream in) throws IOException {
		super(in, TYPE);
	}
	public VertexShader(File src) throws IOException {
		super(src, TYPE);
	}
}