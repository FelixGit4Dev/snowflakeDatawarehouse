package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.dimensions.place.City;
import adventureworks.entity.dimensions.place.Country;
import adventureworks.entity.dimensions.place.State;
import adventureworks.entity.dimensions.place.Territory;
import adventureworks.entity.maps.City_MAP;
import adventureworks.entity.maps.Country_MAP;
import adventureworks.entity.maps.State_MAP;
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
	
	public HashMap<String, Long> initDimension() {
		HashMap<String, Long> mapCount= new HashMap<>();
		Long territoryCount=0L;
		Long countryCount=0L;
		Long stateCount=0L;
		Long cityCount=0L;
		Timestamp now =new Timestamp(System.currentTimeMillis());
        List<Salesterritory> salesHeaders;
        HashMap<String, Countryregion> countryMap= new HashMap<String, Countryregion>();
        List<Countryregion> countries= sourceDao.getAllCountries();
        for(Countryregion country: countries){
        	now =new Timestamp(System.currentTimeMillis());
        Country c = new Country(country.getCountryRegionCode(),country.getName(),now,new Timestamp(0),null);	
       this.targetDao.persistObject(c);
       countryCount++;
       Country_MAP map = new Country_MAP(country.getCountryRegionCode(),c.getCountryCode(),now,new Timestamp(0),null);
       targetDao.persistObject(map);
       
        }
    	int offset = 0;
        while ((salesHeaders = sourceDao.getAllTerritories(offset, 100)).size() > 0)
        {
       
            for (Salesterritory salesHeader : salesHeaders)
            {
            	
            		Territory territory= new  Territory(new Timestamp(0),null,salesHeader.getName(),salesHeader.getGroup());
            		 now =new Timestamp(System.currentTimeMillis());
            		territory.setModfiedDate(now);
            		targetDao.persistObject(territory);
            		territoryCount++;
            		stateCount= stateCount+this.createStates(territory.getTerritoryId());	
            	}
            offset += salesHeaders.size();
            }
       cityCount= createCities(cityCount);
       mapCount.put("City", cityCount);
       mapCount.put("State", stateCount);
       mapCount.put("Country", countryCount);
        mapCount.put("Territory", territoryCount);
        return mapCount;
        }	


	
		
	
	
	
	public Long createStates(long id){
		Long stateCount =0L;
		Timestamp initalFrom =new Timestamp(0);
		Timestamp now =new Timestamp(System.currentTimeMillis());
	List<Stateprovince> states= 	sourceDao.getStatesByTerritoryId( id);
	for(Stateprovince sp: states){
	State state = new State(sp.getTerritoryID(), sp.getCountryRegionCode(), sp.getName(),now, initalFrom, null);
	targetDao.persistObject(state);
	stateCount++;
	State_MAP map= new State_MAP(sp.getStateProvinceID(), state.getStateId(), now, initalFrom, null);
	targetDao.persistObject(map);
	}
	return stateCount;
	}

	
	public Long createCities(Long cityCount){
		Timestamp now =new Timestamp(System.currentTimeMillis());
	List<Object[]> result= this.sourceDao.getDistinctCities();
	for(Object[] o : result){
		State_MAP stateMap=this.targetDao.getStateMapById(((Integer)o[1]).longValue());
	City City = new City(stateMap.getDwhKey(), o[0].toString(), now, new Timestamp(0), null);	
		targetDao.persistObject(City);
		cityCount++;
	//City_MAP map= new City_MAP();
	//map.setModifiedDate(now);
	
	}
	return cityCount;
	}
	
	public HashMap<String, Long> update() {
		return new HashMap<>();
	
		
	}

}
