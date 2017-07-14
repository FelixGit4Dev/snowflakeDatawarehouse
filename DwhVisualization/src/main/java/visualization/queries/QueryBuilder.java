package visualization.queries;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class QueryBuilder implements Serializable{
	
	
	
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public String assembleOlapQuery(HashMap<String, HashMap<String, List<String>>> map){
return buildSelectStatementPart(map.get(""))+buildInnerJoinPart(map.get(""))+buildGroupByPart(map.get(""))+buildOrderByPart(map.get(""))+";";	
}	
	

public String buildSelectStatementPart(HashMap<String, List<String>> hashMap){
	return null;
	
}	
	
public String buildInnerJoinPart(HashMap<String, List<String>> hashMap) {
	return buildJoin(null,null);
	
}	


private String buildJoin(QueryPartObject one, QueryPartObject joinOnObject) {
return String.format(QueryConstants.INNERJOIN, joinOnObject.getTableName()+" "+joinOnObject.getAlias(),one.getJoinAttribute(),joinOnObject.getJoinAttribute());
	
}


public String buildGroupByPart(HashMap<String, List<String>> hashMap){
String groupsBys="";

	return String.format(QueryConstants.GROUPBY, groupsBys );
	
}

public String buildOrderByPart(HashMap<String, List<String>> hashMap){
	return null;
	
}


public boolean isSingleColumnResult() {
	// TODO Auto-generated method stub
	return false;
}
}
