package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the customeraddress database table.
 * 
 */
@Embeddable
public class CustomeraddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int customerID;

	@Column(unique=true, nullable=false)
	private int addressID;

	public CustomeraddressPK() {
	}
	public int getCustomerID() {
		return this.customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getAddressID() {
		return this.addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomeraddressPK)) {
			return false;
		}
		CustomeraddressPK castOther = (CustomeraddressPK)other;
		return 
			(this.customerID == castOther.customerID)
			&& (this.addressID == castOther.addressID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.customerID;
		hash = hash * prime + this.addressID;
		
		return hash;
	}
}