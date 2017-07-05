package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the customeraddress database table.
 * 
 */
@Entity
@Table(name="customeraddress")
@NamedQuery(name="Customeraddress.findAll", query="SELECT c FROM Customeraddress c")
public class Customeraddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CustomeraddressPK id;

	@Column(nullable=false)
	private int addressTypeID;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false)
	private byte[] rowguid;

	public Customeraddress() {
	}

	public CustomeraddressPK getId() {
		return this.id;
	}

	public void setId(CustomeraddressPK id) {
		this.id = id;
	}

	public int getAddressTypeID() {
		return this.addressTypeID;
	}

	public void setAddressTypeID(int addressTypeID) {
		this.addressTypeID = addressTypeID;
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

}