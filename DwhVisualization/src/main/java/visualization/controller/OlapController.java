package visualization.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.mysql.jdbc.log.Log;


import visualization.dao.DatwarehouseAccesDao;
import visualization.entity.DataTableObject;
import visualization.entity.DynamicDataTableObject;
import visualization.entity.QueryComponentsObject;
import visualization.entity.entityDwh.EtlMetaInformation;
import visualization.queries.QueryBuilder;
import visualization.queries.QueryConstants;

@SessionScoped
@Named
public class OlapController implements Serializable{

	/**
	 * 
	 */
	@Inject
	private transient Logger log;
	@Inject
	private QueryBuilder builder; 
	@Inject
	private DatwarehouseAccesDao dao; 
	@Inject
	private ColumnManagerView columnManagerView;
	
	@Inject
	private NativeQueryDatatableView nqdv;
	private static final long serialVersionUID = 1L;

	private String nativeQuery;
	private String errorMessage;
	
	private  String orderDateMask;
	private  String shipDateMask;
	
	
	private String quantityFunction="SUM";
	private String unitPriceFunction="SUM";
	private String discountFunction="SUM";
	private String totalFunction="SUM";
	private String standartCostFunction="SUM";
	private String totalCost="SUM";
	private String marginFunction="SUM";
	
	private String queryInput;
	
	
	
	//Dimension
	private String orderDateDimensionLevel="";
	private String shipDateDimensionLevel="";
	
	private String billToDimensionLevel="";
	private String orderToDimensionLevel="";
	
	private String productDimensionLevel="";
	private String productClassDimension="";
	private String productStyleDimension="";
	private String productLineDimension="";
	private String makedimension="";
	
	private String genderDimension="";
	private String ageGroupDimension="";
	private String incomeGroupDimension="";
	private String homeOwnerDimension="";
	
	private String salesReasonDimensionLevel="";
private String salesChannelDimension="";
private String shippingMethodDimension="";
	
	private String customerTypeDimension="";
	private String customerRegionDimension="";
	
	private String customerGenderDimension="";
	private String customerAgeGroupDimension="";
	private String customerYearlyIncomeGroupDimension="";
	private String customerHomeOwnerDimension="";
	
	
	
	// Filter
	private String orderDateFilter;
	private String shipDateFilter;
	private String shipToFilter;
	private String billToFilter;
	
	
	private String productFilter;
	private String salesReasonFilter;
	private String salesPersonFilter;
	private String customerRegionFilter;
	
	private boolean productLineFilter;
	private boolean productClassFilter;
	private boolean productStyleFilter;
	private boolean makeFilter;
	
	
	private boolean salesChannelFilter;
	private boolean shippingMethodFilter;
	
	private boolean genderFilter;
	private boolean ageGroupFilter;
	private boolean yearlyIncomeFilter;
	private boolean homeOwnerFilter;
	
	
	//group by

	
	
	private boolean SalesReason;
	private boolean SalesPerson;
	private boolean SalesChannel;
	private boolean ShippingMethod;
	
	
	private List<String> orderbyList= new ArrayList<String>();
	private List<String> groupbyList =new ArrayList<String>();
	
	
	public List<EtlMetaInformation> getEtlMetaInformation(){
		return this.dao.getAllEtlMetaInformation();	
		}
	
	
	public void executeNativeQuery(){
	ArrayList<String> names = nqdv.extractColumnNames(nativeQuery);	
	ArrayList<DynamicDataTableObject> tablesObjects= new ArrayList<>();
	if(names.size()>1){
	tablesObjects =this.createNativeQueryObjects(names,this.dao.executeNativeQueryMulticolumns(nativeQuery),null);	
	}
	else{
	tablesObjects=  this.createNativeQueryObjects(names,null,this.dao.executeNativeQuerySingleColumn(nativeQuery));	
	}
	nqdv.createColumns(names);
	this.nqdv.setCars(tablesObjects);
	}
	
	
	
	public ArrayList<DynamicDataTableObject> createNativeQueryObjects(ArrayList<String> columnNames, List<Object[]> multi, List<Object> single){
	ArrayList<DynamicDataTableObject> objects = new ArrayList<>();
		if(multi==null){
		for( int i=0; i<columnNames.size();i++){
			LinkedHashMap<String, Object> map= new LinkedHashMap<>();
			map.put(columnNames.get(i), ((ArrayList<Object>)single).get(i) );
			DynamicDataTableObject dto= new DynamicDataTableObject();
			dto.setMap(map);
			objects.add(dto); 	
		
		}
	}
	else{
		for(Object[] arr : multi){
			DynamicDataTableObject dto= new DynamicDataTableObject();
			LinkedHashMap<String, Object> map= new LinkedHashMap<>();
		for( int i=0; i<columnNames.size();i++){
map.put(columnNames.get(i), arr[i] );				
	}
		dto.setMap(map);
		objects.add(dto); 	
		}
		}
		return objects; 
	}



