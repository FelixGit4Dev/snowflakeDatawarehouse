package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the specialofferproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Specialofferproduct.findAll", query="SELECT s FROM Specialofferproduct s")
public class Specialofferproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpecialofferproductPK id;

	private Timestamp modifiedDate;

	private byte[] rowguid;

	public Specialofferproduct() {
	}

	public SpecialofferproductPK getId() {
		return this.id;
	}

	public void setId(SpecialofferproductPK id) {
		this.id = id;
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

}