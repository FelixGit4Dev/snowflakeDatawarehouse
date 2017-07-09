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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long salesReasonTypeId;
	
	//slowly changing dimensions 
		private Timestamp fromDate;
		private Timestamp toDate;
	
}
