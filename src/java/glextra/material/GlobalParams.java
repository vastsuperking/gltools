package glextra.material;

import glextra.material.GlobalParamProvider.ListParamProvider;

public class GlobalParams extends ListParamProvider {
	private static GlobalParams s_instance;
	
	public static GlobalParams getInstance() {
		if (s_instance == null) s_instance = new GlobalParams();
		return s_instance;
	}

}
