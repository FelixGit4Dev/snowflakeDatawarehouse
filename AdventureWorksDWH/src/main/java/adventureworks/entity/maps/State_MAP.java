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
@Table(name="STATE_MAP")
public class State_MAP implements Serializable{

	/**
	 * 
	 */
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
	private Timestamp to; 



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

	public Timestamp getTo() {
		return to;
	}

	public void setTo(Timestamp to) {
		this.to = to;
	}

	public long getMapId() {
		return mapId;
	}

	public State_MAP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public State_MAP(long sourceKey, long dwhKey, Timestamp modifiedDate, Timestamp from, Timestamp to) {
		super();
		this.sourceKey = sourceKey;
		this.dwhKey = dwhKey;
		this.modifiedDate = modifiedDate;
		this.from = from;
		this.to = to;
	}

}
