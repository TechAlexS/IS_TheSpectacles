package gestioneIndirizzi;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import gestioneCarrello.Carrello;
import gestioneOcchiali.OcchialeBean;
import gestioneOrdini.OcchialeOrdineDao;
import gestioneOrdini.OrdineBean;
import gestioneOrdini.OrdineDao;
import gestioneUtenza.UtenteBean;

class ServletAddAddressTest {

		private String nome="Pino";
		private String cognome="Rossi";
		private String telefono="123456789";
		private String via="via napoli";
		private String città="Napoli";
		private String provincia="NA";
	    private String cap="123";

	  private UtenteBean bean=new UtenteBean("a@gmail.com", "Password1234", "Nome", "Cognome",new Date(2000, 12, 12),0);
	  private IndirizziDao dao= new IndirizziDao();
	  private ServletAddAddress servlet = new ServletAddAddress();
	  private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	  private HttpSession session = Mockito.mock(HttpSession.class);
	  private RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
	  private ServletConfig config = Mockito.mock(ServletConfig.class);
	  private ServletContext context = Mockito.mock(ServletContext.class);
	  
	 
	  @BeforeEach
	  public void setUp() throws Exception {
		   
		   Mockito.when(config.getServletContext()).thenReturn(context);
		   servlet.init(config);

		   Mockito.when(request.getSession(false)).thenReturn(session);
	  }
	    
	    
	  @AfterEach
	  public void tearDown() throws Exception{
	  }
	 
	   
	// dati Corretti
	  @Test
	  public void testAddAddres() throws ServletException, IOException{
	   
	   
	 
	   Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
	   
	  

	   Mockito.when(request.getParameter("user_name")).thenReturn(nome);
	   Mockito.when(request.getParameter("user_surname")).thenReturn(cognome);
	   Mockito.when(request.getParameter("user_phone")).thenReturn(telefono);
	   Mockito.when(request.getParameter("user_address")).thenReturn(via);
	   Mockito.when(request.getParameter("city")).thenReturn(città);
	   Mockito.when(request.getParameter("user_countr")).thenReturn(provincia);
	   Mockito.when(request.getParameter("zipcode")).thenReturn(cap);

	   Mockito.when(context.getRequestDispatcher("/Indirizzo")).thenReturn(dispatcher);
	   
	   servlet.doGet(request, response);
	   
	   Mockito.verify(dispatcher).forward(request, response);
	     
	  }
	
	  
}
