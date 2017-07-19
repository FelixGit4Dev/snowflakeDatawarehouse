package adventureworks.entity.dimensions.time;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="DAY",indexes = {@Index(columnList="timeinMilis" , unique=true )})
public class Day {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long dayId;
	private long weekId;
	private long monthId;
	
	private Timestamp timeinMilis;
	private String day;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	
	public long getDayId() {
		return dayId;
	}
	public void setDayId(long dayId) {
		this.dayId = dayId;
	}
	public long getWeekId() {
		return weekId;
	}
	public void setWeekId(long weekId) {
		this.weekId = weekId;
	}
	public long getMonthId() {
		return monthId;
	}
	public void setMonthId(long monthId) {
		this.monthId = monthId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Day() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Day(long weekId, long monthId, String day) {
		super();
		this.weekId = weekId;
		this.monthId = monthId;
		this.day = day;
	}
	public Timestamp getModfiedDate() {
		return modfiedDate;
	}
	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}
	public Timestamp getTimeinMilis() {
		return timeinMilis;
	}
	public void setTimeinMilis(Timestamp timeinMilis) {
		this.timeinMilis = timeinMilis;
	}
	
	

}
