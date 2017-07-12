package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.entity.dimensions.sales.SalesChannel;
import adventureworks.entity.dimensions.sales.SalesPerson;
import adventureworks.entity.dimensions.sales.SalesReason;
import adventureworks.entity.dimensions.sales.SalesReasonType;
import adventureworks.entity.dimensions.sales.ShippingMethod;
import adventureworks.entity.maps.SalesPerson_MAP;
import adventureworks.entity.maps.SalesReason_MAP;
import adventureworks.entity.maps.ShippingMethod_MAP;
import adventureworks.entitySource.Salesperson;
import adventureworks.entitySource.Salesreason;
import adventureworks.entitySource.Shipmethod;
import adventureworks.transformations.meta.Transformation;

@Named
@ApplicationScoped
public class SalesTransformations implements Transformation{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
private DwhTargetAcess targetDao;
	@Inject
	private DwhSourceAccess sourceDao;
	/**
	 * 
	 */
	

	public void update() {
		
		
	}

	public void initDimension() {
	Timestamp initialFrom= new Timestamp(0);
	 Timestamp now =new Timestamp(System.currentTimeMillis());
	//	SalesChannel Dimensions
	 SalesChannel channel =new SalesChannel(initialFrom,null,"ONLINE");
	 channel.setModfiedDate(now);
	this.targetDao.persistObject(channel );
	channel= new SalesChannel(initialFrom,null,"DIREKT");
	channel.setModfiedDate(now);
	this.targetDao.persistObject(new SalesChannel(null,null,"DIREKT"));	
	//SalesPerson Dimension
	 int offset = 0;

     List<Salesperson> salesPersons;
     while ((salesPersons = this.sourceDao.getAllSalesPersons(offset, Constants.RESULTSETSIZE)).size() > 0)
     {
    
         for (Salesperson salesPerson : salesPersons)
         {
        	 SalesPerson person = new SalesPerson();
        	   now =new Timestamp(System.currentTimeMillis());
        	 person.setModfiedDate(now);
        	 person.setFromDate(initialFrom);
        	 person.setToDate(null);
        		person= this.targetDao.persistSalesPerson(person);
        		
        		SalesPerson_MAP map= new SalesPerson_MAP(salesPerson.getSalesPersonID(),person.getSalesPersonId(),now,initialFrom,null);
        		this.targetDao.persistSalesPerson_MAP(map); 
         }
         offset += salesPersons.size();
     }
	
     // Shipping Method Dimension
      offset = 0;
     List<Shipmethod> shippingMethods;
     while ((shippingMethods = this.sourceDao.getShippingMethods(offset, Constants.RESULTSETSIZE)).size() > 0)
     {
    
         for (Shipmethod method : shippingMethods)
         {
        ShippingMethod shipping = new ShippingMethod(method.getName(),initialFrom,null);
        now =new Timestamp(System.currentTimeMillis());
        shipping.setModfiedDate(now);
        this.targetDao.persistObject(shipping);
        ShippingMethod_MAP map= new ShippingMethod_MAP(method.getShipMethodID(), shipping.getShippingMethodId(), now, initialFrom, null);
       targetDao.persistObject(map);
         }
         offset += shippingMethods.size();
     } 
     
     
	//SalesReason Dimension
     offset=0;
   List<Object> reasonTypes = sourceDao.getSalesReasonsDistinctReasonType();
   for(Object reasontype : reasonTypes){
	SalesReasonType  type = new SalesReasonType(initialFrom,null,((String)reasontype.toString())); 
	now =new Timestamp(System.currentTimeMillis());
	type.setModfiedDate(now);
	this.targetDao.persistObject(type);
	 List<Salesreason> salesReasons;
	 while ((salesReasons = this.sourceDao.getallSalesReasonsByReasonType(reasontype.toString(),offset, Constants.RESULTSETSIZE)).size() > 0)
     {
    
         for (Salesreason method : salesReasons)
         {
        SalesReason reasonTarget= new SalesReason(type.getSalesReasonTypeId(),initialFrom,null,method.getName());
        now =new Timestamp(System.currentTimeMillis());
reasonTarget.setModfiedDate(now);
        targetDao.persistObject(reasonTarget);
        SalesReason_MAP map= new SalesReason_MAP(method.getSalesReasonID(),reasonTarget.getSalesReasonId(),now,initialFrom,null);
          }
         offset += salesReasons.size();
     } 
   }
	}

}
