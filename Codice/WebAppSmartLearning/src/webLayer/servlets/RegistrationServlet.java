package webLayer.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.utilities.StateResult;
import serviceLayer.registration.implementation.ImplRegistrazione;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DoPostREGISTER");
		
		String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("cognome="+cognome+"&nome="+nome+"&email="+email+"&password="+password+"\n");
		
		
		ImplRegistrazione reg = new ImplRegistrazione();
		
		StateResult result = reg.registraUtente(nome, cognome, email, password);
		StringBuffer xmlReply = new StringBuffer();
		
		System.out.println(result.toString());
		
		if(result == StateResult.CREATED) {
			
			xmlReply.append("<risposta>utenteCreato</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else if (result == StateResult.NOCHANGES) {
			
			xmlReply.append("<risposta>utenteNonCreato</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else {
			
			xmlReply.append("<risposta>errore</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
