package glextra.material;

import gltools.shader.InputUsage;

import java.util.HashMap;

public interface GlobalParamProvider {
	public boolean hasParam(InputUsage usage);
	public GlobalParam getParam(InputUsage usage);
	
	public class ListParamProvider implements GlobalParamProvider {
		private HashMap<InputUsage, GlobalParam> m_paramMap = 
				new HashMap<InputUsage, GlobalParam>();
		
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
			m_paramMap.put(param.getUsage(), param);
		}
		
		@Override
		public boolean hasParam(InputUsage usage) {
			return m_paramMap.containsKey(usage);
		}
		@Override
		public GlobalParam getParam(InputUsage usage) {
			return m_paramMap.get(usage);
		}
	}	
}
