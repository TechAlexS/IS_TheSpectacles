package test_gestioneUtenza;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneUtenza.UtenteBean;
import gestioneUtenza.UtenteDao;
import util.ConnectionPool;

public class TestProva {
  private Connection connection;
  private UtenteBean bean = new UtenteBean("email@gmail.com", "Password1", "Nome", "Cognome",new Date(2000, 12, 12),0);
  
  @BeforeEach
  public void setUp() throws Exception {
	  
  }

  @AfterEach
  public void tearDown() throws Exception {
    
  }
  

  @Test
  public void testConnection() {
    
    try (Connection connection = ConnectionPool.getConnection()) {
	    	//assertNotNull(connection);
    	    String selectSQL = "SELECT * FROM utente WHERE email = 'a@gmail.com' ";
    	    PreparedStatement prep = connection.prepareStatement(selectSQL);
            ResultSet rs = prep.executeQuery();
            
            while (rs.next()) {
 				
            	bean.setPass(rs.getString("pass"));
            	bean.setRole(rs.getInt("role"));
            	bean.setEmail(rs.getString("email"));
            	bean.setFirstName(rs.getString("firstName"));
            	bean.setLastName(rs.getString("lastName"));
            	bean.setBirthday(rs.getDate("birthday"));
 			}
            
            rs.close();
			prep.close();
	        connection.close();
	       
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
  }
}
