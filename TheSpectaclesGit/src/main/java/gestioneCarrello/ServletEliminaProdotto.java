package gestioneCarrello;

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

import gestioneOcchiali.OcchialeBean;

/**
 * Questa classe e' un control che si occupa di eliminare un occhiale dal Carrello, scelto dall'utente 
 */
@WebServlet("/EliminaProdotto")
public class ServletEliminaProdotto extends HttpServlet {
	private static final long serialVersionUID=1L;
       
    public ServletEliminaProdotto() {
        super();
    }

    /**
	 * @precondition request.getSession().getAttribute("carrello")!=null AND request.getParameter("id")!=null 
	 * @postcondition request.getSession().getAttribute("auth")!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello car=(Carrello) request.getSession().getAttribute("carrello");
		String id=request.getParameter("id");
		OcchialeBean bean=car.prendiProdotto(id);
		car.deleteProduct(bean);
		request.getSession().setAttribute("carrello", car);
		RequestDispatcher req=request.getRequestDispatcher("carrello.jsp");
		req.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}