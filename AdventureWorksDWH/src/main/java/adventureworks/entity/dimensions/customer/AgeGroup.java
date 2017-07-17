package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AGEGroup")
public class AgeGroup {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ageGroupId;
	private String name;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	
	public long getAgeGroupId() {
		return ageGroupId;
	}
	public void setAgeGroupId(long ageGroupId) {
		this.ageGroupId = ageGroupId;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AgeGroup(String name) {
		super();
		this.name = name;
	}
	public AgeGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Timestamp getModfiedDate() {
		return modfiedDate;
	}
	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}
	public AgeGroup(String name, Timestamp modfiedDate) {
		super();
		this.name = name;
		this.modfiedDate = modfiedDate;
	}
	
}
