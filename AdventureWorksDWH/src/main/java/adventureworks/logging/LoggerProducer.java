package adventureworks.logging;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;  
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

import org.apache.log4j.Logger;  
  
/** 
 * Logging producer for injectable log4j logger 
 * 
 * @author cem ikta 
 */  
@Named
@ApplicationScoped
public class LoggerProducer {  
   /** 
    * @param injectionPoint 
    * @return logger 
    */  
    @Produces  
    public Logger produceLogger(InjectionPoint injectionPoint) {  
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());  
    }  
}  
