package visualization.queries;

public class QueryBuilder {
	
	
	
public String assembleOlapQuery(){
return buildSelectStatementPart()+buildInnerJoinPart()+buildGroupByPart()+buildOrderByPart()+";";	
}	
	

public String buildSelectStatementPart(){
	return null;
	
}	
	
public String buildInnerJoinPart() {
	return null;
	
}	


public String buildGroupByPart(){
	return null;
	
}

public String buildOrderByPart(){
	return null;
	
}
}
