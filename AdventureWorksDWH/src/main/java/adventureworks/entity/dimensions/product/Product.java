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
@Table
public class Product implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productId;
	
	private long subcategoryId;
	
	private String name;
	
	private boolean makeFlag;
	
	private String productLine;
	
	private String klasse;
	
	private String style;
	
	// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
	
	public Product(long subcategoryId, String name, boolean makeFlag, String productLine, String klasse, String style,
			Timestamp fromDate, Timestamp toDate) {
		super();
		this.subcategoryId = subcategoryId;
		this.name = name;
		this.makeFlag = makeFlag;
		this.productLine = productLine;
		this.klasse = klasse;
		this.style = style;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

		public long getProductId() {
			return productId;
		}
		public void setProductId(long productId) {
			this.productId = productId;
		}
		public long getSubcategoryId() {
			return subcategoryId;
		}
		public void setSubcategoryId(long subcategoryId) {
			this.subcategoryId = subcategoryId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isMakeFlag() {
			return makeFlag;
		}
		public void setMakeFlag(boolean makeFlag) {
			this.makeFlag = makeFlag;
		}
		public String getProductLine() {
			return productLine;
		}
		public void setProductLine(String productLine) {
			this.productLine = productLine;
		}
		public String getKlasse() {
			return klasse;
		}
		public void setKlasse(String klasse) {
			this.klasse = klasse;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
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
		
		
		
		
		
}
