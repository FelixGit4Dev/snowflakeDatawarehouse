package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.entity.StagingTable;
import adventureworks.entity.maps.SpecialOffer_MAP;
import adventureworks.entity.other.SalesTaxrate;
import adventureworks.entity.other.SpecialOffer;
import adventureworks.entitySource.Salestaxrate;
import adventureworks.entitySource.Specialoffer;
import adventureworks.transformations.meta.Transformation;

@Named
@ApplicationScoped
public class TransferNecessaryTables implements Transformation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private DwhSourceAccess sourceDao;
	@Inject
	private DwhTargetAcess targetDao;
	
	
	public HashMap<String, Long> initDimension(){
		HashMap<String, Long> mapCount= new HashMap<>();
		int offset=0;
		Timestamp init = new Timestamp(0);
		  Timestamp now =new Timestamp(System.currentTimeMillis());
		   List<Specialoffer> salesHeaders;
	        while ((salesHeaders = sourceDao.getAllSpecialOffers(offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
	        	for(Specialoffer offer: salesHeaders){
	        	SpecialOffer specialOffer = new SpecialOffer();
	        			specialOffer.setDiscount(offer.getDiscountPct());
	        			specialOffer.setModifiedDate(now);
	        			specialOffer.setFromDate(init);
	        			targetDao.persistObject(specialOffer);
	        			SpecialOffer_MAP map = new SpecialOffer_MAP();
	        			map.setDwhKey(specialOffer.getSpecialOfferId());
	        			map.setSourceKey(offer.getSpecialOfferID());
	        			map.setModifiedDate(now);
	        			map.setFrom(init);
	        			
	        			
	        			targetDao.persistObject(map);
	        	}
	        	 offset += salesHeaders.size();
	        }
		
	        
	        
	       offset=0;
			   List<Salestaxrate> salesTaxrate;
		        while ((salesTaxrate = sourceDao.getAllSalesTaxrates(offset, Constants.RESULTSETSIZE)).size() > 0)
		        {
		        	for(Salestaxrate offer: salesTaxrate){
		        		SalesTaxrate rate = new SalesTaxrate();
		        		rate.setRate(offer.getTaxRate());
		        		rate.setStateId(targetDao.getStateMapById(offer.getStateProvinceID()).getDwhKey());
		        		rate.setModifiedDate(now);
		        		rate.setFromDate(init);
		        		targetDao.persistObject(rate);
		        	}
		        	 offset += salesTaxrate.size();
		        }
			   
	     return mapCount;   
	        
	}


	@Override
	public HashMap<String, Long> update() {
		return new HashMap<>();// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
