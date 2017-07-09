package adventureworks.cronJobs;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import adventureworks.DAO.DwhTargetAcess;
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

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	System.out.println("Hello World, I am an ETLJob");
	this.synchronizeDimensions();
	//this.transformOperationalData();
	//this.createEtlMetaInf();
		
	
		
	
		
	}
	
	
	public void synchronizeDimensions(){
	targetDao.getAllIdHousekeeping();	
	if(targetDao.initialImport()){	
	placeTransformation.initDimension();
	customerTransformation.initDimension();
	productTransformation.initDimension();
	salesTransformations.initDimension();
	timeDimensionTransformations.initDimension();
	
	}
	else{
		placeTransformation.update();
		customerTransformation.update();
		productTransformation.update();
		salesTransformations.update();
		timeDimensionTransformations.update();	
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
