package visualization.queries;

import java.util.HashMap;

public class QueryConstants {

public static final String SELECTFROM="SELECT s% FROM sales_fact f";
public static final String INNERJOIN="INNER JOIN  s% ON s%=  s%";
public static final String GROUPBY="GROUP BY ";
public  static final String ORDERBY="ORDER BY ";


private static HashMap<String, String> JOINMAP;


public static HashMap<String, String> getJOINMAP() {
	if(JOINMAP==null){
QueryConstants.initializeJoinMap();		
	}
	return JOINMAP;
}


private static void initializeJoinMap() {
JOINMAP= new HashMap<String, String>();

	
}







}
