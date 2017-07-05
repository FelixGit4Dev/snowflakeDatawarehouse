package adventureworks.entity.facts;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALES_FACT")
public class SalesFact implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private	long saleFactId;
private	 long shippingMethodId;
private	long billToAdressId;
private	long salesReasonId;
private	long productId;
private long territoryId;

}
