package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="INDIVIDUAL")
public class Individual {
	
	@Id
	private long individualId;
	
	@Column(nullable=false)
	private Timestamp modfiedDate;
	private String firstName;
	private String lastName;
	private String dateFirstPurchase;

	private String birthDate;
	
	private String maritalStatus;
	
	private  double yearlyIncome;	

	private String gender;

	private  int totalChildren;

	private int numberChildrenAtHome;

	private String education;

	private String occupation;
	
	private boolean homeOwnerFlag;
	
	private int numberCarsOwned;

	private String commuteDistance;

	public long getIndividualId() {
		return individualId;
	}

	public void setIndividualId(long individualId) {
		this.individualId = individualId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateFirstPurchase() {
		return dateFirstPurchase;
	}

	public void setDateFirstPurchase(String dateFirstPurchase) {
		this.dateFirstPurchase = dateFirstPurchase;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public double getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(double yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getTotalChildren() {
		return totalChildren;
	}

	public void setTotalChildren(int totalChildren) {
		this.totalChildren = totalChildren;
	}

	public int getNumberChildrenAtHome() {
		return numberChildrenAtHome;
	}

	public void setNumberChildrenAtHome(int numberChildrenAtHome) {
		this.numberChildrenAtHome = numberChildrenAtHome;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public boolean isHomeOwnerFlag() {
		return homeOwnerFlag;
	}

	public void setHomeOwnerFlag(boolean homeOwnerFlag) {
		this.homeOwnerFlag = homeOwnerFlag;
	}

	public int getNumberCarsOwned() {
		return numberCarsOwned;
	}

	public void setNumberCarsOwned(int numberCarsOwned) {
		this.numberCarsOwned = numberCarsOwned;
	}

	public String getCommuteDistance() {
		return commuteDistance;
	}

	public void setCommuteDistance(String commuteDistance) {
		this.commuteDistance = commuteDistance;
	}

	public Individual() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Individual(String firstName, String lastName, String dateFirstPurchase, String birthDate,
			String maritalStatus, double yearlyIncome, String gender, int totalChildren, int numberChildrenAtHome,
			String education, String occupation, boolean homeOwnerFlag, int numberCarsOwned, String commuteDistance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateFirstPurchase = dateFirstPurchase;
		this.birthDate = birthDate;
		this.maritalStatus = maritalStatus;
		this.yearlyIncome = yearlyIncome;
		this.gender = gender;
		this.totalChildren = totalChildren;
		this.numberChildrenAtHome = numberChildrenAtHome;
		this.education = education;
		this.occupation = occupation;
		this.homeOwnerFlag = homeOwnerFlag;
		this.numberCarsOwned = numberCarsOwned;
		this.commuteDistance = commuteDistance;
	}

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}
	
	
	

}
