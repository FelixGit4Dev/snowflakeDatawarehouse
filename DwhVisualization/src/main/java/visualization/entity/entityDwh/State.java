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
@Table(name="STATE")
public class State implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STATE_ID", nullable=false, unique=true)
private long stateId;	
	@Column(nullable=false)
	private long territoryId;
	@Column(nullable=false)
	private String countryCode;
	@Column(name="NAME", nullable=false)
	private String name;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

		// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(long territoryId) {
		this.territoryId = territoryId;
	}

	public State(long territoryId, String countryCode, String name, Timestamp modfiedDate, Timestamp fromDate,
			Timestamp toDate) {
		super();
		this.territoryId = territoryId;
		this.countryCode = countryCode;
		this.name = name;
		this.modfiedDate = modfiedDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public State() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
