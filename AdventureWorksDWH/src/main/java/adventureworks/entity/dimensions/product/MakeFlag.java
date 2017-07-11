package adventureworks.entity.dimensions.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="MAKE_FLAG")
public class MakeFlag implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long makeId;
	
private String makeType;

public long getMakeId() {
	return makeId;
}

public void setMakeId(long makeId) {
	this.makeId = makeId;
}

public String getMakeType() {
	return makeType;
}

public void setMakeType(String makeType) {
	this.makeType = makeType;
}

public MakeFlag() {
	super();
	// TODO Auto-generated constructor stub
}

public MakeFlag(String makeType) {
	super();
	this.makeType = makeType;
}
	


}
