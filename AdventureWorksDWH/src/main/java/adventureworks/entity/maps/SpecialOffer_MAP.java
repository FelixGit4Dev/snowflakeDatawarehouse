package adventureworks.entity.maps;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class SpecialOffer_MAP {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long mapId;	

	@Column(name="SOURCE_KEY" , nullable=false)
	private long sourceKey;
	@Column(name="DWH_KEY" , nullable=false)
	private long dwhKey;

	@Column(name="MODIFIED_DATE", nullable=false)
	private Timestamp modifiedDate;

	@Column(name="VALID_FROM")
	private Timestamp from;

	@Column(name="VALID_TO")
	private Time to; 



	public SpecialOffer_MAP(long sourceKey, long dwhKey, Timestamp modifiedDate, Timestamp from, Time to) {
		super();
		this.sourceKey = sourceKey;
		this.dwhKey = dwhKey;
		this.modifiedDate = modifiedDate;
		this.from = from;
		this.to = to;
	}

	public SpecialOffer_MAP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(long sourceKey) {
		this.sourceKey = sourceKey;
	}

	public long getDwhKey() {
		return dwhKey;
	}

	public void setDwhKey(long dwhKey) {
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
}
