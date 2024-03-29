package gestioneIndirizzi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import gestioneUtenza.UtenteBean;
import gestioneCarrello.Carrello;

/**
 * Questa classe e' un control che si occupa di recuperare gli indirizzi usando IndirizziDao.
 */
@WebServlet("/Indirizzo")
public class IndirizzoServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	static boolean isDataSource=true;
	private IndirizziDao indDao=new IndirizziDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		indDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));
	}
	
	public IndirizzoServlet() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute("auth")!=null 
	 * @postcondition request.getAttribute("attivo")!=null AND request.getAttribute("indirizzi")!=null AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean bean=(UtenteBean) request.getSession().getAttribute("auth");
		if(request.getSession().getAttribute("auth")!=null) {
			System.out.println("Sono nella Servlet Indirizzi ");
			
			try {
			    Collection<IndirizziBean> beans=indDao.doRetrieveAllAddress(bean.getEmail());
			    for(IndirizziBean b:beans) {
			    	System.out.println(b.getAddress());
			    	}
			    IndirizziBean defaultAddress=indDao.doRetrieveActive(bean.getEmail());
			    request.removeAttribute("attivo");
			    request.setAttribute("attivo",defaultAddress);
			    System.out.println(request.getAttribute("attivo"));
				request.removeAttribute("indirizzi");
				request.setAttribute("indirizzi", beans);
				}catch(SQLException e) {
					System.out.println("Errore Indirizzi Servlet: " + e.getMessage());
				}
				String page=request.getParameter("page");
				System.out.println(page);
				if(page!=null && page.equals("ok")){
					System.out.println("if");
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/address.jsp");
					dispatcher.forward(request, response);
					return;
					}
				else {
					System.out.println("else");

					Carrello cart=(Carrello) request.getSession().getAttribute("carrello");
					
					if(cart!=null ) {
						
						if (cart.getDimensione()>0) {
							RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/checkout.jsp");
							dispatcher.forward(request, response);
							return;
						}
						else {
							RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
							dispatcher.forward(request, response);
							return;
						}
						
						
					}
					else {
						
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
						dispatcher.forward(request, response);
						return;
					}
				   }
			   }
			else {
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
				return;
			    }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}