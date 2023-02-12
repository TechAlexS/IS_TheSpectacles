package gestioneOcchiali;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'entita' Occhiale.
 * @author Mario Ranieri
 */
public class OcchialeBean implements Serializable {
	private static final long serialVersionUID=-4959563723922476262L;
	String idGlasses;
	String nameGlasses;
	String brand;
	int price;
	int availability;
	String type;
	String color;
    String category;
	String image;
	String image2;
	String description;
	int quantity=1;
	float totalPrice;
	
	@Override
	public OcchialeBean() {
		quantity=1;
		totalPrice=0;
	}

	/**
	 * @param idGlasses id dell'occhiale
	 * @param nameGlasses nome dell'occhiale
	 * @param brand brand dell'occhiale
	 * @param price prezzo dell'occhiale
	 * @param avaiability disponibilità dell'occhiale
	 * @param type tipo di occhiale
	 * @param color colore dell'occhiale
	 * @param category categoria dell'occhiale
	 * @param image immagine dell'occhiale
	 * @param image2 seconda immagine dell'occhiale
	 * @param description descrizione dell'occhiale
	 * @return 
	 */
	public OcchialeBean(String idGlasses, String nameGlasses, String brand, int price, int availability, String type, String color, String category, String image,String image2, String description) {
		super();
		this.idGlasses=idGlasses;
		this.nameGlasses=nameGlasses;
		this.brand=brand;
		this.price=price;
		this.availability=availability;
		this.type=type;
		this.color=color;
		this.category=category;
		this.image=image;
		this.image2=image2;
		this.description=description;
	}

	/**
	 * @return idGlasses id dell'occhiale
	 */
	public String getIdGlasses() {
		return idGlasses;
	}

	/**
	 * @param idGlasses id da assegnare all'occhiale
	 * @precondition idGlasses non deve essere gia' presente nel DB 
	 * @postcondition idGlasses e' presente nel DB
	 * @return
	 */
	public void setIdGlasses(String idGlasses) {
		this.idGlasses=idGlasses;
	}

	/**
	 * @return nameGlasses nome dell'occhiale
	 */
	public String getNameGlasses() {
		return nameGlasses;
	}

	/**
	 * @param nameGlasses nome da assegnare all'occhiale
	 * @return
	 */
	public void setNameGlasses(String nameGlasses) {
		this.nameGlasses=nameGlasses;
	}

	/**
	 * @return brand brand dell'occhiale
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param setBrand brand da assegnare all'occhiale
	 * @return
	 */
	public void setBrand(String brand) {
		this.brand=brand;
	}

	/**
	 * @return price prezzo dell'occhiale
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param setPrice prezzo da assegnare all'occhiale
	 * @return
	 */
	public void setPrice(int price) {
		this.price=price;
	}

	/**
	 * @return availability disponibilita' dell'occhiale
	 */
	public int getAvailability() {
		return availability;
	}

	/**
	 * @param setAvaiability disponibilita' da assegnare all'occhiale
	 * @return
	 */
	public void setAvailability(int availability) {
		this.availability=availability;
	}

	/**
	 * @return type tipo dell'occhiale
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param setType tipo da assegnare all'occhiale
	 * @return
	 */
	public void setType(String type) {
		this.type=type;
	}

	/**
	 * @return color colore dell'occhiale
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param setColor colore da assegnare all'occhiale
	 * @return
	 */
	public void setColor(String color) {
		this.color=color;
	}

	/**
	 * @return category categoria dell'occhiale
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param setCategory categoria da assegnare all'occhiale
	 * @return
	 */
	public void setCategory(String category) {
		this.category=category;
	}

	/**
	 * @return image immagine dell'occhiale
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param setImage immagine da assegnare all'occhiale
	 * @return
	 */
	public void setImage(String image) {
		this.image=image;
	}
	
	/**
	 * @return image2 seconda immagine dell'occhiale
	 */
	public String getImage2() {
		return image2;
	}

	/**
	 * @param setImage2 seconda immagine da assegnare all'occhiale
	 * @return
	 */
	public void setImage2(String image2) {
		this.image2=image2;
	}

	/**
	 * @return description descrizione dell'occhiale
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param setDescription descrizione da assegnare all'occhiale
	 * @return
	 */
	public void setDescription(String description) {
		this.description=description;
	}
	
	/**
	 * @return quantity quantita' dell'occhiale
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param setQuantity quantita' da assegnare all'occhiale
	 * @precondition quantity>=0
	 * @return
	 */
	public void setQuantity(int q) {
		this.quantity=q ;
	}
	
	/**
	 * @return totalPrice prezzo totale dell'occhiale
	 */
	public float getTotPrezzo() {
		return totalPrice;
	}
	
	/**
	 * @param setTotPrezzo prezzo totale da assegnare all'occhiale
	 * @return
	 */
	public void setTotPrezzo(float prezzotot) {
		this.totalPrice=prezzotot;
	}

	@Override
	public String toString() {
		return "OcchialeBean [idGlasses=" + idGlasses + ", nameGlasses=" + nameGlasses + ", brand=" + brand + ", price="
				+ price + ", availability=" + availability + ", type=" + type + ", color=" + color + ", idCategory="
				+ category + ", image=" + image + ", image2= " + image2 + ", description=" + description + "]";
	}	
}