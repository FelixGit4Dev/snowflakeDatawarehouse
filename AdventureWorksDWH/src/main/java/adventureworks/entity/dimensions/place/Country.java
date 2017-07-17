package adventureworks.entity.dimensions.place;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
public class Country implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="COUNTRY_ID", nullable=false, unique=true)
	private String countryCode;
	@Column(name="NAME", nullable=false)
	private String name;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String cityId) {
		this.countryCode = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

	public Country(String countryCode,String name, Timestamp modfiedDate, Timestamp fromDate, Timestamp toDate) {
		super();
		this.countryCode=countryCode;
		this.name = name;
		this.modfiedDate = modfiedDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

}
