package gltools.shader;


public class InputUsage {
	public static final InputUsage VERTEX_POSITION_3D = new InputUsage("VERTEX_POSITION", DataType.VEC3, Attribute.class);
	public static final InputUsage VERTEX_POSITION_2D = new InputUsage("VERTEX_POSITION", DataType.VEC2, Attribute.class);
	public static final InputUsage VERTEX_COLOR = new InputUsage("VERTEX_COLOR", DataType.VEC4, Attribute.class);
	public static final InputUsage VERTEX_NORMAL = new InputUsage("VERTEX_NORMAL", DataType.VEC3, Attribute.class);
	public static final InputUsage VERTEX_TANGENT = new InputUsage("VERTEX_TANGENT", DataType.VEC3, Attribute.class);
	public static final InputUsage VERTEX_BITANGENT = new InputUsage("VERTEX_BITANGENT", DataType.VEC3, Attribute.class);
	public static final InputUsage VERTEX_TEX_COORD = new InputUsage("VERTEX_TEX_COORD", DataType.VEC2, Attribute.class);

	public static final InputUsage POINT_SIZE = new InputUsage("POINT_SIZE", DataType.FLOAT, Attribute.class);
	public static final InputUsage POINT_SIZE_FACTOR = new InputUsage("POINT_SIZE_FACTOR", DataType.FLOAT, Uniform.class);
	public static final InputUsage PARTICLE_LIFE_REMAINING_PERCENTAGE = new InputUsage("PARTICLE_LIFE_REMAINING_PERCENTAGE", DataType.FLOAT, Attribute.class);

	public static final InputUsage POINT_TEXTURE_SAMPLER = new InputUsage("POINT_TEXTURE_SAMPLER", DataType.SAMPLER2D, Uniform.class);
	
	public static final InputUsage POINT_SIZE_TEXTURE_SAMPLER = new InputUsage("POINT_SIZE_TEXTURE_SAMPLER", DataType.SAMPLER1D, Uniform.class);
	public static final InputUsage POINT_COLOR_TEXTURE_SAMPLER = new InputUsage("POINT_COLOR_TEXTURE_SAMPLER", DataType.SAMPLER1D, Uniform.class);

	public static final InputUsage GBUFFER_VERTEX_SAMPLER = new InputUsage("GBUFFER_VERTEX_SAMPLER", DataType.SAMPLER2D, Uniform.class);
	public static final InputUsage GBUFFER_NORMAL_SAMPLER = new InputUsage("GBUFFER_NORMAL_SAMPLER", DataType.SAMPLER2D, Uniform.class);
	public static final InputUsage GBUFFER_DIFFUSE_SAMPLER = new InputUsage("GBUFFER_DIFFUSE_SAMPLER", DataType.SAMPLER2D, Uniform.class);
	public static final InputUsage GBUFFER_SPECULAR_SAMPLER = new InputUsage("GBUFFER_SPECULAR_SAMPLER", DataType.SAMPLER2D, Uniform.class);
	
	public static final InputUsage DIFFUSE_COLOR = new InputUsage("DIFFUSE_COLOR", DataType.VEC4, Uniform.class);
	public static final InputUsage DIFFUSE_SAMPLER = new InputUsage("DIFFUSE_SAMPLER", DataType.SAMPLER2D, Uniform.class);

	public static final InputUsage LIGHT_AMBIENT = new InputUsage("LIGHT_AMBIENT", DataType.VEC4, Uniform.class);
	public static final InputUsage LIGHT_DIFFUSE = new InputUsage("LIGHT_DIFFUSE", DataType.VEC4, Uniform.class);
	public static final InputUsage LIGHT_SPECULAR = new InputUsage("LIGHT_SPECULAR", DataType.VEC4, Uniform.class);
	public static final InputUsage LIGHT_POSITION = new InputUsage("LIGHT_POSITION", DataType.VEC3, Uniform.class);
	public static final InputUsage LIGHT_ATTENUATION_VEC3 = new InputUsage("LIGHT_ATTENUATION_VEC3", DataType.VEC3, Uniform.class);
	
	public static final InputUsage MODEL_MATRIX = new InputUsage("MODEL_MATRIX", DataType.MAT4, Uniform.class);
	public static final InputUsage VIEW_MATRIX = new InputUsage("VIEW_MATRIX", DataType.MAT4, Uniform.class);
	public static final InputUsage PROJECTION_MATRIX = new InputUsage("PROJECTION_MATRIX", DataType.MAT4, Uniform.class);
	public static final InputUsage NORMAL_MATRIX = new InputUsage("NORMAL_MATRIX", DataType.MAT3, Uniform.class);

	public static final InputUsage MODEL_MATRIX_2D = new InputUsage("MODEL_MATRIX", DataType.MAT3, Uniform.class);
	public static final InputUsage VIEW_MATRIX_2D = new InputUsage("VIEW_MATRIX", DataType.MAT3, Uniform.class);
	public static final InputUsage PROJECTION_MATRIX_2D = new InputUsage("PROJECTION_MATRIX", DataType.MAT3, Uniform.class);

	//----------------------------------------
	private final String m_usage;
	private final Class<? extends Input> m_inputType;
	private final DataType m_dataType;
	
	public InputUsage(String usage, DataType d, Class<? extends Input> inputType) {
		m_usage = usage;
		m_dataType = d;
		m_inputType = inputType;
	}

	public String getUsage() { return m_usage; }
	public DataType getDataType() { return m_dataType; }
	public Class<? extends Input> getInputType() { return m_inputType; }
	
	@Override
	public String toString() {
		return m_usage + ":" + m_dataType;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + m_usage.hashCode();
		result = 31 * result + m_dataType.hashCode();
		result = 31 * result + m_inputType.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof InputUsage) {
			return ((InputUsage) object).getUsage().equals(getUsage()) &&
					m_dataType == ((InputUsage) object).getDataType() && 
					((InputUsage) object).getInputType().equals(getInputType());
		} else return false;
	}
}
