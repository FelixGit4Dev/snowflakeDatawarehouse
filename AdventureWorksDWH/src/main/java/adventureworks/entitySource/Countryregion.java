package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the countryregion database table.
 * 
 */
@Entity
@Table(name="countryregion")
@NamedQuery(name="Countryregion.findAll", query="SELECT c FROM Countryregion c")
public class Countryregion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=3)
	private String countryRegionCode;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	public Countryregion() {
	}

	public String getCountryRegionCode() {
		return this.countryRegionCode;
	}

	public void setCountryRegionCode(String countryRegionCode) {
		this.countryRegionCode = countryRegionCode;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}