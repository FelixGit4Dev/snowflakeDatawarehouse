package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int employeeID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date birthDate;

	@Column(nullable=false)
	private int contactID;

	@Column(nullable=false)
	private byte currentFlag;

	@Column(nullable=false, length=1)
	private String gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date hireDate;

	@Column(nullable=false, length=256)
	private String loginID;

	private int managerID;

	@Column(nullable=false, length=1)
	private String maritalStatus;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=15)
	private String nationalIDNumber;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private byte salariedFlag;

	@Column(nullable=false)
	private short sickLeaveHours;

	@Column(nullable=false, length=50)
	private String title;

	@Column(nullable=false)
	private short vacationHours;

	public Employee() {
	}

	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getContactID() {
		return this.contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public byte getCurrentFlag() {
		return this.currentFlag;
	}

	public void setCurrentFlag(byte currentFlag) {
		this.currentFlag = currentFlag;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLoginID() {
		return this.loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public int getManagerID() {
		return this.managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getNationalIDNumber() {
		return this.nationalIDNumber;
	}

	public void setNationalIDNumber(String nationalIDNumber) {
		this.nationalIDNumber = nationalIDNumber;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public byte getSalariedFlag() {
		return this.salariedFlag;
	}

	public void setSalariedFlag(byte salariedFlag) {
		this.salariedFlag = salariedFlag;
	}

	public short getSickLeaveHours() {
		return this.sickLeaveHours;
	}

	public void setSickLeaveHours(short sickLeaveHours) {
		this.sickLeaveHours = sickLeaveHours;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public short getVacationHours() {
		return this.vacationHours;
	}

	public void setVacationHours(short vacationHours) {
		this.vacationHours = vacationHours;
	}

}