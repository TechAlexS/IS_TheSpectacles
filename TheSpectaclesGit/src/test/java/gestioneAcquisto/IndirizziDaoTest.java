package gestioneAcquisto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.sql.Connection;
import gestioneAcquisto.*;
import gestioneUtenza.UtenteBean;
import gestioneUtenza.UtenteDao;
import util.ConnectionPool;

class IndirizziDaoTest {
	
	private IndirizziDao  dao = new IndirizziDao();
	private IndirizziBean bean = new IndirizziBean("Via fuga", 1,"città","PR",81000,"prova@gmail.com","08234567", "Nome", "Cognome");
	
	@BeforeEach
	void setUp() throws Exception {
		 dao.doSave(bean);
		 bean.setIdIndirizzo(dao.getLastIndexAdded());
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.doDelete(bean);
		
	}

	

	@Test 
	  void testdoRetrieveActive() throws SQLException {
		
		
		String email="prova@gmail.com";
		
	    assertTrue(dao.doRetrieveActive("prova@gmail.com") != null);
	  }
	@Test 
	  void testSearch() throws SQLException {
		
		
		String email="prova@gmail.com";
		String via="Via fuga";
	    assertTrue(dao.search(email, via) != null);
	  }
	
	@Test 
	  void testdoRetrieveAllAddress() throws SQLException {
		
		String email="prova@gmail.com";
	    assertTrue(dao.doRetrieveAllAddress(email) != null);
	  }
	@Test 
	  void testdoRetrieveAll() throws SQLException {
		
	    assertTrue(dao.doRetrieveAll(null) != null);
	  }
	 @Test
	  void testDoSave() throws SQLException {
		 
		 IndirizziBean bean2 = new IndirizziBean("Via fuga 2", 0,"città2","RR",81020,"prova@gmail.com","07734567", "Nome2", "Cognome2");
	   
		 try {
	      dao.doSave(bean2);
	      String email="prova@gmail.com";
	      assertTrue(dao.doRetrieveAllAddress(email).size()==2);
	    } finally {
	      dao.doDelete(bean2);
	    }
	  }

}
