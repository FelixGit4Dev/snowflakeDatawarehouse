package adventureworks.entity.dimensions.sales;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALESCHANNEL")
public class SalesChannel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//slowly changing dimensions 
		private Timestamp fromDate;
		private Timestamp toDate;
		private String channel;
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Id
		private long salesChannelId;
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
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public long getSalesChannelId() {
			return salesChannelId;
		}
		public void setSalesChannelId(long salesChannelId) {
			this.salesChannelId = salesChannelId;
		}
		public SalesChannel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public SalesChannel(Timestamp fromDate, Timestamp toDate, String channel) {
			super();
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.channel = channel;
		}
		
	
}