	public QueryComponentsObject evaluateDimensions(){
		
		HashMap<String, HashMap<String,String>> result= new HashMap<>();
	ArrayList<String> innerJoin= new ArrayList<String>();
	ArrayList<String> where= new ArrayList<String>();
	ArrayList<String> groupBy= new ArrayList<String>();
	LinkedHashMap<String, String> select= new LinkedHashMap<String,String>();
	this.buildStaticSelectPart(select);
	evauluateTimeDimension(innerJoin,where,select,groupBy);
	evauluatePlaceDimension(innerJoin,where,select,groupBy);
	evauluateCustomerDimension(innerJoin,where,select,groupBy);
	evauluateSalesDimension(innerJoin,where,select,groupBy);
	evauluateProductDimension(innerJoin,where,select,groupBy);
	


	return new QueryComponentsObject(innerJoin,select,where,groupBy);
	}
	
	
	private void evauluateProductDimension(ArrayList<String> innerjoin, ArrayList<String> where,
			HashMap<String, String> select,ArrayList<String> groupBy) {
	HashMap<String, String> lineMap= new HashMap<String,String>();
	lineMap.put("", "");
	lineMap.put("", "");
	lineMap.put("", "");
	lineMap.put("", "");
	HashMap<String, String> classMap= new HashMap<String,String>();
	HashMap<String, String> styleMap= new HashMap<String,String>();
	HashMap<String, String> makeMap= new HashMap<String,String>();
		
		boolean productjoin= false;
		if(productDimensionLevel!=null && !"".equals(productDimensionLevel)){
				switch (productDimensionLevel) {
				case "PRODUCT": 
					productjoin=true;
					innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
				select.put("productDimension","prod.name");
				if(this.groupbyList!=null && groupbyList.contains("Product")){
				groupBy.add("prod.productId");}
				if(productFilter!=null && "".equals(productFilter)){
				where.add("prod.name='"+productFilter+"'");	
				}
					break;
				case"SUBCATEGORY": 
					productjoin=true;
					innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
					innerjoin.add("inner join subcategory as subcat on subcat.subcategoryId=prod.subcategoryId \n");
					if(this.groupbyList!=null && groupbyList.contains("Product")){groupBy.add("subcat.subcategoryId");}
					select.put("productDimension","subcat.name");
					if(productFilter!=null && "".equals(productFilter)){
						where.add("subcat.name='"+productFilter+"'");	
						}
					break;

				case "CATEGORY":
					productjoin=true;
					innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
					innerjoin.add("inner join subcategory as subcat on subcat.subcategoryId=prod.subcategoryId \n");
					innerjoin.add(" inner join category as cat on cat.categoryId=subcat.categoryId \n");
					if(this.groupbyList!=null && groupbyList.contains("Product")){groupBy.add("cat.categoryId");}
					select.put("productDimension","cat.CATEGORY_NAME");
					if(productFilter!=null && "".equals(productFilter)){
						where.add("cat.CATEGORY_NAME='"+productFilter+"'");	
						}
					break; 	
				default:
					break;
				}	
		}
		if(this.productLineDimension!=null && !"".equals(productLineDimension)){
			if(!productjoin){
				innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
				productjoin=true;
			}
			innerjoin.add("inner join productline as pl on prod.productLine=pl.productLineCode \n");
			select.put("productLine","pl.productLineName");
			if(this.groupbyList!=null && groupbyList.contains("ProductLine")){
			groupBy.add("prod.productLine");	
			}
			if(this.productLineFilter)	{
			where.add("pl.productLineCode='"+productLineDimension+"'");	
			}
			
		}
if(this.productClassDimension!=null && !"".equals(productClassDimension)){
	if(!productjoin){
		innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
		productjoin=true;
	}
	innerjoin.add("inner join productclasse as pc on prod.klasse=pl.productClassCode \n");
	select.put("productClass","pc.className");
	if(this.groupbyList!=null && groupbyList.contains("ProductClass")){
		groupBy.add("prod.klasse");	
		}
	if(this.productClassFilter)	{
		where.add("pl.prodcutClassCode='"+productClassDimension+"'");	
		}
		}
if(this.productStyleDimension!=null && !"".equals(productStyleDimension)){
	if(!productjoin){
		innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
		productjoin=true;
	}
	innerjoin.add("inner join productstyle as prodst on prod.style=prodst.styleCode \n");
	select.put("productStyle","prodst.StyleName");
	if(this.groupbyList!=null && groupbyList.contains("ProductStyle")){
		groupBy.add("prod.style");	
		}
	if(this.productStyleFilter)	{
		where.add("pl.prodcutClassCode='"+productStyleDimension+"'");	
		}

}
if(this.makedimension!=null && !"".equals(makedimension)){
	if(!productjoin){
		innerjoin.add("inner join product as prod on prod.productId=sf.productId \n");
		productjoin=true;
	}
	innerjoin.add("inner join make_flag as mf on prod.makeFlag=mf.makeTypeCode \n");
	select.put("makeFlag","mf.makeType");
	if(this.groupbyList!=null && groupbyList.contains("Make")){
		groupBy.add("prod.makeFlag");	
		}
	if(this.makeFilter)	{
		where.add("pl.prodcutClassCode='"+makedimension+"'");	
		}

}
		
		
		
		// TODO Auto-generated method stub
	
		
		
		
	}



	
	public HashMap<String, HashMap<String,List<String>>> evaluateOrderBy(){
		return null;
		
	}
	
	private void evauluateSalesDimension(ArrayList<String> innerJoin, ArrayList<String> where, HashMap<String, String> select,ArrayList<String> groupBy) {
	if(salesReasonDimensionLevel!=null && !"".equals(salesReasonDimensionLevel)){	
switch (salesReasonDimensionLevel) {
case "SALESREASON":
	innerJoin.add("inner join salesreason as salesr on sf.salesReason=salesr.salesReasonId \n");
	select.put("salesReason","salesr.name");
	if(this.groupbyList!=null && groupbyList.contains("SalesReason")){
		groupBy.add("salesr.salesReasonId");	
		}
	if(salesReasonFilter!=null && !"".equals(salesReasonFilter)){
	where.add("salesr.name='"+salesReasonFilter+"'");	
	}
	break;
case "REASONTYPE":
	innerJoin.add("inner join salesreason as salesr on sf.salesReason=salesr.salesReasonId \n");
	innerJoin.add("inner join salesreasontype as salesrt on saler.salesReasonTypeId=salesrt.salesReasonTypeId \n");
	select.put("salesReason","salesrt.typeName");
	if(this.groupbyList!=null && groupbyList.contains("SalesReason")){
		groupBy.add("salesrt.salesReasonTypeId");	
		}
	break;

default:
	break;
}}		
if(salesChannelDimension!=null && !"".equals(salesChannelDimension)){
	innerJoin.add("inner join saleschannel as sc on saleschannel.salesChannelId=sf.salesChannel \n");
	select.put("","sc.channel");
	if(salesChannelFilter){
	where.add("sc.channel="+salesChannelDimension);	
	}
	if(this.groupbyList!=null && groupbyList.contains("SalesChannel")){
		groupBy.add("sf.salesChannel");	
		}
}
if(shippingMethodDimension!=null && !"".equals(shippingMethodDimension)){
	innerJoin.add("inner join shippingMethod as shipMeth on sf.shippingMethodId=shipMeth.shippingMethodId \n");
	select.put("","shipMeth.METHOD_NAME");
if(shippingMethodFilter){
	where.add("shipMeth.METHOD_NAME="+shippingMethodDimension);	
	}
if(this.groupbyList!=null && groupbyList.contains("ShippingMethod")){
	groupBy.add("sf.shippingMethodId");	
	}
}
		
		
		// TODO Auto-generated method stub
		

		
		
	}


