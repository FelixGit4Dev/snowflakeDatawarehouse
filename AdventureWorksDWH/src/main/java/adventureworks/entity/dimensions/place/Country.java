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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COUNTRY_ID", nullable=false, unique=true)
	private long countryId;
	@Column(name="NAME", nullable=false)
	private String name;
	
	// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long cityId) {
		this.countryId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
