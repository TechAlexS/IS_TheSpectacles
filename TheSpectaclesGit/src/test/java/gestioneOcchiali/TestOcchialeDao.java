package gestioneOcchiali;

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


public class TestOcchialeDao {

	
	
	private OcchialeDao dao= new OcchialeDao();
	String email="pas@gmail.com";
	private OcchialeBean bean = new OcchialeBean("IDPROVA", "nomeOcchiale", "brand", 100, 10, "D",
			"red", "vista", "img","img2", "descrizione");
	

	
		@BeforeEach
		public void setUp() throws Exception{
			 dao.doSave(bean);
	    }
	
		@AfterEach
	    public void tearDown() throws Exception{
			dao.doDelete(bean.getIdGlasses());
			
	    }
		
		
		 @Test 
		  void testDoRetrieveByCategory() throws SQLException {
			
			 ArrayList<String> keys= new ArrayList<String>();
			 keys.add(bean.getCategory());
			
		    assertTrue(dao.doRetrieveByKey(keys) !=null);
		  }
		
		  @Test
		  void testDoRetrieveBySex() throws SQLException {
			  ArrayList<String> keys= new ArrayList<String>();
			  keys.add(bean.getType());
			  assertTrue(dao.doRetrieveBySex(keys) != null);
		  }
		 
		  @Test
		  void testDoRetrieveOcchiale() throws SQLException {
		   
			 assertTrue(dao.doRetrieveOcchiale(bean.getIdGlasses()) != null);
		  }
		 
		  @Test
		  void testDoRetrieveAll() throws SQLException {
		   
			 assertTrue(dao.doRetrieveAll(null) != null);
		  }
		  

		  
		  @Test
		  void testDoSave() throws SQLException {
			  OcchialeBean bean2 = new OcchialeBean("IDPROVA2", "nomeOcchiale2", "brand2", 150, 15, "U",
						"green", "sole", "img11","img22", "descrizione2");
		    try {
		      dao.doSave(bean2);
		     
		      assertTrue(dao.doRetrieveOcchiale(bean2.getIdGlasses()) != null);
		    } finally {
		      dao.doDelete(bean2.getIdGlasses());
		    }
		  } 
		 
		  @Test
		  void testDoRetrieveByBrand() throws SQLException {
		   
			 assertTrue(dao.doRetrieveByBrand(bean.getBrand())!=null);
		  }
		
		  @Test
		  void testDecreaseAvailability() throws SQLException {
		   
			 int oldDisp=bean.getAvailability();
			 int newDisp=dao.decreaseAvailability(bean);
			 assertTrue(oldDisp>newDisp);
		  }
		 
		  
		  
		  
}
