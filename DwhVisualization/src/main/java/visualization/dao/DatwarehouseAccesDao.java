package visualization.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;


import visualization.entity.entityDwh.Category;
import visualization.entity.entityDwh.City;
import visualization.entity.entityDwh.Country;
import visualization.entity.entityDwh.Product;
import visualization.entity.entityDwh.SalesPerson;
import visualization.entity.entityDwh.SalesReason;
import visualization.entity.entityDwh.SalesReasonType;
import visualization.entity.entityDwh.State;
import visualization.entity.entityDwh.Subcategory;
import visualization.entity.entityDwh.Territory;
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
	private transient Logger log; 
	
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

public List<Object[]> executeOlapQueryMulticolumns(String queryString,int offset,int max ){
Query query =entityManager.createNativeQuery(queryString);	
List<Object[]> result=query.setFirstResult(offset).setMaxResults(max).getResultList();
return result;
}	
	
public List<Object> executeOlapQuerySingleColumn(String queryString,int offset,int max ){
Query query =entityManager.createNativeQuery(queryString);	
List<Object> result=query.setFirstResult(offset).setMaxResults(max).getResultList();
return result;
}	

public List<Object[]> executeNativeQueryMulticolumns(String queryString ){
	try{	
Query query =entityManager.createNativeQuery(queryString);	
List<Object[]> result=query.getResultList();
return result;}
catch (Exception e) {
	return new ArrayList<Object[]>();	
	}

}	
	
public List<Object> executeNativeQuerySingleColumn(String queryString ){
	try{
Query query =entityManager.createNativeQuery(queryString);	
List<Object> result=query.getResultList();
return result;}
	catch (IllegalStateException e) {
	return new ArrayList<Object>();	
	}
	catch (TransactionRequiredException e) {
		return new ArrayList<Object>();	
		}
	catch (Exception e) {
		return new ArrayList<Object>();	
		}
	
}


public List<String> getAllCities(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<City> cq = cb.createQuery(City.class);
	Root<City> from = cq.from(City.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<City> q = entityManager.createQuery(cq);
	List<City> item = q.getResultList();
	List<String> list =item.stream().map(City::getName).collect(Collectors.toList());
	return 	list;
}

public List<String> getAllStates(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<State> cq = cb.createQuery(State.class);
	Root<State> from = cq.from(State.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<State> q = entityManager.createQuery(cq);
	List<State> item = q.getResultList();
	List<String> list =item.stream().map(State::getName).collect(Collectors.toList());
	return 	list;
	
}

public List<String> getAllCountries(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Country> cq = cb.createQuery(Country.class);
	Root<Country> from = cq.from(Country.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<Country> q = entityManager.createQuery(cq);
	List<Country> item = q.getResultList();
	List<String> list =item.stream().map(Country::getName).collect(Collectors.toList());
	return 	list;	
}

public List<String> getAllSalesTerritories(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Territory> cq = cb.createQuery(Territory.class);
	Root<Territory> from = cq.from(Territory.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<Territory> q = entityManager.createQuery(cq);
	List<Territory> item = q.getResultList();
	List<String> list =item.stream().map(Territory::getName).collect(Collectors.toList());
	return 	list;
}

public List<String> getAllSalesReasons(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<SalesReason> cq = cb.createQuery(SalesReason.class);
	Root<SalesReason> from = cq.from(SalesReason.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<SalesReason> q = entityManager.createQuery(cq);
	List<SalesReason> item = q.getResultList();
	List<String> list =item.stream().map(SalesReason::getName).collect(Collectors.toList());
	return 	list;
}

public List<String> getAllSalesReasonTypes(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<SalesReasonType> cq = cb.createQuery(SalesReasonType.class);
	Root<SalesReasonType> from = cq.from(SalesReasonType.class);
	cq.select(from).where(cb.like(from.get("typeName"),startWith+"%"));
	TypedQuery<SalesReasonType> q = entityManager.createQuery(cq);
	List<SalesReasonType> item = q.getResultList();
	List<String> list =item.stream().map(SalesReasonType::getTypeName).collect(Collectors.toList());
	return 	list;
}

public List<String> getAllProducts(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Product> cq = cb.createQuery(Product.class);
	Root<Product> from = cq.from(Product.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<Product> q = entityManager.createQuery(cq);
	List<Product> item = q.getResultList();
	List<String> list =item.stream().map(Product::getName).collect(Collectors.toList());
	return 	list;	
}

public List<String> getAllSubcategories(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Subcategory> cq = cb.createQuery(Subcategory.class);
	Root<Subcategory> from = cq.from(Subcategory.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<Subcategory> q = entityManager.createQuery(cq);
	List<Subcategory> item = q.getResultList();
	List<String> list =item.stream().map(Subcategory::getName).collect(Collectors.toList());
	return 	list;
}

public List<String> getAllCategories(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Category> cq = cb.createQuery(Category.class);
	Root<Category> from = cq.from(Category.class);
	cq.select(from).where(cb.like(from.get("name"),startWith+"%"));
	TypedQuery<Category> q = entityManager.createQuery(cq);
	List<Category> item = q.getResultList();
	List<String> list =item.stream().map(Category::getName).collect(Collectors.toList());
	return 	list;
}

public List<String> getAllSalesPersons(String startWith){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<SalesPerson> cq = cb.createQuery(SalesPerson.class);
	Root<SalesPerson> from = cq.from(SalesPerson.class);
	cq.select(from).where(cb.like(from.get("salesPersonId"),startWith+"%"));
	TypedQuery<SalesPerson> q = entityManager.createQuery(cq);
	List<SalesPerson> item = q.getResultList();
	List<String> list =item.stream().map(SalesPerson::getSalesPersonId).map(Object::toString).collect(Collectors.toList());
	return 	list;
}

	
}
