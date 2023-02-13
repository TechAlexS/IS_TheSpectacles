package gestioneAcquisto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Questa classe rappresenta l'entita' Spedizione.
 * @author Mario Ranieri
 */
public class SpedizioneBean implements Serializable {
	private static final long serialVersionUID=-1258634432830603623L;
	private int idShipment;
	private Date dateShipment;
	private int idOrder;
	private int shipmentType;
	
	public SpedizioneBean() {	
	}
	
	/**
	 * @param idShipment id della spedizione
	 * @param dateShipment data della spedizione
	 * @param idOrder id dell'ordine associato alla spedizione
	 * @param shipmentType tipo di spedizione
	 * @return 
	 */
	public SpedizioneBean(int idShipment, Date dateShipment, int idOrder, int shipmentType) {
		this.idShipment=idShipment;
		this.dateShipment=dateShipment;
		this.idOrder=idOrder;
		this.shipmentType=shipmentType;
	}

	/**
	 * @precondition idShipment deve essere presente nel DB 
	 * @postcondition idShipment e' presente nel DB
	 * @return idShipment id della spedizione
	 */
	public int getIdShipment() {
		return idShipment;
	}

	/**
	 * @param idShipment id associato alla spedizione
	 * @return
	 */
	public void setIdShipment(int idShipment) {
		this.idShipment=idShipment;
	}

	/**
	 * @return idShipment id della spedizione
	 */
	public Date getDateShipment() {
		return dateShipment;
	}

	/**
	 * @param dateShipment data associata alla spedizione
	 * @return
	 */
	public void setDateShipment(Date dateShipment) {
		this.dateShipment=dateShipment;
	}

	/**
	 * @return idOrder id dell'ordine associato alla spedizione
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @precondition idOrder deve avere una corrispondeza nel DB come chiave di un Ordine 
	 * @postcondition idOrder ha una corrispondeza nel DB come chiave di un Ordine
	 * @param shipmentType tipo di spedizione
	 * @return
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder=idOrder;
	}

	/**
	 * @return shipmentType tipo di spedizione
	 */
	public int getShipmentType() {
		return shipmentType;
	}

	/**
	 * @param shipmentType tipo di spedizione
	 * @return
	 */
	public void setShipmentType(int shipmentType) {
		this.shipmentType=shipmentType;
	}

	@Override
	public String toString() {
		return "Spedizione [idShipment=" + idShipment + ", dateShipment=" + dateShipment + ", idOrder=" + idOrder + ", shipmentType=" + shipmentType + "]";
	}
}