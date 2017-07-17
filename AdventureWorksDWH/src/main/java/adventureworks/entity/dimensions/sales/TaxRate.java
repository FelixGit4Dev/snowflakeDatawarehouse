package adventureworks.entity.dimensions.sales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TaxRate {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long taxRateId;
	
}
