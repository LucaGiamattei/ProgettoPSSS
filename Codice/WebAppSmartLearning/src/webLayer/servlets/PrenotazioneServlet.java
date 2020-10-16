package webLayer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idUser;
import serviceLayer.lezione.implementation.ImplLezione;
import serviceLayer.pagamento.implementation.ImplPagamento;

/**
 * Servlet implementation class SubscribeServlet
 */


public class PrenotazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazioneServlet() {
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
		
		System.out.println("doGET_subscribeServlet");
		String reqId = request.getParameter("requesterId");
		String progId = request.getParameter("idprog");
		System.out.println("requesterId="+reqId+"&idprog="+progId+"\n");
		StringBuffer xmlReply = new StringBuffer();
		StateResult result = StateResult.NOCHANGES;
		ImplPagamento implp = new ImplPagamento();
		FasciaOraria fascia = new FasciaOraria();
		fascia.setId(new idFasciaOraria(Integer.parseInt(progId)));
		
		
		
		if (implp.verifyFasciaOrariaIsNotStarted(fascia ) == StateResult.VALID){
			result = implp.effettuaPagamento(new idUser(Integer.parseInt(reqId)), new idFasciaOraria(Integer.parseInt(progId)));
		}
		
		if(result == StateResult.CREATED) {
			
			xmlReply.append("<risposta>subscribeOK</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else if (result == StateResult.NOCHANGES) {
			
			xmlReply.append("<risposta>subscribeERROR</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else {
			
			xmlReply.append("<risposta>errore</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}

	}

}
