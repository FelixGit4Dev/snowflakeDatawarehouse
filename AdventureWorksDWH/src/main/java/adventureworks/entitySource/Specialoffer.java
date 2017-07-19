package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the specialoffer database table.
 * 
 */
@Entity
@NamedQuery(name="Specialoffer.findAll", query="SELECT s FROM Specialoffer s")
public class Specialoffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int specialOfferID;

	private String category;

	private String description;

	private Double discountPct;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private Integer maxQty;

	private Integer minQty;

	private Timestamp modifiedDate;

	private byte[] rowguid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private String type;

	public Specialoffer() {
	}

	public int getSpecialOfferID() {
		return this.specialOfferID;
	}

	public void setSpecialOfferID(int specialOfferID) {
		this.specialOfferID = specialOfferID;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDiscountPct() {
		return this.discountPct;
	}

	public void setDiscountPct(Double discountPct) {
		this.discountPct = discountPct;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}

	public Integer getMinQty() {
		return this.minQty;
	}

	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}