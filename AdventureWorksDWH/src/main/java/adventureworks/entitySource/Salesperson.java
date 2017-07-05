package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the salesperson database table.
 * 
 */
@Entity
@Table(name="salesperson")
@NamedQuery(name="Salesperson.findAll", query="SELECT s FROM Salesperson s")
public class Salesperson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int salesPersonID;

	@Column(nullable=false)
	private double bonus;

	@Column(nullable=false)
	private double commissionPct;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private double salesLastYear;

	private double salesQuota;

	@Column(nullable=false)
	private double salesYTD;

	private int territoryID;

	public Salesperson() {
	}

	public int getSalesPersonID() {
		return this.salesPersonID;
	}

	public void setSalesPersonID(int salesPersonID) {
		this.salesPersonID = salesPersonID;
	}

	public double getBonus() {
		return this.bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public double getSalesQuota() {
		return this.salesQuota;
	}

	public void setSalesQuota(double salesQuota) {
		this.salesQuota = salesQuota;
	}

	public double getSalesYTD() {
		return this.salesYTD;
	}

	public void setSalesYTD(double salesYTD) {
		this.salesYTD = salesYTD;
	}

	public int getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(int territoryID) {
		this.territoryID = territoryID;
	}

}