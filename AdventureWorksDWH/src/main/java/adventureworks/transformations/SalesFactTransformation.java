package adventureworks.transformations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.entity.StagingTable;
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
	@Inject 
	private transient Logger log; 
	
	
	public void initDimension2() {
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
	
	         fact.setOrderDateId(orderDateId);

	         fact.setProductId(productId);
	         fact.setCustomerId(customerId);
	         fact.setShippingMethodId(shippingMethodId);
	         fact.setSalesPersonId(salesPersonId);
	        //S fact.setBillToStateProvinceId(billToAdressId);// Join Fact Adress and State where SalesHEader id ...
	         
	         
	         //Kennzahlen
	            	fact.setQuantity(detail.getOrderQty());
	            	fact.setUnitPrice(detail.getUnitPrice());
	            	fact.setDiscount(0.0); // inner join product specialofferPRoduct and specialoffer where product id 
	           
	            	fact.setTotal(detail.getOrderQty()*detail.getUnitPrice()-fact.getDiscount());
	            	factList.add(fact);	
	            	}
	            	offsetdetails=list.size();
	            	targetDao.persistListOfEntities(factList);
	            	 }
	            		    	
	           
	            }
	            offset += salesHeaders.size();
	        }
		
	}

	public HashMap<String, Long> update() {
		return new HashMap<String,Long>();
		
	}
	
	
	
	public void fillStageTable(){
		
	}

	public HashMap<String, Long> initDimension() {
		HashMap<String, Long> mapCount= new HashMap<>();
		Long factCount=0L;
		 int offset = 0;
			log.info("Started transfer to Staging Table: "+ new Date());
			long salesReasonId= targetDao.getOtherSalesReason().getSalesReasonId();
	        List<StagingTable> salesHeaders;
	        while ((salesHeaders = sourceDao.getJoinedSalesOrderAndHeaders(offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
	        	this.targetDao.persistListOfStagingtables(salesHeaders);	
	        	  offset += salesHeaders.size();	
	}
	        log.info("Finished transfer to Staging Table: "+ new Date());
        offset = 0;  
        List<Object[]> list;
       HashMap<BigInteger,SalesFact> map = new HashMap<BigInteger, SalesFact>();
       log.info("Started transfer to Sales Facts: "+ new Date());
        while ((list =  this.targetDao.getCustomerIdForSalesFact(offset, Constants.RESULTSETSIZE)).size() > 0)      	
	        {
        	log.info("Started new Block: Size: "+list.size()+ "Time: "+new Date());
       	for(Object[]o: list){
       	SalesFact fact = new SalesFact();
        	fact.setCustomerId(((BigInteger)o[1]).longValue());
        	map.put((BigInteger) o[0], fact);
       	}
    	log.info("Started retrieving product ids and sales person ids: Time: "+new Date());
        	ArrayList<Object[]> v1 = (ArrayList<Object[]>)targetDao.getProductIdAndSalesPersonIdForSalesFact(offset, Constants.RESULTSETSIZE);
        log.info("Started retrieving billTo Ids: Time: "+new Date());
        	ArrayList<Object[]> v2 = (ArrayList<Object[]>) targetDao.getBillToIdForSalesFact(offset, Constants.RESULTSETSIZE);
        log.info("Started retrieving shipTo Ids: Time: "+new Date());	
        	ArrayList<Object[]> v3 = (ArrayList<Object[]>) targetDao.getShipToIdForSalesFact(offset, Constants.RESULTSETSIZE);
        log.info("Started retrieving orderDate Ids: Time: "+new Date());	
        	ArrayList<Object[]> v4 = (ArrayList<Object[]>) targetDao.getOrderDateIdForSalesFact(offset, Constants.RESULTSETSIZE);
        log.info("Started retrieving shipDate Ids: Time: "+new Date());
        	ArrayList<Object[]> v5 = (ArrayList<Object[]>) targetDao.getShipDateIdForSalesFact(offset, Constants.RESULTSETSIZE);
        log.info("Started retrieving shippingMethod Ids: Time: "+new Date());
        	ArrayList<Object[]> v6 = (ArrayList<Object[]>) targetDao.getShippingMethodIdForSalesFact(offset, Constants.RESULTSETSIZE);
        	
        	ArrayList<Object[]> v7= (ArrayList<Object[]>) targetDao.getTaxrateAndSpecialOffer(offset, Constants.RESULTSETSIZE);
        	SalesFact fact;
        	for(int i =0; i<list.size();i++){
      		Object[] arr;     		
      		arr= v1.get(i); 
      		fact =map.get((BigInteger)arr[0]);
      				 fact.setProductId(((BigInteger)arr[1]).longValue());
      		 fact.setSalesPersonId(arr[2]==null? salesReasonId :((BigInteger)arr[2]).longValue());
      		 fact.setQuantity((Integer) arr[3]);
      		 fact.setUnitPrice((Double)arr[4]);
      		 fact.setProductStandartCost((Double)arr[5]);
     arr = v7.get(i);
     fact =map.get((BigInteger)arr[0]);
     fact.setDiscount(fact.getQuantity()*fact.getUnitPrice()*((Double)arr[1]));  
      		 fact.setTotal((fact.getQuantity()*fact.getUnitPrice()-fact.getDiscount()));
      		 fact.setTotalCost(fact.getQuantity()*fact.getProductStandartCost());
      		 fact.setMargin(fact.getTotal()-fact.getTotalCost());
      		arr= v2.get(i);
      		fact =map.get((BigInteger)arr[0]);
      		fact.setBillTo(((BigInteger)arr[1]).longValue());
      		arr= v3.get(i);
      		fact =map.get((BigInteger)arr[0]);
      		fact.setShipTo(((BigInteger)arr[1]).longValue());
      		arr= v4.get(i);
      		fact =map.get((BigInteger)arr[0]);
      		fact.setOrderDateId(((BigInteger)arr[1]).longValue());
      		arr=v5.get(i);
      		fact =map.get((BigInteger)arr[0]);
      		fact.setShipdateId(((BigInteger)arr[1]).longValue());
      		arr=v6.get(i);
      		fact =map.get((BigInteger)arr[0]);
      		fact.setShippingMethodId(((BigInteger)arr[1]).longValue());
        	}
        		
       		log.info("Started persisting Block of SalesFacts");
       		factCount= factCount+list.size();
        this.targetDao.persistMapOfEntities(map);
        log.info("Finished persisting Block of SalesFacts");	
      	  offset += list.size();	
      	  map.clear();
	        }	 
//	        this.targetDao.clearStagingTable();
        mapCount.put("Fact", factCount);
        return mapCount;
	        }
	
	
	
	

}
