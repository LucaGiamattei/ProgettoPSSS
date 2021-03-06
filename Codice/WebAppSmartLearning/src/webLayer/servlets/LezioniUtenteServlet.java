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
import dataLayer.user.entities.UtenteDB;
import serviceLayer.lezione.implementation.ImplLezione;
import serviceLayer.topic.implementation.ImplTopic;
import serviceLayer.user.implementation.ImplUtente;
import utilities.StateResult;
import utilities.idUser;


/**
 * Servlet implementation class SearchServlet
 */

public class LezioniUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LezioniUtenteServlet() {
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
		
		System.out.println("DoPostSEARCH");
		
		String ricerca = request.getParameter("ricerca");
		System.out.println("ricerca="+ricerca+"\n");

		ImplLezione lezione = new ImplLezione();
		Vector<LezioneDB> lezioni = new Vector<LezioneDB>();
		boolean isCognome = true;
		
		Vector<String> str = new Vector<String>();
		str.add(ricerca);
		
		StateResult result = lezione.getLessonsByCognome(str, lezioni); //str(0): cognome str(1): nome str(2): topic
		
		if(result == StateResult.NOVALID) {
			isCognome = false;
			result = lezione.getLessonsByTopic(str, lezioni); //str(0): topic str(1): cognome str(2): nome
		}
		
		
		StringBuffer xmlReply = new StringBuffer();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		
		xmlReply.append("<risposta>");
		
		
		if(isCognome) {
			for(int i=0; i<lezioni.size(); i++) {
				xmlReply.append("<lezione><id>"+lezioni.get(i).getId().getId()+"</id><nome>"+lezioni.get(i).getNomeLezione()+"</nome><nstudenti>"+lezioni.get(i).getNmax()+"</nstudenti><descrizione>"+lezioni.get(i).getDescrizioneLezione()+
						"</descrizione><score>"+lezioni.get(i).getMedia_score()+"</score><cognomedoc>"+str.get(0)+"</cognomedoc><nomedoc>"+str.get(1)+"</nomedoc><topic>"+str.get(2+i)+"</topic>");
					for(int j=0; j<lezioni.get(i).getSlots().size(); j++) {
						xmlReply.append("<fascia><data>"+df.format(lezioni.get(i).getSlots().get(j).getDataLezione())+"</data><orarioinizio>"+lezioni.get(i).getSlots().get(j).getOrarioInizio().getHours()+':'+lezioni.get(i).getSlots().get(j).getOrarioInizio().getMinutes()+
								"</orarioinizio><orariofine>"+lezioni.get(i).getSlots().get(j).getOrarioFine().getHours()+':'+lezioni.get(i).getSlots().get(j).getOrarioFine().getMinutes()+"</orariofine><prezzo>"+ 
										lezioni.get(i).getSlots().get(j).getPrezzo()+"</prezzo>"+
										"</fascia>");
					
					}
					xmlReply.append("</lezione>");
			}
		}else {
			for(int i=0; i<lezioni.size(); i++) {
				xmlReply.append("<lezione><nome>"+lezioni.get(i).getNomeLezione()+"</nome><nstudenti>"+lezioni.get(i).getNmax()+"</nstudenti><descrizione>"+lezioni.get(i).getDescrizioneLezione()+
						"</descrizione><score>"+lezioni.get(i).getMedia_score()+"</score><cognomedoc>"+str.get(1)+"</cognomedoc><nomedoc>"+str.get(2)+"</nomedoc><topic>"+str.get(0)+"</topic>");
					for(int j=0; j<lezioni.get(i).getSlots().size(); j++) {
						xmlReply.append("<fascia><data>"+df.format(lezioni.get(i).getSlots().get(j).getDataLezione())+"</data><orarioinizio>"+lezioni.get(i).getSlots().get(j).getOrarioInizio().getHours()+':'+lezioni.get(i).getSlots().get(j).getOrarioInizio().getMinutes()+
								"</orarioinizio><orariofine>"+lezioni.get(i).getSlots().get(j).getOrarioFine().getHours()+':'+lezioni.get(i).getSlots().get(j).getOrarioFine().getMinutes()+"</orariofine><prezzo>"+ 
										lezioni.get(i).getSlots().get(j).getPrezzo()+"</prezzo>"+
										"</fascia>");
					
					}
					xmlReply.append("</lezione>");
			}
		}
		
		if(result == StateResult.NOVALID){
			xmlReply.append("noLezioni");
		}
		xmlReply.append("</risposta>");
		
		if(result == StateResult.VALID) {
			System.out.println(xmlReply.toString());
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 			
		} else if(result == StateResult.NOVALID){
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString());
			System.out.println(xmlReply.toString());
		}else {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
		}

	}
}




