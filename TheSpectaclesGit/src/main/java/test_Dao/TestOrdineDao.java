package test_Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.sql.Connection;
import gestioneOrdini.*;
import gestioneUtenza.UtenteBean;
import gestioneUtenza.UtenteDao;
import util.ConnectionPool;


public class TestOrdineDao {

	
	
	private OrdineDao dao= new OrdineDao();
	String email="pas@gmail.com";
	private OrdineBean bean = new OrdineBean(UUID.randomUUID(), new Date(),email,"confermato");
	

	
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
			
			String id=bean.getIdOrder().toString();
			
		    assertTrue(dao.doRetrieveByKey(id) !=null);
		  }
		 
		  
		  @Test
		  void testDoRetrieveAll() throws SQLException {
		   
			 assertTrue(dao.doRetrieveAll(null) != null);
		  }
		 

		  
		  @Test
		  void testDoSave() throws SQLException {
			  OrdineBean bean2 = new OrdineBean(UUID.randomUUID(), new Date(),"a@gmail.com","confermato");
		    try {
		      dao.doSave(bean2);
		      String id=bean2.getIdOrder().toString();
		      assertTrue(dao.doRetrieveByKey(id) != null);
		    } finally {
		      dao.doDelete(bean2);
		    }
		  }
		 
		  @Test
		  void testDoRetrieveByUser() throws SQLException {
		   
			 assertTrue(dao.doRetrieveByUser(bean.getEmail())!=null);
		  }
		 /*
		  @Test
		  void testDoRetrieveByDate() throws SQLException {
		   
			 assertTrue(dao.doRetrieveByDate() !=null);
		  }
		 */
		  
		  
		  
}
