package adventureworks.entity.dimensions.customer;

import java.sql.Timestamp;

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
		private long customerId;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long storeId;
	
}
