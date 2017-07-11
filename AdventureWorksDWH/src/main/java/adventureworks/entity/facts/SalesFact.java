package adventureworks.entity.facts;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALES_FACT")
public class SalesFact implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private	long saleFactId;
	
	
private	 long shippingMethodId;

private	long billToStateProvinceId;

private	long salesReasonId;

private	long productId;

private long territoryId;

private long orderDateId;

private long dueDateId;

private long shipdateId;

private long customerId;

private long salesPersonId;


//Kennzahlen
private int quantity;
private double unitPrice;
private double taxAmt;
private double discount;
private double total;
private double productStandartCost;


public SalesFact() {
	super();
	// TODO Auto-generated constructor stub
}
public SalesFact(long shippingMethodId, long billToAdressId, long salesReasonId, long productId, long territoryId, long orderDateId,
		long dueDateId, long shipdateId) {
	super();
	this.shippingMethodId = shippingMethodId;
	this.billToStateProvinceId = billToAdressId;
	this.salesReasonId = salesReasonId;
	this.productId = productId;
	this.territoryId = territoryId;
	this.orderDateId=orderDateId;
	this.dueDateId=dueDateId;
	this.shipdateId=shipdateId;
}
public long getShippingMethodId() {
	return shippingMethodId;
}
public void setShippingMethodId(long shippingMethodId) {
	this.shippingMethodId = shippingMethodId;
}
public long getBillToStateProvinceId() {
	return billToStateProvinceId;
}
public void setBillToStateProvinceId(long billToAdressId) {
	this.billToStateProvinceId = billToAdressId;
}
public long getSalesReasonId() {
	return salesReasonId;
}
public void setSalesReasonId(long salesReasonId) {
	this.salesReasonId = salesReasonId;
}
public long getProductId() {
	return productId;
}
public void setProductId(long productId) {
	this.productId = productId;
}
public long getTerritoryId() {
	return territoryId;
}
public void setTerritoryId(long territoryId) {
	this.territoryId = territoryId;
}
public long getSaleFactId() {
	return saleFactId;
}
public long getOrderDateId() {
	return orderDateId;
}
public void setOrderDateId(long orderDateId) {
	this.orderDateId = orderDateId;
}
public long getDueDateId() {
	return dueDateId;
}
public void setDueDateId(long dueDateId) {
	this.dueDateId = dueDateId;
}
public long getShipdateId() {
	return shipdateId;
}
public void setShipdateId(long shipdateId) {
	this.shipdateId = shipdateId;
}
public void setSaleFactId(long saleFactId) {
	this.saleFactId = saleFactId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(double unitPrice) {
	this.unitPrice = unitPrice;
}
public double getTaxAmt() {
	return taxAmt;
}
public void setTaxAmt(double taxAmt) {
	this.taxAmt = taxAmt;
}
public double getDiscount() {
	return discount;
}
public void setDiscount(double discount) {
	this.discount = discount;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public long getCustomerId() {
	return customerId;
}
public void setCustomerId(long customerId) {
	this.customerId = customerId;
}
public long getSalesPersonId() {
	return salesPersonId;
}
public void setSalesPersonId(long salesPersonId) {
	this.salesPersonId = salesPersonId;
}
public double getProductStandartCost() {
	return productStandartCost;
}
public void setProductStandartCost(double productStandartCost) {
	this.productStandartCost = productStandartCost;
}

}
