package adventureworks.entity.dimensions.sales;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="SHIPPINGMETHOD")
public class ShippingMethod {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long shippingMethodId;
	
	@Column(name="METHOD_NAME", nullable=false)
	private String name;
	@Column(nullable=false)
	private Timestamp modfiedDate;
	//slowly changing dimensions 
	

		private Timestamp fromDate;
		public ShippingMethod(String name, Timestamp fromDate, Timestamp toDate) {
			super();
			this.name=name;
			this.fromDate = fromDate;
			this.toDate = toDate;
		}
		public ShippingMethod() {
			super();
			// TODO Auto-generated constructor stub
		}
		private Timestamp toDate;
		public long getShippingMethodId() {
			return shippingMethodId;
		}
		public void setShippingMethodId(long shippingMethodId) {
			this.shippingMethodId = shippingMethodId;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Timestamp getModfiedDate() {
			return modfiedDate;
		}
		public void setModfiedDate(Timestamp modfiedDate) {
			this.modfiedDate = modfiedDate;
		}
	
}
