package adventureworks.DAO;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import adventureworks.entity.EtlMetaInformation;
import adventureworks.entity.IdHousekeeping;
import adventureworks.entity.StagingTable;
import adventureworks.entity.dimensions.customer.Customer;
import adventureworks.entity.dimensions.customer.Store;
import adventureworks.entity.dimensions.product.Category;
import adventureworks.entity.dimensions.product.Product;
import adventureworks.entity.dimensions.product.Subcategory;
import adventureworks.entity.dimensions.sales.SalesChannel;
import adventureworks.entity.dimensions.sales.SalesPerson;
import adventureworks.entity.dimensions.sales.SalesReasonType;
import adventureworks.entity.dimensions.sales.ShippingMethod;
import adventureworks.entity.dimensions.time.Day;
import adventureworks.entity.dimensions.time.Month;
import adventureworks.entity.dimensions.time.Week;
import adventureworks.entity.dimensions.time.Year;
import adventureworks.entity.facts.SalesFact;
import adventureworks.entity.maps.Category_MAP;
import adventureworks.entity.maps.City_MAP;
import adventureworks.entity.maps.Customer_MAP;
import adventureworks.entity.maps.Product_MAP;
import adventureworks.entity.maps.SalesPerson_MAP;
import adventureworks.entity.maps.ShippingMethod_MAP;
import adventureworks.entity.maps.State_MAP;
import adventureworks.entitySource.Individual;
import adventureworks.entitySource.Salesorderheader;
import adventureworks.interceptor.Transactional;
import adventureworks.interceptor.qualifiers.DwhTarget;

@Named
@ApplicationScoped
public class DwhTargetAcess implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@DwhTarget
		EntityManager entityManager;


	
	
	
	
	public Timestamp getLatestDate() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Salesorderheader> cq = cb.createQuery(Salesorderheader.class);
		Root<Salesorderheader> from = cq.from(Salesorderheader.class);
		cq.select(cb.greatest(((Expression)from.get("orderDate"))));
		TypedQuery<Salesorderheader> q = entityManager.createQuery(cq);
		Salesorderheader item = q.getSingleResult();
		return item.getOrderDate();		
	}
	
@Transactional
	public Year persistYear(Year year){
	return entityManager.merge(year);	
	}
	
@Transactional
public Month persistMonth(Month month){
return entityManager.merge(month);	
}

@Transactional
public Day persistDay(Day day){
return entityManager.merge(day);	
}

@Transactional
public Week persistWeek(Week week){
return entityManager.merge(week);	
}

	
@Transactional
public Category persistCategory(Category category){
return entityManager.merge(category);	
}

@Transactional
public Subcategory persistSubcategory(Subcategory subcategory){
return entityManager.merge(subcategory);	
}


@Transactional
public Product persistProduct(Product product){
return entityManager.merge(product);	
}

@Transactional
public Customer persistCustomer(Customer customer){
 entityManager.persist(customer);	
entityManager.flush();
return customer;
}

@Transactional
public SalesFact persistSalesFact(SalesFact salesFact){
return entityManager.merge(salesFact);	
}
	

@Transactional
public EtlMetaInformation getLatestEtlMeta(){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<EtlMetaInformation> cq = cb.createQuery(EtlMetaInformation.class);
	Root<EtlMetaInformation> from = cq.from(EtlMetaInformation.class);
	cq.select(cb.greatest(((Expression)from.get("etlJobRun_Date"))));
	TypedQuery<EtlMetaInformation> q = entityManager.createQuery(cq);
	EtlMetaInformation item = q.getSingleResult();
	return item;		
}

@Transactional
public List<EtlMetaInformation> getAllEtlMetaInformation(){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<EtlMetaInformation> cq = cb.createQuery(EtlMetaInformation.class);
	Root<EtlMetaInformation> from = cq.from(EtlMetaInformation.class);
	cq.select(from).orderBy(cb.asc(from.get("etlJobRun_Date")));
	TypedQuery<EtlMetaInformation> q = entityManager.createQuery(cq);
	List<EtlMetaInformation> item = q.getResultList();
	return item;	
}
@Transactional
public boolean initialImport(){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<EtlMetaInformation> cq = cb.createQuery(EtlMetaInformation.class);
	Root<EtlMetaInformation> from = cq.from(EtlMetaInformation.class);
	cq.select(from).orderBy(cb.asc(from.get("etlJobRun_Date")));
	TypedQuery<EtlMetaInformation> q = entityManager.createQuery(cq);
	List<EtlMetaInformation> item = q.getResultList();
	return item.isEmpty();	
}
@Transactional
public Category_MAP persistCategory_MAP(Category_MAP map) {
	return entityManager.merge(map);
	
}
@Transactional
public Customer_MAP persistCustomer_MAP(Customer_MAP map) {
	return entityManager.merge(map);// TODO Auto-generated method stub
	
}
@Transactional
public Store persistStore(adventureworks.entity.dimensions.customer.Store dwhStore) {
	return entityManager.merge(dwhStore);
}
@Transactional
public SalesChannel persistSalesChannel(SalesChannel channel) {
	return this.entityManager.merge(channel);
	
}
@Transactional
public SalesPerson persistSalesPerson(SalesPerson person) {
	return this.entityManager.merge(person);
	
}
@Transactional
public SalesPerson_MAP persistSalesPerson_MAP(SalesPerson_MAP map) {
	return this.entityManager.merge(map);
	
}
@Transactional
public ShippingMethod_MAP persistShippingMethod(ShippingMethod_MAP map) {
	return this.entityManager.merge(map);
	
}
@Transactional
public ShippingMethod persistShippingMethod(ShippingMethod shipping) {
	return this.entityManager.merge(shipping);
	
}
@Transactional
public SalesReasonType persistSalesReasonType(SalesReasonType type) {
	return this.entityManager.merge(type);
	
}


