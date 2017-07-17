package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int productID;

	@Column(name="Class", length=2)
	private String class_;

	@Column(length=15)
	private String color;

	@Column(nullable=false)
	private int daysToManufacture;

	@Temporal(TemporalType.TIMESTAMP)
	private Date discontinuedDate;

	@Column(nullable=false)
	private byte finishedGoodsFlag;

	@Column(nullable=false)
	private double listPrice;

	@Column(nullable=false)
	private byte makeFlag;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(length=2)
	private String productLine;

	private Integer productModelID;

	@Column(nullable=false, length=25)
	private String productNumber;

	private Integer productSubcategoryID;

	@Column(nullable=false)
	private short reorderPoint;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private short safetyStockLevel;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sellEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date sellStartDate;

	@Column(length=5)
	private String size;

	@Column(length=3)
	private String sizeUnitMeasureCode;

	@Column(nullable=false)
	private double standardCost;

	@Column(length=2)
	private String style;

	@Column(precision=10, scale=2)
	private BigDecimal weight;

	@Column(length=3)
	private String weightUnitMeasureCode;

	public Product() {
	}

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getDaysToManufacture() {
		return this.daysToManufacture;
	}

	public void setDaysToManufacture(int daysToManufacture) {
		this.daysToManufacture = daysToManufacture;
	}

	public Date getDiscontinuedDate() {
		return this.discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public byte getFinishedGoodsFlag() {
		return this.finishedGoodsFlag;
	}

	public void setFinishedGoodsFlag(byte finishedGoodsFlag) {
		this.finishedGoodsFlag = finishedGoodsFlag;
	}

	public double getListPrice() {
		return this.listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public byte getMakeFlag() {
		return this.makeFlag;
	}

	public void setMakeFlag(byte makeFlag) {
		this.makeFlag = makeFlag;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductLine() {
		return this.productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public Integer getProductModelID() {
		return this.productModelID;
	}

	public void setProductModelID(Integer productModelID) {
		this.productModelID = productModelID;
	}

	public String getProductNumber() {
		return this.productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Integer getProductSubcategoryID() {
		return this.productSubcategoryID;
	}

	public void setProductSubcategoryID(Integer productSubcategoryID) {
		this.productSubcategoryID = productSubcategoryID;
	}

	public short getReorderPoint() {
		return this.reorderPoint;
	}

	public void setReorderPoint(short reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public short getSafetyStockLevel() {
		return this.safetyStockLevel;
	}

	public void setSafetyStockLevel(short safetyStockLevel) {
		this.safetyStockLevel = safetyStockLevel;
	}

	public Date getSellEndDate() {
		return this.sellEndDate;
	}

	public void setSellEndDate(Date sellEndDate) {
		this.sellEndDate = sellEndDate;
	}

	public Date getSellStartDate() {
		return this.sellStartDate;
	}

	public void setSellStartDate(Date sellStartDate) {
		this.sellStartDate = sellStartDate;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeUnitMeasureCode() {
		return this.sizeUnitMeasureCode;
	}

	public void setSizeUnitMeasureCode(String sizeUnitMeasureCode) {
		this.sizeUnitMeasureCode = sizeUnitMeasureCode;
	}

	public double getStandardCost() {
		return this.standardCost;
	}

	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getWeightUnitMeasureCode() {
		return this.weightUnitMeasureCode;
	}

	public void setWeightUnitMeasureCode(String weightUnitMeasureCode) {
		this.weightUnitMeasureCode = weightUnitMeasureCode;
	}

}