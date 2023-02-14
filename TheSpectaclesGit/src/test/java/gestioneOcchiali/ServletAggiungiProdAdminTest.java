package gestioneOcchiali;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;



import gestioneOcchiali.OcchialeBean;
import gestioneOcchiali.OcchialeDao;
import gestioneUtenza.*;
import util.ConnectionPool;



class ServletAggiungiProdAdminTest {
	 String uploadPath="C:\\Users\\User\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TheSpectaclesGit\\";
	
	 private Part part= Mockito.mock(Part.class);
	 
	 private Part part2=Mockito.mock(Part.class);

	 
	 private String idOcchiale="IDOcchiale";
	 private String nomeProd="nomeOcchiale";
	 private String brand="brand";
	 private String prezzo="100";
	 private String disp="10";
	 private String colore="colore";
	 private String categoria="categoria";
	 private String sex="S";
	 private String desc="descrizione";
	
	
	  private  OcchialeBean occhiale = new OcchialeBean("IDPROVA", "nomeOcchiale", "brand", 100, 10, "D",
				"red", "vista", "img","img2", "descrizione");
	  private  UtenteBean bean=new UtenteBean("a@gmail.com", "Password1234", "Nome", "Cognome",new Date(2000, 12, 12),1);
	 
	  private  ServletAggiungiProdAdmin servlet = new ServletAggiungiProdAdmin();
	  private OcchialeDao dao=new OcchialeDao();
	  private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	  private HttpSession session = Mockito.mock(HttpSession.class);
	  private RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
	  private ServletConfig config = Mockito.mock(ServletConfig.class);
	  private ServletContext context = Mockito.mock(ServletContext.class);
	  
	  

	  
	@BeforeEach
	void setUp() throws Exception {
		 Mockito.when(config.getServletContext()).thenReturn(context);
		 servlet.init(config);
		 Mockito.when(request.getSession()).thenReturn(session);
		 
		//String filePath = "C:\\Users\\User\\Desktop\\Uml.png";

	}

	@AfterEach
	void tearDown() throws Exception {
		dao.doDelete(idOcchiale);
	}

	//TC_4.3 - dati Corretti
	  @Test
	  public void testCase_4_3() throws ServletException, IOException{
	   
	    PrintWriter out = Mockito.mock(PrintWriter.class);
	   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

	   Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
	    

	   Mockito.when(request.getParameter("id")).thenReturn(idOcchiale);
	   Mockito.when(request.getParameter("nome")).thenReturn(nomeProd);
	   Mockito.when(request.getParameter("brand")).thenReturn(brand);
	   Mockito.when(request.getParameter("prezzo")).thenReturn(prezzo);
	   Mockito.when(request.getParameter("disp")).thenReturn(disp);
	   Mockito.when(request.getParameter("colore")).thenReturn(colore);
	   Mockito.when(request.getParameter("categoria")).thenReturn(categoria);
	   Mockito.when(request.getParameter("sesso")).thenReturn(sex);
	   Mockito.when(request.getParameter("desc")).thenReturn(desc);
	   
	      
	   Mockito.when(response.getWriter()).thenReturn(out);
	   
	   Mockito.when(context.getRealPath("")).thenReturn(uploadPath);
	   Mockito.when(request.getPart("img1")).thenReturn(part);
	   Mockito.when(request.getPart("img2")).thenReturn(part2);
	   
	  
	   Mockito.when(request.getRequestDispatcher("PageAmministratore.jsp")).thenReturn(dispatcher);
	   
	   
	   
	   Mockito.when(part.getSubmittedFileName()).thenReturn("img.png");
	   Mockito.when(part2.getSubmittedFileName()).thenReturn("img2.png");
	   //Mockito.when(response.getWriter()).thenReturn(out);
	  
	   
	   servlet.doPost(request, response);
	   
	   Mockito.verify(part).write("C:\\Users\\User\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TheSpectaclesGit\\images\\shop\\products\\img.png");
	  Mockito.verify(part2).write("C:\\Users\\User\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TheSpectaclesGit\\images\\shop\\products\\img2.png");
	  
	  
	   Mockito.verify(dispatcher).forward(request, response);
	      
	    Mockito.verify(out).print(argument.capture());
	    assertEquals("non presente nel db", argument.getValue());
	  
	  }

	//TC_4.2 - Id già presente
	  @Test
	  public void testCase_4_2() throws ServletException, IOException{
	   
	 
	   Mockito.when(session.getAttribute("auth")).thenReturn(bean);  
	   
	   PrintWriter out = Mockito.mock(PrintWriter.class);
	   ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
	    
	   String idPresente="Gucci_GG0926S_1";

	   Mockito.when(request.getParameter("id")).thenReturn(idPresente);
	   Mockito.when(request.getParameter("nome")).thenReturn(nomeProd);
	   Mockito.when(request.getParameter("brand")).thenReturn(brand);
	   Mockito.when(request.getParameter("prezzo")).thenReturn(prezzo);
	   Mockito.when(request.getParameter("disp")).thenReturn(disp);
	   Mockito.when(request.getParameter("colore")).thenReturn(colore);
	   Mockito.when(request.getParameter("categoria")).thenReturn(categoria);
	   Mockito.when(request.getParameter("sesso")).thenReturn(sex);
	   Mockito.when(request.getParameter("desc")).thenReturn(desc);
	   
	   Mockito.when(response.getWriter()).thenReturn(out);
	   
	   Mockito.when(context.getRealPath("")).thenReturn(uploadPath);
	   Mockito.when(request.getPart("img1")).thenReturn(part);
	   Mockito.when(request.getPart("img2")).thenReturn(part2);
	   
	   Mockito.when(part.getSubmittedFileName()).thenReturn("img.png");
	   Mockito.when(part2.getSubmittedFileName()).thenReturn("img2.png");
	   
	  
	   Mockito.when(request.getRequestDispatcher("Amministratore?action=aggiungi")).thenReturn(dispatcher);
	   
	   servlet.doPost(request, response);
	   
	   Mockito.verify(part).write("C:\\Users\\User\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TheSpectaclesGit\\images\\shop\\products\\img.png");
	   Mockito.verify(part2).write("C:\\Users\\User\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TheSpectaclesGit\\images\\shop\\products\\img2.png");
	  
	  
	   Mockito.verify(dispatcher).forward(request, response);
	   
	    Mockito.verify(out).print(argument.capture());
	    assertEquals("già presente nel db", argument.getValue());
	  
	  }

}













 


 
   
