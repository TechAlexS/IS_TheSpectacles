package gestioneUtenza;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Questa classe è un control che si occupa di effettua il logout dell’utente dal sito 
 */
@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
       
	/**
	 * @precondition Request.getSession().getAttribute(“auth”)!=null
	 * @postcondition request.getSession().getAttribute(“auth”)==null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out=response.getWriter()) {
			if(request.getSession().getAttribute("auth")!=null) {
				request.getSession().removeAttribute("auth");
				request.getSession().invalidate();
				response.sendRedirect("login.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
		} 
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}