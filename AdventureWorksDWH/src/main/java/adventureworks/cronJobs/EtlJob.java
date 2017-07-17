package adventureworks.cronJobs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.TimeUtil;
import adventureworks.entity.EtlMetaInformation;
import adventureworks.transformations.CustomerTransformation;
import adventureworks.transformations.PlaceTransformation;
import adventureworks.transformations.ProductTransformation;
import adventureworks.transformations.SalesFactTransformation;
import adventureworks.transformations.SalesTransformations;
import adventureworks.transformations.TimeDimensionTransformations;
@Named
@ApplicationScoped
public class EtlJob implements Job{
	
@Inject
private Logger log;
	
	@Inject
	private DwhTargetAcess targetDao;
	@Inject	
private 	CustomerTransformation customerTransformation;
	@Inject
private 	PlaceTransformation placeTransformation;
	@Inject
private 	ProductTransformation productTransformation;
	@Inject
private	SalesFactTransformation factTransformation;
	@Inject
private	SalesTransformations salesTransformations;
	@Inject
private	TimeDimensionTransformations timeDimensionTransformations;
	
	
	private boolean isRunning=false;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	if(isRunning){
	log.info("an  ETL-Job is already rnning, no new job will be started");	
	}
	else{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	isRunning=true;	
	Date start= new Date();
		log.info("Beginning ETL Job: " + new Date());
	EtlMetaInformation meta = new EtlMetaInformation();
	Timestamp runTime= new  Timestamp(System.currentTimeMillis());
	meta.setEtlJobRun_Date(runTime);
	log.info("Started synchronising Dimensions: " + new Date());
	this.synchronizeDimensions();
	log.info("Finished synchronising Dimensions: " + new Date());
	log.info("Started transferring OperationalData to Fact-Table: " + new Date());
	//this.transformOperationalData();
	log.info("Finished transferring OperationalData to Fact-Table: " + new Date());
	Date end = new  Date();
	long duration= end.getTime()-start.getTime();
	meta.setDuration(TimeUtil.formatDuration(duration));
	this.targetDao.persistObject(meta);
		
	log.info("Finished ETL Job: " + new Date());
	isRunning=false;
	}	
	
		
	}
	
	
	
	
	
	public void synchronizeDimensions(){

	if(targetDao.initialImport()){
log.info("	Started initializing Time Dimension: " + new Date());
	timeDimensionTransformations.initDimension();
	log.info("	Finished initializing Time Dimension: " + new Date());
	log.info("	Started initializing Product Dimension: " + new Date());
	productTransformation.initDimension();
	log.info("	Finished initializing Product Dimension: " + new Date());
	log.info("	Started initializing Sales Dimensions: " + new Date());
	salesTransformations.initDimension();
	log.info("	Finished initializing Sales Dimensions: " + new Date());
	log.info("	Started initializing Customer Dimensions: " + new Date());
	customerTransformation.initDimension();
	log.info("	Started finished Customer Dimensions: " + new Date());
	log.info("	Started initializing Place Dimensions: " + new Date());
	//placeTransformation.initDimension();
	log.info("	Finished initializing Place Dimensions: " + new Date());	
	}
	else{
	//placeTransformation.update();
		//customerTransformation.update();
		//productTransformation.update();
		//salesTransformations.update();
		//timeDimensionTransformations.update();	
	}
	}
	
	public void extractOperationalData(){
		
	}
	

	public void transformOperationalData(){
		if(targetDao.initialImport()){	
		factTransformation.initDimension();
		}else{
		factTransformation.update();	
		}
	}
	
	public void loadOperationalData(){
	
	}
	
	
	public void createEtlMetaInf(){
		
	}


	public boolean isRunning() {
		return isRunning;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	
}
