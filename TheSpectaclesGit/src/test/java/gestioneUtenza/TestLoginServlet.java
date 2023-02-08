package gestioneUtenza;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import gestioneUtenza.*;
import util.ConnectionPool;

public class TestLoginServlet {
	
		private static final String rightEmail = "a@gmail.com";
		private static final String badEmail = "test@libero.it";
		private static final String rightPass = "Password123";
		private static final String badPass = "badPassword";
		
		
		private static final LoginServlet servlet = new LoginServlet();
		private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		private static final HttpSession session = Mockito.mock(HttpSession.class);
	
	 @BeforeAll
	  static void beforeAll() throws SQLException {
	   
	    Mockito.doReturn(session).when(request).getSession();
	    
	  }

	  @AfterAll
	  static void afterAll() throws SQLException {
	    
	  }
	
	//TC_2.1_1 - Email non trovata nel database
		@Test
		public void testCase_1() throws ServletException, IOException{
			Mockito.doReturn(badEmail).when(request).getParameter("email");
			Mockito.doReturn(rightPass).when(request).getParameter("password");
			
			servlet.doPost(request, response);
			
			assertEquals("utente inesistentet", (String)request.getAttribute("loginResult"));
			
		
		}
}
