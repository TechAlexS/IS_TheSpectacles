package gestioneAcquisto;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'entità Indirizzi.
 * @author Mario Ranieri
 */
public class IndirizziBean implements Serializable {
	
	int idIndirizzo;
	String address;
	int status;
	String city;
	String province;
	int cap;
	String email;
	String telefono;
	String name;
	String surname;
	
	public IndirizziBean() {
		
	}
	

	/**
	 * @param idIndirizzo id dell'indirizzo
	 * @param address la stringa dell'indirizzo
	 * @param status lo stato associato all'indirizzo
	 * @param city la città associata all'indirizzo
	 * @param province la provincia associata all'indirizzo
	 * @param cap il CAP associato all'indirizzo
	 * @param email l'email associata all'indirizzo
	 * @param telefono il telefono associato all'indirizzo
	 * @param name il nome associato all'indirizzo
	 * @param surname il cognome associato all'indirizzo
	 * @return 
	 */
	public IndirizziBean(int idIndirizzo, String address, int status, String city, String province, int cap,
			String email, String telefono, String name, String surname) {
		
		this.idIndirizzo=idIndirizzo;
		this.address=address;
		this.status=status;
		this.city=city;
		this.province=province;
		this.cap=cap;
		this.email=email;
		this.telefono=telefono;
		this.name=name;
		this.surname=surname;
	}

	/**
	 * @return idIndirizzo id dell'indirizzo
	 */
	public int getIdIndirizzo() {
		return idIndirizzo;
	}
	
	/**
	 * @param idIndirizzo id da assegnare all'indirizzo
	 * @return
	 */
	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	
	/**
	 * @return address stringa dell'indirizzo
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address stringa da assegnare all'indirizzo
	 * @return
	 */
	public void setAddress(String address) {
		this.address=address;
	}
	
	/**
	 * @return status stato dell'indirizzo
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * @param status stato da assegnare all'indirizzo
	 * @return
	 */
	public void setStatus(int i) {
		this.status=i;
	}
	
	/**
	 * @return city città dell'indirizzo
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city città da assegnare all'indirizzo
	 * @return
	 */
	public void setCity(String city) {
		this.city=city;
	}
	
	/**
	 * @return province provincia dell'indirizzo
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * @param province provincia da assegnare all'indirizzo
	 * @return
	 */
	public void setProvince(String province) {
		this.province=province;
	}
	
	/**
	 * @return cap CAP dell'indirizzo
	 */
	public int getCap() {
		return cap;
	}
	
	/**
	 * @param cap CAP da assegnare all'indirizzo
	 * @return
	 */
	public void setCap(int cap) {
		this.cap=cap;
	}
	
	/**
	 * @return email email associata all'indirizzo
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email email da assegnare all'indirizzo
	 * @return
	 */
	public void setEmail(String email) {
		this.email=email;
	}
	
	/**
	 * @return telefono telefono associato all'indirizzo
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * @param telefono telefono da assegnare all'indirizzo
	 * @return
	 */
	public void setTelefono(String telefono) {
		this.telefono=telefono;
	}
	
	/**
	 * @return name nome associato all'indirizzo
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name nome da assegnare all'indirizzo
	 * @return
	 */
	public void setName(String name) {
		this.name=name;
	}
	
	/**
	 * @return surname cognome associato all'indirizzo
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * @param surname cognome da assegnare all'indirizzo
	 * @return
	 */
	public void setSurname(String surname) {
		this.surname=surname;
	}
	
	@Override
	public String toString() {
		return "Indirizzi [idIndirizzo=" + idIndirizzo + "name=" + name + "surname=" + surname + ", address=" + address + ", status=" + status + ", city="
				+ city + ", province=" + province + ", cap=" + cap + ", email=" + email + ", telefono=" + telefono + "]";
	}
}