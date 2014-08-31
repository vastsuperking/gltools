package glextra;



public abstract class RenderState {
	/*private StencilTest m_stencilTest = new StencilTest();
	private StencilOperation m_stencilOP = new StencilOperation();
	
	private float m_polgonOffsetFactor = 0f;
	private float m_polgonOffsetUnit = 0f;
	private float m_pointSize = 1f;
	private float m_lineWidth = 1f;
	
	
	private boolean m_stencilTestEnabled = false;
	private boolean m_polyOffsetEnabled = false;
	private boolean m_depthTestEnabled = false;
	
	
	private boolean m_redColorMask = true;
	private boolean m_greenColorMask = true;
	private boolean m_blueColorMask = true;
	
	private boolean m_depthMask = true;*/
	
	
	public abstract void apply();
	
	public abstract void setEnabled(boolean value);
	public abstract boolean isEnabled(); 
}
