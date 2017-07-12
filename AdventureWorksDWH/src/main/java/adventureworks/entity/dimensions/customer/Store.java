package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="STORE")
public class Store {

	//slowly changing dimensions 
		private Timestamp fromDate;
		private Timestamp toDate;
		@Column(nullable=false)
		private Timestamp modfiedDate;
		
		@Id		
		private long storeId;
		
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

		public long getStoreId() {
			return storeId;
		}

		public void setStoreId(long storeId) {
			this.storeId = storeId;
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
