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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long incomeId;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

	private String groupName;

	public long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}

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

	public YearlyIncomeGroup( String groupName) {
		super();
	
		this.groupName = groupName;
	}
	
}
