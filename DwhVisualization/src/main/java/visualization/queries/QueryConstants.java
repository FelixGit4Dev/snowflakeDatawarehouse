package visualization.queries;

import java.util.HashMap;

public class QueryConstants {

public static final String SELECTFROM="SELECT s% FROM %s";
public static final String INNERJOIN="INNER JOIN  s% ON s%=  s%";
public static final String GROUPBY="GROUP BY %s";
public  static final String ORDERBY="ORDER BY %s";


private static HashMap<String, QueryPartObject> JOINMAP;


public static HashMap<String, QueryPartObject> getJOINMAP() {
	if(JOINMAP==null){
QueryConstants.initializeJoinMap();		
	}
	return JOINMAP;
}


private static void initializeJoinMap() {
JOINMAP= new HashMap<String, QueryPartObject>();
JOINMAP.put("AgeGroup", new QueryPartObject("AgeGroup","ag", ""));
JOINMAP.put("Customer", new QueryPartObject("Customer","cu", ""));
JOINMAP.put("Gender", new QueryPartObject("Gender","ge", ""));
JOINMAP.put("HomeOwner", new QueryPartObject("HomeOwner","ho", ""));
JOINMAP.put("Individual", new QueryPartObject("Individual","ind", ""));
JOINMAP.put("Store", new QueryPartObject("Store","sto", ""));
JOINMAP.put("YearlyIncomeGroup", new QueryPartObject("YearlyIncomeGroup","yig", ""));
JOINMAP.put("City", new QueryPartObject("City","ci", ""));
JOINMAP.put("Country", new QueryPartObject("Country","ca", ""));
JOINMAP.put("State", new QueryPartObject("State","sta", ""));
JOINMAP.put("Territory", new QueryPartObject("Territory","te", ""));
JOINMAP.put("Category", new QueryPartObject("Category","cat", ""));
JOINMAP.put("Product", new QueryPartObject("Product","pro", ""));
JOINMAP.put("Subcategory", new QueryPartObject("Subcategory","spro", ""));
JOINMAP.put("SalesChannel", new QueryPartObject("SalesChannel","sc", ""));
JOINMAP.put("SalesPerson", new QueryPartObject("SalesPerson","sp", ""));
JOINMAP.put("SalesReason", new QueryPartObject("SalesReason","sr", ""));
JOINMAP.put("SalesReasonType", new QueryPartObject("SalesReasonType","srt", ""));
JOINMAP.put("ShippingMethod", new QueryPartObject("ShippingMethod","sm", ""));
JOINMAP.put("Day", new QueryPartObject("Day","da", ""));
JOINMAP.put("Month", new QueryPartObject("Month","mo", ""));
JOINMAP.put("Week", new QueryPartObject("Week","we", ""));
JOINMAP.put("Year", new QueryPartObject("Year","ye", ""));
JOINMAP.put("SalesFact", new QueryPartObject("SalesFact","sf", ""));
	
}







}
