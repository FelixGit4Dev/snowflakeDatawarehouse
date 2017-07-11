package adventureworks.transformations;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
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
	            		 List<Object> factList= new ArrayList<Object>();
	            		 for(Salesorderdetail detail: list){
	            	SalesFact fact= 	new SalesFact();	
	         long dueDateId = targetDao.getDateId(salesHeader.getDueDate());
	         long orderDateId=targetDao.getDateId(salesHeader.getOrderDate());;
	         long territoryId=targetDao.getMappedTerritoryId(salesHeader.getSalesOrderID());;
	         long productId=targetDao.getMappedProductId(detail.getId().getSalesOrderDetailID());;
	         long customerId=targetDao.getMappedDCustomerId(salesHeader.getSalesOrderID());;
	         long shippingMethodId=targetDao.getMappedShippingMethodId(salesHeader.getSalesOrderID());;
	         long salesPersonId=targetDao.getMappedSalesPersonId(salesHeader.getSalesOrderID());;
	         
	         //DimensionsIds
	         fact.setDueDateId(dueDateId);
	         fact.setOrderDateId(orderDateId);
	         fact.setTerritoryId(territoryId);
	         fact.setProductId(productId);
	         fact.setCustomerId(customerId);
	         fact.setShippingMethodId(shippingMethodId);
	         fact.setSalesPersonId(salesPersonId);
	        //S fact.setBillToStateProvinceId(billToAdressId);// Join Fact Adress and State where SalesHEader id ...
	         
	         
	         //Kennzahlen
	            	fact.setQuantity(detail.getOrderQty());
	            	fact.setUnitPrice(detail.getUnitPrice());
	            	fact.setDiscount(0.0); // inner join product specialofferPRoduct and specialoffer where product id 
	            	fact.setTaxAmt(0.0);
	            	fact.setTotal(detail.getOrderQty()*detail.getUnitPrice()-fact.getDiscount()+fact.getTaxAmt());
	            	factList.add(fact);	
	            	}
	            	offsetdetails=list.size();
	            	targetDao.persistListOfEntities(factList);
	            	 }
	            		    	
	           
	            }
	            offset += salesHeaders.size();
	        }
		
	}

	public void update() {
		
		
	}

}
