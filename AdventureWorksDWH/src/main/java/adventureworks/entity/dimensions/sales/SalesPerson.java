package adventureworks.entity.dimensions.sales;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALESPERSON")
public class SalesPerson implements Serializable{

	//slowly changing dimensions 
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Timestamp fromDate;
		private Timestamp toDate;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long salesPersonId;
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
		public long getSalesPersonId() {
			return salesPersonId;
		}
		public void setSalesPersonId(long salesPersonId) {
			this.salesPersonId = salesPersonId;
		}
	
		
}
