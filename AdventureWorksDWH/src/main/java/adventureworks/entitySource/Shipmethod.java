package adventureworks.entitySource;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the shipmethod database table.
 * 
 */
@Entity
@Table(name="shipmethod")
@NamedQuery(name="Shipmethod.findAll", query="SELECT s FROM Shipmethod s")
public class Shipmethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int shipMethodID;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private byte[] rowguid;

	@Column(nullable=false)
	private double shipBase;

	@Column(nullable=false)
	private double shipRate;

	public Shipmethod() {
	}

	public int getShipMethodID() {
		return this.shipMethodID;
	}

	public void setShipMethodID(int shipMethodID) {
		this.shipMethodID = shipMethodID;
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

	public byte[] getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(byte[] rowguid) {
		this.rowguid = rowguid;
	}

	public double getShipBase() {
		return this.shipBase;
	}

	public void setShipBase(double shipBase) {
		this.shipBase = shipBase;
	}

	public double getShipRate() {
		return this.shipRate;
	}

	public void setShipRate(double shipRate) {
		this.shipRate = shipRate;
	}

}