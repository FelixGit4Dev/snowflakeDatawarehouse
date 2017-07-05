package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the salesorderheader database table.
 * 
 */
@Entity
@Table(name="salesorderheader")
@NamedQuery(name="Salesorderheader.findAll", query="SELECT s FROM Salesorderheader s")
public class Salesorderheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int salesOrderID;

	@Column(length=15)
	private String accountNumber;

	@Column(nullable=false)
	private int billToAddressID;

	@Column(length=128)
	private String comment;

	@Column(nullable=false)
	private int contactID;

	@Column(length=15)
	private String creditCardApprovalCode;

	private int creditCardID;

	private int currencyRateID;

	@Column(nullable=false)
	private int customerID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dueDate;

	@Column(nullable=false)
	private double freight;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false)
	private byte onlineOrderFlag;

	@Column(nullable=false)
	private Timestamp orderDate;

	@Column(length=25)
	private String purchaseOrderNumber;

	@Column(nullable=false)
	private byte revisionNumber;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false, length=25)
	private String salesOrderNumber;

	private int salesPersonID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date shipDate;

	@Column(nullable=false)
	private int shipMethodID;

	@Column(nullable=false)
	private int shipToAddressID;

	@Column(nullable=false)
	private byte status;

	@Column(nullable=false)
	private double subTotal;

	@Column(nullable=false)
	private double taxAmt;

	private int territoryID;

	@Column(nullable=false)
	private double totalDue;

	public Salesorderheader() {
	}

	public int getSalesOrderID() {
		return this.salesOrderID;
	}

	public void setSalesOrderID(int salesOrderID) {
		this.salesOrderID = salesOrderID;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBillToAddressID() {
		return this.billToAddressID;
	}

	public void setBillToAddressID(int billToAddressID) {
		this.billToAddressID = billToAddressID;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getContactID() {
		return this.contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public String getCreditCardApprovalCode() {
		return this.creditCardApprovalCode;
	}

	public void setCreditCardApprovalCode(String creditCardApprovalCode) {
		this.creditCardApprovalCode = creditCardApprovalCode;
	}

	public int getCreditCardID() {
		return this.creditCardID;
	}

	public void setCreditCardID(int creditCardID) {
		this.creditCardID = creditCardID;
	}

	public int getCurrencyRateID() {
		return this.currencyRateID;
	}

	public void setCurrencyRateID(int currencyRateID) {
		this.currencyRateID = currencyRateID;
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getFreight() {
		return this.freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public byte getOnlineOrderFlag() {
		return this.onlineOrderFlag;
	}

	public void setOnlineOrderFlag(byte onlineOrderFlag) {
		this.onlineOrderFlag = onlineOrderFlag;
	}

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getPurchaseOrderNumber() {
		return this.purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public byte getRevisionNumber() {
		return this.revisionNumber;
	}

	public void setRevisionNumber(byte revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public int getSalesPersonID() {
		return this.salesPersonID;
	}

	public void setSalesPersonID(int salesPersonID) {
		this.salesPersonID = salesPersonID;
	}

	public Date getShipDate() {
		return this.shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public int getShipMethodID() {
		return this.shipMethodID;
	}

	public void setShipMethodID(int shipMethodID) {
		this.shipMethodID = shipMethodID;
	}

	public int getShipToAddressID() {
		return this.shipToAddressID;
	}

	public void setShipToAddressID(int shipToAddressID) {
		this.shipToAddressID = shipToAddressID;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public double getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTaxAmt() {
		return this.taxAmt;
	}

	public void setTaxAmt(double taxAmt) {
		this.taxAmt = taxAmt;
	}

	public int getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(int territoryID) {
		this.territoryID = territoryID;
	}

	public double getTotalDue() {
		return this.totalDue;
	}

	public void setTotalDue(double totalDue) {
		this.totalDue = totalDue;
	}

}