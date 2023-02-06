package test_Dao;

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
import gestioneUtenza.*;
import util.ConnectionPool;


public class TestUtenteDao {
	
	
	private UtenteDao  dao = new UtenteDao();
	private UtenteBean bean = new UtenteBean("email@gmail.com", "Password1", "Nome", "Cognome",new Date(2000, 12, 12),0);
	
	
	
		@BeforeEach
		public void setUp() throws Exception{
			 dao.doSave(bean);
	    }
	
		@AfterEach
	    public void tearDown() throws Exception{
			dao.doDelete(bean);
			
	    }
		
		
		@Test 
		  void testDoRetrieveByKey() throws SQLException {
			
			ArrayList<String> param= new ArrayList<String>();
			param.add(bean.getEmail());
			param.add(bean.getPass());
		    assertTrue(dao.doRetrieveByKey(param) != null);
		  }

		  @Test
		  void testDoRetrieveAll() throws SQLException {
		   
			 assertTrue(dao.doRetrieveAll(null).size() != 0);
		  }

		  @Test
		  void testDoSave() throws SQLException {
			  UtenteBean bean2 = new UtenteBean("email2@gmail.com", "Password1", "Nome", "Cognome",new Date(2000, 12, 12),0);
		    try {
		      dao.doSave(bean2);
		      	ArrayList<String> param= new ArrayList<String>();
				param.add(bean2.getEmail());
				param.add(bean2.getPass());
		      assertTrue(dao.doRetrieveByKey(param) != null);
		    } finally {
		      dao.doDelete(bean2);
		    }
		  }
		  
		  @Test
		  void testEsisteEmail() throws SQLException {
		   
			 assertTrue(dao.esisteEmail(bean.getEmail()));
		  }
		  
}
