package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the salesorderdetail database table.
 * 
 */
@Embeddable
public class SalesorderdetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int salesOrderDetailID;

	@Column(unique=true, nullable=false)
	private int salesOrderID;

	public SalesorderdetailPK() {
	}
	public int getSalesOrderDetailID() {
		return this.salesOrderDetailID;
	}
	public void setSalesOrderDetailID(int salesOrderDetailID) {
		this.salesOrderDetailID = salesOrderDetailID;
	}
	public int getSalesOrderID() {
		return this.salesOrderID;
	}
	public void setSalesOrderID(int salesOrderID) {
		this.salesOrderID = salesOrderID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SalesorderdetailPK)) {
			return false;
		}
		SalesorderdetailPK castOther = (SalesorderdetailPK)other;
		return 
			(this.salesOrderDetailID == castOther.salesOrderDetailID)
			&& (this.salesOrderID == castOther.salesOrderID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.salesOrderDetailID;
		hash = hash * prime + this.salesOrderID;
		
		return hash;
	}
}