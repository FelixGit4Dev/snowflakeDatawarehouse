package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int addressID;

	@Column(nullable=false, length=60)
	private String addressLine1;

	@Column(length=60)
	private String addressLine2;

	@Column(nullable=false, length=30)
	private String city;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=15)
	private String postalCode;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private int stateProvinceID;

	public Address() {
	}

	public int getAddressID() {
		return this.addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

}