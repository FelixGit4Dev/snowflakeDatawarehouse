package visualization.queries;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class QueryBuilder implements Serializable{
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public String assembleOlapQuery(){
return buildSelectStatementPart()+buildInnerJoinPart()+buildGroupByPart()+buildOrderByPart()+";";	
}	
	

public String buildSelectStatementPart(){
	return null;
	
}	
	
public String buildInnerJoinPart() {
	return buildJoin(null,null);
	
}	


private String buildJoin(QueryPartObject one, QueryPartObject joinOnObject) {
return String.format(QueryConstants.INNERJOIN, joinOnObject.getTableName()+" "+joinOnObject.getAlias(),one.getJoinAttribute(),joinOnObject.getJoinAttribute());
	
}


public String buildGroupByPart(){
String groupsBys="";

	return String.format(QueryConstants.GROUPBY, groupsBys );
	
}

public String buildOrderByPart(){
	return null;
	
}


public boolean isSingleColumnResult() {
	// TODO Auto-generated method stub
	return false;
}
}
