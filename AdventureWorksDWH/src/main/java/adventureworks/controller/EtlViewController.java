package adventureworks.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.mysql.fabric.HashShardMapping;

import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.TimeUtil;
import adventureworks.cronJobs.EtlJob;
import adventureworks.entity.EtlMetaInformation;
import adventureworks.transformations.CustomerTransformation;
import adventureworks.transformations.PlaceTransformation;
import adventureworks.transformations.ProductTransformation;
import adventureworks.transformations.SalesFactTransformation;
import adventureworks.transformations.SalesTransformations;
import adventureworks.transformations.TimeDimensionTransformations;
import adventureworks.transformations.TransferNecessaryTables;

@SessionScoped
@Named
public class EtlViewController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private DwhTargetAcess targetDao;
@Inject 
private EtlJob job;
	

private String message; 

	public List<EtlMetaInformation> getEtlMetaInformation(){
	return this.targetDao.getAllEtlMetaInformation();	
	}
	
	
	
	public void triggerEtlJob(){
	this.execute();
	}
	
	
	
	@Inject
	private transient Logger log;
		
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
		
		@Inject 
		private TransferNecessaryTables transferTables;

		private Timestamp runTime;

		
		
		
		
	public void execute() {
		if (job.isRunning()) {
			log.info(
					"An ETL-Job is running already, no new Job will be started try again after current Job has terminated");
		} else {
			job.setRunning(true);
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("controlForm");
			log.info("Beginning ETL Job: " + new Date());
			runTime = new Timestamp(System.currentTimeMillis());

			log.info("Started synchronising Dimensions: " + new Date());
			HashMap<String, Long> dimCounter = this.synchronizeDimensions();
			log.info("Finished synchronising Dimensions: " + new Date());

			log.info("Start Tranferring Tables necessary for Measure computation: " + new Date());
			this.transferTablesNecessaryforComputations();
			log.info("Finished Tranferring Tables necessary for Measure computation: " + new Date());

			log.info("Started transferring OperationalData to Fact-Table: " + new Date());
			HashMap<String, Long> factCounter = this.transformOperationalData();
			log.info("Finished transferring OperationalData to Fact-Table: " + new Date());

			EtlMetaInformation meta = createEtlMetaInformation(dimCounter, factCounter);
			this.targetDao.persistObject(meta);

			log.info("Finished ETL Job: " + new Date());

			job.setRunning(false);
		}
	}
		
		private EtlMetaInformation createEtlMetaInformation(HashMap<String, Long> dimCounter, HashMap<String, Long> factCounter) {
			EtlMetaInformation meta = new EtlMetaInformation();
			meta.setEtlJobRun_Date(runTime);
			String dimensions= "";
			String vorlage=" %s(%s) ";
			for(String key: dimCounter.keySet()){
			dimensions=dimensions+String.format(vorlage, key,dimCounter.get(key));	
			}
			
			Date end = new  Date();
			long duration= end.getTime()-runTime.getTime();
			meta.setChangedDimensions(dimensions);
			meta.setDuration(TimeUtil.formatDuration(duration));
			meta.setTransferredFacts(factCounter.get("Fact"));	
			return meta; 
		}



		public HashMap<String, Long> synchronizeDimensions(){
			HashMap<String, Long> map = new HashMap<String, Long>();
		if(targetDao.initialImport()){
			log.info("	Started initializing Customer Dimensions: " + new Date());
			map.putAll(customerTransformation.initDimension());
			log.info("	Started finished Customer Dimensions: " + new Date());
		log.info("	Started initializing Product Dimension: " + new Date());
		map.putAll(productTransformation.initDimension());
		log.info("	Finished initializing Product Dimension: " + new Date());
		
		log.info("	Started initializing Sales Dimensions: " + new Date());
		map.putAll(salesTransformations.initDimension());
		log.info("	Finished initializing Sales Dimensions: " + new Date());
		log.info("	Started initializing Place Dimensions: " + new Date());
		map.putAll(placeTransformation.initDimension());
		log.info("	Finished initializing Place Dimensions: " + new Date());	
		
		log.info("	Started initializing Time Dimension: " + new Date());
		map.putAll(timeDimensionTransformations.initDimension());
		log.info("	Finished initializing Time Dimension: " + new Date());
		return map;
		}
		else{
		//placeTransformation.update();
			//customerTransformation.update();
			//productTransformation.update();
			//salesTransformations.update();
			//timeDimensionTransformations.update();	
			return new HashMap<String,Long>();
		}
		}
		
		public void extractOperationalData(){
			
		}
		

		public HashMap<String,Long> transformOperationalData(){
			HashMap<String,Long> l=new HashMap<>();
			if(targetDao.initialImport()){	
			l=factTransformation.initDimension();
			}else{
				 l =factTransformation.update();	
			}
			return l;
		}
		
		public void loadOperationalData(){
		
		}
		
		
		public void createEtlMetaInf(){
			
		}


		public void transferTablesNecessaryforComputations(){
			if(this.targetDao.initialImport()){
			this.transferTables.initDimension();	
			}
			else{
			this.transferTables.update();	
			}
			}

		public String getMessage() {
			if(job.isRunning()){
			return "An ETL-Job is running, you cannot start another one, try again when the process terminated";	
			}
			else{
			return "Klick the Button to trigger an new Etl-Job";
		}}



		public void setMessage(String message) {
			this.message = message;
		}



		public Timestamp getRunTime() {
			return runTime;
		}



		public void setRunTime(Timestamp runTime) {
			this.runTime = runTime;
		}

	

}
