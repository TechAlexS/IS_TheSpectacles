package gestioneUtenza;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class TestRegistrazioneServlet {

	  private static final String email = "test@gmail.com";
	  private static final String existingEmail = "a@gmail.com";
	  private static final String pass = "Password1234";
	  private static final String nome = "Nome";
	  private static final String cognome = "Cognome";
	  private Date data= Mockito.mock(Date.class);
	  private UtenteDao dao= new UtenteDao();
	  private UtenteBean bean=new UtenteBean(email, pass, nome, cognome,data,0);
	  
	  private static final SigninServlet servlet = new SigninServlet();
	  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	  private static final HttpSession session = Mockito.mock(HttpSession.class);
	  
	 
	  
	  
	 
	  @BeforeEach
	  public void setUp() throws Exception {
	     Mockito.doReturn(session).when(request).getSession();
	   
		 
	  }
	    
	    
	  @AfterEach
	  public void tearDown() throws Exception{
		  dao.doDelete(bean);
	   
	  }
	 
	 //TC_1.7 Dati corretti, email non presente
	  @Test
	  public void testCase_1_7() throws ServletException, IOException{
	   
	   
	   PrintWriter out = Mockito.mock(PrintWriter.class);
	   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

	   Mockito.when(request.getParameter("email")).thenReturn(email);
	   Mockito.when(request.getParameter("password")).thenReturn(pass);
	   Mockito.when(request.getParameter("nome")).thenReturn(nome);
	   Mockito.when(request.getParameter("cognome")).thenReturn(cognome);
	   Mockito.when(request.getParameter("data")).thenReturn("2000-12-12");
	   Mockito.when(response.getWriter()).thenReturn(out);

	   servlet.doPost(request, response);

	
	   Mockito.verify(out).print(argument.capture());
	   assertEquals("Utente", argument.getValue());
	   
	     
	  }
	  
	  //TC_1.4 - Email esistente
	  @Test
	  public void testCase_1_4() throws ServletException, IOException{
	   
	   
	   PrintWriter out = Mockito.mock(PrintWriter.class);
	   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

	   Mockito.when(request.getParameter("email")).thenReturn(existingEmail);
	   Mockito.when(request.getParameter("password")).thenReturn(pass);
	   Mockito.when(request.getParameter("nome")).thenReturn(nome);
	   Mockito.when(request.getParameter("cognome")).thenReturn(cognome);
	   Mockito.when(request.getParameter("data")).thenReturn("2000-12-12");
	   Mockito.when(response.getWriter()).thenReturn(out);

	   servlet.doPost(request, response);

	   Mockito.verify(out).print(argument.capture());
	   assertEquals("Esistente", argument.getValue());
	   
	     
	  }
	  
}
