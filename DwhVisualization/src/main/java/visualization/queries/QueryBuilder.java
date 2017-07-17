package visualization.queries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class QueryBuilder implements Serializable{
	
	
	
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public String assembleOlapQuery(HashMap<String, HashMap<String, List<String>>> map, ArrayList<String> columnOrder){
return buildSelectStatementPart(map.get("select"), columnOrder)+buildInnerJoinPart(map.get("innerJoin"))+buildGroupByPart(map.get("groupBy"))+buildOrderByPart(map.get(""))+";";	
}	
	

public String buildSelectStatementPart(HashMap<String, List<String>> hashMap, ArrayList<String> columnOrder){
	List<String> finalSelect= new ArrayList<>();

	for(String key: hashMap.keySet()){
	List<String> list= hashMap.get(key);
	finalSelect.add(list.stream().collect(Collectors.joining(", ")))	;
	}
	String result= finalSelect.stream().collect(Collectors.joining(", "));
	
	return String.format(QueryConstants.SELECTFROM, result,"salesfact");
	
}	
	
public String buildInnerJoinPart(HashMap<String, List<String>> hashMap) {
	StringBuffer buffer = new StringBuffer();
	for(String key :hashMap.keySet()){		
	buffer.append(buildJoin(new Join2Tupel(QueryConstants.getJOINMAP().get(""),QueryConstants.getJOINMAP().get(""))));}
	return buffer.toString();
	
}	


private String buildJoin(Join2Tupel tupel) {
return String.format(QueryConstants.INNERJOIN, tupel.getTwo().getTableName()+" "+tupel.getTwo().getAlias(),tupel.getOne().getJoinAttribute(),tupel.getTwo().getJoinAttribute())+"\n";
	
}


public String buildGroupByPart(HashMap<String, List<String>> hashMap){
	List<String> finalSelect= new ArrayList<>();

	for(String key: hashMap.keySet()){
	List<String> list= hashMap.get(key);
	finalSelect.add(list.stream().collect(Collectors.joining(", ")))	;
	}
	String groupsBys= finalSelect.stream().collect(Collectors.joining(", "));

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
