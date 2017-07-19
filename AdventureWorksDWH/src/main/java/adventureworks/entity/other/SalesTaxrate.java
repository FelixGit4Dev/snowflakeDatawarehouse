package adventureworks.entity.other;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SalesTaxrate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long salesTaxrateId;
	private Timestamp modifiedDate;
	private Timestamp fromDate;
	private Timestamp toDate;
	
	
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

	private double rate;
	
	private long stateId;

	public long getSalesTaxrateId() {
		return salesTaxrateId;
	}

	public void setSalesTaxrateId(long salesTaxrateId) {
		this.salesTaxrateId = salesTaxrateId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	
	
	
	
}
