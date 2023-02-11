package gestioneCarrello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneCarrello.*;
import gestioneOcchiali.OcchialeBean;
import gestioneOcchiali.OcchialeDao;

class CarrelloTest {
	
	private OcchialeBean bean = new OcchialeBean("IDPROVA", "nomeOcchiale", "brand", 100, 10, "D",
			"red", "vista", "img","img2", "descrizione");
	
	
	private Carrello carrello;

	@BeforeEach
	void setUp() throws Exception {
		carrello= new Carrello();
		carrello.addCarrello(bean);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddProduct() {
		OcchialeBean bean2 = new OcchialeBean("IDPROVA2", "nomeOcchiale2", "brand2", 100, 10, "U",
				"red", "vista", "img11","img22", "descrizione2");
		carrello.addCarrello(bean2);
		assertTrue(carrello.getDimensione() ==2);
	}
	@Test
	void testdeleteProduct() {
		
		carrello.deleteProduct(bean);
		assertTrue(carrello.getDimensione() ==0);
	}
	@Test
	void testPrendiProdotto() {
		
		
		assertTrue(carrello.prendiProdotto("IDPROVA") !=null );
	}
	@Test
	void testDeleteAll() {
		OcchialeBean bean2 = new OcchialeBean("IDPROVA2", "nomeOcchiale2", "brand2", 100, 10, "U",
				"red", "vista", "img11","img22", "descrizione2");
		carrello.addCarrello(bean2);
		carrello.delete();
		assertTrue(carrello.getDimensione() ==0);
	}
	
	@Test
	void testGetCarrello() {
		
		assertTrue(carrello.getCarrello() !=null);
	}
	@Test
	void testSearchProdotto() {
		
		assertTrue(carrello.searchProdotto("IDPROVA"));
	}
	
	@Test
	void testGetPrezzoTotale() {
		
		int quantità=2;
		int ris=quantità*100;
		
		assertTrue(carrello.getPrezzoTotale(quantità,"IDPROVA")==ris);
	}
	
	@Test
	void testInsertBadQuantity() {
		
		int quantità=20; //invece di max 10
		String code="IDPROVA";
		assertTrue(carrello.insertQuantita(code, quantità)==-1);
	}
	@Test
	void testInsertRightQuantity() {
		
		int quantità=2;
		String code="IDPROVA";
		assertTrue(carrello.insertQuantita(code, quantità)==1);
	}
	
	
	
	

}
