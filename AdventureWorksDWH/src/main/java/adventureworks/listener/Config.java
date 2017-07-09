package adventureworks.listener;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import adventureworks.cdi.CDIJobFactory;

@WebListener
public class Config implements ServletContextListener {

@Inject 
private CDIJobFactory factory;
	
    public void contextInitialized(ServletContextEvent event) {
      System.out.println("TEst");
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp shutdown.
    }

}