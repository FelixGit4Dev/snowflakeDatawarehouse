package adventureworks.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Named;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
@Named
@ApplicationScoped
public class CDIJobFactory implements JobFactory {

   private BeanManager beanManager;
    String test ="test";


    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException{
    	beanManager=CDI.current().getBeanManager();
        Class<?> jobClazz = bundle.getJobDetail().getJobClass();
       Bean<?> bean =beanManager.getBeans(jobClazz).iterator().next();
        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);             
    return    (Job) beanManager.getReference(bean, jobClazz, ctx) ;      
  
    }
}