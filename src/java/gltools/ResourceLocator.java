package gltools;

import java.io.InputStream;

public interface ResourceLocator {
	public InputStream getResource(String resource);
	public class ClasspathResourceLocator implements ResourceLocator {
		public InputStream getResource(String resource) {
			InputStream in = ResourceLocator.class.getClassLoader().getResourceAsStream(resource);
			if (in == null) throw new RuntimeException("Resource " + resource + " not found");
			return in;
		}
	}
}
