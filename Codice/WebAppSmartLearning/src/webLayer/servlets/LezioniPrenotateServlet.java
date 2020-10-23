package webLayer.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.lezione.entities.LezioneDB;
import serviceLayer.lezione.implementation.ImplLezione;
import utilities.StateResult;
import utilities.idUser;

/**
 * Servlet implementation class SubscribesServlet
 */

public class LezioniPrenotateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LezioniPrenotateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("doGET_subscribesServlet");
		
		ImplLezione impll = new ImplLezione();
		Vector<LezioneDB> lezioni = new Vector<LezioneDB>();
		Vector<String> topics = new Vector<String>();
		String reqId = request.getParameter("requesterId");
		
		
		
		StateResult result = impll.getPayedLessons(new idUser(Integer.parseInt(reqId)), topics, lezioni);
		
		StringBuffer xmlReply = new StringBuffer();
		
		if (result == StateResult.VALID) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			
			xmlReply.append("<risposta>");
			
			for(int i=0; i<lezioni.size(); i++) {
				xmlReply.append("<lezione><id>"+lezioni.get(i).getId().getId()+"</id><nome>"+lezioni.get(i).getNomeLezione()+"</nome><descrizione>"+lezioni.get(i).getDescrizioneLezione()+
						"</descrizione><topic>"+topics.get(i)+"</topic>");
					for(int j=0; j<lezioni.get(i).getSlots().size(); j++) {
						xmlReply.append("<fascia><data>"+df.format(lezioni.get(i).getSlots().get(j).getDataLezione())+"</data><orarioinizio>"+lezioni.get(i).getSlots().get(j).getOrarioInizio().getHours()+':'+lezioni.get(i).getSlots().get(j).getOrarioInizio().getMinutes()+
								"</orarioinizio><orariofine>"+lezioni.get(i).getSlots().get(j).getOrarioFine().getHours()+':'+lezioni.get(i).getSlots().get(j).getOrarioFine().getMinutes()+"</orariofine>"+
										"</fascia>");
					
					}
					xmlReply.append("</lezione>");
			}
			xmlReply.append("</risposta>");
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 	
		}else {
			xmlReply.append("<risposta>noSubscribes</risposta>");
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
