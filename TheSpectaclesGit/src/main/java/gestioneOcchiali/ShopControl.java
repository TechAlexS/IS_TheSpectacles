package gestioneOcchiali;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Questa classe e' un control che si occupa di passare l'email a OrdineDao per ottenere la lista dei suoi ordini.
 */
 @WebServlet("/ShopControl")
public class ShopControl extends HttpServlet {
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
	
	public ShopControl() {
		super();
	}

	/**
	 * @precondition request.getAttribute("sort")!=null 
	 * @postcondition request.getAttribute("occhiali")!=null AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort=request.getParameter("sort");
      
		try {
			request.removeAttribute("occhiali");
			request.setAttribute("occhiali", modelOcchiale.doRetrieveAll(sort));
		} catch(SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/shop.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}