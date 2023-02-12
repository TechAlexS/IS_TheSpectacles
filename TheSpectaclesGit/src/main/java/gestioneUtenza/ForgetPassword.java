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
 * Questa classe e' un control che si occupa di passare la mail ad UtenteDao per vedere se esiste giàa' in fase di registrazione 
 *
 */
@WebServlet("/ForgetPass")
public class ForgetPassword extends HttpServlet {
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
	
    public ForgetPassword() {
        super();  
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(response.encodeRedirectURL("error.jsp"));
	}
    
    /**
	 * @precondition request.getParameter("password")!=null AND request.getSession().getAttribute("email")!=null 
	 * @postcondition password aggiornata
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword=request.getParameter("password");
        byte[] data1=newPassword.getBytes("UTF-8");
        MessageDigest mdhash=null;
        
		try {
			mdhash=MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
        byte[] digest=mdhash.digest(data1);              
        String HashPassw=Base64.getEncoder().encodeToString(digest);
		String email=(String) request.getSession().getAttribute("email");
		PrintWriter out=response.getWriter();
		
		try {
			utenteModel.changePassword(email, HashPassw);
			out.print("ok");
		} catch(SQLException e) {
			System.out.println("Errore nella signin: " + e.getMessage());
		}
	}
}