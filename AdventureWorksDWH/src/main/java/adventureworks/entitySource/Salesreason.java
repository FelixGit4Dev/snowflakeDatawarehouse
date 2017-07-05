package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the salesreason database table.
 * 
 */
@Entity
@Table(name="salesreason")
@NamedQuery(name="Salesreason.findAll", query="SELECT s FROM Salesreason s")
public class Salesreason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int salesReasonID;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=50)
	private String reasonType;

	public Salesreason() {
	}

	public int getSalesReasonID() {
		return this.salesReasonID;
	}

	public void setSalesReasonID(int salesReasonID) {
		this.salesReasonID = salesReasonID;
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

	public String getReasonType() {
		return this.reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

}