package adventureworks.transformations;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.Util.DreiTupel;
import adventureworks.Util.xml.XmlParser;
import adventureworks.Util.xml.entity.IndividualSurvey;
import adventureworks.entity.dimensions.customer.AgeGroup;
import adventureworks.entity.dimensions.customer.Customer;
import adventureworks.entity.dimensions.customer.Gender;
import adventureworks.entity.dimensions.customer.HomeOwner;
import adventureworks.entity.dimensions.customer.YearlyIncomeGroup;
import adventureworks.entity.maps.Customer_MAP;
import adventureworks.entitySource.Contact;
import adventureworks.entitySource.Individual;
import adventureworks.entitySource.Store;
import adventureworks.transformations.meta.Transformation;
@Named
@ApplicationScoped
public class CustomerTransformation implements Transformation {
	
	/**
	 * 
	 */
	
	private long maxCustomerId;
private long maxStoreId;
private long maxIndividualId;
	
	private static final long serialVersionUID = 1L;
	@Inject
	private DwhSourceAccess sourceDao;
	@Inject
	private DwhTargetAcess targetDao;
@Inject 
	private Logger log; 
	public void initDimension2() {
		   Timestamp now=new Timestamp(System.currentTimeMillis());
		//Gender
		Gender maennlich= new Gender("M",now,"MAENNLICH");
		Gender weiblich= new Gender("W",now,"WEIBLICH");
		Gender undefinedGender= new Gender("UD",now,"UNDEFINED");
		
		//AgeGroup
		AgeGroup jugendlich= new AgeGroup("0-20",now);
		AgeGroup jung= new AgeGroup("21-40",now);
		AgeGroup mittel= new AgeGroup("41.60",now);
		AgeGroup alt= new AgeGroup("60+",now);
		AgeGroup undefinedAge= new AgeGroup("UNDEFINED",now);
		targetDao.persistObject(jugendlich);
		targetDao.persistObject(jung);
		targetDao.persistObject(mittel);
		targetDao.persistObject(alt);
		targetDao.persistObject(undefinedAge);
		
		//YearlyIncomeGroup
		YearlyIncomeGroup low= new YearlyIncomeGroup("L",now,"0-25000");
		YearlyIncomeGroup middel= new YearlyIncomeGroup("M",now,"25001-50000");
		YearlyIncomeGroup high= new YearlyIncomeGroup("H",now,"50001-75000");
		YearlyIncomeGroup veryHigh= new YearlyIncomeGroup("VH",now,"75001-100000");

		//HomeOwner
	HomeOwner yes =new HomeOwner("YES", now);
	HomeOwner no =new HomeOwner("NO", now);
	
		  int offset = 0;

	        List<adventureworks.entitySource.Customer> customers;
	        while ((customers = sourceDao.getAllCustomers(offset, Constants.RESULTSETSIZE)).size() > 0)	        
	        {
	        	log.info("Start Block");
	       
	            for (adventureworks.entitySource.Customer customer : customers)
	            {
	            now =new Timestamp(System.currentTimeMillis());
	            Customer customerDwh= new Customer();
	            customerDwh.setFromDate(new Timestamp(0));
	            customerDwh.setToDate(null);
	            customerDwh.setModfiedDate(now);
	   
	           customerDwh= this.targetDao.persistCustomer(customerDwh); //   log.info("do something with model: " + salesHeader.toString());

	           Customer_MAP map = new Customer_MAP(customer.getCustomerID(),customerDwh.getCustomerId(), now,new Timestamp(0),null);
	            this.targetDao.persistCustomer_MAP(map);
	            Store store = this.sourceDao.getStoreByCustomerId( customer.getCustomerID());
	     
	         if(store!=null){
	            adventureworks.entity.dimensions.customer.Store dwhStore=    new adventureworks.entity.dimensions.customer.Store(); 
	         dwhStore.setStoreId(customerDwh.getCustomerId());
	         dwhStore.setName(store.getName());
	         now =new Timestamp(System.currentTimeMillis());
	         dwhStore.setModfiedDate(now);
	         //TODO
	        // evaluate Store survey 
	            this.targetDao.persistStore(dwhStore);
	            }
	            else{
	            		
	           Individual individual= this.sourceDao.getIndividualForCustomerId(customer.getCustomerID());	
	        Contact contact=    sourceDao.getContactForCustomerId(individual.getContactID()); 
	           adventureworks.entity.dimensions.customer.Individual individualDwh= new adventureworks.entity.dimensions.customer.Individual();
	            IndividualSurvey survey =XmlParser.umarshal(individual.getDemographics());
	            Calendar c = Calendar.getInstance(); 
	     
	            LocalDate localDate = LocalDate.parse(survey.getBirthDate().replaceAll("Z", ""));
	            LocalDateTime localDateTime = localDate.atStartOfDay();
	            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
	            now =new Timestamp(System.currentTimeMillis());
	          c.setTimeInMillis(now.getTime()-instant.toEpochMilli());
	          int mYear = c.get(Calendar.YEAR);
	          
	           // individualDwh.setBirthDate();
	          individualDwh.setIndividualId(customerDwh.getCustomerId());
	            individualDwh.setDateFirstPurchase(survey.getDateFirstPurchase());
	            individualDwh.setFirstName(contact.getFirstName());
	            individualDwh.setLastName(contact.getLastName());
	            
	           
	            individualDwh.setModfiedDate(now);
	           
	            this.targetDao.persistObject(individualDwh);
	            }}
	            offset += customers.size();
	            log.info("Start Block:"+offset);
	}
	      
	        
	        
	        
	        
	        
	        
}

	
	public HashMap<String, Long> initDimension(){
	HashMap<String, Long> map= new HashMap<>();	
	Long customerCount=0L;
	Long storeCount=0L;
	Long individualCount=0L;
		   Timestamp now=new Timestamp(System.currentTimeMillis());
		   List<Object> topersist= new ArrayList<Object>();
		//Gender
		Gender gender= new Gender("M",now,"MAENNLICH");
		targetDao.persistObject(gender);
		gender= new Gender("W",now,"WEIBLICH");
		targetDao.persistObject(gender);
		gender= new Gender("UD",now,"UNDEFINED");
		targetDao.persistObject(gender);
		map.put("Gender", 3L);
		//AgeGroup
		AgeGroup jugendlich= new AgeGroup("0-20",now);
		jugendlich.setLower(0);
		jugendlich.setUpper(20);
		AgeGroup jung= new AgeGroup("21-40",now);
		jung.setLower(21);
		jung.setUpper(40);
		AgeGroup mittel= new AgeGroup("41-60",now);
		mittel.setLower(41);
		mittel.setUpper(60);
		AgeGroup alt= new AgeGroup("60+",now);
		alt.setLower(61);
		alt.setUpper(200);
	
		AgeGroup undefinedAge= new AgeGroup("UNDEFINED",now);
		targetDao.persistObject(jugendlich);
		targetDao.persistObject(jung);
		targetDao.persistObject(mittel);
		targetDao.persistObject(alt);
		targetDao.persistObject(undefinedAge);
		map.put("AgeGroup", 4L);
		//YearlyIncomeGroup
		YearlyIncomeGroup low= new YearlyIncomeGroup("L",now,"0-25000");
		YearlyIncomeGroup middel= new YearlyIncomeGroup("M",now,"25001-50000");		
		YearlyIncomeGroup high= new YearlyIncomeGroup("H",now,"50001-75000");
		YearlyIncomeGroup veryHigh= new YearlyIncomeGroup("VH",now,"75001-100000");
		YearlyIncomeGroup excess= new YearlyIncomeGroup("EX",now,"greater than 100000");
		targetDao.persistObject(low);
		targetDao.persistObject(middel);
		targetDao.persistObject(high);
		targetDao.persistObject(veryHigh);
		targetDao.persistObject(excess);
		map.put("YearlyIncomeGroup", 4L);
		//HomeOwner
	HomeOwner yes =new HomeOwner("YES", now);
	HomeOwner no =new HomeOwner("NO", now);
	targetDao.persistObject(yes);
	targetDao.persistObject(no);
	map.put("HomeOwner", 2L);
		
		 now =new Timestamp(System.currentTimeMillis());
		Timestamp init= new Timestamp(0);	
		int offset = 0;
List<DreiTupel> list = new ArrayList<DreiTupel>();
	        List<Object[]> customers;
	        while ((customers =   this.sourceDao.getJoinedCustomerAndStore(offset, Constants.RESULTSETSIZE)).size() > 0)	        
	        {
	        	list = new ArrayList<DreiTupel>();
	        	log.info("Start Block");
	        	for(Object[] o: customers){
	        	now =new Timestamp(System.currentTimeMillis());
	       Customer c= new Customer();
	       c.setModfiedDate(now);
	       c.setFromDate(init);
	       c.setToDate(null);
	       adventureworks.entity.dimensions.customer.Store s= new adventureworks.entity.dimensions.customer.Store();
	    s.setName((String) o[1]);
	    s.setModfiedDate(now);
	       Customer_MAP m =  new Customer_MAP();
	       m.setModifiedDate(now);
	       m.setFrom(init);
	       m.setTo(null);
	       m.setSourceKey(((Integer) o[0]).longValue());	     
	     list.add(new DreiTupel(c, s, m));
	    
	        	}
	        	 customerCount= customerCount+ list.size();
	    	     storeCount= storeCount+list.size();
	        	  this.targetDao.persistListOfEntities(list.stream().map(p->p.getOne()).collect(Collectors.toList()));	
	  	        for(DreiTupel tupel: list){
	  	        (( adventureworks.entity.dimensions.customer.Store)tupel.getTwo()).setStoreId(((Customer)tupel.getOne()).getCustomerId());	
	  	        ((Customer_MAP)tupel.getThree()).setDwhKey(((Customer)tupel.getOne()).getCustomerId());
	  	        }	
	  	        this.targetDao.persistListOfEntities(list.stream().map(p->p.getTwo()).collect(Collectors.toList()));			      
	  	        this.targetDao.persistListOfEntities(list.stream().map(p->p.getThree()).collect(Collectors.toList()));	
	  		
	        	
	        	  offset += customers.size();
		            log.info("Start Block:"+offset);
		}
	        
	    offset = 0;
	        while ((customers =    this.sourceDao.getJoinedCustomerAndIndividual(offset, Constants.RESULTSETSIZE)).size() > 0)	        
	        {
	        	log.info("Start Block");
	        	list = new ArrayList<DreiTupel>();
	        	for(Object[] o: customers){
		        	now =new Timestamp(System.currentTimeMillis());
	        	   Customer c= new Customer();
	        	    c.setModfiedDate(now);
	     	       c.setFromDate(init);
	     	       c.setToDate(null);
	        	   adventureworks.entity.dimensions.customer.Individual i = new adventureworks.entity.dimensions.customer.Individual();
	        	   IndividualSurvey survey =XmlParser.umarshal((String) o[1]);
	        	   
	        	 	      	     
		            LocalDate birthdate = LocalDate.parse(survey.getBirthDate().replaceAll("Z", ""));
		            LocalDate nowDate = LocalDate.now();		                
		          int mYear =Period.between(birthdate, nowDate).getYears();
		          if(mYear>= jung.getLower() && mYear<=jung.getUpper()){
		        i.setAgeGroupId(jung.getAgeGroupId());	  
		          }
		          if(mYear>= jugendlich.getLower() && mYear<=jugendlich.getUpper()){
				        i.setAgeGroupId(jugendlich.getAgeGroupId());	  
				          }
		          if(mYear>= mittel.getLower() && mYear<=mittel.getUpper()){
				        i.setAgeGroupId(mittel.getAgeGroupId());	  
				          }
		          if(mYear>= alt.getLower() && mYear<=alt.getUpper()){
				        i.setAgeGroupId(alt.getAgeGroupId());	  
				          }
		          if(low.getGroupName().equals(survey.getYearlyIncome())){
		        	  i.setYearlyIncome(low.getIncomeGroupCode());	 
		          }
if(middel.getGroupName().equals(survey.getYearlyIncome())){
		        i.setYearlyIncome(middel.getIncomeGroupCode());	  
		          }
if(high.getGroupName().equals(survey.getYearlyIncome())){
	 i.setYearlyIncome(high.getIncomeGroupCode());	
}
if(veryHigh.getGroupName().equals(survey.getYearlyIncome())){
	 i.setYearlyIncome(veryHigh.getIncomeGroupCode());	 
}
if(excess.getGroupName().equals(survey.getYearlyIncome())){
	 i.setYearlyIncome(excess.getIncomeGroupCode());	 
}
	        	   i.setModfiedDate(now);
		       i.setGender(survey.getGender());
		       i.setFirstName((String) o[3]);
		       i.setLastName((String) o[2]);
		       i.setHomeOwnerFlag(survey.isHomeOwnerFlag()==1?"YES":"NO");
	        	   
		     
	    	       Customer_MAP m =  new Customer_MAP();
	    	     m.setSourceKey(((Integer) o[0]).longValue());
	    	     m.setModifiedDate(now);
	  	       m.setFrom(init);
	  	       m.setTo(null);
	  	     list.add(new DreiTupel(c, i, m));
	        	
	        	}
	        	 customerCount= customerCount+ list.size();
	    	    individualCount= individualCount+list.size();
	        this.targetDao.persistListOfEntities(list.stream().map(p->p.getOne()).collect(Collectors.toList()));	
	        for(DreiTupel tupel: list){
	        ((adventureworks.entity.dimensions.customer.Individual)tupel.getTwo()).setIndividualId(((Customer)tupel.getOne()).getCustomerId());	
	        ((Customer_MAP)tupel.getThree()).setDwhKey(((Customer)tupel.getOne()).getCustomerId());
	        }	
	        this.targetDao.persistListOfEntities(list.stream().map(p->p.getTwo()).collect(Collectors.toList()));			      
	        this.targetDao.persistListOfEntities(list.stream().map(p->p.getThree()).collect(Collectors.toList()));	
		      
	        	
	        	  offset += customers.size();
		            log.info("Start Block:"+offset);
		}
	map.put("Customer", customerCount);
	map.put("Store", storeCount);
	map.put("Individual", individualCount);
	return map;	
	}
	

	public HashMap<String, Long> update() {
	targetDao.getLatestEtlMeta();
	return new HashMap<>();
		
	}
	
	
	 
}