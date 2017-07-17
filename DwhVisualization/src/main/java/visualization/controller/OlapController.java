package visualization.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private static final long serialVersionUID = 1L;

	private String nativeQuery;
	private String errorMessage;
	
	private  String orderDateMask;
	private  String shipDateMask;
	
	
	private String quantityFunction="SUM";
	private String unitPriceFunction="SUM";
	private String taxAmtFunction="SUM";
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
	
	private String salesReasonDimensionLevel="";

	private String customerTypeDimension="";
	private String customerRegionDimension="";
	
	private String customerGenderDimension="";
	private String customerAgeGroupDimension="";
	private String customerYearlyIncomeGroupDimension="";
	private String customerHomeOwnerDimension="";
	
	
	
	// Filter
	private String orderDateFilter;
	private String shipDateFilter;
	private String placeFilter;
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
	
	
	private List<String> orderbyList;
	private List<String> groupbyList;
	
	public void executeNativeQuery(){
	extractColumnNames();	
	}
	
	
	private void extractColumnNames() {
		// TODO Auto-generated method stub
		
	}


	public HashMap<String, HashMap<String, List<String>>> evaluateDimensions(){
		HashMap<String, HashMap<String,List<String>>> result= new HashMap<>();
	HashMap<String, List<String>> innerJoin= new HashMap<>();
	HashMap<String, List<String>> where= new HashMap<>();
	HashMap<String, List<String>> groupBy= new HashMap<>();
	HashMap<String, List<String>> select= new HashMap<>();
	evauluateTimeDimension(innerJoin,where,select);
	evauluatePlaceDimension(innerJoin,where,select);
	evauluateCustomerDimension(innerJoin,where,select);
	evauluateSalesDimension(innerJoin,where,select);
	evauluateProductDimension(innerJoin,where,select);
	result.put("innerJoin", innerJoin);
	result.put("where", where);
	result.put("select", select);
	result.put("groupBy", groupBy );
	return result;
	}
	
	
	private void evauluateProductDimension(HashMap<String, List<String>> innerJoin, HashMap<String, List<String>> where,
			HashMap<String, List<String>> select) {
		ArrayList<String> joins=new ArrayList<>();
		ArrayList<String> wheres=new  ArrayList<String>();
		ArrayList<String> selects= new ArrayList<String>();
		
		
		
		// TODO Auto-generated method stub
		innerJoin.put("product",joins );
		where.put("product",wheres );
		
		select.put("product",selects );
		
	}


	public HashMap<String, HashMap<String,List<String>>> evaluateGroupBy(){
		return null;
		
	}
	
	public HashMap<String, HashMap<String,List<String>>> evaluateOrderBy(){
		return null;
		
	}
	
	private void evauluateSalesDimension(HashMap<String, List<String>> innerJoin, HashMap<String, List<String>> where, HashMap<String, List<String>> select) {
		ArrayList<String> joins=new ArrayList<>();
		ArrayList<String> wheres=new  ArrayList<String>();
		ArrayList<String> selects= new ArrayList<String>();
		
		
		
		// TODO Auto-generated method stub
		innerJoin.put("sales",joins );
		where.put("sales",wheres );
		
		select.put("sales",selects );
		
	}


	private void evauluateCustomerDimension(HashMap<String, List<String>> innerJoin, HashMap<String, List<String>> where, HashMap<String, List<String>> select) {
		ArrayList<String> joins=new ArrayList<>();
		ArrayList<String> wheres=new  ArrayList<String>();
		ArrayList<String> selects= new ArrayList<String>();
		// TODO Auto-generated method stub
		innerJoin.put("customer",joins );
		where.put("customer",wheres );
	
		select.put("customer",selects );
		
	}


	private void evauluatePlaceDimension(HashMap<String, List<String>> innerJoin, HashMap<String, List<String>> where, HashMap<String, List<String>> select) {
		ArrayList<String> joins=new ArrayList<>();
		ArrayList<String> wheres=new  ArrayList<String>();
		ArrayList<String> selects= new ArrayList<String>();
		
		if(!"".equals(this.billToDimensionLevel)){
			switch (billToDimensionLevel) {
			case "CITY": 
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
			selects.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getFilterAttribute());
				break;
			case"STATE": 
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.STATE).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getFilterAttribute());
				break;

			case "COUNTRY":
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.COUNTRY).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.COUNTRY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.COUNTRY).getFilterAttribute());
				
				break; 

			case "TERRITORY":
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.TERRITORY).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.TERRITORY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.TERRITORY).getFilterAttribute());
				
				break; 
			default:
				break;
			}
			
		}
		if(!"".equals(this.orderToDimensionLevel)){
			switch (orderToDimensionLevel) {
			case "CITY": 
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
			selects.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getFilterAttribute());
				break;
			case"STATE": 
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.STATE).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getFilterAttribute());
				break;

			case "COUNTRY":
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.COUNTRY).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.COUNTRY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.COUNTRY).getFilterAttribute());
				
				break; 

			case "TERRITORY":
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.CITY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.STATE).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.TERRITORY).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.TERRITORY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.TERRITORY).getFilterAttribute());
				
				break; 
			default:
				break;
			}
			
		}
		
		innerJoin.put("place",joins );
		where.put("place",wheres );
		
		select.put("place",selects );
	}


	


	
	private void evauluateTimeDimension(HashMap<String, List<String>> innerJoin, HashMap<String, List<String>> where, HashMap<String, List<String>> select) {
		ArrayList<String> joins=new ArrayList<>();
		ArrayList<String> wheres=new  ArrayList<String>();
		ArrayList<String> groupBys= new ArrayList<String>();
		ArrayList<String> selects= new ArrayList<String>();
		if(!"".equals(shipDateDimensionLevel)){
			switch (shipDateDimensionLevel) {
			case "DAY": 
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName());
			selects.add(QueryConstants.getJOINMAP().get(QueryConstants.DAY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getFilterAttribute());
				break;
			case"MONTH": 
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getFilterAttribute());
				break;

			case "YEAR":
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getTableName());
				joins.add(QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.YEAR).getTableName());
				
				selects.add(QueryConstants.getJOINMAP().get(QueryConstants.YEAR).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.YEAR).getFilterAttribute());
				
				break; 
			default:
				break;
			}
			
			if(shipDateFilter!=null && !"".equals(shipDateFilter)){
			//wheres.add(e)	
				
			}
		}
