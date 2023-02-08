package gestioneUtenza;

import java.io.Serializable;
import java.util.Date; 

/**
 * Questa classe rappresenta l'entità Utente.
 * @author Mario Ranieri
 */
public class UtenteBean implements Serializable, Cloneable {

	private static final long serialVersionUID=-850475236728288737L;
	private String pass;
	private int role;
	private String email;
	private String firstName;
	private String lastName;
	private Date birthday;

	
	public UtenteBean() {
	}
	/**
	 * @param firstName nome dell'utente
	 * @param lastName cognome dell'utente
	 * @param email indirizzo email dell'utente
	 * @param birthday data di nascita dell'utente
	 * @param pass password dell'utente
	 * @param role ruolo dell'utente
	 * @return 
	 */
	public UtenteBean(String email, String pass,String firstName,String lastName,Date birthday,int role) {
		this.firstName=firstName;
		this.email=email;
		this.pass=pass;
		this.lastName=lastName;
		this.birthday=birthday;
		this.role=role;
	}
	
	/**
	 * @return pass password dell'utente
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @return role ruolo dell'utente(standard o admin)
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @return email email dell'utente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return firstName nome dell'utente
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return lastName cognome dell'utente
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return birthday data di nascita dell'utente
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param pass password da assegnare all'utente
	 * @return 
	 */
	public void setPass(String pass) {
		this.pass=pass;
	}

	/**
	 * @param role ruolo da assegnare all'utente(standard o admin)
	 * @return 
	 */
	public void setRole(int role) {
		this.role=role;
	}

	/**
	 * @param email indirizzo email dell'utente
	 * @return 
	 */
	public void setEmail(String email) {
		this.email=email;
	}

	/**
	 * @param firstName nome da assegnare all'utente
	 * @return 
	 */
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}

	/**
	 * @param lastName cognome da assegnare all'utente
	 * @return 
	 */
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}

	/**
	 * @param birthdat data di nascita da assegnare all'utente
	 * @return 
	 */
	public void setBirthday(Date birthday) {
		this.birthday=birthday;
	}

	@Override
	public String toString() {
		return "UtenteBean [pass=" + pass + ", role=" + role + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthday=" + birthday+ "]";
	}
}