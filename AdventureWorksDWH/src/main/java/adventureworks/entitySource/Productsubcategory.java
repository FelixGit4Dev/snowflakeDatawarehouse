package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the productsubcategory database table.
 * 
 */
@Entity
@Table(name="productsubcategory")
@NamedQuery(name="Productsubcategory.findAll", query="SELECT p FROM Productsubcategory p")
public class Productsubcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int productSubcategoryID;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private int productCategoryID;

	@Column(nullable=false)
	private byte[] rowguid;

	public Productsubcategory() {
	}

	public int getProductSubcategoryID() {
		return this.productSubcategoryID;
	}

	public void setProductSubcategoryID(int productSubcategoryID) {
		this.productSubcategoryID = productSubcategoryID;
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

	public int getProductCategoryID() {
		return this.productCategoryID;
	}

	public void setProductCategoryID(int productCategoryID) {
		this.productCategoryID = productCategoryID;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

}