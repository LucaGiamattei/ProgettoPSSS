package webLayer.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;
import serviceLayer.user.implementation.ImplUtente;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		

		ImplUtente utente = new ImplUtente();
		Vector<LezioneDB> lezioni = new Vector<LezioneDB>();
		Vector<String> str = new Vector<String>();
		str.add(ricerca);
		
		StateResult result = utente.getLessonsByCognome(str, lezioni);
		
		System.out.println("[SEARCHSERVLET] nome: "+ str.get(1)+" topic: "+str.get(2));
		
		StringBuffer xmlReply = new StringBuffer();
		System.out.println(result.toString());
		
		xmlReply.append("<risposta>");
		
		for(int i=0; i<lezioni.size(); i++) {
			xmlReply.append("<lezione><nome>"+lezioni.get(i).getNomeLezione()+"</nome><nstudenti>"+lezioni.get(i).getNmax()+"</nstudenti><descrizione>"+lezioni.get(i).getDescrizioneLezione()+
					"</descrizione><score>"+lezioni.get(i).getMedia_score()+"</score><cognomedoc>"+ricerca+"</cognomedoc><nomedoc>"+str.get(1)+"</nomedoc><topic>"+str.get(2)+"</topic>");
				for(int j=0; j<lezioni.get(i).getSlots().size(); j++) {
					xmlReply.append("<fascia><data>"+lezioni.get(i).getSlots().get(j).getDataLezione()+"</data><orarioinizio>"+lezioni.get(i).getSlots().get(j).getOrarioInizio()+
							"</orarioinizio><orariofine>"+lezioni.get(i).getSlots().get(j).getOrarioFine()+"</orariofine></fascia>");
				
				}
				xmlReply.append("</lezione>");
		}
		
		xmlReply.append("</risposta>");
		
		if(result == StateResult.VALID) {
			System.out.println(xmlReply.toString());
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 			
		} else {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
		}

	}
	
	

}


/*
 * <risposta>
 * 	<lezione>
 * 		<nome></nome>
 * 		<nstudenti></nstudenti>
 * 		<prezzo></prezzo>						!!!DA TOGLIERE QUI E AGGIUNGERE A FASCIA ORARIA
 * 		<descrizione></descrizione>
 * 		<score></score>
 * 		
 * 		<fascia>
 * 			<data></data>
 * 			<orarioinizio></orarioinizio>
 * 			<orariofine></orariofine>
 * 		</fascia>
 * 		...
 * 
 * 		<cognomedoc></cognomedoc>
 * 		<nomedoc></nomedoc>
 * 		<topic></topic>						
 *	</lezione>
 * 	...
 * 
 * </risposta>
 * */



