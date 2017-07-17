package adventureworks.entity.facts;

import java.io.Serializable;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name="SALES_FACT")
//@SqlResultSetMapping(name="SalesFactMapping",
//classes = {
// @ConstructorResult(targetClass = SalesFact.class,
//   columns = {@ColumnResult(name="shippingMethodId"),
//		   @ColumnResult(name="billTo"),
//		   @ColumnResult(name="StateProvinceId"),
//		   @ColumnResult(name="salesReasonId"),
//		   @ColumnResult(name="productId"),
//		   @ColumnResult(name="orderDateId"),
//		   @ColumnResult(name="dueDateId"),
//		   @ColumnResult(name="shipdateId"),
//		   @ColumnResult(name="customerId"),
//		   @ColumnResult(name="salesPersonId"),
//		   @ColumnResult(name="quantity"),
//		   @ColumnResult(name="unitPrice"),
//		   @ColumnResult(name="taxAmt"),
//		   @ColumnResult(name="discount"),
//		   @ColumnResult(name="total"),
//		   @ColumnResult(name="productStandartCost"),
//		   @ColumnResult(name="billTo"),
//		   @ColumnResult(name="shipTo"),
//   
// }
// )}
//)
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


private long orderDateId;

private long shipdateId;

private long customerId;

private long salesPersonId;

private long billTo;
private long shipTo;


//Kennzahlen
private int quantity;
private double unitPrice;
private double taxAmt;
private double discount;
private double total;
private double productStandartCost;






public SalesFact(long shippingMethodId, long billToStateProvinceId, long salesReasonId, long productId,
		 long orderDateId, long dueDateId, long shipdateId, long customerId, long salesPersonId,
		int quantity, double unitPrice, double taxAmt, double discount, double total, double productStandartCost, long billTo, long shipTo) {
	super();
	this.shippingMethodId = shippingMethodId;
	this.billToStateProvinceId = billToStateProvinceId;
	this.salesReasonId = salesReasonId;
	this.productId = productId;
	this.orderDateId = orderDateId;
	this.shipdateId = shipdateId;
	this.customerId = customerId;
	this.salesPersonId = salesPersonId;
	this.quantity = quantity;
	this.unitPrice = unitPrice;
	this.taxAmt = taxAmt;
	this.discount = discount;
	this.total = total;
	this.productStandartCost = productStandartCost;
	this.setBillTo(billTo);
	this.setShipTo(shipTo);
}






public SalesFact() {
	super();
	// TODO Auto-generated constructor stub
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

public long getSaleFactId() {
	return saleFactId;
}
public long getOrderDateId() {
	return orderDateId;
}
public void setOrderDateId(long orderDateId) {
	this.orderDateId = orderDateId;
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






public long getBillTo() {
	return billTo;
}






public void setBillTo(long billTo) {
	this.billTo = billTo;
}






public long getShipTo() {
	return shipTo;
}






public void setShipTo(long shipTo) {
	this.shipTo = shipTo;
}

}
