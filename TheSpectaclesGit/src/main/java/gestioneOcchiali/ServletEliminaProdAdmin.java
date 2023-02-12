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
import java.util.*;

/**
 * Questa classe e' un control che si occupa di rimuovere passare l'id di un Occhiale a OcchialeDao, che eliminera' il prodotto dal DB.
 */
@WebServlet("/EliminaProdAdmin")
public class ServletEliminaProdAdmin extends HttpServlet {
	private static final long serialVersionUID=1L;
	private OcchialeDao occhialeDao=new OcchialeDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		occhialeDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));	
	}
	
    public ServletEliminaProdAdmin() {
        super();
    }   
    
    /**
	 * @precondition request.getSession().getAttribute("carrello")!=null AND request.getParameter("id")!=null 
	 * @postcondition request.getSession().getAttribute("auth")!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		
		try {
			occhialeDao.doDelete(id);
			RequestDispatcher dis=request.getRequestDispatcher("/PageAmministratore.jsp");
			dis.forward(request, response);
		}
		catch(SQLException e) {
			System.out.println("Errore ServletEliminaProdAdmin: " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}