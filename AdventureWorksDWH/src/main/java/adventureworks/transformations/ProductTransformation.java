package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.Util.Constants;
import adventureworks.entity.dimensions.product.Category;
import adventureworks.entity.dimensions.product.Product;
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
		   int offset = 0;

	        List<Productcategory> salesHeaders;
	        while ((salesHeaders = sourceDao.getAllCategories(offset, Constants.RESULTSETSIZE)).size() > 0)
	        {
	       
	            for (Productcategory salesHeader : salesHeaders)
	            {
	            Category category = new Category(new Timestamp(0), null, salesHeader.getName());
	            category= this.targetDao.persistCategory(category);
	            Timestamp now =new Timestamp(System.currentTimeMillis());
	            Category_MAP map= new Category_MAP(salesHeader.getProductCategoryID(),category.getCategoryId(),new Timestamp((new Date()).getTime()),new Timestamp((new Date()).getTime()),new Timestamp((new Date()).getTime()));
	           createSubcategory(category.getCategoryId());
	            this.targetDao.persistCategory_MAP(map);
	            
	            }
	            offset += salesHeaders.size();
	        }
		
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
        targetDao.persistSubcategory(subcategory);
        Timestamp now =new Timestamp(System.currentTimeMillis());
        Subcategory_MAP map= new Subcategory_MAP(salesHeader.getProductSubcategoryID(),subcategory.getSubcategoryId(),now,now,now);
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
          product.setMakeFlag(salesHeader.getMakeFlag());
          product.setKlasse(salesHeader.getClass_());
          targetDao.persistProduct(product);
          Product_MAP map= new Product_MAP();
          Timestamp now =new Timestamp(System.currentTimeMillis());
          map.setModifiedDate(now);
          targetDao.persistObject(map);
            	
            
            }
            offset += salesHeaders.size();
        }		
	} 
	
	

	public void update() {
		
		
	}

}
