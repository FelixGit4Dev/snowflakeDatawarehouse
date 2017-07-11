package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.Util.xml.XmlParser;
import adventureworks.Util.xml.entity.IndividualSurvey;
import adventureworks.entity.dimensions.customer.Customer;
import adventureworks.entity.maps.Customer_MAP;
import adventureworks.entitySource.Contact;
import adventureworks.entitySource.Individual;
import adventureworks.entitySource.Store;
import adventureworks.transformations.meta.Transformation;
@Named
@ApplicationScoped
public class CustomerTransformation implements Transformation {
	
	/**
	 * 
	 */
	
	private long maxCustomerId;
private long maxStoreId;
private long maxIndividualId;
	
	private static final long serialVersionUID = 1L;
	@Inject
	private DwhSourceAccess sourceDao;
	@Inject
	private DwhTargetAcess targetDao;
@Inject 
	private Logger log; 
	public void initDimension() {
	
		  int offset = 0;

	        List<adventureworks.entitySource.Customer> customers;
	        while ((customers = sourceDao.getAllCustomers(offset, Constants.RESULTSETSIZE)).size() > 0)	        
	        {
	        	log.info("Start Block");
	       
	            for (adventureworks.entitySource.Customer customer : customers)
	            {
	           Timestamp now =new Timestamp(System.currentTimeMillis());
	            Customer customerDwh= new Customer();
	            customerDwh.setFromDate(new Timestamp(0));
	            customerDwh.setToDate(null);
	            customerDwh.setModfiedDate(now);
	   
	           customerDwh= this.targetDao.persistCustomer(customerDwh); //   log.info("do something with model: " + salesHeader.toString());

	           Customer_MAP map = new Customer_MAP(customer.getCustomerID(),customerDwh.getCustomerId(), now,new Timestamp(0),null);
	            this.targetDao.persistCustomer_MAP(map);
	            Store store = this.sourceDao.getStoreByCustomerId( customer.getCustomerID());
	         if(store!=null){
	            adventureworks.entity.dimensions.customer.Store dwhStore=    new adventureworks.entity.dimensions.customer.Store(); 
	         dwhStore.setStoreId(customerDwh.getCustomerId());
	         dwhStore.setName(store.getName());
	         //TODO
	        // evaluate Store survey 
	            this.targetDao.persistStore(dwhStore);
	            }
	            else{
	           Individual individual= this.sourceDao.getIndividualForCustomerId(customer.getCustomerID());	
	        Contact contact=    sourceDao.getContactForCustomerId(individual.getContactID()); 
	           adventureworks.entity.dimensions.customer.Individual individualDwh= new adventureworks.entity.dimensions.customer.Individual();
	            IndividualSurvey survey =XmlParser.umarshal(individual.getDemographics());
	            individualDwh.setBirthDate(survey.getBirthDate());
	            individualDwh.setDateFirstPurchase(survey.getDateFirstPurchase());
	            individualDwh.setFirstName(contact.getFirstName());
	            individualDwh.setLastName(contact.getLastName());
	           

	            }}
	            offset += customers.size();
	            log.info("Start Block:"+offset);
	}
	        
	        
}

	public void update() {
	targetDao.getLatestEtlMeta();
	
		
	}
	
	
	 
}