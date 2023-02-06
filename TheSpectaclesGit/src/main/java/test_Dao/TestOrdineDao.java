package test_Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.sql.Connection;
import gestioneOrdini.*;
import util.ConnectionPool;


public class TestOrdineDao {

	
	private DataSource ds = null;
	private OrdineDao dao= new OrdineDao();
	
		@BeforeEach
		public void setUp() {
			
			
			
	    }
	
		@AfterEach
	    public void tearDown(){
			
			
	    }
		
		
	

		  @Test
		  void testConnection() throws SQLException{
		   
			  try {
					Context initCtx = new InitialContext();
					Context envCtx = (Context) initCtx.lookup("java:comp/env");

					ds = (DataSource) envCtx.lookup("jdbc/ecommerce");
					
					Connection connection=ds.getConnection();
					//dao.setDB(ds);
					assertNotNull(connection);

				} catch (NamingException e) {
					System.out.println("Error:" + e.getMessage());
				}
				
		  }

		  
		  
}
