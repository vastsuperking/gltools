package gltools.texture;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

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
	
	public Texture(int handle, TextureTarget target) {
		m_id = handle;
		m_target = target;
	}
	public Texture(TextureTarget target) {
		this(GL11.glGenTextures(), target);
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
		
	public boolean isValid() {
		return GL11.glIsTexture(getID());
	}
	
	public void bind(int unit) {
		TextureUnit.s_use(unit);		
		GL11.glBindTexture(m_target.getID(), getID());
		s_currentTextures.put(unit, this);
		m_boundUnit = unit;
	}
	public void bind() {
		bind(m_unit);
	}
	public void unbind() {
		TextureUnit.s_use(m_boundUnit);
		GL11.glBindTexture(m_target.getID(), 0);
		s_currentTextures.put(m_boundUnit, null);
	}
	public abstract void load();
	public abstract void loadParams();
	
	public void delete() {
		GL11.glDeleteTextures(m_id);
	}	
	
	protected void checkBound() {
		if (!isBound()) throw new RuntimeException("Must call texture.bind() first!");
	}
}
