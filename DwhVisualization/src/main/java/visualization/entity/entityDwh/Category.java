package visualization.entity.entityDwh;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// slowly changing dimensions
	@Column(name = "FROM_DATE", nullable = true)
	private Timestamp fromDate;
	@Column(name = "TO_DATE", nullable = true)
	private Timestamp toDate;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;
	@Column(name = "CATEGORY_NAME", nullable = false, updatable = false)
	private String name;

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Timestamp fromDate, Timestamp toDate, String name) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.name = name;
	}

	public Timestamp getModfiedDate() {
		return modfiedDate;
	}

	public void setModfiedDate(Timestamp modfiedDate) {
		this.modfiedDate = modfiedDate;
	}

}
