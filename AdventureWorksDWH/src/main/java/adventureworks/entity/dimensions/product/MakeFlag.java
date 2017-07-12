package adventureworks.entity.dimensions.product;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
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
	private String makeTypeCode;
private String makeType;

@Column(nullable=false)
private Timestamp modfiedDate;

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

public MakeFlag(String makeCode, String makeType) {
	super();
	this.makeTypeCode= makeCode;
	this.makeType = makeType;
}

public String getMakeTypeCode() {
	return makeTypeCode;
}

public void setMakeTypeCode(String makeTypeCode) {
	this.makeTypeCode = makeTypeCode;
}

public Timestamp getModfiedDate() {
	return modfiedDate;
}

public void setModfiedDate(Timestamp modfiedDate) {
	this.modfiedDate = modfiedDate;
}
	


}
