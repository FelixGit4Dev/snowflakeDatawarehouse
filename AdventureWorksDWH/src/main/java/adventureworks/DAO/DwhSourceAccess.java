package adventureworks.DAO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import adventureworks.entitySource.Contact;
import adventureworks.entitySource.Customer;
import adventureworks.entitySource.Individual;
import adventureworks.entitySource.Product;
import adventureworks.entitySource.Productcategory;
import adventureworks.entitySource.Productsubcategory;
import adventureworks.entitySource.Salesorderdetail;
import adventureworks.entitySource.Salesorderheader;
import adventureworks.entitySource.Salesperson;
import adventureworks.entitySource.Salesreason;
import adventureworks.entitySource.Salesterritory;
import adventureworks.entitySource.Shipmethod;
import adventureworks.entitySource.Store;

@Named
@ApplicationScoped
public class DwhSourceAccess implements Serializable{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String join = "SELECT %s FROM %s INNER JOIN Customers ON %s WHERE %s; ";
	
	@Inject
Logger log;
	
//@Inject
//@DwhSource
//	EntityManager entityManager;

@PersistenceContext(unitName="dwhSource")
EntityManager entityManager;




public Timestamp getEarliestDate() {
	
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Timestamp> cq = cb.createQuery(Timestamp.class);
	Root<Salesorderheader> from = cq.from(Salesorderheader.class);
	cq.select(cb.least(((Expression)from.get("orderDate"))));
	TypedQuery<Timestamp> q = entityManager.createQuery(cq);
	Timestamp item = q.getSingleResult();
	return item;		
}

public List<Salesorderheader> getAllSalesOrderHeader(int offset, int max)
{
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Salesorderheader> cq = cb.createQuery(Salesorderheader.class);
	Root<Salesorderheader> from = cq.from(Salesorderheader.class);
	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Salesorderheader> q = entityManager.createQuery(cq);
	List<Salesorderheader> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
    }

public List<Salesorderheader> getAllSalesOrderHeaderSinceDate(Timestamp since,int offset, int max)
{
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Salesorderheader> cq = cb.createQuery(Salesorderheader.class);
	Root<Salesorderheader> from = cq.from(Salesorderheader.class);
	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Salesorderheader> q = entityManager.createQuery(cq);
	List<Salesorderheader> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
    }
    
    
    

public List<Customer> getAllCustomers(int offset, int max)
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
    	Root<Customer> from = cq.from(Customer.class);
    	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
    	TypedQuery<Customer> q = entityManager.createQuery(cq);
    	List<Customer> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
    	return item;
    	
        }


public List<Productcategory> getAllCategories(int offset, int max)
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Productcategory> cq = cb.createQuery(Productcategory.class);
    	Root<Productcategory> from = cq.from(Productcategory.class);
    	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
    	TypedQuery<Productcategory> q = entityManager.createQuery(cq);
    	List<Productcategory> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
    	return item;
    	
        }



public List<Product> getProductsBySubcategoryId(long id, int offset, int max)
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Product> cq = cb.createQuery(Product.class);
    	Root<Product> from = cq.from(Product.class);
    	cq.select(from).where(cb.equal(from.get("productSubcategoryID"), id)).orderBy(cb.asc(from.get("modifiedDate")));
    	TypedQuery<Product> q = entityManager.createQuery(cq);
    	List<Product> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
    	return item;
    	
        }

  

public List<Customer> getAllProducts(int offset, int max)
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
    	Root<Customer> from = cq.from(Customer.class);
    	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
    	TypedQuery<Customer> q = entityManager.createQuery(cq);
    	List<Customer> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
    	return item;
    	
        }


public List<Productsubcategory> getSubcategoriesByCategoryId(long id ,int offset, int max)
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Productsubcategory> cq = cb.createQuery(Productsubcategory.class);
    	Root<Productsubcategory> from = cq.from(Productsubcategory.class);
    	cq.select(from).where(cb.equal(from.get("productCategoryID"), id)).orderBy(cb.asc(from.get("modifiedDate")));
    	TypedQuery<Productsubcategory> q = entityManager.createQuery(cq);
    	List<Productsubcategory> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
    	return item;
    	
        }


public List<Salesorderdetail> getAllSalesOrderdetailsBySalesHeaderId( int id, int offset, int max)
{
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Salesorderdetail> cq = cb.createQuery(Salesorderdetail.class);
	Root<Salesorderdetail> from = cq.from(Salesorderdetail.class);
	cq.select(from).where(cb.equal(from.get("id").get("salesOrderID"), id)).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Salesorderdetail> q = entityManager.createQuery(cq);
	List<Salesorderdetail> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
    }





public Store getStoreByCustomerId(int customerID) {
	 {
	    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    	CriteriaQuery<Store> cq = cb.createQuery(Store.class);
	    	Root<Store> from = cq.from(Store.class);
	    	cq.select(from).where(cb.equal(from.get("customerID"), customerID));
	    	TypedQuery<Store> q = entityManager.createQuery(cq);
	    	Store item; 
	    	try{
	    	 item = q.getSingleResult();}
	    	catch (NoResultException e){
	    	return null;	
	    	}
	    	return item;
	    	
	        }
	
}




public List<Salesperson> getAllSalesPersons(int offset, int max)
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Salesperson> cq = cb.createQuery(Salesperson.class);
    	Root<Salesperson> from = cq.from(Salesperson.class);
    	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
    	TypedQuery<Salesperson> q = entityManager.createQuery(cq);
    	List<Salesperson> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
    	return item;
    	
        }





public List<Shipmethod> getShippingMethods(int offset, int max)   {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Shipmethod> cq = cb.createQuery(Shipmethod.class);
	Root<Shipmethod> from = cq.from(Shipmethod.class);
	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Shipmethod> q = entityManager.createQuery(cq);
	List<Shipmethod> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
    }




public List<Object> getSalesReasonsDistinctReasonType() {
return entityManager.createNativeQuery("SELECT DISTINCT ReasonType FROM salesreason").getResultList();

}
   


public List<Salesreason> getallSalesReasonsByReasonType( String salesreasonType, int offset, int max)
{
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Salesreason> cq = cb.createQuery(Salesreason.class);
	Root<Salesreason> from = cq.from(Salesreason.class);
	cq.select(from).where(cb.equal(from.get("reasonType"),salesreasonType)).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Salesreason> q = entityManager.createQuery(cq);
	List<Salesreason> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
    }






public List<Salesterritory> getAllTerritories(int offset, int max) {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Salesterritory> cq = cb.createQuery(Salesterritory.class);
	Root<Salesterritory> from = cq.from(Salesterritory.class);
	cq.select(from).orderBy(cb.asc(from.get("modifiedDate")));
	TypedQuery<Salesterritory> q = entityManager.createQuery(cq);
	List<Salesterritory> item = q.setFirstResult(offset).setMaxResults(max).getResultList();
	return item;
	
}



public Individual getIndividualForCustomerId(int id){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Individual> cq = cb.createQuery(Individual.class);
	Root<Individual> from = cq.from(Individual.class);
	cq.select(from).where(cb.equal(from.get("customerID"),id));
	TypedQuery<Individual> q = entityManager.createQuery(cq);
	Individual item = q.getSingleResult();
	return item;	
}


public Contact getContactForCustomerId(int id){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
	Root<Contact> from = cq.from(Contact.class);
	cq.select(from).where(cb.equal(from.get("customerID"),id));
	TypedQuery<Contact> q = entityManager.createQuery(cq);
	Contact item = q.getSingleResult();
	return item;	
}







private static void main (String[] args){
	
}


}








