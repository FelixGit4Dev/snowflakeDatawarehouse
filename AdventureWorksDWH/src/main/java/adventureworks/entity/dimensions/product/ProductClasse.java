package adventureworks.entity.dimensions.product;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class ProductClasse {

	
	@Id

private String 	prodcutClassCode;
	
	private String className;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	public String getProdcutClassCode() {
		return prodcutClassCode;
	}

	public void setProdcutClassCode(String prodcutClassCode) {
		this.prodcutClassCode = prodcutClassCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ProductClasse(String prodcutClassCode, String className) {
		super();
		this.prodcutClassCode = prodcutClassCode;
		this.className = className;
	}

	public ProductClasse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}
	
}
