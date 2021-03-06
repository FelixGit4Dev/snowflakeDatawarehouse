package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="GENDER")
public class Gender {
 
	@Id
	private String genderCode; 
	@Column(nullable=false)
	private Timestamp modfiedDate;
	
	

	private String gender;

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

	public Gender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gender(String genderCode, Timestamp modfiedDate, String gender) {
		super();
		this.genderCode = genderCode;
		this.modfiedDate = modfiedDate;
		this.gender = gender;
	}
	
	
	
}
