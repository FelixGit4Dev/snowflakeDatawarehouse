package visualization.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class DatwarehouseAccesDao {

	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Logger log; 
	
public List<Object[]> executeOlapQueryMulticolumns(String queryString ){
Query query =entityManager.createNativeQuery(queryString);	
List<Object[]> result=query.getResultList();
return result;
}	
	
public List<Object> executeOlapQuerySingleColumn(String queryString ){
Query query =entityManager.createNativeQuery(queryString);	
List<Object> result=query.getResultList();
return result;
}	

	
}
