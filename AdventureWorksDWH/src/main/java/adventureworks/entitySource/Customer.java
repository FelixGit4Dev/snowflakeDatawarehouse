package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int customerID;

	@Column(nullable=false, length=10)
	private String accountNumber;

	@Column(nullable=false, length=1)
	private String customerType;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false)
	private byte[] rowguid;

	private int territoryID;

	public Customer() {
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public int getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(int territoryID) {
		this.territoryID = territoryID;
	}

}