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

import java.util.*;

/**
 * Questa classe e' un control che si occupa di recuperare i dettagli da OrdineDao tramite OcchialeOrdineDao.
 */
@WebServlet("/DettagliOrdini")
public class ServletDettagliOrdine extends HttpServlet {
	private static final long serialVersionUID=1L;
	static boolean isDataSource=true;
	private OrdineDao ordineDao=new OrdineDao();
	private OcchialeOrdineDao occhialeOrdineDao=new OcchialeOrdineDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		occhialeOrdineDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));
		ordineDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));
	}
	
	public ServletDettagliOrdine() {
		super();
	}	
	
	/**
	 * @precondition request.getSession().getAttribute("auth")!=null AND request.getParameter("ordineId")!=null 
	 * @postcondition request.getAttribute("ordine")!=null AND request.getParameter("prodotti")!=null AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet dettagli ordini\n");
		if(request.getSession().getAttribute("auth")!=null) {
			UtenteBean bean=(UtenteBean) request.getSession().getAttribute("auth");
			String idOrdine=request.getParameter("ordineId");
			
			try {
				OrdineBean ordine=ordineDao.doRetrieveByKey(idOrdine);
				//System.out.println(ordine);
				
				//request.setAttribute("prodotti", occhialeOrdineDao.doRetrivebyOrder(idOrdine,(DataSource) getServletContext().getAttribute("DataSource")));
				request.setAttribute("prodotti", occhialeOrdineDao.doRetrivebyOrder(idOrdine));
				request.setAttribute("ordine", ordine);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/orderDetails.jsp");
				dispatcher.forward(request, response);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}