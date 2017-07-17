package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

	public void initDimension() {
		Timestamp startFrom = new Timestamp(0);
		initProductLineClassStyle();
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
	         
	            Category_MAP map= new Category_MAP(salesHeader.getProductCategoryID(),category.getCategoryId(),now,startFrom,null);
	           createSubcategory(category.getCategoryId());
	            this.targetDao.persistCategory_MAP(map);
	            
	            }
	            offset += salesHeaders.size();
	        }
	        createUndefined();
		
	}
	
	
	private void initProductLineClassStyle() {
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
	}


	public void createSubcategory(long id){
		int offset = 0;

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
        targetDao.persistSubcategory(subcategory);
        
        Subcategory_MAP map= new Subcategory_MAP(salesHeader.getProductSubcategoryID(),subcategory.getSubcategoryId(),now,new Timestamp(0),null);
        targetDao.persistObject(map);    	
        createProduct(salesHeader.getProductSubcategoryID());
            
            }
            offset += salesHeaders.size();
        }	
	}
	
	public void createProduct(long id){
	
		int offset = 0;

        List<adventureworks.entitySource.Product> salesHeaders;
        while ((salesHeaders = sourceDao.getProductsBySubcategoryId(id,offset, Constants.RESULTSETSIZE)).size() > 0)
        {
       
            for (adventureworks.entitySource.Product salesHeader : salesHeaders)
            {
          Product product = new Product();
          product.setSubcategoryId(id);
          product.setStyle(salesHeader.getStyle());
          product.setProductLine(salesHeader.getProductLine());
          product.setName(salesHeader.getName());
          product.setMakeFlag(((salesHeader.getMakeFlag()==1)?"M":"B"));
          product.setKlasse(salesHeader.getClass_());
        Timestamp now =new Timestamp(System.currentTimeMillis());
          product.setModfiedDate(now);
          targetDao.persistObject(product);
          Product_MAP map= new Product_MAP(((long)salesHeader.getProductID()),product.getProductId(),now,new Timestamp(0), null);
          map.setModifiedDate(now);
          targetDao.persistObject(map);
            	
            
            }
            offset += salesHeaders.size();
        }		
	} 
	
	
	
	public void createUndefined(){
		Timestamp now =new Timestamp(System.currentTimeMillis());
		Category category = new Category(new Timestamp(0), null, "Other");	
		 category.setModfiedDate(now);
		 targetDao.persistObject(category);
		 Subcategory subcategory= new Subcategory();
		 subcategory.setName("Other");
		 subcategory.setModfiedDate(now);
		 subcategory.setCategoryId(category.getCategoryId());
		 targetDao.persistObject(subcategory);
		 int offset = 0;
		    List<adventureworks.entitySource.Product> salesHeaders;
		  while ((salesHeaders = sourceDao.getProductsBySubcategoryId(null,offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
		 for(adventureworks.entitySource.Product p: salesHeaders){
			 Product product = new Product();
	          product.setSubcategoryId(subcategory.getSubcategoryId());
	          product.setStyle(p.getStyle());
	          product.setProductLine(p.getProductLine());
	          product.setName(p.getName());
	          product.setMakeFlag(((p.getMakeFlag()==1)?"M":"B"));
	          product.setKlasse(p.getClass_());
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

	public void update() {
		
		
	}

}