	private void evauluateCustomerDimension(ArrayList<String> innerJoin, ArrayList<String> where, HashMap<String, String> select, ArrayList<String> groupBy) {
		
boolean individual =false;
boolean customer =false;
		// TODO Auto-generated method stub
		
if(customerTypeDimension!=null && !"".equals(customerTypeDimension)){
		switch (customerTypeDimension) {
		case "ALL":
			customer=true;
			innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");
			break;
case "STORE":
	customer=true;
	innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");	
	innerJoin.add("inner join store on store.storeId=customer.customerId   \n");
	select.put("","store.name");		
			break;
case "INDIVIDUAL":
	individual=true;
	customer=true;
	innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");
	innerJoin.add(" inner join individual on customer.customerId=individual.individualId \n");
	
	
	break;

		default:
			break;
		}
	if(this.genderDimension!=null && !"".equals(genderDimension)){
	if(!customer){
		innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");	
		customer=true;
	}	
	if(!individual){
	innerJoin.add(" inner join individual on customer.customerId=individual.individualId \n");	
	individual=true;
	}
		
	}
if(this.ageGroupDimension!=null && !"".equals(ageGroupDimension)){
	if(!customer){
		innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");	
		customer=true;
	}	
	if(!individual){
	innerJoin.add(" inner join individual on customer.customerId=individual.individualId \n");	
	individual=true;
	}
	
	innerJoin.add("inner join agegroup on agegroup.ageGroupId=individual.ageGroupId");		
	
	}
if(this.incomeGroupDimension!=null && !"".equals(incomeGroupDimension)){
	if(!customer){
		innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");	
		customer=true;
	}	
	if(!individual){
	innerJoin.add(" inner join individual on customer.customerId=individual.individualId \n");	
	individual=true;
	}
	innerJoin.add("");	
	
}
if(this.homeOwnerDimension!=null && !"".equals(homeOwnerDimension)){
	if(!customer){
		innerJoin.add("inner join customer on customer.customerId=sf.customerId \n");	
		customer=true;
	}	
	if(!individual){
	innerJoin.add(" inner join individual on customer.customerId=individual.individualId \n");	
	individual=true;
	}
			
}

		
}

		
		
	}


