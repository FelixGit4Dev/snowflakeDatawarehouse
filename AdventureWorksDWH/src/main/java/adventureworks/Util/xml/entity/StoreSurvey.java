package adventureworks.Util.xml.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="StoreSurvey")
public class StoreSurvey {

	

	private String businessType;
	private int yearOpened;
	private String specialty;
	private int squareFeet;
	private int numberEmployees;
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public int getYearOpened() {
		return yearOpened;
	}
	public void setYearOpened(int yearOpened) {
		this.yearOpened = yearOpened;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public int getSquareFeet() {
		return squareFeet;
	}
	public void setSquareFeet(int squareFeet) {
		this.squareFeet = squareFeet;
	}
	public int getNumberEmployees() {
		return numberEmployees;
	}
	public void setNumberEmployees(int numberEmployees) {
		this.numberEmployees = numberEmployees;
	}
	public StoreSurvey() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
