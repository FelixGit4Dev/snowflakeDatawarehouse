package adventureworks.Util.xml.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="IndividualSurvey")
public class IndividualSurvey {

	
	private String dateFirstPurchase;
	
	private String birthDate;
	
	private String maritalStatus;

	private  double yearlyIncome;	

	private String gender;

	private  int totalChildren;

	private int numberChildrenAtHome;

	private String education;

	private String occupation;

	private int homeOwnerFlag;

	private int numberCarsOwned;

	private String commuteDistance;
	
	
private String xmlns;
	
	@XmlElement(name="DateFirstPurchase")
	public String getDateFirstPurchase() {
		return dateFirstPurchase;
	}
	public void setDateFirstPurchase(String dateFirstPurchase) {
		this.dateFirstPurchase = dateFirstPurchase;
	}
	@XmlElement(name="BirthDate")
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	@XmlElement(name="MaritalStatus")
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	@XmlElement(name="YearlyIncome")
	public double getYearlyIncome() {
		return yearlyIncome;
	}
	public void setYearlyIncome(double yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}
	@XmlElement(name="Gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@XmlElement(name="TotalChildren")
	public int getTotalChildren() {
		return totalChildren;
	}
	public void setTotalChildren(int totalChildren) {
		this.totalChildren = totalChildren;
	}
	@XmlElement(name="NumberChildrenAtHome")
	public int getNumberChildrenAtHome() {
		return numberChildrenAtHome;
	}
	public void setNumberChildrenAtHome(int numberChildrenAtHome) {
		this.numberChildrenAtHome = numberChildrenAtHome;
	}
	@XmlElement(name="Education")
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@XmlElement(name="Occupation")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@XmlElement(name="HomeOwnerFlag")
	public int isHomeOwnerFlag() {
		return homeOwnerFlag;
	}
	public void setHomeOwnerFlag(int homeOwnerFlag) {
		this.homeOwnerFlag = homeOwnerFlag;
	}
	@XmlElement(name="NumberCarsOwned")
	public int getNumberCarsOwned() {
		return numberCarsOwned;
	}
	public void setNumberCarsOwned(int numberCarsOwned) {
		this.numberCarsOwned = numberCarsOwned;
	}
	@XmlElement(name="CommuteDistance")
	public String getCommuteDistance() {
		return commuteDistance;
	}
	public void setCommuteDistance(String commuteDistance) {
		this.commuteDistance = commuteDistance;
	}
	public IndividualSurvey() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IndividualSurvey(String dateFirstPurchase, String birthDate, String maritalStatus, double yearlyIncome,
			String gender, int totalChildren, int numberChildrenAtHome, String education, String occupation,
			int homeOwnerFlag, int numberCarsOwned, String commuteDistance) {
		super();
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
	@XmlAttribute
	public String getXmlns() {
		return xmlns;
	}
	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}
	
	
	
}
