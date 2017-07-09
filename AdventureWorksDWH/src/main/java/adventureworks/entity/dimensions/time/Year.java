package adventureworks.entity.dimensions.time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="YEAR")
public class Year {
	
@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long yearId;
	private String year;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public long getYearId() {
		return yearId;
	}
	public Year(String year) {
		super();
		this.year = year;
	}
	public Year() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setYearId(long yearId) {
		this.yearId = yearId;
	}
	
	
	
	
}
