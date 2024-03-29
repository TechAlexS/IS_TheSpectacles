package gestioneUtenza;

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
 * Questa classe e' un control che si occupa di gestire l'apertura delle 3 pagine azione dell'Admin 
 */
@WebServlet("/ControlloAdmin")
public class ServletControlloAdmin extends HttpServlet {
	private static final long serialVersionUID=1L;
       
	/**
	 * @precondition request.getSession().getAttribute("auth")!=null AND request.getParameter("id")!=null 
	 * @postcondition request.getAttribute("admin")!=null AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("auth")!=null) {
		
			UtenteBean cerca=(UtenteBean) request.getSession().getAttribute("auth");
			if(cerca.getRole()==1) {
				String azione=request.getParameter("azione");
				
				request.setAttribute("admin", cerca);
				
				if(azione!=null && azione.equalsIgnoreCase("controllo")) {
					RequestDispatcher dis=request.getRequestDispatcher("/PageAmministratore.jsp");
					dis.forward(request, response);
				}
				if(azione!=null && azione.equalsIgnoreCase("ordiniNominativo")) {
					RequestDispatcher dis=request.getRequestDispatcher("/ControlloOrdiniAdmin.jsp");
					dis.forward(request, response);
				}
				if(azione!=null && azione.equalsIgnoreCase("ordiniData")) {
					RequestDispatcher dis=request.getRequestDispatcher("/ControlloOrdiniDataAdmin.jsp");
					dis.forward(request, response);
				}
				if(azione==null) {
				RequestDispatcher dis=request.getRequestDispatcher("/ControlloAmministratore.jsp");
				dis.forward(request, response);
				}
			}else {
				RequestDispatcher dis=getServletContext().getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			}
		}else {
			RequestDispatcher dis=getServletContext().getRequestDispatcher("/login.jsp");
			dis.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}