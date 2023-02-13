package gestioneUtenza;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;



import gestioneUtenza.*;
import util.ConnectionPool;


public class TestLoginServlet {
 
  private static final String rightEmail = "a@gmail.com";
  private static final String badEmail = "test@libero.it";
  private static final String rightPass = "Password1234";
  private static final String badPass = "badPassword";
  
  
  
  private static final LoginServlet servlet = new LoginServlet();
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final HttpSession session = Mockito.mock(HttpSession.class);
  
  
  
 
  @BeforeEach
  public void setUp() throws Exception {
     Mockito.doReturn(session).when(request).getSession();
    //request.setSession(session);
	 
  }
    
    
  @AfterEach
  public void tearDown() throws Exception{
   
  }
 
 //TC_1.2_1 - Email inesistente
  @Test
  public void testCase_1_2_1() throws ServletException, IOException{
   
   
   PrintWriter out = Mockito.mock(PrintWriter.class);
   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

   Mockito.when(request.getParameter("email")).thenReturn(badEmail);
   Mockito.when(request.getParameter("password")).thenReturn(rightPass);
   Mockito.when(response.getWriter()).thenReturn(out);

   servlet.doPost(request, response);

   Mockito.verify(out).print(argument.capture());
   assertEquals("Nulla", argument.getValue());
   
     
  }
  
//TC_1.2_2 - Password errata
  @Test
  public void testCase_1_2_2() throws ServletException, IOException{
   
   
   PrintWriter out = Mockito.mock(PrintWriter.class);
   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

   Mockito.when(request.getParameter("email")).thenReturn(rightEmail);
   Mockito.when(request.getParameter("password")).thenReturn(badPass);
   Mockito.when(response.getWriter()).thenReturn(out);

   servlet.doPost(request, response);

   Mockito.verify(out).print(argument.capture());
   assertEquals("Nulla", argument.getValue());
   
     
  }
  
//TC_1.2_3 - Email e password Corretti
  @Test
  public void testCase_1_2_3() throws ServletException, IOException{
   
   
   PrintWriter out = Mockito.mock(PrintWriter.class);
   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

   Mockito.when(request.getParameter("email")).thenReturn(rightEmail);
   Mockito.when(request.getParameter("password")).thenReturn(rightPass);
   Mockito.when(response.getWriter()).thenReturn(out);

   servlet.doPost(request, response);

   Mockito.verify(out).print(argument.capture());
   //UtenteBean usr=(UtenteBean)session.getAttribute("auth");
   //assertTrue( usr.getEmail().equals(rightEmail) );
   assertEquals("Utente", argument.getValue());
   
  }
}
