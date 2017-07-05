package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the salesterritory database table.
 * 
 */
@Entity
@Table(name="salesterritory")
@NamedQuery(name="Salesterritory.findAll", query="SELECT s FROM Salesterritory s")
public class Salesterritory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int territoryID;

	@Column(nullable=false)
	private double costLastYear;

	@Column(nullable=false)
	private double costYTD;

	@Column(nullable=false, length=3)
	private String countryRegionCode;

	@Column(nullable=false, length=50)
	private String group;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private double salesLastYear;

	@Column(nullable=false)
	private double salesYTD;

	public Salesterritory() {
	}

	public int getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(int territoryID) {
		this.territoryID = territoryID;
	}

	public double getCostLastYear() {
		return this.costLastYear;
	}

	public void setCostLastYear(double costLastYear) {
		this.costLastYear = costLastYear;
	}

	public double getCostYTD() {
		return this.costYTD;
	}

	public void setCostYTD(double costYTD) {
		this.costYTD = costYTD;
	}

	public String getCountryRegionCode() {
		return this.countryRegionCode;
	}

	public void setCountryRegionCode(String countryRegionCode) {
		this.countryRegionCode = countryRegionCode;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public double getSalesLastYear() {
		return this.salesLastYear;
	}

	public void setSalesLastYear(double salesLastYear) {
		this.salesLastYear = salesLastYear;
	}

	public double getSalesYTD() {
		return this.salesYTD;
	}

	public void setSalesYTD(double salesYTD) {
		this.salesYTD = salesYTD;
	}

}