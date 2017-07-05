package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the individual database table.
 * 
 */
@Entity
@Table(name="individual")
@NamedQuery(name="Individual.findAll", query="SELECT i FROM Individual i")
public class Individual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int customerID;

	@Column(nullable=false)
	private int contactID;

	@Lob
	private String demographics;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	public Individual() {
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getContactID() {
		return this.contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public String getDemographics() {
		return this.demographics;
	}

	public void setDemographics(String demographics) {
		this.demographics = demographics;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}