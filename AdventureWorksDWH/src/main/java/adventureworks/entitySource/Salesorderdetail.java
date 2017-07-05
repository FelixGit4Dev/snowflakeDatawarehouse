package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the salesorderdetail database table.
 * 
 */
@Entity
@Table(name="salesorderdetail")
@NamedQuery(name="Salesorderdetail.findAll", query="SELECT s FROM Salesorderdetail s")
public class Salesorderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SalesorderdetailPK id;

	@Column(length=25)
	private String carrierTrackingNumber;

	@Column(nullable=false)
	private double lineTotal;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false)
	private short orderQty;

	@Column(nullable=false)
	private int productID;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private int specialOfferID;

	@Column(nullable=false)
	private double unitPrice;

	@Column(nullable=false)
	private double unitPriceDiscount;

	public Salesorderdetail() {
	}

	public SalesorderdetailPK getId() {
		return this.id;
	}

	public void setId(SalesorderdetailPK id) {
		this.id = id;
	}

	public String getCarrierTrackingNumber() {
		return this.carrierTrackingNumber;
	}

	public void setCarrierTrackingNumber(String carrierTrackingNumber) {
		this.carrierTrackingNumber = carrierTrackingNumber;
	}

	public double getLineTotal() {
		return this.lineTotal;
	}

	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public short getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(short orderQty) {
		this.orderQty = orderQty;
	}

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public int getSpecialOfferID() {
		return this.specialOfferID;
	}

	public void setSpecialOfferID(int specialOfferID) {
		this.specialOfferID = specialOfferID;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getUnitPriceDiscount() {
		return this.unitPriceDiscount;
	}

	public void setUnitPriceDiscount(double unitPriceDiscount) {
		this.unitPriceDiscount = unitPriceDiscount;
	}

}