package webLayer.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;
import serviceLayer.user.implementation.ImplUtente;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		ImplUtente user = new ImplUtente();
		String myId = request.getParameter("requesterId");
		
		System.out.println("FILTRO AUTH! ID:"+myId);
		
		if(myId == "" || myId == null) {
			System.out.println("errore AuthFilter");
			StringBuffer xmlReply = new StringBuffer();
			xmlReply.append("<risposta>errore</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else {
			StateResult valid = user.validateUser(new idUser(Integer.parseInt(myId)));
			
			if(valid == StateResult.VALID) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}else {
				System.out.println("Utente Non Loggato");
				StringBuffer xmlReply = new StringBuffer();
				
				xmlReply.append("<risposta>utenteNonLoggato</risposta>");
				response.setContentType("text/xml");
				response.getWriter().write(xmlReply.toString());
			}
		
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
