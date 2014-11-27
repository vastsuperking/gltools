package gltools.buffer;

import gltools.gl.GL1;

public class StencilBuffer {
	private static StencilBuffer s_instance = null;

	private StencilOperation m_currentOP = new StencilOperation();
	private StencilTest m_currentTest = new StencilTest();
	private int m_currentMask = 0x00;

	public StencilOperation getOperation() { return m_currentOP; }
	public StencilTest getTest() { return m_currentTest; }
	public int getMask() { return m_currentMask; }
	
	public boolean isEnabled() { return getMask() == 0x00 ? false : true; }
	
	public void setTest(StencilTest test, GL1 gl) {
		m_currentTest = test;
		gl.glStencilFunc(test.getFunction().getID(), test.getReference(), test.getMask());
	}
	public void setOperation(StencilOperation operation, GL1 gl) {
		m_currentOP = operation;
		gl.glStencilOp(operation.getFail().getID(), operation.getZFail().getID(), operation.getPass().getID());
	}
	public void setMask(int mask, GL1 gl) {
		m_currentMask = mask;
		gl.glStencilMask(mask);
	}
	public void setEnabled(boolean enabled, GL1 gl) {
		setMask(enabled ? 0xFF : 0x00, gl);
	}
	public void clear(GL1 gl) {
		setEnabled(true, gl);
		gl.glClear(GL1.GL_STENCIL_BUFFER_BIT);
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
		NEVER(GL1.GL_NEVER), ALWAYS(GL1.GL_ALWAYS), EQUAL(GL1.GL_EQUAL), NOT_EQUAL(GL1.GL_NOTEQUAL), 
		LESS(GL1.GL_LESS), GREATER(GL1.GL_GREATER), LESS_OR_EQUAL(GL1.GL_LEQUAL), GREATER_OR_EQUAL(GL1.GL_GEQUAL);
		private final int m_func;
		
		TestFunction(int func) {
			m_func = func;
		}
		
		public int getID() { return m_func; }
	}
	public static enum StencilOP { 
		KEEP(GL1.GL_KEEP), ZERO(GL1.GL_ZERO), REPLACE(GL1.GL_REPLACE), INCREMENT(GL1.GL_INCR), DECREMENT(GL1.GL_DECR), INVERT(GL1.GL_INVERT); 
		int m_op;
		StencilOP(int op) { m_op = op; }
		public int getID() { return m_op; }
	}
}
