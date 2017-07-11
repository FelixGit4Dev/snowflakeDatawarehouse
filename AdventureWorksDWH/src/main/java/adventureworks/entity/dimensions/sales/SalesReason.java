package adventureworks.entity.dimensions.sales;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 @Entity
 @Table(name="SalesReason")
public class SalesReason implements Serializable {

	//slowly changing dimensions 
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long salesReasonId;
	 
		private Timestamp fromDate;
		private Timestamp toDate;
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getSalesReasonId() {
			return salesReasonId;
		}
		public void setSalesReasonId(long salesReasonId) {
			this.salesReasonId = salesReasonId;
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
