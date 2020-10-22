package webLayer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;
import serviceLayer.user.implementation.ImplUtente;

/**
 * Servlet implementation class UserServlet
 */

public class UtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("DoGetUSER");
		
		UtenteDB utente = new UtenteDB();
		ImplUtente iutente = new ImplUtente();
		utente.setId(new idUser(Integer.parseInt(request.getParameter("requesterId"))));
		
		StateResult result = iutente.getUserDataById(utente);
		
		StringBuffer xmlReply = new StringBuffer();
		
		if (result == StateResult.VALID) {
			
			xmlReply.append("<risposta><nome>"+utente.getNome()+"</nome><cognome>"+utente.getCognome()+"</cognome><email>"+utente.getEmail()+"</email><mediascorelezioni>"+utente.getMediaScoreLezioni()+"</mediascorelezioni></risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}
		
		
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub

	}

}
