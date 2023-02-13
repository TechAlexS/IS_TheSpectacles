package gestioneOrdini;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Questa classe e' un control che si occupa di passare i dati inseriti dall'admin ad OrdineDao per visualizzare gli ordini di una certa data.
 */
@WebServlet("/ordiniData")
public class ListaOrdiniDataAdmin extends HttpServlet {
	private static final long serialVersionUID=1L;
	private OrdineDao oDao=new OrdineDao();
	
	/**
	 * @return
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();
		oDao.setDB((DataSource) getServletContext().getAttribute("DataSource"));
	}
	
	public ListaOrdiniDataAdmin() {
		super();
	}

	 /**
	 * @precondition request.getSession().getAttribute("auth")!=null AND request.getParameter("skip")!=null AND request.getParameter("limit")!=null 
	 * @postcondition request.getAttribute("ordini")!=null AND request.getAttribute("skip")!=null AND request.getAttribute("limit")!=null AND dispatcher!=null 
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono in ordini per data admin\n");
		
		try {
			int skip=Integer.parseInt(request.getParameter("skip"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			if(limit==0)
				limit=10;
			Date dateStart=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateStart"));
			Date dateEnd=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateEnd"));
			ArrayList<OrdineBean> ordini=oDao.doRetriveByDate(dateStart, dateEnd, skip, limit);
			request.setAttribute("ordini", ordini);
			request.setAttribute("skip", skip);
			request.setAttribute("limit", limit);
			RequestDispatcher dis=getServletContext().getRequestDispatcher("/AdminCercaPerData.jsp");
			dis.forward(request, response);	
			System.out.println("Sono dopo dispatcher \n");
			return;
		} catch(ParseException e) {
			response.getWriter().println("Errore nel convertire la data");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}