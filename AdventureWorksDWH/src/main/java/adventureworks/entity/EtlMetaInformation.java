package adventureworks.entity;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

 @Column
private String changedDimensions;

@Column
private Blob log;
	
}
