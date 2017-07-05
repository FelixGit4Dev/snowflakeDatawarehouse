package adventureworks.entity.dimensions.time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="YEAR")
public class Year {
	
@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long yearId;
	private String year;
	
}
