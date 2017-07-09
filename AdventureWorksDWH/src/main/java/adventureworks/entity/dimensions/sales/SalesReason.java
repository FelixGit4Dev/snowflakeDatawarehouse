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
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long salesReasonId;
	 
		private Timestamp fromDate;
		private Timestamp toDate;
	
}
