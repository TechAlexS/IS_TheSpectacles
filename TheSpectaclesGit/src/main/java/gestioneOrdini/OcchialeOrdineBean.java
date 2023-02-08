package gestioneOrdini;

import java.io.Serializable;
import java.util.UUID;

import gestioneOcchiali.OcchialeBean;

/**
 * Questa classe rappresenta l'entità OcchialeOrdine (i vari occhiali presenti nell'ordine).
 * @author Mario Ranieri
 */
public class OcchialeOrdineBean implements Serializable {

	private static final long serialVersionUID = -1258634432830603623L;
	private int idOcchialeOrdine;
	private UUID idOrdine;
	private OcchialeBean occhiale;
	private String idOcchiale;
	private int prezzoEffettivo;
	private float iva;
	private int quantita;

	/**
	 * @param idOcchialeOrdine id dell'occhialeOrdine
	 * @param occhiale occhiale dell'occhialeOrdine
	 * @param prezzoEffettivo prezzo dell'occhialeOrdine
	 * @param iva iva dell'occhialeOrdine
	 * @param quantita quantità dell'occhialeOrdine
	 * @return 
	 */
	public OcchialeOrdineBean() {
		this.idOcchialeOrdine=0;
		this.occhiale=null;
		this.prezzoEffettivo=0;
		this.iva=0;
		this.quantita=0;
	}
	
	/**
	 * @param idOcchialeOrdine id dell'occhialeOrdine
	 * @param idOrdine id dell'ordine
	 * @param occhiale occhiale dell'occhialeOrdine
	 * @param prezzoEffettivo prezzo dell'occhialeOrdine
	 * @param iva iva dell'occhialeOrdine
	 * @param quantita quantità dell'occhialeOrdine
	 * @return 
	 */
	public OcchialeOrdineBean(int idOcchialeOrdine, UUID idOrdine, OcchialeBean occhiale, int prezzoEffettivo, float iva, int quantita) {
		this.idOcchialeOrdine=idOcchialeOrdine;
		this.idOrdine=idOrdine;
		this.occhiale=occhiale;
		this.prezzoEffettivo=prezzoEffettivo;
		this.iva=iva;
		this.quantita=quantita;
	}
	
	/**
	 * @return idOcchialeOrdine id dell'occhialeOrdine
	 */
	public int getIdOcchialeOrdine() {
		return idOcchialeOrdine;
	}

	/**
	 * @return idOrdine id dell'ordine
	 */
	public UUID getIdOrdine() {
		return idOrdine;
	}

	/**
	 * @return occhiale occhiale dell'occhialeOrdine
	 */
	public OcchialeBean getProdotto() {
		return occhiale;
	}
	
	/**
	 * @return idOcchiale id dell'occhialeOrdine
	 */
	public String getIdProdotto() {
		return idOcchiale;
	}

	/**
	 * @return prezzoEffettivo prezzo dell'occhialeOrdine
	 */
	public float getPrezzoEffettivo() {
		return prezzoEffettivo;
	}

	/**
	 * @return iva iva dell'occhialeOrdine
	 */
	public float getIva() {
		return iva;
	}

	/**
	 * @return quantita quantità dell'occhialerdine
	 */
	public int getQuantita() {
		return quantita;
	}

	/**
	 * @param id id da assegnare all'occhialeOrdine
	 * @return
	 */
	public void setIdProdotto(String id) {
		this.idOcchiale=id;
	}
	
	/**
	 * @param idOcchialeOrdine idOcchiale da assegnare all'ordine
	 * @return
	 */
	public void setIdOcchialeOrdine(int idOcchialeOrdine) {
		this.idOcchialeOrdine=idOcchialeOrdine;
	}

	/**
	 * @param idOrdine id da assegnare all'ordine
	 * @return
	 */
	public void setIdOrdine(UUID idOrdine) {
		this.idOrdine=idOrdine;
	}

	/**
	 * @param occhiale occhiale da assegnare all'occhialeOrdine
	 * @return
	 */
	public void setProdotto(OcchialeBean occhiale) {
		this.occhiale=occhiale;
	}

	/**
	 * @param prezzoEffettivo prezzo da assegnare all'occhialeOrdine
	 * @return
	 */
	public void setPrezzoEffettivo(int prezzoEffettivo) {
		this.prezzoEffettivo=prezzoEffettivo;
	}

	/**
	 * @param quantita quantità da assegnare all'occhialeOrdine
	 * @return
	 */
	public void setQuantita(int quantita) {
		this.quantita=quantita;
	}
	
	@Override
	public String toString() {
		return "OcchialeOrdineBean [idOcchialeOrdine=" + idOcchialeOrdine + ", idOrdine=" + idOrdine + ", occhiale=" + idOcchiale + ",prezzoEffettivo="
				+ prezzoEffettivo+ ", iva=" + iva + ", quantita=" +quantita  + "]";
	}
}