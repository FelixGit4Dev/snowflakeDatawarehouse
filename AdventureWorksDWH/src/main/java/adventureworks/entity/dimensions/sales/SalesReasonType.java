package adventureworks.entity.dimensions.sales;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALESREASONTYPE")
public class SalesReasonType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long salesReasonTypeId;
	
	//slowly changing dimensions 
		private Timestamp fromDate;
		private Timestamp toDate;
		private String typeName;
		public long getSalesReasonTypeId() {
			return salesReasonTypeId;
		}
		public void setSalesReasonTypeId(long salesReasonTypeId) {
			this.salesReasonTypeId = salesReasonTypeId;
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
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
	
}
