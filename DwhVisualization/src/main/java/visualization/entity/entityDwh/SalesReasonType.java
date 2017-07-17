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
		@Column(nullable=false)
		private Timestamp modfiedDate;
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
		public SalesReasonType(Timestamp fromDate, Timestamp toDate, String typeName) {
			super();
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.typeName = typeName;
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
		public Timestamp getModfiedDate() {
			return modfiedDate;
		}
		public void setModfiedDate(Timestamp modfiedDate) {
			this.modfiedDate = modfiedDate;
		}
		public SalesReasonType() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	
}
