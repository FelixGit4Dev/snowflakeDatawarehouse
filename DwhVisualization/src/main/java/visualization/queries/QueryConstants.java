package visualization.queries;

import java.util.HashMap;

public class QueryConstants {

	public static final String SALES_FACT = "SalesFact";
public static final String YEAR = "Year";
public static final String WEEK = "Week";
public static final String MONTH = "Month";
public static final String DAY = "Day";
public static final String SHIPPING_METHOD = "ShippingMethod";
public static final String SALES_REASON_TYPE = "SalesReasonType";
public static final String SALES_REASON = "SalesReason";
public static final String SALES_PERSON = "SalesPerson";
public static final String SALES_CHANNEL = "SalesChannel";
public static final String SUBCATEGORY = "Subcategory";
public static final String PRODUCT = "Product";
public static final String CATEGORY = "Category";
public static final String TERRITORY = "Territory";
public static final String STATE = "State";
public static final String COUNTRY = "Country";
public static final String CITY = "City";
public static final String YEARLY_INCOME_GROUP = "YearlyIncomeGroup";
public static final String STORE = "Store";
public static final String INDIVIDUAL = "Individual";
public static final String HOME_OWNER = "HomeOwner";
public static final String GENDER = "Gender";
public static final String CUSTOMER = "Customer";
public static final String AGE_GROUP = "AgeGroup";
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
JOINMAP.put(AGE_GROUP, new QueryPartObject(AGE_GROUP,"ag", ""));
JOINMAP.put(CUSTOMER, new QueryPartObject(CUSTOMER,"cu", ""));
JOINMAP.put(GENDER, new QueryPartObject(GENDER,"ge", ""));
JOINMAP.put(HOME_OWNER, new QueryPartObject(HOME_OWNER,"ho", ""));
JOINMAP.put(INDIVIDUAL, new QueryPartObject(INDIVIDUAL,"ind", ""));
JOINMAP.put(STORE, new QueryPartObject(STORE,"sto", ""));
JOINMAP.put(YEARLY_INCOME_GROUP, new QueryPartObject(YEARLY_INCOME_GROUP,"yig", ""));
JOINMAP.put(CITY, new QueryPartObject(CITY,"ci", ""));
JOINMAP.put(COUNTRY, new QueryPartObject(COUNTRY,"ca", ""));
JOINMAP.put(STATE, new QueryPartObject(STATE,"sta", ""));
JOINMAP.put(TERRITORY, new QueryPartObject(TERRITORY,"te", ""));
JOINMAP.put(CATEGORY, new QueryPartObject(CATEGORY,"cat", ""));
JOINMAP.put(PRODUCT, new QueryPartObject(PRODUCT,"pro", ""));
JOINMAP.put(SUBCATEGORY, new QueryPartObject(SUBCATEGORY,"spro", ""));
JOINMAP.put(SALES_CHANNEL, new QueryPartObject(SALES_CHANNEL,"sc", ""));
JOINMAP.put(SALES_PERSON, new QueryPartObject(SALES_PERSON,"sp", ""));
JOINMAP.put(SALES_REASON, new QueryPartObject(SALES_REASON,"sr", ""));
JOINMAP.put(SALES_REASON_TYPE, new QueryPartObject(SALES_REASON_TYPE,"srt", ""));
JOINMAP.put(SHIPPING_METHOD, new QueryPartObject(SHIPPING_METHOD,"sm", ""));
JOINMAP.put(DAY, new QueryPartObject(DAY,"da", ""));
JOINMAP.put(MONTH, new QueryPartObject(MONTH,"mo", ""));
JOINMAP.put(WEEK, new QueryPartObject(WEEK,"we", ""));
JOINMAP.put(YEAR, new QueryPartObject(YEAR,"ye", ""));
JOINMAP.put(SALES_FACT, new QueryPartObject(SALES_FACT,"sf", ""));
	
}







}
