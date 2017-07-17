package visualization.entity.entityDwh;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
public class City implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CITY_ID", nullable = false, unique = true)
	private long cityId;
	
	private long stateId;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(long stateId, String name, Timestamp modfiedDate, Timestamp fromDate, Timestamp toDate) {
		super();
		this.stateId = stateId;
		this.name = name;
		this.modfiedDate = modfiedDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

}
