package visualization.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
@SessionScoped
@Named
public class DatwarehouseAccesDao implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
