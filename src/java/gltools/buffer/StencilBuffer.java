package gltools.buffer;

import org.lwjgl.opengl.GL11;

public class StencilBuffer {
	private static StencilBuffer s_instance = null;

	private StencilOperation m_currentOP = new StencilOperation();
	private StencilTest m_currentTest = new StencilTest();
	private int m_currentMask = 0x00;

	public StencilOperation getOperation() { return m_currentOP; }
	public StencilTest getTest() { return m_currentTest; }
	public int getMask() { return m_currentMask; }
	
	public boolean isEnabled() { return getMask() == 0x00 ? false : true; }
	
	public void setTest(StencilTest test) {
		m_currentTest = test;
		GL11.glStencilFunc(test.getFunction().getID(), test.getReference(), test.getMask());
	}
	public void setOperation(StencilOperation operation) {
		m_currentOP = operation;
		GL11.glStencilOp(operation.getFail().getID(), operation.getZFail().getID(), operation.getPass().getID());
	}
	public void setMask(int mask) {
		m_currentMask = mask;
		GL11.glStencilMask(mask);
	}
	public void setEnabled(boolean enabled) {
		setMask(enabled ? 0xFF : 0x00);
	}
	public void clear() {
		setEnabled(true);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
	}
	
	public static StencilBuffer getInstance() {
		if (s_instance == null) s_instance = new StencilBuffer();
		return s_instance;
	}
	
	public static class StencilTest {
		private TestFunction m_func = TestFunction.EQUAL;
		private int m_ref = 0x00;
		private int m_mask = 0xFF;
		
		public StencilTest() {}
		public StencilTest(TestFunction func, int ref) {
			m_func = func;
			m_ref = ref;
		}
		public StencilTest(TestFunction func, int ref, int mask) {
			m_func = func;
			m_ref = ref;
			m_mask = mask;
		}
		
		public TestFunction 	getFunction() 	{ return m_func; 	}
		public int 			getReference() 	{ return m_ref; 	}
		public int 			getMask() 		{ return m_mask;	}

		public void setFunction(TestFunction func) {
			m_func = func;
		}
		public void setReference(int ref) {
			m_ref = ref;
		}
		public void setMask(int mask) {
			m_mask = mask;
		}
	}
	public static class StencilOperation {
		private StencilOP m_fail = StencilOP.KEEP;
		private StencilOP m_zfail = StencilOP.KEEP;
		private StencilOP m_pass = StencilOP.KEEP;
		
		public StencilOperation() {}
		public StencilOperation(StencilOP fail, StencilOP zfail, StencilOP pass) {
			m_fail = fail;
			m_zfail = zfail;
			m_pass = pass;
		}
		
		public StencilOP getFail() 		{ return m_fail; 	}
		public StencilOP getZFail() 	{ return m_zfail; 	}
		public StencilOP getPass() 		{ return m_pass; 	}
		
		public void setFail(StencilOP fail) { m_fail = fail; }
		public void setZFail(StencilOP zfail) { m_zfail = zfail; }
		public void setPass(StencilOP pass) { m_pass = pass; }
	}
	public static enum TestFunction {
		NEVER(GL11.GL_NEVER), ALWAYS(GL11.GL_ALWAYS), EQUAL(GL11.GL_EQUAL), NOT_EQUAL(GL11.GL_NOTEQUAL), 
		LESS(GL11.GL_LESS), GREATER(GL11.GL_GREATER), LESS_OR_EQUAL(GL11.GL_LEQUAL), GREATER_OR_EQUAL(GL11.GL_GEQUAL);
		private final int m_func;
		
		TestFunction(int func) {
			m_func = func;
		}
		
		public int getID() { return m_func; }
	}
	public static enum StencilOP { 
		KEEP(GL11.GL_KEEP), ZERO(GL11.GL_ZERO), REPLACE(GL11.GL_REPLACE), INCREMENT(GL11.GL_INCR), DECREMENT(GL11.GL_DECR), INVERT(GL11.GL_INVERT); 
		int m_op;
		StencilOP(int op) { m_op = op; }
		public int getID() { return m_op; }
	}
}