	private void evauluatePlaceDimension(ArrayList<String> innerJoin, ArrayList<String> where, HashMap<String, String> select, ArrayList<String> groupBy) {
		

		
		if(billToDimensionLevel!=null && !"".equals(this.billToDimensionLevel)){
			switch (billToDimensionLevel) {
			case "CITY": 
				innerJoin.add(" inner join city as billToCity  on billToCity.CITY_ID=sf.billTo \n");
				select.put("billTo"," billToCity.NAME");
				if(groupbyList!=null && groupbyList.contains("BillTo")){
					groupBy.add("billToCity.CITY_ID");
				}
			if(billToFilter!=null && !"".equals(billToFilter) ){
				where.add("billToCity.NAME='"+billToFilter+"'");}
				break;
			case"STATE/PROVINCE": 
				innerJoin.add(" inner join city as billToCity  on billToCity.CITY_ID=sf.billTo \n");
				innerJoin.add( " inner join state as billToState on billToCity.stateId=billToState.STATE_ID \n");
				if(groupbyList!=null && groupbyList.contains("BillTo")){
					groupBy.add("billToState.STATE_ID");
				}
				select.put("billTo","billToState.NAME");
				if(billToFilter!=null && !"".equals(billToFilter) ){
					where.add("billToState.NAME='"+billToFilter+"'");}
				break;

			case "COUNTRY":
				innerJoin.add(" inner join city as billToCity  on billToCity.CITY_ID=sf.billTo \n");
				innerJoin.add( " inner join state as billToState on billToCity.stateId=billToState.STATE_ID \n");
				innerJoin.add( " inner join country as billToCountry on billToCountry.COUNTRY_ID=billToState.countryCode \n");
				if(groupbyList!=null && groupbyList.contains("BillTo")){
					groupBy.add("billToCountry.COUNTRY_ID");
				}
				select.put("billTo","billToCountry.NAME");
				if(billToFilter!=null && !"".equals(billToFilter) ){
					where.add("billToCountry.NAME='"+billToFilter+"'");}
				break; 

			case "TERRITORY":
				innerJoin.add(" inner join city as billToCity  on billToCity.CITY_ID=sf.billTo \n");
				innerJoin.add( " inner join state as billToState on billToCity.stateId=billToState.STATE_ID \n");
				innerJoin.add(" inner join territory as billToTerritory on billToTerritory.territoryId=billToState.territoryId \n");
				if(groupbyList!=null && groupbyList.contains("BillTo")){
					groupBy.add("billToTerritory.territoryId");
				}
				select.put("billTo","billToTerritory.TERRITORY_NAME");
				if(billToFilter!=null && !"".equals(billToFilter) ){
					where.add("billToTerritory.TERRITORY_NAME='"+billToFilter+"'");}
				break; 
			default:
				break;
			}
			
		}
		if(orderToDimensionLevel!=null && !"".equals(this.orderToDimensionLevel)){
			switch (orderToDimensionLevel) {
			case "CITY": 
				innerJoin.add(" inner join city as shipToCity  on shipToCity.CITY_ID=sf.shipTo \n");
				select.put("shipTo"," shipToCity.NAME");
				if(groupbyList!=null && groupbyList.contains("ShipTo")){
					groupBy.add("shipToCity.CITY_ID");
				}
			if(shipToFilter!=null && !"".equals(shipToFilter) ){
				where.add("shipToCity.NAME='"+shipToFilter+"'");}
				break;
			case"STATE/PROVINCE": 
				innerJoin.add(" inner join city as shipToCity  on shipToCity.CITY_ID=sf.shipTo \n");
				innerJoin.add( " inner join state as shipToState on shipToCity.stateId=shipToState.STATE_ID \n");
				if(groupbyList!=null && groupbyList.contains("ShipTo")){
					groupBy.add("shipToState.STATE_ID");
				}
				select.put("shipTo","shipToState.NAME");
				if(shipToFilter!=null && !"".equals(shipToFilter) ){
					where.add("shipToState.NAME='"+shipToFilter+"'");}
				break;

			case "COUNTRY":
				innerJoin.add(" inner join city as shipToCity  on shipToCity.CITY_ID=sf.shipTo \n");
				innerJoin.add( " inner join state as shipToState on shipToCity.stateId=shipToState.STATE_ID \n");
				innerJoin.add( " inner join country as shipToCountry on shipToCountry.COUNTRY_ID=shipToState.countryCode \n");
				if(groupbyList!=null && groupbyList.contains("ShipTo")){
					groupBy.add("shipToCountry.COUNTRY_ID");
				}
				select.put("shipTo","shipToCountry.NAME");
				if(shipToFilter!=null && !"".equals(shipToFilter) ){
					where.add("shipToCountry.NAME='"+shipToFilter+"'");}
				
				break; 

			case "TERRITORY":
				innerJoin.add(" inner join city as shipToCity  on shipToCity.CITY_ID=sf.shipTo \n" );
				innerJoin.add( " inner join state as shipToState on shipToCity.stateId=shipToState.STATE_ID \n");
				innerJoin.add(" inner join territory as shipToTerritory on shipToTerritory.territoryId=shipToState.territoryId \n");
				if(groupbyList!=null && groupbyList.contains("ShipTo")){
					groupBy.add("shipToTerritory.territoryId");
				}
				select.put("shipTo","shipToTerritory.TERRITORY_NAME");
				if(shipToFilter!=null && !"".equals(shipToFilter) ){
					where.add("shipToTerritory.NAME='"+shipToFilter+"'");}
				
				break; 
			default:
				break;
			}
			
		}
		
		
		
		
	}


	
public LinkedHashMap<String, String> buildStaticSelectPart(LinkedHashMap<String, String> map){	
map.put("discount",this.discountFunction+"("+"sf.discount"+") ");
map.put("margin",this.marginFunction+"("+"sf.margin"+") ");
map.put("quantity",this.quantityFunction+"("+"sf.quantity"+") ");
map.put("productStandartCost",this.standartCostFunction+"("+"sf.productStandartCost"+") ");
map.put("total",this.totalFunction+"("+"sf.total"+") ");
map.put("unitPrice",this.unitPriceFunction+"("+"sf.unitPrice"+")");
map.put("totalCost",this.totalCost+"("+"sf.totalCost"+")");
return map;
}

	
	private void evauluateTimeDimension(ArrayList<String> innerJoin, ArrayList<String> where, HashMap<String, String> select, ArrayList<String> groupBy) {

		if(shipDateDimensionLevel!=null && !"".equals(shipDateDimensionLevel)){
			switch (shipDateDimensionLevel) {
			case "DAY": 
				innerJoin.add("inner join day as shipdate on sf.shipdateId=shipdate.dayId \n");
				select.put("shipDate","shipdate.day");
				if(groupbyList!=null && groupbyList.contains("ShipDate")){
				groupBy.add("shipdate.dayId");}
			if(shipDateFilter!=null && !"".equals(shipDateFilter) ){
				where.add("shipdate.day='"+shipDateFilter+"'");}
				break;
			case"MONTH": 
				innerJoin.add("inner join day as shipdate on sf.shipdateId=shipdate.dayId \n");
				innerJoin.add("inner join month as shipmonth on shipdate.monthId=shipmonth.monthId \n");
				if(groupbyList!=null &&groupbyList.contains("ShipDate")){
				groupBy.add("shipmonth.monthId");}
				select.put("shipDate","shipmonth.month");
				if(shipDateFilter!=null && !"".equals(shipDateFilter) ){
					where.add("shipmonth.month='"+shipDateFilter+"'");}
				break;

			case "YEAR":
				innerJoin.add("inner join day as shipdate on sf.shipdateId=shipdate.dayId \n");
				innerJoin.add("inner join month as shipmonth on shipdate.monthId=shipmonth.monthId \n");			
				innerJoin.add("inner join year as shipyear on shipyear.yearId=shipmonth.yearId \n");
				if(groupbyList!=null &&groupbyList.contains("ShipDate")){groupBy.add("shipyear.yearId");}
				select.put("shipDate","shipyear.year");
				if(shipDateFilter!=null && !"".equals(shipDateFilter) ){
				where.add("shipyear.year='"+shipDateFilter+"'");}
				break; 
			default:
				break;
			}
			
			if(shipDateFilter!=null && !"".equals(shipDateFilter)){
			//wheres.add(e)	
				
			}
		}
if(orderDateDimensionLevel !=null && !"".equals(orderDateDimensionLevel)){
	switch (orderDateDimensionLevel) {
	case "DAY": 
		innerJoin.add("inner join day as orderdate on sf.orderDateId=orderdate.dayId \n");
		select.put("orderDate","orderdate.day");
		if(groupbyList!=null &&groupbyList.contains("OrderDate")){groupBy.add("orderdate.dayId");}
	if(orderDateFilter!=null && !"".equals(orderDateFilter) ){
		where.add("orderdate.day='"+orderDateFilter+"'");}
		break;
	case"MONTH": 
		innerJoin.add("inner join day as orderdate on sf.orderDateId=orderdate.dayId \n");
		innerJoin.add("inner join month as ordermonth on orderdate.monthId=ordermonth.monthId \n");
		if(groupbyList!=null &&groupbyList.contains("OrderDate")){groupBy.add("ordermonth.monthId");}
		select.put("orderDate","ordermonth.month");
		if(orderDateFilter!=null && !"".equals(orderDateFilter) ){
			where.add("ordermonth.month='"+orderDateFilter+"'");}
		break;

	case "YEAR":
		innerJoin.add("inner join day as orderdate on sf.orderDateId=orderdate.dayId \n");
		innerJoin.add("inner join month as ordermonth on orderdate.monthId=ordermonth.monthId \n");			
		innerJoin.add("inner join year as orderyear on orderyear.yearId=ordermonth.yearId \n");
		if(groupbyList!=null &&groupbyList.contains("OrderDate")){groupBy.add("orderyear.yearId");}
		select.put("orderDate","orderyear.year");
		if(orderDateFilter!=null && !"".equals(orderDateFilter) ){
			where.add("orderyear.year='"+orderDateFilter+"'");}
		break; 
	default:
		break;
		
	}
		}



		
	}


