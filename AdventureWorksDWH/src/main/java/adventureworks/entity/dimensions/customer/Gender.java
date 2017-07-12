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
	private long genderId;
	
	private String genderCode; 
	@Column(nullable=false)
	private Timestamp modfiedDate;
	
	public long getGenderId() {
		return genderId;
	}

	public void setGenderId(long genderId) {
		this.genderId = genderId;
	}

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
	
	
}
