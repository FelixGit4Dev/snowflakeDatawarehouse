package adventureworks.entity.dimensions.place;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Territory {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long territoryId;
	// slowly changing dimensions
		@Column(name = "FROM_DATE", nullable = true)
		private Timestamp fromDate;
		@Column(name = "TO_DATE", nullable = true)
		private Timestamp toDate;
		
		@Column(name="TERRITORY_NAME", nullable=false, updatable=true)
		private String name;
		
		@Column (name="TERRITORY_GROUP")
		private String group;
		
		
}
