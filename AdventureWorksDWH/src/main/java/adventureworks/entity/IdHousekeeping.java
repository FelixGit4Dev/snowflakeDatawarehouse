package adventureworks.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ID_HOUSEKEEPING")
public class IdHousekeeping {

@Id	
@GeneratedValue(strategy=GenerationType.IDENTITY)	
private long id;	

private String tableName;

private long lastId;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getTableName() {
	return tableName;
}

public void setTableName(String tableName) {
	this.tableName = tableName;
}

public long getLastId() {
	return lastId;
}

public void setLastId(long lastId) {
	this.lastId = lastId;
}

public IdHousekeeping() {
	super();
	// TODO Auto-generated constructor stub
}

public IdHousekeeping(String tableName, long lastId) {
	super();
	this.tableName = tableName;
	this.lastId = lastId;
}




}
