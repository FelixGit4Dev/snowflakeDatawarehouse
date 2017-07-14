package visualization.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import visualization.dao.DatwarehouseAccesDao;
import visualization.queries.QueryBuilder;

@SessionScoped
@Named
public class OlapController implements Serializable{

	/**
	 * 
	 */
	
	@Inject
	private QueryBuilder builder; 
	@Inject
	private DatwarehouseAccesDao dao; 
	
	private static final long serialVersionUID = 1L;

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
	private String orderDateDimensionLevel;
	private String shipDateDimensionLevel;
	private String billToDimensionLevel;
	private String orderToDimensionLevel;
	private String productDimensionLevel;
	private String productClassDimension;
	private String productStyleDimension;
	private String productLineDimension;
	private String salesReasonDimensionLevel;
	private String makedimension;
	
	
	
	// Filter
	private String timeFilter;
	private String placeFilter;
	private String productFilter;
	private String salesReasonFilter;
	
	
	//group by
	private boolean groupByShipDate;
	private boolean groupByOrderDate;
	private boolean groupByBillTo;
	private boolean groupByShipTo;
	
	
	
	
	public List<String> completeText(String query){
		return null;
	
		
	}
	
	
	public void buildAndExecuteQuery(){
	String olapQuery = this.builder.assembleOlapQuery();
	if(builder.isSingleColumnResult()){
dao.executeOlapQuerySingleColumn(olapQuery);		
	}else{
dao.executeOlapQueryMulticolumns(olapQuery);		
	}
		
	}
	
	
	public void resetPlaceDimension(){
		billToDimensionLevel="";
		 orderToDimensionLevel="";	
		 placeFilter="";
	}
	
	public void resetTimeDimension(){
		this.orderDateDimensionLevel="";
		this.shipDateDimensionLevel="";
		this.timeFilter="";
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

	public String getTimeFilter() {
		return timeFilter;
	}
	public void setTimeFilter(String timeFilter) {
		this.timeFilter = timeFilter;
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


	public boolean isGroupByShipDate() {
		return groupByShipDate;
	}


	public void setGroupByShipDate(boolean groupByShipDate) {
		this.groupByShipDate = groupByShipDate;
	}


	public boolean isGroupByOrderDate() {
		return groupByOrderDate;
	}


	public void setGroupByOrderDate(boolean groupByOrderDate) {
		this.groupByOrderDate = groupByOrderDate;
	}


	public boolean isGroupByBillTo() {
		return groupByBillTo;
	}


	public void setGroupByBillTo(boolean groupByBillTo) {
		this.groupByBillTo = groupByBillTo;
	}


	public boolean isGroupByShipTo() {
		return groupByShipTo;
	}


	public void setGroupByShipTo(boolean groupByShipTo) {
		this.groupByShipTo = groupByShipTo;
	}





	
	
	
	
}

