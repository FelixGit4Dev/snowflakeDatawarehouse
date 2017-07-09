package adventureworks.entity.dimensions.time;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WEEK")
public class Week {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long weekId;
private long yearId;
private String week;
public long getYearId() {
	return yearId;
}
public void setYearId(long yearId) {
	this.yearId = yearId;
}
public String getWeek() {
	return week;
}
public void setWeek(String week) {
	this.week = week;
}
public long getWeekId() {
	return weekId;
}
public void setWeekId(long weekId) {
	this.weekId = weekId;
}
public Week() {
	super();
	// TODO Auto-generated constructor stub
}
public Week(long yearId, String week) {
	super();
	this.yearId = yearId;
	this.week = week;
}
	
	
}
