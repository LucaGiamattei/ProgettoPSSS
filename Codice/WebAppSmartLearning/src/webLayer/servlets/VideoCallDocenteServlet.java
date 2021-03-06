package webLayer.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.lezione.entities.FasciaOraria;
import serviceLayer.videoroom.implementation.ImplVideoRoom;
import utilities.StateResult;

/**
 * Servlet implementation class AvviaVideoCallServlet
 */
@WebServlet("/AvviaVideoCallServlet")
public class VideoCallDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoCallDocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet_AVVIAVIDEOCALL");
		ImplVideoRoom implVideoRoom = new ImplVideoRoom();
		String[] tokenDocente = new String[1];
		String[] nomeRoom = new String[1];
		Vector<String> tokens = new Vector<String>();
		
		// TODO Auto-generated method stub
		
		String idFasciaOraria = request.getParameter("idprog");
		String idDocente = request.getParameter("requesterId");
		
		System.out.println("idFasciaOraria="+idFasciaOraria+"&idDocente="+idDocente+"\n");
		
		
		StringBuffer xmlReply = new StringBuffer();
		
		if (implVideoRoom.avviaVideoRoom(idFasciaOraria,idDocente,nomeRoom, tokenDocente, tokens)==StateResult.CREATED) {
				xmlReply.append("<risposta><risultato>VideoCallCreata</risultato><nomeRoom>"+nomeRoom[0]+"</nomeRoom><tokenDocente>"+tokenDocente[0]+"</tokenDocente><tokenUtente>");
				for (int i=0; i<tokens.size();i++) {
					xmlReply.append("<token>"+tokens.get(i)+"</token>");
				}
				xmlReply.append("</tokenUtente></risposta>");
				response.setContentType("text/xml");
				System.out.println("XML: "+ xmlReply.toString());
				response.getWriter().write(xmlReply.toString());
			
		}else {
			xmlReply.append("<risposta><risultato>VideoCallNonCreata</risultato></risposta>");
			response.setContentType("text/xml");
			response.getWriter().write(xmlReply.toString());
			System.out.println("XML2: "+ xmlReply.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet_DELETEVIDEOCALL");
		ImplVideoRoom implVideoRoom = new ImplVideoRoom();
		String idFasciaOraria = request.getParameter("idprog");
		String idDocente = request.getParameter("requesterId");
		StringBuffer xmlReply = new StringBuffer();
		
	
		if (implVideoRoom.deleteVideoRoom(idFasciaOraria, idDocente)==StateResult.REMOVED) {
			
				xmlReply.append("<risposta><risultato>VideoCallEliminata</risultato></risposta>");
		}else {
			xmlReply.append("<risposta><risultato>VideoCallNonEliminata</risultato></risposta>");
		}
		response.setContentType("text/xml");
		response.getWriter().write(xmlReply.toString());
	}

}
