package glextra.picker;

import glcommon.BufferUtils;
import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glcommon.vector.Vector4f;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Uniform;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

//MULTIPLE PICKERS SHOULD NOT BE RUNNING AT THE SAME TIME!!!
public class Picker3D {
	private static Program s_program = null;
	
	public Picker3D() {
		
	}
	
	//Does the rendering of the scene in colored mode
	public void startRenderingScene() {
		GL11.glClearColor(1, 1, 1, 1);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		s_program.bind();
	}
	
	public void stopRenderingScene() {
		s_program.unbind();
	}
	
	public void setID(int id) {
		//Convert the id to a color
		float r = ((id & 0x000000FF) >> 0) / 255f;
		float g = ((id & 0x0000FF00) >> 8) / 255f;
		float b = ((id & 0x00FF0000) >> 16) / 255f;
		s_program.getInputs(Uniform.class, new InputUsage("PICKING_COLOR", DataType.VEC4, Uniform.class))
			.setValue(new Vector4f(r, g, b, 1f));;
	}
	
	public int pick(int mouseX, int mouseY) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(4);
		GL11.glReadPixels(mouseX, mouseY, 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		
		int pickedID = buffer.getInt() & 0x00FFFFFF;
		
		return pickedID;
	}
	
	public static void s_init() {
		try {
			s_program = ProgramXMLLoader.s_load("Programs/picker.prog", new ClasspathResourceLocator()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
