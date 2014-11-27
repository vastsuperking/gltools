package gltools.texture;

import gltools.gl.GL1;

import java.nio.ByteBuffer;
import java.util.HashMap;

public abstract class Texture {
	private HashMap<Integer, Texture> s_currentTextures = new HashMap<Integer, Texture>();
	
	//Represents the unit to which this texture is currently bound
	private int m_boundUnit = 0;

	private TextureFormat m_format = TextureFormat.RGBA8;
	//The unit to which this texture will be bound if no arguments are
	//specified with bind()
	private int m_unit = 0;
	private TextureTarget m_target;
	private TextureFilterMode m_minFilterMode = TextureFilterMode.NEAREST;
	private TextureFilterMode m_maxFilterMode = TextureFilterMode.NEAREST;
	private TextureWrapMode m_sWrapMode = TextureWrapMode.CLAMP_TO_EDGE;
	private TextureWrapMode m_tWrapMode = TextureWrapMode.CLAMP_TO_EDGE;
	
	private int m_id = -1;
	
	//A flipped bytebuffer containing texture data
	private ByteBuffer m_data = null;
	
	public Texture(TextureTarget target) {
		m_target = target;
	}
	
	public void init(GL1 gl) {
		m_id = gl.glGenTextures();
	}
	
	public TextureFilterMode 	getMinFilterMode() 	{ return m_minFilterMode; 	}
	public TextureFilterMode 	getMaxFilterMode() 	{ return m_maxFilterMode; 	}
	public TextureWrapMode 		getSWrapMode() 		{ return m_sWrapMode; 		}
	public TextureWrapMode 		getTWrapMode() 		{ return m_tWrapMode; 		}
	public TextureTarget 		getTarget() 		{ return m_target; 			}
	public int		 			getUnit() 			{ return m_unit; 			}
	public TextureFormat 		getFormat()			{ return m_format;			}
	public ByteBuffer 			getData()			{ return m_data;			}

	public int 				getID() 			{ return m_id;			}
	
	public boolean				isBound()			{ return s_currentTextures.containsValue(this); }

	public void setMinFilterMode(TextureFilterMode minFilterMode) 	{ m_minFilterMode 	= minFilterMode; 	}
	public void setMaxFilterMode(TextureFilterMode maxFilterMode) 	{ m_maxFilterMode 	= maxFilterMode; 	}
	public void setSWrapMode(TextureWrapMode sTextureWrapMode) 	{ m_sWrapMode 		= sTextureWrapMode; }
	public void setTWrapMode(TextureWrapMode tTextureWrapMode) 	{ m_tWrapMode 		= tTextureWrapMode; }
	public void setTarget(TextureTarget target) 					{ m_target 			= target; 			}	
	public void setUnit(int unit) 									{ m_unit 			= unit; 			}
	public void setFormat(TextureFormat format)					{ m_format			= format;			}
	
	public void setData(ByteBuffer data)							{ m_data			= data;				}
		
	public boolean isValid(GL1 gl) {
		return gl.glIsTexture(getID());
	}
	
	public void bind(int unit, GL1 gl) {
		TextureUnit.s_use(gl, unit);		
		gl.glBindTexture(m_target.getID(), getID());
		s_currentTextures.put(unit, this);
		m_boundUnit = unit;
	}
	public void bind(GL1 gl) {
		bind(m_unit, gl);
	}
	public void unbind(GL1 gl) {
		TextureUnit.s_use(gl, m_boundUnit);
		gl.glBindTexture(m_target.getID(), 0);
		s_currentTextures.put(m_boundUnit, null);
	}
	public abstract void load(GL1 gl);
	public abstract void loadParams(GL1 gl);
	
	public void delete(GL1 gl) {
		gl.glDeleteTextures(m_id);
	}	
	
	//TODO: Cleanup, make this not context-specific
	protected void checkBound() {
		if (!isBound()) throw new RuntimeException("Must call texture.bind() first!");
	}
}