	public void showSchemaDialog(){
		  Map<String,Object> options = new HashMap<String, Object>();
	      options.put("resizable", true);
	      options.put("width", 640);
	      options.put("height", 340);
	      options.put("contentWidth", "100%");
	      options.put("contentHeight", "100%");
	      RequestContext.getCurrentInstance().openDialog("schemaDialog", options, null);
	}
	
	
	
	
	
	
	
	public List<String> completeTextBillTo(String query){
		final String key = billToDimensionLevel;
		List<String> result;
		switch (key) {
		case "CITY": result=this.dao.getAllCities(query);
			
			break;
case "STATE/PROVINCE": result=this.dao.getAllStates(query);
			
			break;
case "COUNTRY": result=this.dao.getAllCountries(query);
	
	break;
case "TERRITORY":result=this.dao.getAllSalesTerritories(query);
	
	break;

		default: result= new ArrayList<String>();
		
		}
		return result;

		
	}
	
	public List<String> completeTextShipTo(String query){
			final String key = orderToDimensionLevel;
			List<String> result;
			switch (key) {
			case "CITY": result=this.dao.getAllCities(query);
				
				break;
	case "STATE/PROVINCE": result=this.dao.getAllStates(query);
				
				break;
	case "COUNTRY": result=this.dao.getAllCountries(query);
		
		break;
	case "TERRITORY":result=this.dao.getAllSalesTerritories(query);
		
		break;

			default: result= new ArrayList<String>();
			
			}
			return result;

			
		}
	
	public List<String> completeTextProduct(String query){
		
		final String key = productDimensionLevel;
		List<String> result= new ArrayList<>();
		if(key!=null){
		switch (key) {
		case "PRODUCT": result=this.dao.getAllProducts(query);
			
			break;
case "SUBCATEGORY": result=this.dao.getAllSubcategories(query);
			
			break;
case "CATEGORY": result=this.dao.getAllCategories(query);
	
	break;


		default: result= new ArrayList<String>();
		
		}}
		return result;

		
	}
	public List<String> completeTextSalesReason(String query){
		final String key = salesReasonDimensionLevel;
		List<String> result;
		switch (key) {
	
case "SALESREASON": result=this.dao.getAllSalesReasons(query);
			
			break;
case "REASONTYPE": result=this.dao.getAllSalesReasonTypes(query);
	
	break;


		default: result= new ArrayList<String>();
		
		}
		return result;

		
	}
	
	public List<String> completeTextCustomerRegion(String query){
		final String key = customerRegionDimension;
		List<String> result;
		switch (key) {
		case "CITY": result=this.dao.getAllCities(query);
			
			break;
case "STATE": result=this.dao.getAllStates(query);
			
			break;
case "COUNTRY": result=this.dao.getAllCountries(query);
	
	break;
case "TERRITORY":result=this.dao.getAllSalesTerritories(query);
	
	break;

		default: result= new ArrayList<String>();
		
		}
		return result;
		
		
	}
	public List<String> completeTextOrderBy(String query){
		List<String> list= new ArrayList<String>();
		if(!"".equals(orderDateDimensionLevel) && "OrderDate".toUpperCase().startsWith(query.toUpperCase())){
		list.add("OrderDate");	
		}
		if(!"".equals(shipDateDimensionLevel)&& "ShipDate".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ShipDate");	
			}
		if(!"".equals(billToDimensionLevel) && "BillTo".toUpperCase().startsWith(query.toUpperCase())){
			list.add("BillTo");	
			}
		if(!"".equals(orderToDimensionLevel)&& "ShipTo".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ShipTo");	
			}
		if(!"".equals( productDimensionLevel)&& "Product".toUpperCase().startsWith(query.toUpperCase())){
			list.add( "Product");	
			}
		if(!"".equals( productClassDimension)&& "ProductClass".toUpperCase().startsWith(query.toUpperCase())){
			list.add( "ProductClass");	
			}
		if(!"".equals( productStyleDimension)&& "ProductStyle".toUpperCase().startsWith(query.toUpperCase())){
			list.add( "ProductStyle");	
			}
		if(!"".equals(productLineDimension)&& "ProductLine".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ProductLine");	
			}
		if(!"".equals(makedimension)&& "Make".toUpperCase().startsWith(query)){
			list.add("Make");	
			}
		if(!"".equals(salesReasonDimensionLevel)&& "SalesReason".toUpperCase().startsWith(query.toUpperCase())){
			list.add("SalesReason");	
			}			
			
		if(!"".equals(customerTypeDimension)&& "CustomerType".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerType");	
			}
		if(!"".equals(customerRegionDimension)&& "CustomerRegion".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerRegion");	
			}
		if(!"".equals(customerGenderDimension)&& "CustomerGender".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerGender");	
			}
		if(!"".equals(customerAgeGroupDimension)&& "CustomerAgeGroup".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerAgeGroup");	
			}
		if(!"".equals(customerYearlyIncomeGroupDimension)&& "CustomerYearlyIncomeGroup".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerYearlyIncomeGroup");	
			}
		if(!"".equals(customerHomeOwnerDimension)&& "CustomerHomeOwner".toUpperCase().startsWith(query.toUpperCase())){
				list.add("CustomerHomeOwner");	
				}
		return list;
	}
	
