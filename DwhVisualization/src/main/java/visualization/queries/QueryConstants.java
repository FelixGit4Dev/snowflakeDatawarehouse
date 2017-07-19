package visualization.queries;

import java.util.HashMap;

public class QueryConstants {

	public static final String SALES_FACT = "sales_fact";
public static final String YEAR = "year";
public static final String WEEK = "week";
public static final String MONTH = "month";
public static final String DAY = "day";
public static final String SHIPPING_METHOD = "shippingmethod";
public static final String SALES_REASON_TYPE = "salesreasontype";
public static final String SALES_REASON = "salesreason";
public static final String SALES_PERSON = "Salesperson";
public static final String SALES_CHANNEL = "saleschannel";
public static final String SUBCATEGORY = "subcategory";
public static final String PRODUCT = "product";
public static final String CATEGORY = "category";
public static final String TERRITORY = "territory";
public static final String STATE = "state";
public static final String COUNTRY = "country";
public static final String CITY = "city";
public static final String YEARLY_INCOME_GROUP = "yearly_income_group";
public static final String STORE = "store";
public static final String INDIVIDUAL = "individual";
public static final String HOME_OWNER = "homeowner";
public static final String GENDER = "gender";
public static final String CUSTOMER = "customer";
public static final String AGE_GROUP = "agegroup";
public static final String SELECTFROM="SELECT %s FROM %s";
public static final String INNERJOIN="INNER JOIN  %s ON %s=  %s";
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
JOINMAP.put(AGE_GROUP, new QueryPartObject(AGE_GROUP,"ag", "","",""));
JOINMAP.put(CUSTOMER, new QueryPartObject(CUSTOMER,"cu", "","",""));
JOINMAP.put(GENDER, new QueryPartObject(GENDER,"ge", "","",""));
JOINMAP.put(HOME_OWNER, new QueryPartObject(HOME_OWNER,"ho", "","",""));
JOINMAP.put(INDIVIDUAL, new QueryPartObject(INDIVIDUAL,"ind", "","",""));
JOINMAP.put(STORE, new QueryPartObject(STORE,"sto", "","",""));
JOINMAP.put(YEARLY_INCOME_GROUP, new QueryPartObject(YEARLY_INCOME_GROUP,"yig", "","",""));
JOINMAP.put(CITY, new QueryPartObject(CITY,"ci", "","",""));
JOINMAP.put(COUNTRY, new QueryPartObject(COUNTRY,"ca", "","",""));
JOINMAP.put(STATE, new QueryPartObject(STATE,"sta", "","",""));
JOINMAP.put(TERRITORY, new QueryPartObject(TERRITORY,"te", "","",""));
JOINMAP.put(CATEGORY, new QueryPartObject(CATEGORY,"cat", "","",""));
JOINMAP.put(PRODUCT, new QueryPartObject(PRODUCT,"pro", "","",""));
JOINMAP.put(SUBCATEGORY, new QueryPartObject(SUBCATEGORY,"spro", "","",""));
JOINMAP.put(SALES_CHANNEL, new QueryPartObject(SALES_CHANNEL,"sc", "","",""));
JOINMAP.put(SALES_PERSON, new QueryPartObject(SALES_PERSON,"sp", "","",""));
JOINMAP.put(SALES_REASON, new QueryPartObject(SALES_REASON,"sr", "","",""));
JOINMAP.put(SALES_REASON_TYPE, new QueryPartObject(SALES_REASON_TYPE,"srt", "","",""));
JOINMAP.put(SHIPPING_METHOD, new QueryPartObject(SHIPPING_METHOD,"sm", "","",""));
JOINMAP.put(DAY, new QueryPartObject(DAY,"da", "monthId","","dayId"));
JOINMAP.put(MONTH, new QueryPartObject(MONTH,"mo", "yearId","month","monthId"));
JOINMAP.put(WEEK, new QueryPartObject(WEEK,"we", "","",""));
JOINMAP.put(YEAR, new QueryPartObject(YEAR,"ye", "","year","yearId"));
JOINMAP.put(SALES_FACT, new QueryPartObject(SALES_FACT,"sf", "","",""));
	
}







}
