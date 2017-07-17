package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HOMEOWNER")
public class HomeOwner {

	@Id
private String homeOwnerCode;
	@Column(nullable=false)
	private Timestamp modifiedDate;






public HomeOwner() {
	super();
	// TODO Auto-generated constructor stub
}

public HomeOwner(String homeOwnerCode,  Timestamp modifiedDate) {
	super();
	this.homeOwnerCode = homeOwnerCode;

	this.modifiedDate=modifiedDate;
}

public Timestamp getModfiedDate() {
	return modifiedDate;
}

public void setModfiedDate(Timestamp modfiedDate) {
	this.modifiedDate = modfiedDate;
}


}
