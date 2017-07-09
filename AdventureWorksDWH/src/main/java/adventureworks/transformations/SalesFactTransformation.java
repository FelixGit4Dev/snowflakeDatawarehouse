package adventureworks.transformations;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.Constants;
import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.facts.SalesFact;
import adventureworks.entitySource.Salesorderdetail;
import adventureworks.entitySource.Salesorderheader;
import adventureworks.transformations.meta.Transformation;

@Named
@ApplicationScoped
public class SalesFactTransformation implements Transformation{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private DwhSourceAccess sourceDao;
	@Inject
	private DwhTargetAcess targetDao;
	
	
	public void initDimension() {
		  int offset = 0;

	        List<Salesorderheader> salesHeaders;
	        while ((salesHeaders = sourceDao.getAllSalesOrderHeader(offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
	        	
	            for (Salesorderheader salesHeader : salesHeaders)
	            {
	            	 int offsetdetails = 0;
	            	 List<Salesorderdetail> list;
	            	 while ((list = sourceDao.getAllSalesOrderdetailsBySalesHeaderId(salesHeader.getSalesOrderID(), offset, Constants.RESULTSETSIZE)).size() > 0){
	            	for(Salesorderdetail detail: list){
	            	SalesFact fact= 	new SalesFact();	
	         long dueDateId = 0;
	         long orderDateId=0;
	         long territoryId=0;
	         long productId=0;
	         long customerId=0;
	         long shippingMethodId=0;
	         long salesPersonId=0;
	         
	         //DimensionsIds
	         fact.setDueDateId(dueDateId);
	         fact.setOrderDateId(orderDateId);
	         fact.setTerritoryId(territoryId);
	         fact.setProductId(productId);
	         fact.setCustomerId(customerId);
	         fact.setShippingMethodId(shippingMethodId);
	         fact.setSalesPersonId(salesPersonId);
	         
	         
	         //Kennzahlen
	            	fact.setQuantity(0);
	            	fact.setUnitPrice(0.0);
	            	fact.setDiscount(0.0);
	            	fact.setTaxAmt(0.0);
	            	this.targetDao.persistSalesFact(fact);	
	            	}
	            	offsetdetails=list.size();
	            	 }
	            		    	
	           
	            }
	            offset += salesHeaders.size();
	        }
		
	}

	public void update() {
		
		
	}

}
