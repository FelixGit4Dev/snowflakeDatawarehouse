package adventureworks.entity.dimensions.time;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MONTH")
public class Month {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long monthId;	
	
private long yearId;	
private String month;
}
