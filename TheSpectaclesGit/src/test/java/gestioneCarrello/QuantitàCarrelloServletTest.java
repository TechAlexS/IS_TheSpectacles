package gestioneCarrello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

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

import gestioneOrdini.Checkout;
import gestioneOcchiali.OcchialeBean;
import gestioneOrdini.OcchialeOrdineDao;
import gestioneOrdini.OrdineBean;
import gestioneOrdini.OrdineDao;
import gestioneUtenza.UtenteBean;

class Quantit‡CarrelloServletTest {


	  private String quantit‡ = "2";
	  private String id="Gucci_GG0926S_1";
	  private  int maxQuantity=10;
	  private Carrello cart = new Carrello();
	  private  OcchialeBean occhiale = new OcchialeBean("Gucci_GG0926S_1", "nomeOcchiale", "brand", 100, maxQuantity, "D",
				"red", "vista", "img","img2", "descrizione");
	  
	  private  ServletCarrello servlet = new ServletCarrello();
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
		   
		   cart.addCarrello(occhiale);
		   
		   //System.out.println(dispatcher);
		   System.out.println(session);

		   Mockito.when(request.getSession(false)).thenReturn(session);
	  }
	    
	    
	  @AfterEach
	  public void tearDown() throws Exception{
		  
	  }
	 
	  //TC_3.2 - Quantit‡ eccessiva
	  @Test
	  public void testCase_3_2() throws ServletException, IOException{
	   
	   
	   Mockito.when(session.getAttribute("carrello")).thenReturn(cart);

	  
	   Mockito.when(request.getParameter("nascosto")).thenReturn(id);
	   Mockito.when(request.getParameter("scelta")).thenReturn("2000");

	
	   Mockito.when(context.getRequestDispatcher("/carrello.jsp")).thenReturn(dispatcher);
	   
	   servlet.doGet(request, response);
	   
	   Mockito.verify(dispatcher).forward(request, response);
	      
	  
	   assertEquals(cart.getCarrello().get(0).getQuantity(),maxQuantity);
	  
	  }
	//TC_3.3 - Quantit‡ corretta
	  @Test
	  public void testCase_3_3() throws ServletException, IOException{
	   
	   
	   Mockito.when(session.getAttribute("carrello")).thenReturn(cart);

	  
	   Mockito.when(request.getParameter("nascosto")).thenReturn(id);
	   Mockito.when(request.getParameter("scelta")).thenReturn(quantit‡);

	
	   Mockito.when(context.getRequestDispatcher("/carrello.jsp")).thenReturn(dispatcher);
	   
	   servlet.doGet(request, response);
	   
	   Mockito.verify(dispatcher).forward(request, response);
	      
	   
	   assertEquals(cart.getCarrello().get(0).getQuantity(),2);
	  
	  }
	
}
