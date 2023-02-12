package gestioneOrdini;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import gestioneUtenza.UtenteBean;
import gestioneUtenza.UtenteDao;

/**
 * Questa classe è un control che si occupa di ottenere le informazioni dell’utente loggato, usando UtenteDao.
 */
@WebServlet("/CercaCliente")
public class SevletCercaCliente extends HttpServlet {
	private static final long serialVersionUID=1L;
	private OrdineDao id_ordine=new OrdineDao();
	private UtenteDao id_utente=new UtenteDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		id_ordine.setDB((DataSource) getServletContext().getAttribute("DataSource"));
		id_utente.setDB((DataSource) getServletContext().getAttribute("DataSource"));
	}
	
	@Override
	public SevletCercaCliente() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute(“auth”)!=null 
	 * @postcondition dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		System.out.println("Sono nel Cerca Cliente Admin\n");
		System.out.println("email Cerca Utente: "+email);
	
		try{
			UtenteBean dati=id_utente.doRetrieveByMail(email);
			ArrayList<OrdineBean> ordini=id_ordine.doRetrieveByUser(email);
			request.setAttribute("dati", dati);
			request.setAttribute("email", ordini);	
		} catch(SQLException e) {
		System.out.println("Error Servlet Cerca Cliente: " + e.getMessage());
		}
		
		RequestDispatcher dis=request.getRequestDispatcher("/AdminCercaEmail.jsp");
		dis.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}