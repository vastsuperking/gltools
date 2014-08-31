package gltools.shader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FragmentShader extends Shader {
	public static final ShaderType TYPE = ShaderType.FRAGMENT_SHADER;

	public FragmentShader() {
		super(TYPE);
	}
	public FragmentShader(FragmentShader src) {
		super(src);
	}
	public FragmentShader(String src) {
		super(src, TYPE);
	}
	public FragmentShader(InputStream in) throws IOException {
		super(in, TYPE);
	}
	public FragmentShader(File src) throws IOException {
		super(src, TYPE);
	}
}