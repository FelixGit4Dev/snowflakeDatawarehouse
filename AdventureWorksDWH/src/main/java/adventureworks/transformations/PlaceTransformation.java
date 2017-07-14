package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.dimensions.place.Territory;
import adventureworks.entitySource.Countryregion;
import adventureworks.entitySource.Salesterritory;
import adventureworks.entitySource.Stateprovince;
import adventureworks.interceptor.qualifiers.DwhTarget;
import adventureworks.transformations.meta.Transformation;

@Named
@ApplicationScoped
public class PlaceTransformation implements Transformation{

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

        List<Salesterritory> salesHeaders;
        HashMap<String, Countryregion> countryMap= new HashMap<String, Countryregion>();
        List<Countryregion> countries= null;//sourceDao.getAllCountries();
        for(Countryregion country: countries){
        countryMap.put(country.getCountryRegionCode(), country);	
        }
        while ((salesHeaders = sourceDao.getAllTerritories(offset, 100)).size() > 0)
        {
       
            for (Salesterritory salesHeader : salesHeaders)
            {
            	
            		Territory territory= new  Territory(new Timestamp(0),null,salesHeader.getName(),salesHeader.getGroup());
            		
            			
            	}
            }
            offset += salesHeaders.size();
        }	


	
		
	
	
	
	public void createStates(long id){
//	List<Stateprovince> states= 	sourceDao.getStatesByTerritoryId( id);	
	}

	
	public void createCities(){
		
	}
	
	public void update() {
		
	
		
	}

}
