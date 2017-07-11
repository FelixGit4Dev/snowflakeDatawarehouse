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
@Table(name="SUBCATEGORY")
public class Subcategory implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long subcategoryId;
		
		private long categoryId;
		
		public void setCategoryId(long categoryId) {
			this.categoryId = categoryId;
		}

		private String name; 

		public Subcategory() {
			super();
			// TODO Auto-generated constructor stub
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

		public long getSubcategoryId() {
			return subcategoryId;
		}

		public void setSubcategoryId(long subcategoryId) {
			this.subcategoryId = subcategoryId;
		}

		public long getCategoryId() {
			return categoryId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
		
		
}
