package gltools.gl.lwjgl.glfw;

import gltools.display.Monitor;
import gltools.display.ResizeListener;
import gltools.display.Window;
import gltools.gl.lwjgl.LWJGLNativesLoader;
import gltools.input.Keyboard;
import gltools.input.Mouse;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.glfw.ErrorCallback;
import org.lwjgl.system.glfw.GLFW;
import org.lwjgl.system.glfw.WindowCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GLFWWindow implements Window {
	private final static Logger logger = LoggerFactory.getLogger(GLFWWindow.class);
	
	private final boolean m_vsync;
	
	private int m_width;
	private int m_height;
	private int m_x;
	private int m_y;
	
	private long m_id = -1;
	
	private boolean m_fullscreen;
	private boolean m_resizable;
	
	private String m_title;
	
	
	private ArrayList<ResizeListener> m_resizeListeners = new ArrayList<ResizeListener>();
	
	private GLFWKeyboard m_keyboard;
	private GLFWMouse m_mouse;
	
	
	static {
		if (!LWJGLNativesLoader.isLoaded()) LWJGLNativesLoader.load(new File(System.getProperty("user.home") + "/.gltools/lwjgl"));
		GLFW.glfwSetErrorCallback(ErrorCallback.Util.getDefault());
		if (GLFW.glfwInit() != GL11.GL_TRUE) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
	}
	
	public GLFWWindow(int width, int height, boolean vsync) {
		m_width = width;
		m_height = height;
		m_vsync = vsync;

		m_keyboard = new GLFWKeyboard();
		m_mouse = new GLFWMouse();
	}

	@Override
	public int getWidth() { return m_width; }
	@Override
	public int getHeight() { return m_height; }

	public GLFWMouse getMouse() { return m_mouse; }
	public GLFWKeyboard getKeyboard() { return m_keyboard; }

	public void useFullscreen(boolean fullscreen) {
		m_fullscreen = fullscreen;
	}
	public void setResizable(boolean resizable) {
		m_resizable = resizable;
	}
	
	public void setTitle(String title) { 
		m_title = title;
	}
	public String getTitle() {
		return m_title;
	}

	public boolean initialized() { return m_id != -1; }
	
	@Override
	public void addResizedListener(ResizeListener l) {
		m_resizeListeners.add(l);
	}
	
	@Override
	public void init(Monitor monitor) {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE,  m_resizable ? GL11.GL_TRUE : GL11.GL_FALSE);
		m_id = GLFW.glfwCreateWindow(m_width, m_height, m_title, MemoryUtil.NULL, MemoryUtil.NULL);
		if (m_id == MemoryUtil.NULL) throw new RuntimeException("Failed to create window");
		
		WindowCallback.set(m_id, new WindowCallback() {
			@Override
			public void windowPos(long window, int xpos, int ypos) {
				GLFWUtils.printEvent(window, "moved to %d, %d", xpos, ypos);
			}

			@Override
			public void windowSize(long window, int width, int height) {
				//On resize
				m_width = width;
				m_height = height;
				for (ResizeListener rl : m_resizeListeners) {
					rl.onResize(width, height);
				}
			}

			@Override
			public void windowClose(long window) {
				GLFWUtils.printEvent(window, "closed");
			}

			@Override
			public void windowRefresh(long window) {
				GLFWUtils.printEvent(window, "refreshed");
			}

			@Override
			public void windowFocus(long window, int focused) {
				GLFWUtils.printEvent(window, focused == 0 ? "lost focus" : "gained focus");
			}

			@Override
			public void windowIconify(long window, int iconified) {
				GLFWUtils.printEvent(window, iconified == 0 ? "restored" : "iconified");
			}

			@Override
			public void framebufferSize(long window, int width, int height) {
				GLFWUtils.printEvent(window, "framebuffer resized to %d x %d", width, height);
			}

			@Override
			public void key(long window, int key, int scancode, int action, int mods) {
				m_keyboard.key(GLFWWindow.this, key, scancode, action, mods);
			}

			@Override
			public void character(long window, int codepoint) {
				m_keyboard.character(GLFWWindow.this, Character.toChars(codepoint)[0]);
			}

			@Override
			public void charMods(long window, int codepoint, int mods) {
			}

			@Override
			public void mouseButton(long window, int button, int action, int mods) {
				m_mouse.mouseButton(GLFWWindow.this, button, action, mods);
			}

			@Override
			public void cursorPos(long window, double xpos, double ypos) {
				m_mouse.cursorPos(GLFWWindow.this, (float) xpos, (float) ypos);
			}

			@Override
			public void cursorEnter(long window, int entered) {
				m_mouse.cursorEnter(GLFWWindow.this, entered == 1);
			}

			@Override
			public void scroll(long window, double xoffset, double yoffset) {
				m_mouse.scroll(GLFWWindow.this, (float) xoffset, (float) yoffset);
			}

			@Override
			public void drop(long window, int count, long names) {
				GLFWUtils.printEvent(window, "drop %d file%s", count, count == 1 ? "" : "s");

				PointerBuffer nameBuffer = MemoryUtil.memPointerBuffer(names, count);
				for ( int i = 0; i < count; i++ ) {
					System.out.format("\t%d: %s%n", i + 1, MemoryUtil.memDecodeUTF8(MemoryUtil.memByteBufferNT1(nameBuffer.get(i))));
				}
			}
		});
		
		GLFW.glfwMakeContextCurrent(m_id);
		GLContext.createFromCurrent();
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(m_id);
	}

	@Override
	public void destroy() {
		GLFW.glfwDestroyWindow(m_id);
	}

	@Override
	public boolean closeRequested() {
		return GLFW.glfwWindowShouldClose(m_id) == GL11.GL_TRUE;
	}

	@Override
	public void update(int fps) {
		GLFW.glfwPollEvents();
		GLFW.glfwSwapBuffers(m_id);
	}
}
