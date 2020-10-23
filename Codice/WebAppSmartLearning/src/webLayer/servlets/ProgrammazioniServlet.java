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

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import serviceLayer.lezione.implementation.ImplLezione;
import serviceLayer.user.implementation.ImplUtente;
import utilities.StateResult;
import utilities.idFasciaOraria;
import utilities.idLesson;
import utilities.idUser;

/**
 * Servlet implementation class RetrieveMyProgServlet
 */

public class ProgrammazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgrammazioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("ProgramsLessonServlet");
		
		String idlez = request.getParameter("idlez");
		
		ImplLezione ilez = new ImplLezione();
		Vector<FasciaOraria> fasce = new Vector<FasciaOraria>();
		
		System.out.println("idlez="+idlez);
		 
		
		StateResult result = ilez.getFasceOrarie(new idLesson(Integer.parseInt(idlez)), fasce);

		StringBuffer xmlReply = new StringBuffer();
		
		if (result == StateResult.VALID) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			
			xmlReply.append("<risposta>");
			for(int i=0; i<fasce.size(); i++) {
				xmlReply.append("<fascia><id>"+fasce.get(i).getId().getId()+"</id><data>"+df.format(fasce.get(i).getDataLezione())+"</data><orarioinizio>"+fasce.get(i).getOrarioInizio().getHours()+':'+fasce.get(i).getOrarioInizio().getMinutes()+
						"</orarioinizio><orariofine>"+fasce.get(i).getOrarioFine().getHours()+':'+fasce.get(i).getOrarioFine().getMinutes()+"</orariofine><prezzo>"+ 
						fasce.get(i).getPrezzo()+"</prezzo>"+
								"</fascia>");
			}
			xmlReply.append("</risposta>");
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 
			
		}else {
			xmlReply.append("<risposta>noFasce</risposta>");
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 
		}
		
		System.out.println(xmlReply.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}


}
