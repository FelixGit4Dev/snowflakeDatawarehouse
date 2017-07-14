package adventureworks.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.cronJobs.EtlJob;
import adventureworks.entity.EtlMetaInformation;
import adventureworks.transformations.CustomerTransformation;
import adventureworks.transformations.PlaceTransformation;
import adventureworks.transformations.ProductTransformation;
import adventureworks.transformations.SalesFactTransformation;
import adventureworks.transformations.SalesTransformations;
import adventureworks.transformations.TimeDimensionTransformations;

@SessionScoped
@Named
public class EtlViewController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private DwhTargetAcess targetDao;
@Inject EtlJob job;
	
	public List<EtlMetaInformation> getEtlMetaInformation(){
	return this.targetDao.getAllEtlMetaInformation();	
	}
	
	
	
	public void triggerEtlJob(){
	this.execute();
	}
	
	
	
	@Inject
	private Logger log;
		
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

		public void execute()  {
		if(job.isRunning()){
		log.info("An ETL-Job is running already, no new Job will be started try again after current Job has terminated");	
		}	
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
		this.targetDao.persistObject(meta);
			
		log.info("Finished ETL Job: " + new Date());
			
		
			
		}
		
		
		public void synchronizeDimensions(){

		if(targetDao.initialImport()){
	log.info("Started initializing Time Dimension: " + new Date());
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

	

}
