package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the unitmeasure database table.
 * 
 */
@Entity
@Table(name="unitmeasure")
@NamedQuery(name="Unitmeasure.findAll", query="SELECT u FROM Unitmeasure u")
public class Unitmeasure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=3)
	private String unitMeasureCode;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	public Unitmeasure() {
	}

	public String getUnitMeasureCode() {
		return this.unitMeasureCode;
	}

	public void setUnitMeasureCode(String unitMeasureCode) {
		this.unitMeasureCode = unitMeasureCode;
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