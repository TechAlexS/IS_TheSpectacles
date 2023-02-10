package gestioneUtenza;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import util.Model;

/**
 * Questa classe è un control che si occupa di passare a UtenteDao i dati dell’utente usando la query di Retrieve.
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	private static Model<UtenteBean, DataSource> utenteModel=new UtenteDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		utenteModel.setDB((DataSource) getServletContext().getAttribute("DataSource"));
		
	}
	
	public LoginServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(response.encodeRedirectURL("error.jsp"));
	}
	
	  /**
		 * @precondition Request.getParameter(“email”)!=null AND Request.getParameter(“password”)!=null 
		 * @postcondition request.getSession().getAttribute(“auth”)!=null
		 * @throws ServletException, IOException
		 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String email=request.getParameter("email");
			String pw=request.getParameter("password");
	        byte[] data1=pw.getBytes("UTF-8");
	        MessageDigest mdhash=null;
	        
			try {
				mdhash=MessageDigest.getInstance("SHA-256");
			} catch(NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
	        byte[] digest=mdhash.digest(data1);              
	        String HashPassw=Base64.getEncoder().encodeToString(digest);
			
			ArrayList<String> value=new ArrayList<String>();
			PrintWriter out=response.getWriter();
			value.add(email);
			value.add(HashPassw);
			
			try{
				UtenteBean cerca=utenteModel.doRetrieveByKey(value);
		
				if(cerca.getEmail()==null) {
					out.print("Nulla");
					request.setAttribute("loginResult", "utente inesistente");
				}
				
				if((cerca.getEmail()!=null) && (cerca.getRole()==1)) {
					request.getSession().setAttribute("auth", cerca);
					out.print("Admin");
				}
				
				if((cerca.getEmail()!=null) && !(cerca.getRole()==1)) {
					request.getSession().setAttribute("auth", cerca);
					out.print("Utente");
				}
			}
		catch(Exception e) {
			System.out.println("Error Login Servlet: " + e.getMessage());	
			}
		}
}
