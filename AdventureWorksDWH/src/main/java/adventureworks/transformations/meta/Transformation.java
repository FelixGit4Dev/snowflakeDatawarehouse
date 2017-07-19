package adventureworks.transformations.meta;

import java.io.Serializable;
import java.util.HashMap;

public interface Transformation extends Serializable{

	
	public HashMap<String,Long> initDimension();
		

	
	public HashMap<String,Long> update();
}