	public List<String> completeTextGroupBy(String query){
		List<String> list= new ArrayList<String>();
		if(orderDateDimensionLevel!=null && !"".equals(orderDateDimensionLevel) && "OrderDate".toUpperCase().startsWith(query.toUpperCase())){
		list.add("OrderDate");	
		}
		if(shipDateDimensionLevel!=null && !"".equals(shipDateDimensionLevel)&& "ShipDate".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ShipDate");	
			}
		if(billToDimensionLevel!=null && !"".equals(billToDimensionLevel) && "BillTo".toUpperCase().startsWith(query.toUpperCase())){
			list.add("BillTo");	
			}
		if(orderToDimensionLevel!=null && !"".equals(orderToDimensionLevel)&& "ShipTo".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ShipTo");	
			}
		if(productDimensionLevel!=null && !"".equals( productDimensionLevel)&& "Product".toUpperCase().startsWith(query.toUpperCase())){
			list.add( "Product");	
			}
		if(productClassDimension!=null && !"".equals( productClassDimension)&& "ProductClass".toUpperCase().startsWith(query.toUpperCase())){
			list.add( "ProductClass");	
			}
		if(productStyleDimension!=null && !"".equals( productStyleDimension)&& "ProductStyle".toUpperCase().startsWith(query.toUpperCase())){
			list.add( "ProductStyle");	
			}
		if(productLineDimension!=null && !"".equals(productLineDimension)&& "ProductLine".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ProductLine");	
			}
		if(makedimension!=null && !"".equals(makedimension)&& "Make".toUpperCase().startsWith(query)){
			list.add("Make");	
			}
		if(salesReasonDimensionLevel!=null && !"".equals(salesReasonDimensionLevel)&& "SalesReason".toUpperCase().startsWith(query.toUpperCase())){
			list.add("SalesReason");	
			}
		if(salesChannelDimension!=null && !"".equals(salesChannelDimension) && "SalesChannel".toUpperCase().startsWith(query.toUpperCase())){
		list.add("SalesChannel");	
		}	
		if(shippingMethodDimension!=null && !"".equals(shippingMethodDimension) && "ShippingMethod".toUpperCase().startsWith(query.toUpperCase())){
			list.add("ShippingMethod");	
			}
		
		if(customerTypeDimension!=null && !"".equals(customerTypeDimension)&& "CustomerType".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerType");	
			}
		if(customerRegionDimension!=null && !"".equals(customerRegionDimension)&& "CustomerRegion".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerRegion");	
			}
		if(customerGenderDimension!=null && !"".equals(customerGenderDimension)&& "CustomerGender".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerGender");	
			}
		if(customerAgeGroupDimension!=null && !"".equals(customerAgeGroupDimension)&& "CustomerAgeGroup".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerAgeGroup");	
			}
		if(customerYearlyIncomeGroupDimension!=null && !"".equals(customerYearlyIncomeGroupDimension)&& "CustomerYearlyIncomeGroup".toUpperCase().startsWith(query.toUpperCase())){
			list.add("CustomerYearlyIncomeGroup");	
			}
		if(customerHomeOwnerDimension!=null && !"".equals(customerHomeOwnerDimension)&& "CustomerHomeOwner".toUpperCase().startsWith(query.toUpperCase())){
				list.add("CustomerHomeOwner");	
				}
		return list;
	}
	
