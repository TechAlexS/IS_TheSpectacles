package gestioneOrdini;

import java.io.Serializable;
import java.util.UUID;
import java.util.Date;

/**
 * Questa classe rappresenta l'entità Ordine.
 * @author Mario Ranieri
 */
public class OrdineBean implements Serializable {
	
	UUID idOrder;
	Date data;
	String email;
	String stato;
	int tot=0;
	
	/**
	 * @param idOrder id dell'ordine
	 * @param data data dell'ordine
	 * @param email email dell'ordine
	 * @param stato stato dell'ordine
	 * @return 
	 */
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
	public OrdineBean(UUID idOrder, Date data, String email,String stato) {
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
	 * @param idOrder id da assegnare all'ordine
	 * @precondition idOrder==NULL
	 * @postcodition idOrder!=NULL
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
	 * @param stato stato da assegnare all'ordine
	 * @precondition stato==”confermato” OR stato==”in elaborazione” OR stato==”spedito” 
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
	
	/**
	 * @param email email da assegnare all'ordine
	 * @precondition email deve avere una corrispondenza come chiave di un Utente
	 * @postcondition email corrisponde ad un Utente
	 * @return
	 */
	public void setEmail(String email) {
		this.email=email;
	}

	@Override
	public String toString() {
		return "Ordine [idOrder=" + idOrder + ", date=" + data + ", email=" + email + ", tot = " + tot + "]";
	}
}
