package webLayer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceLayer.videoroom.implementation.ImplVideoRoom;
import utilities.StateResult;

/**
 * Servlet implementation class JoinVideoCallServlet
 */
public class VideoCallUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoCallUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("doGet_JOINVIDEOCALL");
		ImplVideoRoom implVideoRoom = new ImplVideoRoom();
		String[] tokenUtente = new String[1];
		String[] nomeRoom = new String[1];
		
		String idFasciaOraria = request.getParameter("idprog");
		String idUtente = request.getParameter("requesterId");
		
		System.out.println("idFasciaOraria="+idFasciaOraria+"&idDocente="+idUtente+"\n");
		
		StateResult result = implVideoRoom.getJoinUserData(idFasciaOraria, idUtente, tokenUtente, nomeRoom);
		System.out.println("Nome room: "+nomeRoom[0]);
		StringBuffer xmlReply = new StringBuffer();
		
		if(result == StateResult.VALID) {
			xmlReply.append("<risposta><risultato>DatiRecuperati</risultato><nomeRoom>"+nomeRoom[0]+"</nomeRoom><tokenUtente>"+tokenUtente[0]+"</tokenUtente></risposta>");
		}else {
			xmlReply.append("<risposta><risultato>Errore</risultato></risposta>");

		}
		response.setContentType("text/xml");
		System.out.println("XML: "+ xmlReply.toString());
		response.getWriter().write(xmlReply.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
