package gestioneOrdini;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import gestioneCarrello.Carrello;
import gestioneOcchiali.OcchialeBean;
import gestioneOcchiali.OcchialeDao;
import gestioneOrdini.OcchialeOrdineDao;
import gestioneOrdini.OrdineBean;
import gestioneOrdini.OrdineDao;
import gestioneUtenza.*;
import util.ConnectionPool;


public class CheckoutTest {
 
  private  String indirizzo = "Via Napoli";
  private  String cardNumber = "1234567812345678";
  private  String cvv = "123";
  private String expiry = "12/24";
  private Carrello cart = new Carrello();
  private  OcchialeBean occhiale = new OcchialeBean("IDPROVA", "nomeOcchiale", "brand", 100, 10, "D",
			"red", "vista", "img","img2", "descrizione");
  private  UtenteBean bean=new UtenteBean("a@gmail.com", "Password1234", "Nome", "Cognome",new Date(2000, 12, 12),0);
  private  OrdineBean ordine= new OrdineBean();
  private OrdineDao ordineDao=new OrdineDao();
  private OcchialeOrdineDao occhialeOrdineDao=new OcchialeOrdineDao();
  private  Checkout servlet = new Checkout();
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
	   //Mockito.when(config.getServletContext()).thenReturn(context);
	   cart.addCarrello(occhiale);
	   
	   //System.out.println(dispatcher);
	   

	   Mockito.when(request.getSession()).thenReturn(session);
  }
    
    
  @AfterEach
  public void tearDown() throws Exception{
	  
  }
 
   
//TC_7.9 - dati Corretti
  @Test
  public void testCase_7_9() throws ServletException, IOException{
   
   
   Mockito.when(session.getAttribute("carrello")).thenReturn(cart);
   Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
   
   PrintWriter out = Mockito.mock(PrintWriter.class);
   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

   Mockito.when(request.getParameter("indirizzo")).thenReturn(indirizzo);
   Mockito.when(request.getParameter("cardnumber")).thenReturn(cardNumber);
   Mockito.when(request.getParameter("expiry")).thenReturn(expiry);
   Mockito.when(request.getParameter("cvv")).thenReturn(cvv);
   Mockito.when(response.getWriter()).thenReturn(out);
   Mockito.when(context.getRequestDispatcher("/confirmation.jsp")).thenReturn(dispatcher);
   
   servlet.doGet(request, response);
   
   Mockito.verify(dispatcher).forward(request, response);
      
   Mockito.verify(out).print(argument.capture());
   assertEquals("confirmation.jsp", argument.getValue());
  
  }
///TC_7.1 - Sessione non attiva per l'utente
  @Test
  public void testCase_7_1() throws ServletException, IOException{
   
	  
   
   Mockito.when(session.getAttribute("carrello")).thenReturn(cart);
   Mockito.when(session.getAttribute("auth")).thenReturn(null);  
   
   PrintWriter out = Mockito.mock(PrintWriter.class);
   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

   Mockito.when(request.getParameter("indirizzo")).thenReturn(indirizzo);
   Mockito.when(request.getParameter("cardnumber")).thenReturn(cardNumber);
   Mockito.when(request.getParameter("expiry")).thenReturn(expiry);
   Mockito.when(request.getParameter("cvv")).thenReturn(cvv);
   Mockito.when(response.getWriter()).thenReturn(out);
   Mockito.when(context.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
   
   servlet.doGet(request, response);
   

  
  
   Mockito.verify(dispatcher).forward(request, response);
      
   Mockito.verify(out).print(argument.capture());
   assertEquals("login.jsp", argument.getValue());
  
  }
  
///TC_7.1.1 - Carrello inesistente
  @Test
  public void testCase_7_1_1() throws ServletException, IOException{
   
	  
   
   Mockito.when(session.getAttribute("carrello")).thenReturn(null);
   Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
   
   PrintWriter out = Mockito.mock(PrintWriter.class);
   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

   Mockito.when(request.getParameter("indirizzo")).thenReturn(indirizzo);
   Mockito.when(request.getParameter("cardnumber")).thenReturn(cardNumber);
   Mockito.when(request.getParameter("expiry")).thenReturn(expiry);
   Mockito.when(request.getParameter("cvv")).thenReturn(cvv);
   Mockito.when(response.getWriter()).thenReturn(out);
   Mockito.when(context.getRequestDispatcher("/error.jsp")).thenReturn(dispatcher);
   
   servlet.doGet(request, response);
   

  
  
   Mockito.verify(dispatcher).forward(request, response);
      
   Mockito.verify(out).print(argument.capture());
   assertEquals("error.jsp", argument.getValue());
  
  }
  
  @Test
  public void testCreateOrder() throws ServletException, IOException{
   
	  Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
	  ordine=servlet.createOrder(request);
	  assertTrue(ordine.getEmail().equals(bean.getEmail()));
  }
  
  @Test
  public void testCreateOrder2() throws ServletException, IOException{
   
	  Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
	  ordine=servlet.createOrder(request);
	  assertFalse(ordine.getEmail().equals("email@non.esistente"));

  }
  
  @Test
  public void testCreateproducts() throws ServletException, IOException{
      
	  assertTrue(servlet.createProducts(cart,UUID.randomUUID())!=null);

  }
  
  /*
  @Test
  public void testAddressAttribute() throws ServletException, IOException {
     IndirizziBean indirizzo = new IndirizziBean();
     indirizzo.setAddress("Via Napoli");

     ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
     ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
     
     
     servlet.doGet(request, response);

     String key = keyCaptor.getValue();
     IndirizziBean value = (IndirizziBean) valueCaptor.getValue();
     
     Mockito.verify(session).setAttribute(keyCaptor.capture(), valueCaptor.capture());
     

     assertEquals("Via Napoli", key);
     assertEquals(indirizzo.getAddress(), value.getAddress());
  }
 */
	
}