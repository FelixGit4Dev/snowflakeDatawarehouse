package visualization.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class OlapController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String quantityFunction;
	private String unitPriceFunction;
	private String taxAmtFunction;
	private String discountFunction;
	private String totalFunction;
	private String standartCostFunction;
	private String totalCost;
	private String marginFunction;
	
	
	
	//Dimension
	private String timeDimensionLevel;
	
	
	
	// Filter
	private String timeFilter;
	
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
	public String getTimeDimensionLevel() {
		return timeDimensionLevel;
	}
	public void setTimeDimensionLevel(String timeDimensionLevel) {
		this.timeDimensionLevel = timeDimensionLevel;
	}
	public String getTimeFilter() {
		return timeFilter;
	}
	public void setTimeFilter(String timeFilter) {
		this.timeFilter = timeFilter;
	}
	
	
}

