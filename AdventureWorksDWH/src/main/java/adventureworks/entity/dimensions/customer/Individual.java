package adventureworks.entity.dimensions.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INDIVIDUAL")
public class Individual {
	
	
	private long customerId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long individualId;

}
