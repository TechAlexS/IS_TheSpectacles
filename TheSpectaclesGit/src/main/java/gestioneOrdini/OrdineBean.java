package gestioneOrdini;

import java.io.Serializable;
import java.util.UUID;
import java.util.Date;

/**
 * Questa classe rappresenta l'entita' Ordine.
 * @author Mario Ranieri
 */
public class OrdineBean implements Serializable {
	private UUID idOrder;
	private Date data;
	private String email;
	private String stato;
	private int tot=0;
	
	public OrdineBean() {
		this.idOrder=null;
		this.email=null;
		this.data=null;
		this.stato=null;	
	}
	
	/**
	 * @param idOrder id dell'ordine
	 * @param data data dell'ordine
	 * @param email email dell'ordine
	 * @param stato stato dell'ordine
	 * @return 
	 */
	public OrdineBean(UUID idOrder, Date data, String email, String stato) {
		super();
		this.idOrder=idOrder;
		this.data=data;
		this.email=email;
		this.stato=stato;
	}

	/**
	 * @return idOrdine id dell'ordine
	 */
	public UUID getIdOrder() {
		return idOrder;
	}

	/**
	 * @precondition idOrder non deve essere gia' presente nel DB
	 * @postcondition idOrder e' presente nel DB
	 * @param idOrder id da assegnare all'ordine
	 * @return
	 */
	public void setIdOrder(UUID idOrder) {
		this.idOrder=idOrder;
	}

	/**
	 * @return data data dell'ordine
	 */
	public Date getDate() {
		return data;
	}
	
	/**
	 * @return stato stato dell'ordine
	 */
	public String getStato() {
		return stato;
	}
	
	/**
	 * @precondition stato=="confermato" OR stato=="in elaborazione" OR stato=="spedito"
	 * @param stato stato da assegnare all'ordine 
	 * @return
	 */
	public void setStato(String stato) {
		this.stato=stato;
	}
	
	/**
	 * @param data data da assegnare all'ordine
	 * @return
	 */
	public void setDate(Date data) {
		this.data=data;
	}
	
	/**
	 * @precondition idOrder non deve essere gia' presente nel DB
	 * @postcondition email corrisponde ad un utente presente nel DB
	 * @param email email da assegnare all'ordine
	 * @return
	 */
	public void setEmail(String email) {
		this.email=email;
	}

	/**
	 * @return email email dell'ordine
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return tot totale dell'ordine
	 */
	public int getTot() {
		return tot;
	}
	
	/**
	 * @param tot totale da assegnare all'ordine
	 * @return
	 */
	public void setTot(int tot) {
		this.tot=tot;
	}

	@Override
	public String toString() {
		return "Ordine [idOrder=" + idOrder + ", date=" + data + ", email=" + email + ", tot = " + tot + "]";
	}
}