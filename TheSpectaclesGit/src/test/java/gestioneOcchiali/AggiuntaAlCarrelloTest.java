package gestioneOcchiali;

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

import gestioneAcquisto.Checkout;
import gestioneCarrello.Carrello;
import gestioneOcchiali.OcchialeBean;
import gestioneOcchiali.OcchialeDao;
import gestioneOcchiali.ServletProdotti;
import gestioneOrdini.OcchialeOrdineDao;
import gestioneOrdini.OrdineBean;
import gestioneOrdini.OrdineDao;
import gestioneUtenza.UtenteBean;

class AggiuntaAlCarrelloTest {


	  private Carrello cart = new Carrello();
	  private String idOcchiale="IDPROVA";
	  private  OcchialeBean occhiale = new OcchialeBean("IDPROVA", "nomeOcchiale", "brand", 100, 10, "D","red", "vista", "img","img2", "descrizione");
	  private  ServletProdotti servlet = new ServletProdotti();
	  private OcchialeDao dao= new OcchialeDao();
	  private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	  private HttpSession session = Mockito.mock(HttpSession.class);
	  private RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
	  private ServletConfig config = Mockito.mock(ServletConfig.class);
	  private ServletContext context = Mockito.mock(ServletContext.class);
	  
	 
	  @BeforeEach
	  public void setUp() throws Exception {
		   dao.doSave(occhiale);
		   Mockito.when(config.getServletContext()).thenReturn(context);
		   servlet.init(config);
		   
		   Mockito.when(request.getSession()).thenReturn(session);
	  }
	    
	    
	  @AfterEach
	  public void tearDown() throws Exception{
		  dao.doDelete(idOcchiale);
	  }
	 
	   
	//TC_8.1 - Occhiale non presente
	  @Test
	  public void testCase_8_1() throws ServletException, IOException{
		
		  
	   PrintWriter out = Mockito.mock(PrintWriter.class);
	   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

	   Mockito.when(session.getAttribute("carrello")).thenReturn(cart);

	   Mockito.when(response.getWriter()).thenReturn(out);

	   Mockito.when(request.getParameter("id")).thenReturn(idOcchiale);
	   Mockito.when(request.getParameter("action")).thenReturn("aggiungi");
	   
	   Mockito.when(request.getAttribute("descrizione")).thenReturn(occhiale);

	 
	   Mockito.when(context.getRequestDispatcher("/carrello.jsp")).thenReturn(dispatcher);
	   
	   servlet.doGet(request,response);
	   
	   Mockito.verify(dispatcher).forward(request, response);
	      
	   Mockito.verify(out).print(argument.capture());
	   assertEquals("aggiunto", argument.getValue());
	  
	  }
	//TC_8.2 - Occhiale già presente
	  @Test
	  public void testCase_8_2() throws ServletException, IOException{
		
		  
	   PrintWriter out = Mockito.mock(PrintWriter.class);
	   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
	   
	   cart.addCarrello(occhiale);

	   Mockito.when(session.getAttribute("carrello")).thenReturn(cart);

	   Mockito.when(response.getWriter()).thenReturn(out);

	   Mockito.when(request.getParameter("id")).thenReturn(idOcchiale);
	   Mockito.when(request.getParameter("action")).thenReturn("aggiungi");
	   
	   Mockito.when(request.getAttribute("descrizione")).thenReturn(occhiale);

	 
	   Mockito.when(context.getRequestDispatcher("/carrello.jsp")).thenReturn(dispatcher);
	   
	   servlet.doGet(request,response);
	   
	   Mockito.verify(dispatcher).forward(request, response);
	      
	   Mockito.verify(out).print(argument.capture());
	   assertEquals("Occhiale già nel carrello", argument.getValue());
	  
	  }
	
	  
}
