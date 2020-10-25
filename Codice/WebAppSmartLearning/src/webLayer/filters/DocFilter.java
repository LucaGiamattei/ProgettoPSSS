package webLayer.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import dataLayer.user.controller.ControllerUtenteDB;
import serviceLayer.user.implementation.ImplUtente;
import utilities.StateResult;
import utilities.idUser;

/**
 * Servlet Filter implementation class DocFilter
 */
@WebFilter("/DocFilter")
public class DocFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DocFilter() {
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
		
		System.out.println("FILTRO DOCENTE!");
		
		ImplUtente utente = new ImplUtente();
		String myId = request.getParameter("requesterId");
		
		StateResult result = utente.validateDocente(new idUser(Integer.parseInt(myId)));
		
		if(result == StateResult.VALID) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}else if (result == StateResult.NOVALID){
			System.out.println("Tentativo di accesso a funzione docente non autorizzato");
			StringBuffer xmlReply = new StringBuffer();
			
			xmlReply.append("<risposta>utenteNonAutorizzato</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}else {
			System.out.println("Errore DocFilter");
			StringBuffer xmlReply = new StringBuffer();
			xmlReply.append("<risposta>errore</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
