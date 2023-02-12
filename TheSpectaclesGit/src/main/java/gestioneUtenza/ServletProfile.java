package gestioneUtenza;

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
import java.util.*;

/**
 * Questa classe è un control che si occupa di ottenere le informazioni dell’utente loggato, usando UtenteDao.
 */
@WebServlet("/Profile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID=1L;
	static boolean isDataSource=true;
	private UtenteDao utenteDao=new UtenteDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();		
		utenteDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));	
	}
	
	@Override
	public ServletProfile() {
		super();
	}

	/**
	 * @precondition request.getSession().getAttribute(“auth”)!=null 
	 * @postcondition dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth")!=null) {
			System.out.println("Sono nella Servlet profile ");
			RequestDispatcher dispatcher= etServletContext().getRequestDispatcher("/profile_details.jsp");
			dispatcher.forward(request, response);
			return;			
			} else {
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