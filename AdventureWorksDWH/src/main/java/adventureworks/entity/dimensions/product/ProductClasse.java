package adventureworks.entity.dimensions.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class ProductClasse {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long 	prodcutClassId;
}
