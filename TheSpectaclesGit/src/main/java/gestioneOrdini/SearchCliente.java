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

import gestioneOcchiali.OcchialeDao;
import gestioneUtenza.UtenteBean;
import gestioneUtenza.UtenteDao;

/**
 * Questa classe è un control che si occupa di passare i dati di un utente da cercare a UtenteDao e ottenere i suoi ordini, interfacciandosi con OrdineDao.
 */
@WebServlet("/searchCliente")
public class SearchCliente extends HttpServlet {
	private static final long serialVersionUID=1L;
	private OcchialeDao modelOcchiale=new OcchialeDao();
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
	
	public SearchCliente() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute( “auth”)!=null AND request.getParameter(“email”)!=null 
	 * @postcondition request.getAttribute(“dati”)!=null AND request.getParameter("ordini")!=null AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		System.out.println("Sono nel Cerca Cliente Admin\n");
		System.out.println("email Cerca Utente: "+email);
		
		try {
			request.removeAttribute("dati");
			request.removeAttribute("email");
			UtenteBean dati=id_utente.doRetrieveByMail(email);
			ArrayList<OrdineBean> ordini=id_ordine.doRetrieveByUser(email);
			request.setAttribute("dati", dati);
			request.setAttribute("email", ordini);
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/AdminCercaEmail.jsp");
			dispatcher.forward(request, response);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}