if(!"".equals(orderDateDimensionLevel)){
	switch (orderDateDimensionLevel) {
	case "DAY":
		joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName());
		selects.add(QueryConstants.getJOINMAP().get(QueryConstants.DAY).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getFilterAttribute());
		
		break;
	case"MONTH": 
		joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName());
		joins.add(QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getTableName());
		
		selects.add(QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getFilterAttribute());
		
		break;

	case "YEAR":
		joins.add(QueryConstants.getJOINMAP().get(QueryConstants.SALES_FACT).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName());
		joins.add(QueryConstants.getJOINMAP().get(QueryConstants.DAY).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getTableName());
		joins.add(QueryConstants.getJOINMAP().get(QueryConstants.MONTH).getTableName()+";"+QueryConstants.getJOINMAP().get(QueryConstants.YEAR).getTableName());
		
		selects.add(QueryConstants.getJOINMAP().get(QueryConstants.YEAR).getAlias()+"."+QueryConstants.getJOINMAP().get(QueryConstants.YEAR).getFilterAttribute());
		
		break; 
	default:
		break;
	}
			if(orderDateFilter!=null && !"".equals(orderDateFilter)){
				
			}
		}

innerJoin.put("time",joins );
where.put("time",wheres );
select.put("time",selects );
		
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
	
	public List<String> completeTextShipTo(String query){
			final String key = orderToDimensionLevel;
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
	
	public List<String> completeTextProduct(String query){
		
		final String key = productDimensionLevel;
		List<String> result;
		switch (key) {
		case "PRODUCT": result=this.dao.getAllProducts(query);
			
			break;
case "SUBCATEGORY": result=this.dao.getAllSubcategories(query);
			
			break;
case "CATEGORY": result=this.dao.getAllCategories(query);
	
	break;


		default: result= new ArrayList<String>();
		
		}
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
	
	public List<String> completeTextSalesPerson(String query){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<10;i++){
		list.add(query+i);	
		}
	return list;
	}
	
	
	public void buildAndExecuteQuery(){
		HashMap<String, HashMap<String, List<String>>> map =evaluateDimensions();	
		ArrayList<String> columnOrder= new ArrayList<>();
	String olapQuery = this.builder.assembleOlapQuery(map, columnOrder);
log.info(olapQuery);
//	if(builder.isSingleColumnResult()){
//dao.executeOlapQuerySingleColumn(olapQuery);		
//	}else{
//List<DataTableObject> tableObject = this.createDatatableObject(columnOrder,dao.executeOlapQueryMulticolumns(olapQuery));		
//	this.columnManagerView.setTableObjects(tableObject);
	//}
		
	}
	
	
	private List<DataTableObject> createDatatableObject(ArrayList<String> columnOrder, List<Object[]> executeOlapQueryMulticolumns) {
	ArrayList<DataTableObject> list = new ArrayList<>();
	for(Object[] arr: executeOlapQueryMulticolumns){
	DataTableObject object = new DataTableObject();
		for(int i=0; i<arr.length;i++){
		final String attrubiteName= columnOrder.get(i);
		switch (attrubiteName) {
		case "":
			
			break;

		default:
			break;
		}
	}	
	}
		return null;
	}


	public void resetPlaceDimension(){
		billToDimensionLevel="";
		 orderToDimensionLevel="";	
		 placeFilter="";
	}
	
	public void resetTimeDimension(){
		this.orderDateDimensionLevel="";
		this.shipDateDimensionLevel="";
		this.orderDateFilter="";
		this.shipDateFilter="";
		
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
	public String getTaxAmtFunction() {
		return taxAmtFunction;
	}
	public void setTaxAmtFunction(String taxAmtFunction) {
		this.taxAmtFunction = taxAmtFunction;
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
	case "DAY": orderDateMask="99-99-9999";
		
		break;
case "MONTH": orderDateMask="99-9999";
		
		break;
case "YEAR": orderDateMask="9999";
	
	break;

	default:  orderDateMask="99-99-9999";
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

	public String getPlaceFilter() {
		return placeFilter;
	}
	public void setPlaceFilter(String placeFilter) {
		this.placeFilter = placeFilter;
	}










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





	
	
	
	
}

