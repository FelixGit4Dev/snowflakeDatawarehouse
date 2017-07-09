package adventureworks.cronJobs;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import adventureworks.transformations.CustomerTransformation;
@Named
@ApplicationScoped
public class SimulateOperationalTransactions implements Job{
	
	
@Inject
CustomerTransformation ct;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hello World, I am an SimulateOperationalTransactions Job");
		System.out.println(ct.hashCode());
		
		
		
	}

	
	public void alterDimensions(){
		
	}
	
	
	public void generateSalesFacts(){
		
	}
	
	
}
