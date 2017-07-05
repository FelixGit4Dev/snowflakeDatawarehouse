package adventureworks.entity.dimensions.time;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WEEK")
public class Week {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long weekId;
private long yearId;
private String week;
	
	
}
