package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the productcategory database table.
 * 
 */
@Entity
@Table(name="productcategory")
@NamedQuery(name="Productcategory.findAll", query="SELECT p FROM Productcategory p")
public class Productcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int productCategoryID;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private byte[] rowguid;

	public Productcategory() {
	}

	public int getProductCategoryID() {
		return this.productCategoryID;
	}

	public void setProductCategoryID(int productCategoryID) {
		this.productCategoryID = productCategoryID;
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

}