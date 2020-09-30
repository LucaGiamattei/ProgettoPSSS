package webLayer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.utilities.StateResult;
import serviceLayer.login.implementation.ImplLogin;
import serviceLayer.registration.implementation.ImplRegistrazione;

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
		
		StateResult result = log.loginUtente(email, password);
		
		StringBuffer xmlReply = new StringBuffer();
		
		System.out.println(result.toString());
		
		if(result == StateResult.VALID) {
			
			xmlReply.append("<risposta>utenteLoggato</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else if (result == StateResult.NOVALID) {
			
			xmlReply.append("<risposta>passwordErrata</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else {
			
			xmlReply.append("<risposta>emailErrata</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}
	}

}
