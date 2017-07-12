package adventureworks.entity.dimensions.product;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTLINE")
public class ProductLine {

	@Id
private String 	productLineCode;
private String productLineName;
@Column(nullable=false)
private Timestamp modfiedDate;
public Timestamp getModfiedDate() {
	return modfiedDate;
}
public void setModfiedDate(Timestamp modfiedDate) {
	this.modfiedDate = modfiedDate;
}
public String getProductLineCode() {
	return productLineCode;
}
public void setProductLineCode(String productLineCode) {
	this.productLineCode = productLineCode;
}
public String getProductLineName() {
	return productLineName;
}
public void setProductLineName(String productLineName) {
	this.productLineName = productLineName;
}
public ProductLine(String productLineCode, String productLineName) {
	super();
	this.productLineCode = productLineCode;
	this.productLineName = productLineName;
}
public ProductLine() {
	super();
	// TODO Auto-generated constructor stub
}

}
