package gestioneOcchiali;

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

import gestioneCarrello.Carrello;

/**
 * Questa classe è un control che si occupa di ottenere i dettagli di un occhiale passando l’id a OcchialeDao.
 */
@WebServlet("/Prodotto")
public class ServletProdotti extends HttpServlet {
	private static final long serialVersionUID=1L;
	static boolean isDataSource=true;
	//private static Model<OcchialeBean, DataSource> modelOcchiale=new OcchialeDao();
	private OcchialeDao modelOcchiale=new OcchialeDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		modelOcchiale.setDB((DataSource) getServletContext().getAttribute("DataSource"));
	}
	
	public ServletProdotti() {
		super();
	}
	
	/**
	 * @precondition request.getParameter("id")!=null AND request.getParameter("azione")!=null  
	 * @postcondition request.getAttribute("carrello") AND request.getAttribute("descrizione") AND request.getAttribute("id") AND dispatcher!=null 
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Carrello car=(Carrello) session.getAttribute("carrello");
		if(car==null) {
			car=new Carrello();
			session.setAttribute("carrello", car);
		}
		ArrayList<String> valori=new ArrayList<String>();
		valori.add(request.getParameter("id"));
		synchronized(session) {
		try {
			request.removeAttribute("descrizione");
			request.setAttribute("descrizione", modelOcchiale.singleProduct(valori));
			request.setAttribute("id", request.getParameter("id"));
			session.setAttribute("carrello", car);
			String azione= request.getParameter("action");
			System.out.println("Action value: "+ azione);
			
			if(azione!=null && azione.equalsIgnoreCase("dettagli")) {
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/prodotto.jsp");
				dispatcher.forward(request, response);
			}
			if(azione!=null && azione.equalsIgnoreCase("aggiungi")) {			
				System.out.println("Sono nell'if aggiungi ");
				OcchialeBean occhiale=(OcchialeBean) request.getAttribute("descrizione");
				if(!car.searchProdotto(occhiale.getIdGlasses())) {
					car.addCarrello(occhiale);
					car.getPrezzoTotale(1, valori.get(0));
					}
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/carrello.jsp");
				dispatcher.forward(request, response);
			}
			if(Integer.parseInt(request.getParameter("scelta"))>=1) {
				RequestDispatcher dis=getServletContext().getRequestDispatcher("/carello.jsp");
				dis.forward(request, response);
			}
		}
		catch(Exception e) {
			System.out.println("Errore Servlet Prodotti: " + e.getMessage());
			}
		}
		/*RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/shop.jsp");
		dispatcher.forward(request, response);*/
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}