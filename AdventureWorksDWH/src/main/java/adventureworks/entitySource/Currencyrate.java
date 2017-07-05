package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the currencyrate database table.
 * 
 */
@Entity
@Table(name="currencyrate")
@NamedQuery(name="Currencyrate.findAll", query="SELECT c FROM Currencyrate c")
public class Currencyrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int currencyRateID;

	@Column(nullable=false)
	private double averageRate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date currencyRateDate;

	@Column(nullable=false)
	private double endOfDayRate;

	@Column(nullable=false, length=3)
	private String fromCurrencyCode;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=3)
	private String toCurrencyCode;

	public Currencyrate() {
	}

	public int getCurrencyRateID() {
		return this.currencyRateID;
	}

	public void setCurrencyRateID(int currencyRateID) {
		this.currencyRateID = currencyRateID;
	}

	public double getAverageRate() {
		return this.averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public Date getCurrencyRateDate() {
		return this.currencyRateDate;
	}

	public void setCurrencyRateDate(Date currencyRateDate) {
		this.currencyRateDate = currencyRateDate;
	}

	public double getEndOfDayRate() {
		return this.endOfDayRate;
	}

	public void setEndOfDayRate(double endOfDayRate) {
		this.endOfDayRate = endOfDayRate;
	}

	public String getFromCurrencyCode() {
		return this.fromCurrencyCode;
	}

	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getToCurrencyCode() {
		return this.toCurrencyCode;
	}

	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}

}