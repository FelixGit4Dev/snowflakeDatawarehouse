package adventureworks.transformations;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.entity.dimensions.place.Territory;
import adventureworks.entitySource.Salesterritory;
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
	
	public void initDimension() {
		int offset = 0;

        List<Salesterritory> salesHeaders;
        while ((salesHeaders = sourceDao.getAllTerritories(offset, 100)).size() > 0)
        {
       
            for (Salesterritory salesHeader : salesHeaders)
            {
            	sourceDao.getStatesByTerritoryId( id);
            	// log.info("do something with model: " + salesHeader.toString());
            }
            offset += salesHeaders.size();
        }	
	List<Salesterritory> territories= ();
	for(Salesterritory salester: territories){
		Territory territory= new  Territory()
			
	}
	sourceDao.getAllCountries();
	
		
	}

	public void update() {
		
	
		
	}

}
