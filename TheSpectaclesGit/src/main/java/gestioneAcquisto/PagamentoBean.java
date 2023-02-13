package gestioneAcquisto;
import java.io.Serializable;
import java.sql.Date;

/**
 * Questa classe rappresenta l'entità Pagamento.
 * @author Mario Ranieri
 */
public class PagamentoBean implements Serializable {
	private int idPayment;
	private Date date;
	private int idPaymentType;
	private int idOrder;
	private int amount;
	
	public PagamentoBean() {
	}
	
	/**
	 * @param idPayment id del pagamento
	 * @param date la data del pagamento
	 * @param idPaymentType l'id del tipo di pagamento
	 * @param idOrder l'id dell'ordine associato al pagamento
	 * @param amount il prezzo totale associato al pagamento
	 * @return 
	 */
	public PagamentoBean(int idPayment, Date date, int idPaymentType, int idOrder, int amount) {
		this.idPayment=idPayment;
		this.date=date;
		this.idPaymentType=idPaymentType;
		this.idOrder=idOrder;
		this.amount=amount;
	}

	/**
	 * @return idPayment id associato al pagamento
	 */
	public int getIdPayment() {
		return idPayment;
	}

	/**
	 * @param idPayment id da assegnare al pagamento
	 * @return
	 */
	public void setIdPayment(int idPayment) {
		this.idPayment=idPayment;
	}

	/**
	 * @return date data associata al pagamento
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date data da assegnare al pagamento
	 * @return
	 */
	public void setDate(Date date) {
		this.date=date;
	}

	/**
	 * @return idPaymentType id del tipo di pagamento 
	 */
	public int getIdPaymentType() {
		return idPaymentType;
	}

	/**
	 * @param idPaymentType id del tipo di pagamento da assegnare 
	 * @return
	 */
	public void setIdPaymentType(int idPaymentType) {
		this.idPaymentType=idPaymentType;
	}

	/**
	 * @return idOrder id dell'ordine associato al pagamento
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder id dell'ordine da assegnare al pagamento
	 * @return
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder=idOrder;
	}

	/**
	 * @return amount prezzo totale associato al pagamento
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount prezzo totale da assegnare al pagamento
	 * @return
	 */
	public void setAmount(int amount) {
		this.amount=amount;
	}

	@Override
	public String toString() {
		return "Pagamento [idPayment=" + idPayment + ", date=" + date + ", idPaymentType=" + idPaymentType + ", idOrder=" + idOrder + ", amount=" + amount + "]";
	}
}