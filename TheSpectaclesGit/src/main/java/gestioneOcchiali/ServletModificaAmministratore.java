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
 * Questa classe e' un control che si occupa di passare i dati nuovi di un Occhiale esistente a OcchialeDao che fara' l'update dell'occhiale in questione.
 */
@WebServlet("/Modifica")
public class ServletModificaAmministratore extends HttpServlet {
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
	
    public ServletModificaAmministratore() {
        super();  
    }
    
    /**
	 * @precondition request.getSession().getAttribute("auth")!=null AND request.getParameter("nome")!=null AND request.getParameter("descrizione")!=null 
	 * @postcondition OcchialeDao.doUpdate(occhiale) eseguito AND dispatcher!=null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String nome=request.getParameter("nome");
		String descrizione=request.getParameter("desc");
		int quantita=Integer.parseInt(request.getParameter("disp"));
		int prezzo=Integer.parseInt(request.getParameter("prezzo"));
		String id=request.getParameter("id");
		System.out.println("id "+ id);
		OcchialeBean occhiale=new OcchialeBean();
		occhiale.setNameGlasses(nome);
		occhiale.setAvailability(quantita);
		occhiale.setPrice(prezzo);
		occhiale.setDescription(descrizione);
		occhiale.setIdGlasses(id);
		occhialeDao.doUpdate(occhiale);
		}
		catch(Exception e) {
			e.getMessage();
		}
		RequestDispatcher dis=request.getRequestDispatcher("/PageAmministratore.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}