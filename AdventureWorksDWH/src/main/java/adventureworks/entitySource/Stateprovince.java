package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the stateprovince database table.
 * 
 */
@Entity
@Table(name="stateprovince")
@NamedQuery(name="Stateprovince.findAll", query="SELECT s FROM Stateprovince s")
public class Stateprovince implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int stateProvinceID;

	@Column(nullable=false, length=3)
	private String countryRegionCode;

	@Column(nullable=false)
	private byte isOnlyStateProvinceFlag;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false, length=3)
	private String stateProvinceCode;

	@Column(nullable=false)
	private int territoryID;

	public Stateprovince() {
	}

	public int getStateProvinceID() {
		return this.stateProvinceID;
	}

	public void setStateProvinceID(int stateProvinceID) {
		this.stateProvinceID = stateProvinceID;
	}

	public String getCountryRegionCode() {
		return this.countryRegionCode;
	}

	public void setCountryRegionCode(String countryRegionCode) {
		this.countryRegionCode = countryRegionCode;
	}

	public byte getIsOnlyStateProvinceFlag() {
		return this.isOnlyStateProvinceFlag;
	}

	public void setIsOnlyStateProvinceFlag(byte isOnlyStateProvinceFlag) {
		this.isOnlyStateProvinceFlag = isOnlyStateProvinceFlag;
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

	public String getStateProvinceCode() {
		return this.stateProvinceCode;
	}

	public void setStateProvinceCode(String stateProvinceCode) {
		this.stateProvinceCode = stateProvinceCode;
	}

	public int getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(int territoryID) {
		this.territoryID = territoryID;
	}

}