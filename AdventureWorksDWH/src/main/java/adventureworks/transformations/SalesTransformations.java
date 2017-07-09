package adventureworks.transformations;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.Constants;
import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.dimensions.sales.SalesChannel;
import adventureworks.entity.dimensions.sales.SalesPerson;
import adventureworks.entity.dimensions.sales.SalesReason;
import adventureworks.entity.dimensions.sales.SalesReasonType;
import adventureworks.entity.dimensions.sales.ShippingMethod;
import adventureworks.entity.maps.SalesPerson_MAP;
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
	private DwhSourceAccess sourceDao;
	/**
	 * 
	 */
	

	public void update() {
		
		
	}

	public void initDimension() {
	//	SalesChannel Dimensions
	this.targetDao.persistSalesChannel(new SalesChannel(null,null,"ONLINE"));
	this.targetDao.persistSalesChannel(new SalesChannel(null,null,"DIREKT"));	
	//SalesPerson Dimension
	 int offset = 0;

     List<Salesperson> salesPersons;
     while ((salesPersons = this.sourceDao.getAllSalesPersons(offset, Constants.RESULTSETSIZE)).size() > 0)
     {
    
         for (Salesperson salesPerson : salesPersons)
         {
        	 SalesPerson person = new SalesPerson();
        		person= this.targetDao.persistSalesPerson(person);
        		SalesPerson_MAP map= new SalesPerson_MAP(salesPerson.getSalesPersonID(),person.getSalesPersonId(),null,null,null);
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
        ShippingMethod shipping = new ShippingMethod(method.getName(),null,null);
        this.targetDao.persistShippingMethod(shipping);
        ShippingMethod_MAP map= new ShippingMethod_MAP(method.getShipMethodID(), shipping.getShippingMethodId(), null, null, null);
         }
         offset += shippingMethods.size();
     } 
     
     
	//SalesReason Dimension
     offset=0;
     List<String> salesReasonTypes;
   List<String> reasonTypes = sourceDao.getSalesReasonsDistinctReasonType();
   for(String reasontype : reasonTypes){
	SalesReasonType  type = new SalesReasonType(); 
	this.targetDao.persistSalesReasonType(type);
	 List<Salesreason> salesReasons;
	 while ((salesReasons = this.sourceDao.getallSalesReasonsByReasonType(reasontype,offset, Constants.RESULTSETSIZE)).size() > 0)
     {
    
         for (Salesreason method : salesReasons)
         {
        SalesReason reasonTarget= new SalesReason();	 
          }
         offset += shippingMethods.size();
     } 
   }
	}

}
