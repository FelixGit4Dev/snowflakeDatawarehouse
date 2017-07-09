package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the specialofferproduct database table.
 * 
 */
@Embeddable
public class SpecialofferproductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int specialOfferID;

	private int productID;

	public SpecialofferproductPK() {
	}
	public int getSpecialOfferID() {
		return this.specialOfferID;
	}
	public void setSpecialOfferID(int specialOfferID) {
		this.specialOfferID = specialOfferID;
	}
	public int getProductID() {
		return this.productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpecialofferproductPK)) {
			return false;
		}
		SpecialofferproductPK castOther = (SpecialofferproductPK)other;
		return 
			(this.specialOfferID == castOther.specialOfferID)
			&& (this.productID == castOther.productID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.specialOfferID;
		hash = hash * prime + this.productID;
		
		return hash;
	}
}