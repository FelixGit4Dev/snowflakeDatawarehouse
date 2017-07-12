package adventureworks.entity.dimensions.product;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProductStyle")
public class ProductStyle {
@Id
private String styleCode;
@Column(nullable=false)
private Timestamp modfiedDate;
public Timestamp getModfiedDate() {
	return modfiedDate;
}
public void setModfiedDate(Timestamp modfiedDate) {
	this.modfiedDate = modfiedDate;
}
private String StyleName;
public String getStyleCode() {
	return styleCode;
}
public void setStyleCode(String styleCode) {
	this.styleCode = styleCode;
}
public String getStyleName() {
	return StyleName;
}
public void setStyleName(String styleName) {
	StyleName = styleName;
}
public ProductStyle(String styleCode, String styleName) {
	super();
	this.styleCode = styleCode;
	StyleName = styleName;
}
public ProductStyle() {
	super();
	// TODO Auto-generated constructor stub
}
	
}
