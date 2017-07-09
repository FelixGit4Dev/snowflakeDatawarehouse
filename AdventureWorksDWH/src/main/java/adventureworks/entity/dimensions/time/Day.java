package adventureworks.entity.dimensions.time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DAY")
public class Day {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long dayId;
	private long weekId;
	private long monthId;
	private String day;
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
	
	

}
