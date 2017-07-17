package visualization.entity.entityDwh;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
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
	 private long salesReasonTypeId;
		private Timestamp fromDate;
		private Timestamp toDate;
		
		@Column(nullable=false)
		private Timestamp modfiedDate;
		public Timestamp getModfiedDate() {
			return modfiedDate;
		}
		public void setModfiedDate(Timestamp modfiedDate) {
			this.modfiedDate = modfiedDate;
		}
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
		public long getSalesReasonTypeId() {
			return salesReasonTypeId;
		}
		public void setSalesReasonTypeId(long salesReasonTypeId) {
			this.salesReasonTypeId = salesReasonTypeId;
		}
		public SalesReason(long salesReasonTypeId, Timestamp fromDate, Timestamp toDate, 
				String name) {
			super();
			this.salesReasonTypeId = salesReasonTypeId;
			this.fromDate = fromDate;
			this.toDate = toDate;
			
			this.name = name;
		}
		public SalesReason() {
			super();
			// TODO Auto-generated constructor stub
		} 
	
}
