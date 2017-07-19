package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.entity.dimensions.product.Category;
import adventureworks.entity.dimensions.product.MakeFlag;
import adventureworks.entity.dimensions.product.Product;
import adventureworks.entity.dimensions.product.ProductClasse;
import adventureworks.entity.dimensions.product.ProductLine;
import adventureworks.entity.dimensions.product.ProductStyle;
import adventureworks.entity.dimensions.product.Subcategory;
import adventureworks.entity.maps.Category_MAP;
import adventureworks.entity.maps.Product_MAP;
import adventureworks.entity.maps.Subcategory_MAP;
import adventureworks.entitySource.Productcategory;
import adventureworks.entitySource.Productsubcategory;
import adventureworks.transformations.meta.Transformation;

@Named
@ApplicationScoped
public class ProductTransformation implements Transformation{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Inject
private DwhSourceAccess sourceDao;
@Inject
private DwhTargetAcess targetDao;
private Long categoryCount;
private Long subcategoryCount;
private Long productCount;


	public HashMap<String, Long> initDimension() {
		HashMap<String, Long> mapCount= new HashMap<>();
		categoryCount = 0L;
		subcategoryCount = 0L;
		productCount = 0L;
		Timestamp startFrom = new Timestamp(0);
		initProductLineClassStyle(mapCount);
		   int offset = 0;

	        List<Productcategory> salesHeaders;
	        while ((salesHeaders = sourceDao.getAllCategories(offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
	       
	            for (Productcategory salesHeader : salesHeaders)
	            {
	            Category category = new Category(new Timestamp(0), null, salesHeader.getName());
	            Timestamp now =new Timestamp(System.currentTimeMillis());
	            category.setModfiedDate(now);
	            category= this.targetDao.persistCategory(category);
	         categoryCount++;
	            Category_MAP map= new Category_MAP(salesHeader.getProductCategoryID(),category.getCategoryId(),now,startFrom,null);
	           createSubcategory(category.getCategoryId());
	            this.targetDao.persistCategory_MAP(map);
	            
	            }
	            offset += salesHeaders.size();
	        }
	        createUndefined();
	        mapCount.put("Product", productCount );
	        mapCount.put("Category", categoryCount );
	        mapCount.put("Subcategory", subcategoryCount );
	    
	        return mapCount;
		
	        
	}
	
	
	private void initProductLineClassStyle(HashMap<String, Long> mapCount) {
	    Timestamp now =new Timestamp(System.currentTimeMillis());
		List<Object> topersist = new ArrayList<Object>();
		ProductLine line = new ProductLine("R", "Cityrad");
		 line.setModfiedDate(now);
	topersist.add(line);
		 line = new ProductLine("M", "Mountainbike");
		 line.setModfiedDate(now);
		 topersist.add(line);
		
		 line = new ProductLine("T", "Tourenrad");
		 line.setModfiedDate(now);
		 topersist.add(line);
		 line = new ProductLine("S", "Standardrad");
		 line.setModfiedDate(now);
		 topersist.add(line);
		 line = new ProductLine("UD", "UNDEFINED");
		 line.setModfiedDate(now);
		 topersist.add(line);
		 
		ProductClasse classe = new ProductClasse("H", "Hoch");	
		classe.setModfiedDate(now);
		topersist.add(classe);
		classe= new ProductClasse("M", "Mittel");
		classe.setModfiedDate(now);
		topersist.add(classe);
		classe = new ProductClasse("L","Niedrig");
		classe.setModfiedDate(now);
		topersist.add(classe);
		classe= new ProductClasse("UD", "UNDEFINED");
		classe.setModfiedDate(now);
		topersist.add(classe);
		
		ProductStyle style = new ProductStyle("W", "Damenrad");
		style.setModfiedDate(now);
		topersist.add(style);
		style = new ProductStyle("M", "Herrenrad");
		style.setModfiedDate(now);
		topersist.add(style);
		style = new ProductStyle("U", "Universalrad");
		style.setModfiedDate(now);
		topersist.add(style);
		style = new ProductStyle("UD", "UNDEFIND");
		style.setModfiedDate(now);
		topersist.add(style);
		
		
		MakeFlag flag = new MakeFlag("M", "SelfMade");
		flag.setModfiedDate(now);
		topersist.add(flag);
		flag = new MakeFlag("B", "Bought");
		flag.setModfiedDate(now);
		topersist.add(flag);
		targetDao.persistListOfEntities(topersist);
		
	    mapCount.put("ProductLine", 5L );
        mapCount.put("ProductStyle", 4L );
        mapCount.put("ProductClass", 4L );
        mapCount.put("MakeFlag", 2L );
        
	}


	public void createSubcategory(long id){
		int offset = 0;
Timestamp inital= new Timestamp(0);
        List<Productsubcategory> salesHeaders;
        while ((salesHeaders = sourceDao.getSubcategoriesByCategoryId(id,offset, Constants.RESULTSETSIZE)).size() > 0)
        {
       
            for (Productsubcategory salesHeader : salesHeaders)
            {
          Subcategory subcategory= new Subcategory();
          subcategory.setName(salesHeader.getName());
          subcategory.setCategoryId(id);
          Timestamp now =new Timestamp(System.currentTimeMillis());
          subcategory.setModfiedDate(now);
          subcategory.setFromDate(inital);
        targetDao.persistSubcategory(subcategory);
        subcategoryCount++;
        
        Subcategory_MAP map= new Subcategory_MAP(salesHeader.getProductSubcategoryID(),subcategory.getSubcategoryId(),now,new Timestamp(0),null);
        targetDao.persistObject(map);    	
        createProduct(salesHeader.getProductSubcategoryID());
            
            }
            offset += salesHeaders.size();
        }	
	}
	
	public void createProduct(long id){
	Timestamp init= new Timestamp(0);
		int offset = 0;

        List<adventureworks.entitySource.Product> salesHeaders;
        while ((salesHeaders = sourceDao.getProductsBySubcategoryId(id,offset, Constants.RESULTSETSIZE)).size() > 0)
        {
       
            for (adventureworks.entitySource.Product salesHeader : salesHeaders)
            {
          Product product = new Product();
          product.setSubcategoryId(id);
          product.setStyle(salesHeader.getStyle()==null?"UD":salesHeader.getStyle());
          product.setProductLine(salesHeader.getProductLine()==null?"UD":salesHeader.getProductLine());
          product.setName(salesHeader.getName());
          product.setMakeFlag(((salesHeader.getMakeFlag()==1)?"M":"B"));
          product.setKlasse(salesHeader.getClass_()==null?"UD":salesHeader.getClass_());
        Timestamp now =new Timestamp(System.currentTimeMillis());
          product.setModfiedDate(now);
          product.setFromDate(init);
          product.setStandartCost(salesHeader.getStandardCost());
          targetDao.persistObject(product);
          productCount++;
          Product_MAP map= new Product_MAP(((long)salesHeader.getProductID()),product.getProductId(),now,new Timestamp(0), null);
          map.setModifiedDate(now);
          targetDao.persistObject(map);
            	
            
            }
            offset += salesHeaders.size();
        }		
	} 
	
	
	
	public void createUndefined(){
		Timestamp init= new Timestamp(0);
		Timestamp now =new Timestamp(System.currentTimeMillis());
		Category category = new Category(new Timestamp(0), null, "Other");	
		 category.setModfiedDate(now);
		 targetDao.persistObject(category);
		 Subcategory subcategory= new Subcategory();
		 subcategory.setName("Other");
		 subcategory.setModfiedDate(now);
		 subcategory.setCategoryId(category.getCategoryId());
		 subcategory.setFromDate(init);
		 targetDao.persistObject(subcategory);
		 int offset = 0;
		    List<adventureworks.entitySource.Product> salesHeaders;
		  while ((salesHeaders = sourceDao.getProductsBySubcategoryId(null,offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
		 for(adventureworks.entitySource.Product p: salesHeaders){
			 Product product = new Product();
	          product.setSubcategoryId(subcategory.getSubcategoryId());
	          product.setStyle(p.getStyle()==null?"ID":p.getStyle());
	          product.setProductLine(p.getProductLine()==null?"UD":p.getProductLine());
	          product.setName(p.getName());
	          product.setMakeFlag(((p.getMakeFlag()==1)?"M":"B"));
	          product.setKlasse(p.getClass_()==null?"UD":p.getClass_());
	          product.setStandartCost(p.getStandardCost());
	          product.setFromDate(init);
	      now =new Timestamp(System.currentTimeMillis());
	          product.setModfiedDate(now);	
	          targetDao.persistObject(product);
	          Product_MAP map= new Product_MAP(((long)p.getProductID()),product.getProductId(),now,new Timestamp(0), null);
	          map.setModifiedDate(now);
	          targetDao.persistObject(map);
		 }
		  offset += salesHeaders.size();
	        }
	}

	public HashMap<String, Long> update() {
		categoryCount = 0L;
		subcategoryCount = 0L;
		productCount = 0L;
		return new HashMap<>();
		
	}

}
