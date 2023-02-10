package gestioneUtenza;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Questa classe è un control che si occupa di passare la mail ad UtenteDao per vedere se esiste già in fase di registrazione 
 */
@WebServlet("/cercaMail")
public class EsisteEmail extends HttpServlet {
	private static final long serialVersionUID=1L;
	private static UtenteDao utenteModel=new UtenteDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		utenteModel.setDB((DataSource) getServletContext().getAttribute("DataSource"));	
	}
	
    public EsisteEmail() {
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
	 * @precondition request.getParameter(“email”)!=null 
	 * @postcondition request.getSession().getAttribute(“email”)!=null
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		System.out.println("sono in cerca email\n");
		int ruolo=0;
		PrintWriter out=response.getWriter();
		
		try {	
			if(utenteModel.esisteEmail(email)){
				HttpSession session=request.getSession(false);
				session.setAttribute("email", email);
				out.print("ok");
			}
		} catch(SQLException e) {
			System.out.println ("Errore nella signin: " + e.getMessage());
		}	
	}
}