package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the countryregioncurrency database table.
 * 
 */
@Embeddable
public class CountryregioncurrencyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=3)
	private String countryRegionCode;

	@Column(unique=true, nullable=false, length=3)
	private String currencyCode;

	public CountryregioncurrencyPK() {
	}
	public String getCountryRegionCode() {
		return this.countryRegionCode;
	}
	public void setCountryRegionCode(String countryRegionCode) {
		this.countryRegionCode = countryRegionCode;
	}
	public String getCurrencyCode() {
		return this.currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CountryregioncurrencyPK)) {
			return false;
		}
		CountryregioncurrencyPK castOther = (CountryregioncurrencyPK)other;
		return 
			this.countryRegionCode.equals(castOther.countryRegionCode)
			&& this.currencyCode.equals(castOther.currencyCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.countryRegionCode.hashCode();
		hash = hash * prime + this.currencyCode.hashCode();
		
		return hash;
	}
}