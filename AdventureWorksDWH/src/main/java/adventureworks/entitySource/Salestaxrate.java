package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the salestaxrate database table.
 * 
 */
@Entity
@NamedQuery(name="Salestaxrate.findAll", query="SELECT s FROM Salestaxrate s")
public class Salestaxrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int salesTaxRateID;

	private Timestamp modifiedDate;

	private String name;

	private byte[] rowguid;

	private int stateProvinceID;

	private double taxRate;

	private byte taxType;

	public Salestaxrate() {
	}

	public int getSalesTaxRateID() {
		return this.salesTaxRateID;
	}

	public void setSalesTaxRateID(int salesTaxRateID) {
		this.salesTaxRateID = salesTaxRateID;
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

	public int getStateProvinceID() {
		return this.stateProvinceID;
	}

	public void setStateProvinceID(int stateProvinceID) {
		this.stateProvinceID = stateProvinceID;
	}

	public double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public byte getTaxType() {
		return this.taxType;
	}

	public void setTaxType(byte taxType) {
		this.taxType = taxType;
	}

}