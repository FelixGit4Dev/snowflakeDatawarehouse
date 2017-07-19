package visualization.queries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import visualization.entity.QueryComponentsObject;

@Named
@SessionScoped
public class QueryBuilder implements Serializable{
	
	
	
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public String assembleOlapQuery(QueryComponentsObject obj){
StringBuffer buffer = new StringBuffer();	
for(String s: obj.getSelect().keySet()){
buffer.append(obj.getSelect().get(s)+", ");	
}
String select =buffer.toString().substring(0, buffer.toString().length()-2);
String join=obj.getJoins().stream().collect( Collectors.joining(" "));
String groupby=obj.getGroupBy().stream().collect(Collectors.joining(", "));
String where=obj.getWhere().stream().collect( Collectors.joining(" AND "));
return String.format("SELECT %s  %s  \n FROM sales_fact as sf \n %s "+ (where.isEmpty()?"":"WHERE")+" %s \n "+(groupby.isEmpty()?"":"GROUP BY ")+ "%s" ,"", select,join,where,groupby);}	
	

public String buildSelectStatementPart(HashMap<String, List<String>> hashMap, ArrayList<String> columnOrder){
	List<String> finalSelect= new ArrayList<>();

	for(String key: hashMap.keySet()){
	List<String> list= hashMap.get(key);
	finalSelect.add(list.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining(", ")))	;
	}
	String result= finalSelect.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining(", "));
	
	return String.format(QueryConstants.SELECTFROM, result,QueryConstants.SALES_FACT +" as "+QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getAlias())+"\n";
	
}	
	
public String buildInnerJoinPart(HashMap<String, List<String>> hashMap) {
	StringBuffer buffer = new StringBuffer();
	for(String key :hashMap.keySet()){
		for(String s : hashMap.get(key)){
			String[] arr = s.split(";");
	buffer.append(buildJoin(new Join2Tupel(QueryConstants.getJOINMAP().get(arr[0].trim()),QueryConstants.getJOINMAP().get(arr[1].trim()))));}}
	return buffer.toString();
	
}	


private String buildJoin(Join2Tupel tupel) {
return String.format(QueryConstants.INNERJOIN, tupel.getTwo().getTableName()+" as "+tupel.getTwo().getAlias(),tupel.getOne().getAlias()+"."+ tupel.getOne().getJoinAttribute(),tupel.getTwo().getAlias()+"."+tupel.getTwo().getJoinAttribute())+"\n";
	
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
