package gltools.gl.lwjgl;

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
	
	public static void load(File dir) {
		logger.info("Attempting to load natives from " + dir.getAbsolutePath());
		Platform platform = Platform.s_get();
		if (!dir.exists()) dir.mkdirs();
		switch(platform) {
		case WINDOWS: {
			check(dir, WINDOWS_LIBS_LOC, "lwjgl.dll", "86");
			check(dir, WINDOWS_LIBS_LOC, "lwjgl64.dll", "64");
			check(dir, WINDOWS_LIBS_LOC, "OpenAL32.dll", "86");
			check(dir, WINDOWS_LIBS_LOC, "OpenAL64.dll", "64");
			check(dir, WINDOWS_LIBS_LOC, "jinput-raw.dll", "86");
			check(dir, WINDOWS_LIBS_LOC, "jinput-raw_64.dll", "64");
			check(dir, WINDOWS_LIBS_LOC, "jinput-dx8.dll", "86");
			check(dir, WINDOWS_LIBS_LOC, "jinput-dx8_64.dll", "64");
		} break;
		case LINUX: {
			check(dir, LINUX_LIBS_LOC, "liblwjgl.so", "86");
			check(dir, LINUX_LIBS_LOC, "liblwjgl64.so", "64");
			check(dir, LINUX_LIBS_LOC, "libopenal.so", "86");
			check(dir, LINUX_LIBS_LOC, "libopenal64.so", "64");
			check(dir, LINUX_LIBS_LOC, "libjinput-linux.so", "86");
			check(dir, LINUX_LIBS_LOC, "libjinput-linux64.so", "64");
		} break;
		case OSX: {
			check(dir, OSX_LIBS_LOC, "liblwjgl.jnilib", "both");
			check(dir, OSX_LIBS_LOC, "openal.dylib", "both");
			check(dir, OSX_LIBS_LOC, "libjinput-osx.jnilib", "both");
		} break;
		default: logger.error(platform + " not supported!");
		}
		
		System.setProperty("java.library.path", dir.getPath());
		System.setProperty("org.lwjgl.librarypath", dir.getPath());
		System.setProperty("net.java.games.input.librarypath", dir.getPath());
		s_loaded = true;
	}
	
	private static void check(File dir, String libpath, String lib, String arch) {
		String libLoc = "/" + libpath + "/" + lib;
		if(arch.equals("both") || System.getProperty("os.arch").contains(arch)) {
			File file = new File(dir, lib);
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
	}
	public static boolean isLoaded() { return s_loaded; }
}
