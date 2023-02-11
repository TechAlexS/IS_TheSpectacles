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

/**
 * Questa classe è un control che si occupa di restituire un catalogo di occhiali.
 */
@WebServlet("/Prodotto2")
public class ServletCarrello extends HttpServlet {
	private static final long serialVersionUID=1L;
       
    public ServletCarrello() {
        super(); 
    }

    /**
	 * @precondition request.getSession().getAttribute("carrello”)!=null AND request.getParameter("id")!=null AND request.getParameter("scelta")!=null
	 * @postcondition Carrello.insertQuantità(id,scelta) eseguito AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Carrello car=(Carrello) session.getAttribute("carrello");
		String id=request.getParameter("nascosto");
		int scelta=Integer.parseInt(request.getParameter("scelta"));
		System.out.println("Sono nella Servlet Carrello: \nId: "+id+"\nQuantita' scelta: "+scelta+"\n");
		car.insertQuantita(id, scelta);
		car.getPrezzoTotale(scelta, id);
		
		RequestDispatcher dis=getServletContext().getRequestDispatcher("/carrello.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}