package adventureworks.entity.maps;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY_MAP")
public class Country_MAP implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long mapId;	

	@Column(name="SOURCE_KEY" , nullable=false)
	private String sourceKey;
	@Column(name="DWH_KEY" , nullable=false)
	private String dwhKey;

	@Column(name="MODIFIED_DATE", nullable=false)
	private Timestamp modifiedDate;

	@Column(name="VALID_FROM")
	private Timestamp from;

	@Column(name="VALID_TO")
	private Time to; 



	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public String getDwhKey() {
		return dwhKey;
	}

	public void setDwhKey(String dwhKey) {
		this.dwhKey = dwhKey;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getFrom() {
		return from;
	}

	public void setFrom(Timestamp from) {
		this.from = from;
	}

	public Time getTo() {
		return to;
	}

	public void setTo(Time to) {
		this.to = to;
	}

	public long getMapId() {
		return mapId;
	}

	public Country_MAP(String sourceKey, String dwhKey, Timestamp modifiedDate, Timestamp from, Time to) {
		super();
		this.sourceKey = sourceKey;
		this.dwhKey = dwhKey;
		this.modifiedDate = modifiedDate;
		this.from = from;
		this.to = to;
	}

	public Country_MAP() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
