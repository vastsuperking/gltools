package glextra.picker;

import glcommon.BufferUtils;
import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glcommon.vector.Vector4f;
import gltools.gl.GL;
import gltools.gl.GL1;
import gltools.gl.GL2;
import gltools.shader.DataType;
import gltools.shader.InputUsage;
import gltools.shader.Program;
import gltools.shader.ProgramXMLLoader;
import gltools.shader.Uniform;

import java.nio.ByteBuffer;

//MULTIPLE PICKERS SHOULD NOT BE RUNNING AT THE SAME TIME!!!
public class Picker3D {
	private static Program s_program = null;
	
	public Picker3D() {
		
	}
	
	//Does the rendering of the scene in colored mode
	public void startRenderingScene(GL2 gl) {
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL1.GL_DEPTH_BUFFER_BIT | GL1.GL_COLOR_BUFFER_BIT | GL1.GL_STENCIL_BUFFER_BIT);
		s_program.bind(gl);
	}
	
	public void stopRenderingScene(GL2 gl) {
		s_program.unbind(gl);
	}
	
	public void setID(GL2 gl, int id) {
		//Convert the id to a color
		float r = ((id & 0x000000FF) >> 0) / 255f;
		float g = ((id & 0x0000FF00) >> 8) / 255f;
		float b = ((id & 0x00FF0000) >> 16) / 255f;
		s_program.getInputs(Uniform.class, new InputUsage("PICKING_COLOR", DataType.VEC4, Uniform.class))
			.setValue(gl, new Vector4f(r, g, b, 1f));;
	}
	
	public int pick(GL2 gl, int mouseX, int mouseY) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(4);
		gl.glReadPixels(mouseX, mouseY, 1, 1, GL1.GL_RGBA, GL1.GL_UNSIGNED_BYTE, buffer);
		
		int pickedID = buffer.getInt() & 0x00FFFFFF;
		
		return pickedID;
	}
	
	public static void s_init(GL gl) {
		try {
			s_program = ProgramXMLLoader.s_load(gl, "Programs/picker.prog", new ClasspathResourceLocator()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
