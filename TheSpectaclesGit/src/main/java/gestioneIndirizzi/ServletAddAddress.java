package gestioneIndirizzi;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import gestioneUtenza.UtenteBean;

/**
 * Questa classe e' un control che si occupa di passare un nuovo indirizzo a IndirizzoDao.
 */
@WebServlet("/AddAddress")
public class ServletAddAddress extends HttpServlet {
	private static final long serialVersionUID=1L;
	private IndirizziDao indDao=new IndirizziDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		indDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));
	}
	
	public ServletAddAddress() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute("auth")!=null AND request.getParameter("user_address")!=null AND request.getParameter("city")!=null AND request.getParameter("user_country")!=null AND request.getParameter("zip_code")!=null AND request.getParameter("user_phone")!=null 
	 * @postcondition IndirizzoDao.doSave(idIndirizzo) eseguito AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		UtenteBean bean=(UtenteBean)session.getAttribute("auth");
		
		/*System.out.println("Sono nella Servlet aggiungi indirizzo: \n");
		System.out.println("\n"+request.getParameter("user_name"));
		System.out.println("\n"+request.getParameter("user_surname"));
		System.out.println("\n"+request.getParameter("user_address"));
		System.out.println("\n"+request.getParameter("city"));
		System.out.println("\n"+request.getParameter("user_country"));
		System.out.println("\n"+request.getParameter("zipcode"));
		System.out.println("\n"+request.getParameter("user_phone"));
		 
		 */
		String nome=request.getParameter("user_name");
		String cognome=request.getParameter("user_surname");
		String telefono=request.getParameter("user_phone");
		String via=request.getParameter("user_address");
		String citta=request.getParameter("city");
		String provincia=request.getParameter("user_country");
		int cap=Integer.parseInt(request.getParameter("zipcode"));
		String email=bean.getEmail();
		int status=0;
		
		try {
			IndirizziBean indirizzo=new IndirizziBean();
			indirizzo.setName(nome);
			indirizzo.setSurname(cognome);
			indirizzo.setAddress(via);
			indirizzo.setCap(cap);
			indirizzo.setCity(citta);
			indirizzo.setProvince(provincia);
			indirizzo.setStatus(status);
			indirizzo.setEmail(email);
			indirizzo.setTelefono(telefono);
			indDao.doSave(indirizzo);
		} catch(SQLException e) {
			System.out.println("Errore Servelt aggiungi indirizzo: " + e.getMessage());
		}
		
		String page=request.getParameter("page");
		
		if(page.equals("ok")) {
			RequestDispatcher dis=getServletContext().getRequestDispatcher("/Indirizzo?page=ok");
			dis.forward(request, response);
		}
			
		else {
			RequestDispatcher dis=getServletContext().getRequestDispatcher("/Indirizzo");
			dis.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
}