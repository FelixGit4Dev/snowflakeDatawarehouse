package adventureworks.entity.dimensions.time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DAY")
public class Day {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long dayId;
	private long weekId;
	private long monthId;
	
	
	private String day;

}
