package webLayer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.user.entities.UtenteDB;
import serviceLayer.login.implementation.ImplLogin;
import serviceLayer.registration.implementation.ImplRegistrazione;
import utilities.StateResult;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DoPostLOGIN");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("email="+email+"&password="+password+"\n");
		
		
		ImplLogin log = new ImplLogin();
		UtenteDB utente = new UtenteDB();
		utente.setEmail(email);
		
		StateResult result = log.loginUtente(utente, password);
		
		StringBuffer xmlReply = new StringBuffer();
		
		//System.out.println(result.toString());
		
		if(result == StateResult.VALID) {
			
			xmlReply.append("<risposta><id>"+utente.getId()+"</id><risultato>utenteLoggato</risultato></risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else if (result == StateResult.NOVALID) {
			
			xmlReply.append("<risposta><risultato>passwordErrata</risultato></risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else {
			
			xmlReply.append("<risposta><risultato>emailErrata</risultato></risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}
	}

}