	public List<String> completeTextSalesPerson(String query){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<10;i++){
		list.add(query+i);	
		}
	return list;
	}
	
	
	public void buildAndExecuteQuery(){
		QueryComponentsObject obj =evaluateDimensions();	
	String olapQuery = this.builder.assembleOlapQuery(obj);
log.info(olapQuery);
//	if(obj.getSelect.keySet().size()==1){
//dao.executeOlapQuerySingleColumn(olapQuery);		
//	}else{
List<DataTableObject> tableObject = this.createDatatableObject(obj.getSelect(),dao.executeOlapQueryMulticolumns(olapQuery));		
	this.columnManagerView.setTableObjects(tableObject);
	//}
		
	}
	
	
	private List<DataTableObject> createDatatableObject(LinkedHashMap<String, String> columnOrder, List<Object[]> executeOlapQueryMulticolumns) {
	ArrayList<DataTableObject> list = new ArrayList<>();
	Object[] colarr = columnOrder.keySet().toArray();
	for(Object[] oarr :executeOlapQueryMulticolumns){
	DataTableObject dtobject = new DataTableObject();
		for(int i=0;i<colarr.length;i++){
		dtobject.getMap().put((String)colarr[i], oarr[i]);	
		}
		list.add(dtobject);
	}

		return list;
	}


	public void resetPlaceDimension(){
		billToDimensionLevel="";
		 orderToDimensionLevel="";	
		 shipToFilter="";
		 billToFilter="";
		 this.groupbyList.clear();
	}
	
	public void resetTimeDimension(){
		this.orderDateDimensionLevel="";
		this.shipDateDimensionLevel="";
		this.orderDateFilter="";
		this.shipDateFilter="";
		this.groupbyList.clear();
	}
	
	
	
	public void resetProductDimension(){
	this.productDimensionLevel="";
	this.productFilter="";
	this.groupbyList.clear();
	this.productClassDimension="";
	this.productClassFilter=false;
	this.productLineDimension="";
	this.productLineFilter=false;
	this.productStyleDimension="";
	this.productStyleFilter=false;
	}
	
	public void resetSalesDimension(){
	salesReasonDimensionLevel="";
	salesReasonFilter="";
	salesPersonFilter="";
	salesChannelDimension="";
	shippingMethodDimension="";
	shippingMethodFilter=false;
	salesChannelFilter=false;
	}
	
	public void resetCustomerDimension(){
	customerTypeDimension="";
	customerGenderDimension="";
	genderFilter=false;
	ageGroupDimension="";
	ageGroupFilter=false;
	yearlyIncomeFilter=false;
	customerYearlyIncomeGroupDimension="";
	homeOwnerFilter=false;
	homeOwnerDimension="";
	
	}
	
	
	public String getQuantityFunction() {
		return quantityFunction;
	}
	public void setQuantityFunction(String quantityFunction) {
		this.quantityFunction = quantityFunction;
	}
	public String getUnitPriceFunction() {
		return unitPriceFunction;
	}
	public void setUnitPriceFunction(String unitPriceFunction) {
		this.unitPriceFunction = unitPriceFunction;
	}

	public String getDiscountFunction() {
		return discountFunction;
	}
	public void setDiscountFunction(String discountFunction) {
		this.discountFunction = discountFunction;
	}
	public String getTotalFunction() {
		return totalFunction;
	}
	public void setTotalFunction(String totalFunction) {
		this.totalFunction = totalFunction;
	}
	public String getStandartCostFunction() {
		return standartCostFunction;
	}
	public void setStandartCostFunction(String standartCostFunction) {
		this.standartCostFunction = standartCostFunction;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getMarginFunction() {
		return marginFunction;
	}
	public void setMarginFunction(String margin) {
		this.marginFunction = margin;
	}

	public String getProductDimensionLevel() {
		return productDimensionLevel;
	}
	public void setProductDimensionLevel(String productDimensionLevel) {
		this.productDimensionLevel = productDimensionLevel;
	}
	public String getSalesReasonDimensionLevel() {
		return salesReasonDimensionLevel;
	}
	public void setSalesReasonDimensionLevel(String salesReasonDimensionLevel) {
		this.salesReasonDimensionLevel = salesReasonDimensionLevel;
	}
	public String getProductFilter() {
		return productFilter;
	}
	public void setProductFilter(String productFilter) {
		this.productFilter = productFilter;
	}
	public String getSalesReasonFilter() {
		return salesReasonFilter;
	}
	public void setSalesReasonFilter(String salesReasonFilter) {
		this.salesReasonFilter = salesReasonFilter;
	}
	public String getProductClassDimension() {
		return productClassDimension;
	}
	public void setProductClassDimension(String productClassDimension) {
		this.productClassDimension = productClassDimension;
	}
	public String getProductStyleDimension() {
		return productStyleDimension;
	}
	public void setProductStyleDimension(String productStyleDimension) {
		this.productStyleDimension = productStyleDimension;
	}
	public String getProductLineDimension() {
		return productLineDimension;
	}
	public void setProductLineDimension(String productLineDimension) {
		this.productLineDimension = productLineDimension;
	}
	public String getQueryInput() {
		return queryInput;
	}
	public void setQueryInput(String queryInput) {
		this.queryInput = queryInput;
	}
	public OlapController() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMakedimension() {
		return makedimension;
	}
	public void setMakedimension(String makedimension) {
		this.makedimension = makedimension;
	}
	public String getOrderDateMask() {
	if(this.orderDateDimensionLevel!=null && !"".equals(this.orderDateDimensionLevel)){
	switch (this.orderDateDimensionLevel) {
	case "DAY": orderDateMask="9999-99-99";
		
		break;
case "MONTH": orderDateMask="9999-99";
		
		break;
case "YEAR": orderDateMask="9999";
	
	break;

	default:  orderDateMask="9999-99-99";
		break;
	}
	return orderDateMask;
	}	
		return orderDateMask;
	}
	public void setOrderDateMask(String mask) {
		this.orderDateMask = mask;
	}
	
	
	public String getShipDateMask() {
		if(this.shipDateDimensionLevel!=null && !"".equals(this.shipDateDimensionLevel)){
		switch (this.shipDateDimensionLevel) {
		case "DAY": shipDateMask="99-99-9999";
			
			break;
	case "MONTH": shipDateMask="99-9999";
			
			break;
	case "YEAR": shipDateMask="9999";
		
		break;

		default:  shipDateMask="99-99-9999";
			break;
		}
		return shipDateMask;
		}	
			return shipDateMask;
		}
		public void setShipDateMask(String mask) {
			this.shipDateMask = mask;
		}
	
	public void test(){}












	public String getBillToDimensionLevel() {
		return billToDimensionLevel;
	}










	public void setBillToDimensionLevel(String billToDimensionLevel) {
		this.billToDimensionLevel = billToDimensionLevel;
	}










	public String getOrderToDimensionLevel() {
		return orderToDimensionLevel;
	}










	public void setOrderToDimensionLevel(String orderToDimensionLevel) {
		this.orderToDimensionLevel = orderToDimensionLevel;
	}




	public String getOrderDateDimensionLevel() {
		return orderDateDimensionLevel;
	}




	public void setOrderDateDimensionLevel(String orderDateDimensionLevel) {
		this.orderDateDimensionLevel = orderDateDimensionLevel;
	}




	public String getShipDateDimensionLevel() {
		return shipDateDimensionLevel;
	}




	public void setShipDateDimensionLevel(String shipDateDimensionLevel) {
		this.shipDateDimensionLevel = shipDateDimensionLevel;
	}


	


	public String getNativeQuery() {
		return nativeQuery;
	}


	public void setNativeQuery(String nativeQuery) {
		this.nativeQuery = nativeQuery;
	}


	public String getOrderDateFilter() {
		return orderDateFilter;
	}


	public void setOrderDateFilter(String orderDateFilter) {
		this.orderDateFilter = orderDateFilter;
	}


	public String getShipDateFilter() {
		return shipDateFilter;
	}


	public void setShipDateFilter(String shipDateFilter) {
		this.shipDateFilter = shipDateFilter;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Logger getLog() {
		return log;
	}


	public void setLog(Logger log) {
		this.log = log;
	}


	public QueryBuilder getBuilder() {
		return builder;
	}


	public void setBuilder(QueryBuilder builder) {
		this.builder = builder;
	}


	public DatwarehouseAccesDao getDao() {
		return dao;
	}


	public void setDao(DatwarehouseAccesDao dao) {
		this.dao = dao;
	}


	public ColumnManagerView getColumnManagerView() {
		return columnManagerView;
	}


	public void setColumnManagerView(ColumnManagerView columnManagerView) {
		this.columnManagerView = columnManagerView;
	}


	public String getCustomerTypeDimension() {
		return customerTypeDimension;
	}


	public void setCustomerTypeDimension(String customerTypeDimension) {
		this.customerTypeDimension = customerTypeDimension;
	}


	public String getCustomerRegionDimension() {
		return customerRegionDimension;
	}


	public void setCustomerRegionDimension(String customerRegionDimension) {
		this.customerRegionDimension = customerRegionDimension;
	}


	public String getCustomerGenderDimension() {
		return customerGenderDimension;
	}


	public void setCustomerGenderDimension(String customerGenderDimension) {
		this.customerGenderDimension = customerGenderDimension;
	}


	public String getCustomerAgeGroupDimension() {
		return customerAgeGroupDimension;
	}


	public void setCustomerAgeGroupDimension(String customerAgeGroupDimension) {
		this.customerAgeGroupDimension = customerAgeGroupDimension;
	}


	public String getCustomerYearlyIncomeGroupDimension() {
		return customerYearlyIncomeGroupDimension;
	}


	public void setCustomerYearlyIncomeGroupDimension(String customerYearlyIncomeGroupDimension) {
		this.customerYearlyIncomeGroupDimension = customerYearlyIncomeGroupDimension;
	}


	public String getCustomerHomeOwnerDimension() {
		return customerHomeOwnerDimension;
	}


	public void setCustomerHomeOwnerDimension(String customerHomeOwnerDimension) {
		this.customerHomeOwnerDimension = customerHomeOwnerDimension;
	}


	public String getSalesPersonFilter() {
		return salesPersonFilter;
	}


	public void setSalesPersonFilter(String salesPersonFilter) {
		this.salesPersonFilter = salesPersonFilter;
	}


	public String getCustomerRegionFilter() {
		return customerRegionFilter;
	}


	public void setCustomerRegionFilter(String customerRegionFilter) {
		this.customerRegionFilter = customerRegionFilter;
	}


	public boolean isSalesChannelFilter() {
		return salesChannelFilter;
	}


	public void setSalesChannelFilter(boolean salesChannelFilter) {
		this.salesChannelFilter = salesChannelFilter;
	}


	public boolean isShippingMethodFilter() {
		return shippingMethodFilter;
	}


	public void setShippingMethodFilter(boolean shippingMethodFilter) {
		this.shippingMethodFilter = shippingMethodFilter;
	}


	public boolean isGenderFilter() {
		return genderFilter;
	}


	public void setGenderFilter(boolean genderFilter) {
		this.genderFilter = genderFilter;
	}


	public boolean isAgeGroupFilter() {
		return ageGroupFilter;
	}


	public void setAgeGroupFilter(boolean ageGroupFilter) {
		this.ageGroupFilter = ageGroupFilter;
	}


	public boolean isYearlyIncomeFilter() {
		return yearlyIncomeFilter;
	}


	public void setYearlyIncomeFilter(boolean yearlyIncomeFilter) {
		this.yearlyIncomeFilter = yearlyIncomeFilter;
	}


	public boolean isHomeOwnerFilter() {
		return homeOwnerFilter;
	}


	public void setHomeOwnerFilter(boolean homeOwnerFilter) {
		this.homeOwnerFilter = homeOwnerFilter;
	}


	

	public boolean isSalesReason() {
		return SalesReason;
	}


	public void setSalesReason(boolean salesReason) {
		SalesReason = salesReason;
	}


	public boolean isSalesPerson() {
		return SalesPerson;
	}


	public void setSalesPerson(boolean salesPerson) {
		SalesPerson = salesPerson;
	}


	public boolean isSalesChannel() {
		return SalesChannel;
	}


	public void setSalesChannel(boolean salesChannel) {
		SalesChannel = salesChannel;
	}


	public boolean isShippingMethod() {
		return ShippingMethod;
	}


	public void setShippingMethod(boolean shippingMethod) {
		ShippingMethod = shippingMethod;
	}


	public List<String> getOrderbyList() {
		return orderbyList;
	}


	public void setOrderbyList(List<String> orderbyList) {
		this.orderbyList = orderbyList;
	}


	public List<String> getGroupbyList() {
		return groupbyList;
	}


	public void setGroupbyList(List<String> groupbyList) {
		this.groupbyList = groupbyList;
	}


	public boolean isProductLineFilter() {
		return productLineFilter;
	}


	public void setProductLineFilter(boolean productLineFilter) {
		this.productLineFilter = productLineFilter;
	}


	public boolean isProductClassFilter() {
		return productClassFilter;
	}


	public void setProductClassFilter(boolean productClassFilter) {
		this.productClassFilter = productClassFilter;
	}


	public boolean isProductStyleFilter() {
		return productStyleFilter;
	}


	public void setProductStyleFilter(boolean productStyleFilter) {
		this.productStyleFilter = productStyleFilter;
	}


	public boolean isMakeFilter() {
		return makeFilter;
	}


	public void setMakeFilter(boolean makeFilter) {
		this.makeFilter = makeFilter;
	}



	public String getShipToFilter() {
		return shipToFilter;
	}



	public void setShipToFilter(String shipToFilter) {
		this.shipToFilter = shipToFilter;
	}



	public String getBillToFilter() {
		return billToFilter;
	}



	public void setBillToFilter(String billToFilter) {
		this.billToFilter = billToFilter;
	}



	public String getSalesChannelDimension() {
		return salesChannelDimension;
	}



	public void setSalesChannelDimension(String salesChannelDimension) {
		this.salesChannelDimension = salesChannelDimension;
	}



	public String getShippingMethodDimension() {
		return shippingMethodDimension;
	}



	public void setShippingMethodDimension(String shippingMethodDimension) {
		this.shippingMethodDimension = shippingMethodDimension;
	}


	public String getGenderDimension() {
		return genderDimension;
	}


	public void setGenderDimension(String genderDimension) {
		this.genderDimension = genderDimension;
	}


	public String getAgeGroupDimension() {
		return ageGroupDimension;
	}


	public void setAgeGroupDimension(String ageGroupDimension) {
		this.ageGroupDimension = ageGroupDimension;
	}


	public String getIncomeGroupDimension() {
		return incomeGroupDimension;
	}


	public void setIncomeGroupDimension(String incomeGroupDimension) {
		this.incomeGroupDimension = incomeGroupDimension;
	}


	public String getHomeOwnerDimension() {
		return homeOwnerDimension;
	}


	public void setHomeOwnerDimension(String homeOwnerDimension) {
		this.homeOwnerDimension = homeOwnerDimension;
	}





	
	
	
	
}

