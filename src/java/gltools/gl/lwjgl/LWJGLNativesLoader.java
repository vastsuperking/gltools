package gltools.gl.lwjgl;

import glcommon.util.Arch;
import glcommon.util.Platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LWJGLNativesLoader {
	private static final Logger logger = LoggerFactory.getLogger(LWJGLNativesLoader.class);
	
	private static boolean s_loaded;
	
	public static final String LIBS_LOC = "Libraries/Natives";
	public static final String WINDOWS_LIBS_LOC = LIBS_LOC + "/Windows";
	public static final String OSX_LIBS_LOC = LIBS_LOC + "/OSX";
	public static final String LINUX_LIBS_LOC = LIBS_LOC + "/Linux";
	
	public synchronized static void load(File dir) {
		logger.info("Attempting to load natives from " + dir.getAbsolutePath());
		Platform platform = Platform.s_get();
		if (!dir.exists()) dir.mkdirs();
		switch(platform) {
		case WINDOWS: {
			check(dir, WINDOWS_LIBS_LOC, "lwjgl.dll");
			check(dir, WINDOWS_LIBS_LOC, "OpenAL32.dll");
		} break;
		case LINUX: {
			check(dir, LINUX_LIBS_LOC, "liblwjgl.so");
			check(dir, LINUX_LIBS_LOC, "libopenal.so");
		} break;
		case OSX: {
			check(dir, OSX_LIBS_LOC, "liblwjl.dylib");
			check(dir, OSX_LIBS_LOC, "libopenal.dylib");
		} break;
		default: logger.error(platform + " not supported!");
		}
		
		
		
		String loadPath = dir.getPath() + "/";
		
		switch(platform) {
		case WINDOWS: loadPath += WINDOWS_LIBS_LOC; break;
		case LINUX: loadPath += LINUX_LIBS_LOC; break;
		case OSX: loadPath += OSX_LIBS_LOC; break;
		default: logger.error(platform + "not supported!");
		}
		loadPath += "/" + Arch.s_get().toString();
		
		logger.debug("Loading: " + loadPath);
		System.setProperty("java.library.path", loadPath);
		System.setProperty("org.lwjgl.librarypath", loadPath);
		s_loaded = true;
	}
	
	private static void check(File dir, String libpath, String lib) {
		Arch arch = Arch.s_get();

		String libLoc = libpath + "/" + arch + "/" + lib;
		File file = new File(dir, libLoc);
		libLoc = "/" + libLoc;
		
		boolean writing = false;
		logger.info("Checking for " + lib + " at " + file.getPath());
		try {
			if(file.exists()) {
				InputStream in = LWJGLNativesLoader.class.getResourceAsStream(libLoc);
				if (in == null ) logger.error("Could not find packaged library: " + lib); 
				InputStream fin = new FileInputStream(file);
				try {
					if(IOUtils.contentEquals(in, fin)) {
						logger.info(lib + " is up to date at " + file.getPath());
						return;
					} else {
						logger.info(lib + " needs to be updated!");
					}
				} finally {
					IOUtils.closeQuietly(in);
					IOUtils.closeQuietly(fin);
				}
			} else {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			writing = true;
			InputStream in = LWJGLNativesLoader.class.getResourceAsStream(libLoc);
			if (in == null ) logger.error("Could not find packaged library: " + lib); 
			logger.info("Writing " + lib + " to " + file.getPath());
			FileOutputStream out = new FileOutputStream(file);
			IOUtils.copy(in, out);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		} catch(Exception e) {
			if(writing) {
				logger.error("Failed to write library " + lib + " to " + file.getPath(), e);
			} else {
				logger.error("Failed to check for library " + lib + " at " + file.getPath(), e);
			}

			e.printStackTrace();
		}
	}
	public static boolean isLoaded() { return s_loaded; }
}
