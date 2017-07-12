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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long homeOwnerId;
	@Column(nullable=false)
	private Timestamp modfiedDate;
private String name;

public long getHomeOwnerId() {
	return homeOwnerId;
}

public void setHomeOwnerId(long homeOwnerId) {
	this.homeOwnerId = homeOwnerId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public HomeOwner() {
	super();
	// TODO Auto-generated constructor stub
}

public HomeOwner(long homeOwnerId, String name) {
	super();
	this.homeOwnerId = homeOwnerId;
	this.name = name;
}

public Timestamp getModfiedDate() {
	return modfiedDate;
}

public void setModfiedDate(Timestamp modfiedDate) {
	this.modfiedDate = modfiedDate;
}


}
