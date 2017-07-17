package adventureworks.entity.dimensions.time;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MONTH")
public class Month {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long monthId;	
	@Column(nullable=false)
	private Timestamp modfiedDate;	

private long yearId;	
private String month;

public long getYearId() {
	return yearId;
}
public void setYearId(long yearId) {
	this.yearId = yearId;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public long getMonthId() {
	return monthId;
}
public void setMonthId(long monthId) {
	this.monthId = monthId;
}
public Month() {
	super();
	// TODO Auto-generated constructor stub
}
public Month(long yearId, String month) {
	super();
	this.yearId = yearId;
	this.month = month;
}
public Timestamp getModfiedDate() {
	return modfiedDate;
}
public void setModfiedDate(Timestamp modfiedDate) {
	this.modfiedDate = modfiedDate;
}


}
