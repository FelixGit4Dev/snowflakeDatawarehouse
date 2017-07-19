package adventureworks.entity.other;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SpecialOffer {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long specialOfferId;	
private Timestamp modifiedDate;
private Timestamp fromDate;
private Timestamp toDate;

private double discount;


public double getDiscount() {
	return discount;
}


public void setDiscount(double discount) {
	this.discount = discount;
}


public long getSpecialOfferId() {
	return specialOfferId;
}


public void setSpecialOfferId(long specialOfferId) {
	this.specialOfferId = specialOfferId;
}


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

	
}
