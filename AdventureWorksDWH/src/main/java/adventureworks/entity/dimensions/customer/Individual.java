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

	private long ageGroupId;
	
	
	
	private  String yearlyIncomeGroupCode;	

	private String gender;

	private  int totalChildren;

	private int numberChildrenAtHome;

	
	private String homeOwnerFlag;
	
	private int numberCarsOwned;


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



	public String getYearlyIncome() {
		return yearlyIncomeGroupCode;
	}

	public void setYearlyIncome(String yearlyIncome) {
		this.yearlyIncomeGroupCode = yearlyIncome;
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



	public String isHomeOwnerFlag() {
		return homeOwnerFlag;
	}

	public void setHomeOwnerFlag(String homeOwnerFlag) {
		this.homeOwnerFlag = homeOwnerFlag;
	}

	public int getNumberCarsOwned() {
		return numberCarsOwned;
	}

	public void setNumberCarsOwned(int numberCarsOwned) {
		this.numberCarsOwned = numberCarsOwned;
	}

	

	public Individual() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Individual(String firstName, String lastName, String dateFirstPurchase, String birthDate,
			String yearlyIncome, String gender, int totalChildren, int numberChildrenAtHome,
			 String homeOwnerFlag, int numberCarsOwned, String commuteDistance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateFirstPurchase = dateFirstPurchase;
	
		this.yearlyIncomeGroupCode = yearlyIncome;
		this.gender = gender;
		this.totalChildren = totalChildren;
		this.numberChildrenAtHome = numberChildrenAtHome;
		
		this.homeOwnerFlag = homeOwnerFlag;
		this.numberCarsOwned = numberCarsOwned;

	}

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

	public long getAgeGroupId() {
		return ageGroupId;
	}

	public void setAgeGroupId(long ageGroupId) {
		this.ageGroupId = ageGroupId;
	}
	
	
	

}
