package webLayer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceLayer.lezione.implementation.ImplLezione;
import utilities.StateResult;
import utilities.idUser;

/**
 * Servlet implementation class LessonServlet
 */
public class LezioneDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LezioneDocenteServlet() {
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
		System.out.println("lessonServlet");
		
		String descrizione = request.getParameter("descrizione");
		String nome = request.getParameter("nome");
		String topic = request.getParameter("topic");
		String maxstud = request.getParameter("maxstud");
		
		System.out.println("descrizione="+descrizione+"&nome="+nome+"&topic="+topic+"&maxstud="+maxstud+"\n");
		
		String myId = request.getParameter("requesterId");
		ImplLezione lez = new ImplLezione();
		
		StateResult result = lez.creaLezione(new idUser(Integer.parseInt(myId)),nome, descrizione, topic, Integer.parseInt(maxstud));
		StringBuffer xmlReply = new StringBuffer();
		
		System.out.println(result.toString());
		
		if(result == StateResult.CREATED) {
			
			xmlReply.append("<risposta>lezioneCreata</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else if (result == StateResult.NOCHANGES) {
			
			xmlReply.append("<risposta>lezioneNonCreata</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			
		}else {
			
			xmlReply.append("<risposta>errore</risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
		}

	}
		
}
