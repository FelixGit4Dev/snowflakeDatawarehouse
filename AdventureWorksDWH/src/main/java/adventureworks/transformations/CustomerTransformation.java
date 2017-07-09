package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import adventureworks.Constants;
import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.dimensions.customer.Customer;
import adventureworks.entity.maps.Customer_MAP;
import adventureworks.entitySource.Store;
import adventureworks.transformations.meta.Transformation;
@Named
@ApplicationScoped
public class CustomerTransformation implements Transformation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private DwhSourceAccess sourceDao;
	@Inject
	private DwhTargetAcess targetDao;
@Inject 
	private Logger log; 
	public void initDimension() {
		  int offset = 0;

	        List<adventureworks.entitySource.Customer> salesHeaders;
	        while ((salesHeaders = sourceDao.getAllCustomers(offset, Constants.RESULTSETSIZE)).size() > 0)	        
	        {
	        	log.info("Start Block");
	       
	            for (adventureworks.entitySource.Customer salesHeader : salesHeaders)
	            {
	            Customer customerDwh= new Customer();
	           Timestamp now =new Timestamp(System.currentTimeMillis());
	           customerDwh= this.targetDao.persistCustomer(customerDwh); //   log.info("do something with model: " + salesHeader.toString());
	         Customer_MAP map = new Customer_MAP(salesHeader.getCustomerID(),customerDwh.getCustomerId(), now,now,now);
	            this.targetDao.persistCustomer_MAP(map);
	            Store store = this.sourceDao.getStoreByCustomerId( salesHeader.getCustomerID());
	         if(store!=null){
	            adventureworks.entity.dimensions.customer.Store dwhStore=    new adventureworks.entity.dimensions.customer.Store(); 
	          this.targetDao.persistStore(dwhStore);
	            }
	            else{
	            	
	            }}
	            offset += salesHeaders.size();
	            log.info("Start Block:"+offset);
	}
}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	  private void iterateAllCustomer()
	    {
	        int offset = 0;

	        List<adventureworks.entitySource.Customer> salesHeaders;
	        while ((salesHeaders = sourceDao.getAllCustomers(offset, 100)).size() > 0)
	        {
	       
	            for (adventureworks.entitySource.Customer salesHeader : salesHeaders)
	            {
	               // log.info("do something with model: " + salesHeader.toString());
	            }
	            offset += salesHeaders.size();
	        }

}
}