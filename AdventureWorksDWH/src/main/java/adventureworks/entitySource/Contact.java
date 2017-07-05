package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name="contact")
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int contactID;

	@Lob
	private String additionalContactInfo;

	@Column(length=50)
	private String emailAddress;

	@Column(nullable=false)
	private int emailPromotion;

	@Column(nullable=false, length=50)
	private String firstName;

	@Column(nullable=false, length=50)
	private String lastName;

	@Column(length=50)
	private String middleName;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false)
	private byte nameStyle;

	@Column(nullable=false, length=40)
	private String passwordHash;

	@Column(nullable=false, length=10)
	private String passwordSalt;

	@Column(length=25)
	private String phone;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(length=10)
	private String suffix;

	@Column(length=8)
	private String title;

	public Contact() {
	}

	public int getContactID() {
		return this.contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public String getAdditionalContactInfo() {
		return this.additionalContactInfo;
	}

	public void setAdditionalContactInfo(String additionalContactInfo) {
		this.additionalContactInfo = additionalContactInfo;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getEmailPromotion() {
		return this.emailPromotion;
	}

	public void setEmailPromotion(int emailPromotion) {
		this.emailPromotion = emailPromotion;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public byte getNameStyle() {
		return this.nameStyle;
	}

	public void setNameStyle(byte nameStyle) {
		this.nameStyle = nameStyle;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}