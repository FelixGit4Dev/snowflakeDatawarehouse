package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the countryregioncurrency database table.
 * 
 */
@Entity
@Table(name="countryregioncurrency")
@NamedQuery(name="Countryregioncurrency.findAll", query="SELECT c FROM Countryregioncurrency c")
public class Countryregioncurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountryregioncurrencyPK id;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	public Countryregioncurrency() {
	}

	public CountryregioncurrencyPK getId() {
		return this.id;
	}

	public void setId(CountryregioncurrencyPK id) {
		this.id = id;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}