package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="YEARLY_INCOME_GROUP")
public class YearlyIncomeGroup {

	@Id
	private String incomeGroupCode;
	
	private int lower;
	private int upper;
	public String getIncomeGroupCode() {
		return incomeGroupCode;
	}

	public void setIncomeGroupCode(String incomeGroupCode) {
		this.incomeGroupCode = incomeGroupCode;
	}

	@Column(nullable=false)
	private Timestamp modfiedDate;
	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

	private String groupName;


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public YearlyIncomeGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public YearlyIncomeGroup(String incomeGroupCode, Timestamp modfiedDate, String groupName) {
		super();
		this.incomeGroupCode = incomeGroupCode;
		this.modfiedDate = modfiedDate;
		this.groupName = groupName;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	public int getUpper() {
		return upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}

	
	
}
