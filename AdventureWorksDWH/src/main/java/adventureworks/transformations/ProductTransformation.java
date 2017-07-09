package adventureworks.transformations;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.Constants;
import adventureworks.DAO.DwhSourceAccess;
import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.dimensions.product.Category;
import adventureworks.entity.dimensions.product.Product;
import adventureworks.entity.dimensions.product.Subcategory;
import adventureworks.entity.maps.Category_MAP;
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
	            Category category = new Category(salesHeader.getModifiedDate(), null, salesHeader.getName());
	            category= this.targetDao.persistCategory(category);
	            Category_MAP map= new Category_MAP(salesHeader.getProductCategoryID(),category.getCategoryId(),new Timestamp((new Date()).getTime()),new Timestamp((new Date()).getTime()),new Timestamp((new Date()).getTime()));
	           this.targetDao.persistCategory_MAP(map);
	            
	            }
	            offset += salesHeaders.size();
	        }
		
	}
	
	
	public void createSubcategory(int id){
		int offset = 0;

        List<Productsubcategory> salesHeaders;
        while ((salesHeaders = sourceDao.getSubcategoriesByCategoryId(id,offset, Constants.RESULTSETSIZE)).size() > 0)
        {
       
            for (Productsubcategory salesHeader : salesHeaders)
            {
          Subcategory subcategory= new Subcategory();
          
            	createProduct(salesHeader.getProductSubcategoryID());
            
            }
            offset += salesHeaders.size();
        }	
	}
	
	public void createProduct(int id){
		int offset = 0;

        List<adventureworks.entitySource.Product> salesHeaders;
        while ((salesHeaders = sourceDao.getProductsBySubcategoryId(id,offset, Constants.RESULTSETSIZE)).size() > 0)
        {
       
            for (adventureworks.entitySource.Product salesHeader : salesHeaders)
            {
          Product product = new Product();
          targetDao.persistProduct(product);
          
            	
            
            }
            offset += salesHeaders.size();
        }		
	} 
	
	

	public void update() {
		// TODO Auto-generated method stub
		
	}

}
