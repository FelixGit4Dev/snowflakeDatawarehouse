package adventureworks.entity;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="ETL_META_INFORMATION")
public class EtlMetaInformation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;	
	
@Column	
private Timestamp etlJobRun_Date;

@Column
private long transferredFacts;

@Lob
 @Column
private String changedDimensions;

 
 @Column
 private String duration;
 
@Column
private Blob log;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public Timestamp getEtlJobRun_Date() {
	return etlJobRun_Date;
}

public void setEtlJobRun_Date(Timestamp etlJobRun_Date) {
	this.etlJobRun_Date = etlJobRun_Date;
}

public long getTransferredFacts() {
	return transferredFacts;
}

public void setTransferredFacts(long transferredFacts) {
	this.transferredFacts = transferredFacts;
}

public String getChangedDimensions() {
	return changedDimensions;
}

public void setChangedDimensions(String changedDimensions) {
	this.changedDimensions = changedDimensions;
}

public Blob getLog() {
	return log;
}

public void setLog(Blob log) {
	this.log = log;
}

public String getDuration() {
	return duration;
}

public void setDuration(String duration) {
	this.duration = duration;
}

public EtlMetaInformation() {
	super();
	// TODO Auto-generated constructor stub
}

public EtlMetaInformation(long id, Timestamp etlJobRun_Date, long transferredFacts, String changedDimensions,
		Blob log) {
	super();
	this.id = id;
	this.etlJobRun_Date = etlJobRun_Date;
	this.transferredFacts = transferredFacts;
	this.changedDimensions = changedDimensions;
	this.log = log;
}



	
}