@Transactional
public void persistListOfEntities(List<Object> entities){
for(Object entity: entities){
	entityManager.persist(entity);}
entityManager.flush();
}

@Transactional
public void persistListOfStagingtables(List<StagingTable> entities){
for(Object entity: entities){
	entityManager.persist(entity);}
entityManager.flush();
}


@Transactional
public List<IdHousekeeping> getAllIdHousekeeping(){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<IdHousekeeping> cq = cb.createQuery(IdHousekeeping.class);
	Root<IdHousekeeping> from = cq.from(IdHousekeeping.class);
	cq.select(from);
	TypedQuery<IdHousekeeping> q = entityManager.createQuery(cq);
	List<IdHousekeeping> item = q.getResultList();
	return item;	
}

public long getDateId(Date salesOrderID) {
DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate date =salesOrderID.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
String dateString =date.format(formatterDay);
CriteriaBuilder cb = entityManager.getCriteriaBuilder();
CriteriaQuery<Day> cq = cb.createQuery(Day.class);
Root<Day> from = cq.from(Day.class);
cq.select(from).where(cb.equal(from.get("day"),dateString));
TypedQuery<Day> q = entityManager.createQuery(cq);
Day item = q.getSingleResult();
return item.getDayId();	
	
}



public long getMappedTerritoryId(int salesOrderID) {
	// TODO Auto-generated method stub
	return 0;
}

public long getMappedDCustomerId(int salesOrderID) {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Customer_MAP> cq = cb.createQuery(Customer_MAP.class);
	Root<Customer_MAP> from = cq.from(Customer_MAP.class);
	cq.select(from).where(cb.equal(from.get("sourceKey"),salesOrderID));
	TypedQuery<Customer_MAP> q = entityManager.createQuery(cq);
	Customer_MAP item = q.getSingleResult();
	return item.getDwhKey();	
}

public long getMappedProductId(int salesOrderDetailID) {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Product_MAP> cq = cb.createQuery(Product_MAP.class);
	Root<Product_MAP> from = cq.from(Product_MAP.class);
	cq.select(from).where(cb.equal(from.get("sourceKey"),salesOrderDetailID));
	TypedQuery<Product_MAP> q = entityManager.createQuery(cq);
	Product_MAP item = q.getSingleResult();
	return item.getDwhKey();	
}

public long getMappedShippingMethodId(int salesOrderID) {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<ShippingMethod_MAP> cq = cb.createQuery(ShippingMethod_MAP.class);
	Root<ShippingMethod_MAP> from = cq.from(ShippingMethod_MAP.class);
	cq.select(from).where(cb.equal(from.get("sourceKey"),salesOrderID));
	TypedQuery<ShippingMethod_MAP> q = entityManager.createQuery(cq);
	ShippingMethod_MAP item = q.getSingleResult();
	return item.getDwhKey();	
}

public long getMappedSalesPersonId(int salesOrderID) {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<SalesPerson_MAP> cq = cb.createQuery(SalesPerson_MAP.class);
	Root<SalesPerson_MAP> from = cq.from(SalesPerson_MAP.class);
	cq.select(from).where(cb.equal(from.get("sourceKey"),salesOrderID));
	TypedQuery<SalesPerson_MAP> q = entityManager.createQuery(cq);
	SalesPerson_MAP item = q.getSingleResult();
	return item.getDwhKey();	
}

