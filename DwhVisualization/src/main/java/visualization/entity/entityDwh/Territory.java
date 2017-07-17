package visualization.entity.entityDwh;

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
		@Column(nullable=false)
		private Timestamp modfiedDate;
		@Column(name="TERRITORY_NAME", nullable=false, updatable=true)
		private String name;
		
		@Column (name="TERRITORY_GROUP")
		private String group;

		public long getTerritoryId() {
			return territoryId;
		}

		public void setTerritoryId(long territoryId) {
			this.territoryId = territoryId;
		}

		public Timestamp getFromDate() {
			return fromDate;
		}

		public void setFromDate(Timestamp fromDate) {
			this.fromDate = fromDate;
		}

		public Timestamp getToDate() {
			return toDate;
		}

		public void setToDate(Timestamp toDate) {
			this.toDate = toDate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public Territory() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Territory(Timestamp fromDate, Timestamp toDate, String name, String group) {
			super();
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.name = name;
			this.group = group;
		}

		public Timestamp getModfiedDate() {
			return modfiedDate;
		}

		public void setModfiedDate(Timestamp modfiedDate) {
			this.modfiedDate = modfiedDate;
		}
		
		
}
