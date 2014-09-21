package glextra.material;

import gltools.shader.DataType;
import gltools.utils.Pair;

import java.util.HashMap;

public interface GlobalParamProvider {
	public boolean hasParam(String name, DataType type);
	public GlobalParam getParam(String name, DataType type);
	
	public class ListParamProvider implements GlobalParamProvider {
		private HashMap<Pair<String, DataType>, GlobalParam> m_paramMap = 
				new HashMap<Pair<String, DataType>, GlobalParam>();
		
		public ListParamProvider(GlobalParam... params) {
			for (GlobalParam p : params) {
				addParam(p);
			}
		}
		@Override
		public String toString() {
			return m_paramMap.toString();
		}
		
		public void addParam(GlobalParam param) {
			System.out.println("Param name: " + param.getName() + " " + param.getDataType());
			m_paramMap.put(new Pair<String, DataType>(param.getName(), param.getDataType()), param);
		}
		
		@Override
		public boolean hasParam(String name, DataType type) {
			return m_paramMap.containsKey(new Pair<String, DataType>(name, type));
		}
		@Override
		public GlobalParam getParam(String name, DataType type) {
			return m_paramMap.get(new Pair<String, DataType>(name, type));
		}
	}	
}