public void initializeIdHousekeeping() {
	
entityManager.persist(new IdHousekeeping("Customer", 0L));
entityManager.persist(new IdHousekeeping("City", 0L));
entityManager.persist(new IdHousekeeping("Country", 0L));
entityManager.persist(new IdHousekeeping("State", 0L));
entityManager.persist(new IdHousekeeping("Territory", 0L));
entityManager.persist(new IdHousekeeping("Category", 0L));
entityManager.persist(new IdHousekeeping("Product", 0L));
entityManager.persist(new IdHousekeeping("Subcategory", 0L));
entityManager.persist(new IdHousekeeping("SalesChannel", 0L));
entityManager.persist(new IdHousekeeping("SalesPerson", 0L));
entityManager.persist(new IdHousekeeping("SalesReason", 0L));
entityManager.persist(new IdHousekeeping("SaleReasonType", 0L));
entityManager.persist(new IdHousekeeping("ShippingMethod", 0L));
	
}

@Transactional
public void persistIdHousekeeping(IdHousekeeping hk){
entityManager.merge(hk);
entityManager.flush();
}


public IdHousekeeping getIdHousekeepingForDimensionTable(String table){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<IdHousekeeping> cq = cb.createQuery(IdHousekeeping.class);
	Root<IdHousekeeping> from = cq.from(IdHousekeeping.class);
	cq.select(from).where(cb.equal(from.get("tableName"),table));
	TypedQuery<IdHousekeeping> q = entityManager.createQuery(cq);
	IdHousekeeping item = q.getSingleResult();
	return item;	

}

@Transactional
public Object persistObject(Object o){
 entityManager.persist(o);
 entityManager.flush();
 return o;
}
@Transactional
public Object mergeObject(Object o){
	return  entityManager.merge(o);

	}

@Transactional
public void clearStagingTable(){
	Query q = entityManager.createNativeQuery("DELETE FROM staging_table");	
	q.executeUpdate();
}


public List<Object> getFinishedSalesFacts(int offset, int maxResults){
//Query q= entityManager.createNativeQuery("", "StagingValueMapping");	
//List<Object> results = q.setFirstResult(offset).setMaxResults(maxResults).getResultList();
//return results;
	return null;
}


public State_MAP getStateMapById(long id){
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<State_MAP> cq = cb.createQuery(State_MAP.class);
	Root<State_MAP> from = cq.from(State_MAP.class);
	cq.select(from).where(cb.equal(from.get("sourceKey"),id));
	TypedQuery<State_MAP> q = entityManager.createQuery(cq);
	State_MAP item = q.getSingleResult();
	return item;		
}



public List<Object[]> getCustomerIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("select s.salesOrderId, "
+" (select DWH_KEY from customer_map where SOURCE_KEY=s.customerId) as cs"
+" from stagingtable as s"
+" order by s.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}


public List<Object[]> getProductIdAndSalesPersonIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("select st.salesOrderId, pm.DWH_KEY as product, sp.DWH_KEY as salesperson from stagingtable as st"
+" left join salesperson_map as sp on st.salesPersonId=sp.SOURCE_KEY"
+" inner join product_map as pm on pm.SOURCE_KEY= st.productId"
+" inner join product as p on p.productId=pm.DWH_KEY"
+" order by st.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}


public List<Object[]> getBillToIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("select st.salesOrderId, c.CITY_ID as orderTo from stagingtable as st"
			+" inner join state_map as sm on sm.SOURCE_KEY=st.billToState"
			+" inner join city as c on c.stateId= sm.DWH_KEY"
			+" where st.billTo=c.NAME"
			+" order by st.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}

public List<Object[]> getShipToIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("select st.salesOrderId, c.CITY_ID as shipTo from stagingtable as st"
+" inner join state_map as sm on sm.SOURCE_KEY=st.shipToState"
+" inner join city as c on c.stateId= sm.DWH_KEY"
+" where st.shipTo=c.NAME"
+" order by st.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}

public List<Object[]> getOrderDateIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("SELECT st.salesOrderId," 
+" (select dayId from day where st.orderDateId=timeinMilis) as od"
+" FROM stagingtable as st"
+" order by st.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}

public List<Object[]> getShipDateIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("SELECT st.salesOrderId," 
+" (select dayId from day where st.shipDateId=timeinMilis) as od"
+" FROM stagingtable as st"
+" order by st.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}

public List<Object[]> getShippingMethodIdForSalesFact(int offset, int max){
	Query q = entityManager.createNativeQuery("SELECT st.salesOrderId, spm.DWH_KEY as shippingMethod FROM stagingtable as st"
+" inner join shippingmethod_map as spm on  st.shippingMethodId=spm.SOURCE_KEY"
+" order by st.salesOrderId");	
	return q.setFirstResult(offset).setMaxResults(max).getResultList();
	
}


@Transactional
public void persistMapOfEntities(Map<BigInteger, SalesFact> map){
for(BigInteger l : map.keySet()){
entityManager.merge(map.get(l));

}
entityManager.flush();
}

}
