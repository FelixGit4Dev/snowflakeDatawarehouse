package adventureworks.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.SqlResultSetMapping;
import javax.persistence.FieldResult;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Table;


@SqlResultSetMapping(
        name = "StagingValueMapping",
        entities = {@EntityResult(
               
                fields = {
                	@FieldResult(name = "salesOrderId", column = "detailId"),
                    @FieldResult(name = "shippingMethodId", column = "shipMethod"),
                    @FieldResult(name = "orderDateId", column = "orderDate"),
                    @FieldResult(name = "shipdateId", column = "shipDate"),
                    @FieldResult(name = "boughtOnline", column = "online"),
                    @FieldResult(name = "customerId", column = "customer"),
                    @FieldResult(name = "salesPersonId", column = "salesperson"),
                    @FieldResult(name = "quantity", column = "quantity"),
                    @FieldResult(name = "productId", column = "product"),
                    @FieldResult(name = "specialOfferId", column = "specialOffer"),
                    @FieldResult(name = "unitPrice", column = "unitPrice"),
                    @FieldResult(name = "salesReasonId", column = "salesreason"),
                    @FieldResult(name = "billTo", column = "billTo"),
                    @FieldResult(name = "shipTo", column = "shipTo"),
                    @FieldResult(name = "billToState", column = "billToState"),
                    @FieldResult(name = "shipToState", column = "shipToState"),
                    },entityClass = StagingTable.class)}
		
		)

//@SqlResultSetMapping(
//        name = "StagingValueMapping",
//        		classes = {
//        				 @ConstructorResult(targetClass = StagingTable.class,
//        				   columns = {
//                	@ColumnResult( name = "detailId",type=Integer.class ),
//                	@ColumnResult( name = "shipMethod",type=Integer.class),
//                	@ColumnResult( name = "orderDate",type=Timestamp.class),
//                	@ColumnResult( name = "shipDate" ,type=Timestamp.class),
//                	@ColumnResult( name = "online", type=Boolean.class),
//                	@ColumnResult( name = "customer", type=Integer.class),
//                	@ColumnResult( name = "salesperson", type=String.class),
//                	@ColumnResult( name = "quantity" , type=Short.class),
//                	@ColumnResult( name = "product" ,type=Integer.class),
//                	@ColumnResult( name = "specialOffer", type=Integer.class),
//                	@ColumnResult( name = "unitPrice", type=Double.class),
//                	@ColumnResult( name = "salesReason",type=String.class),
//                	@ColumnResult(name = "billTo", type=String.class),
//                	@ColumnResult( name = "billToState",type=Integer.class),
//                	@ColumnResult( name = "shipTo", type=String.class),               	
//                	@ColumnResult( name = "shipToState",type=Integer.class),
//                    })}
//		
//		)
@Entity
@Table( indexes = {@Index(name = "my_index_name",  columnList="salesOrderId", unique = true)})
public class StagingTable implements Serializable {

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
public StagingTable(long salesOrderId, long  shippingMethodId, Timestamp orderDateId, Timestamp shipdateId, boolean boughtOnline,long customerId,long salesPersonId,
		int quantity,long productId, long specialOfferId, double unitPrice,long salesReasonId,String billTo, String shipTo ,
			long billToState, long shipToState   ) {
		super();
		this.salesOrderId=salesOrderId;
		this.shippingMethodId = shippingMethodId;
		this.billTo = billTo;
		this.shipTo = shipTo;
		this.salesReasonId = salesReasonId;
		this.productId = productId;
		this.orderDateId = orderDateId;
		this.boughtOnline = boughtOnline;
		this.shipdateId = shipdateId;
		this.customerId = customerId;
		this.salesPersonId = salesPersonId;
		this.specialOfferId = specialOfferId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.billToState=billToState;
		this.shipToState=shipToState;
	}
private	 Long shippingMethodId;

@Id
private Long salesOrderId;

private	String billTo;
private String shipTo;

@Column(nullable=true)
private	Long salesReasonId;

private	Long productId;

private Long billToState;
private Long shipToState;

private Timestamp orderDateId;

private Boolean boughtOnline;

private Timestamp shipdateId;

private Long customerId;

@Column(nullable=true)
private Long salesPersonId;
private Long specialOfferId;


//Kennzahlen
private Integer quantity;
private Double unitPrice;





public StagingTable() {
	super();
	// TODO Auto-generated constructor stub
}



public long getShippingMethodId() {
	return shippingMethodId;
}


public void setShippingMethodId(long shippingMethodId) {
	this.shippingMethodId = shippingMethodId;
}


public long getSalesOrderId() {
	return salesOrderId;
}


public void setSalesOrderId(long salesOrderId) {
	this.salesOrderId = salesOrderId;
}


public String getBillTo() {
	return billTo;
}


public void setBillTo(String billTo) {
	this.billTo = billTo;
}


public String getShipTo() {
	return shipTo;
}


public void setShipTo(String shipTo) {
	this.shipTo = shipTo;
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




public Timestamp getOrderDateId() {
	return orderDateId;
}


public void setOrderDateId(Timestamp orderDateId) {
	this.orderDateId = orderDateId;
}


public boolean isBoughtOnline() {
	return boughtOnline;
}


public void setBoughtOnline(boolean boughtOnline) {
	this.boughtOnline = boughtOnline;
}


public Timestamp getShipdateId() {
	return shipdateId;
}


public void setShipdateId(Timestamp shipdateId) {
	this.shipdateId = shipdateId;
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


public long getSpecialOfferId() {
	return specialOfferId;
}


public void setSpecialOfferId(long specialOfferId) {
	this.specialOfferId = specialOfferId;
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




public static long getSerialversionuid() {
	return serialVersionUID;
}





public long getBillToState() {
	return billToState;
}



public void setBillToState(long billToState) {
	this.billToState = billToState;
}



public long getShipToState() {
	return shipToState;
}



public void setShipToState(long shipToState) {
	this.shipToState = shipToState;
}


}
