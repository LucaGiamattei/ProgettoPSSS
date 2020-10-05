package webLayer.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;
import serviceLayer.lezione.implementation.ImplLezione;

/**
 * Servlet implementation class ProgramLessonServlet
 */

public class ProgramLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgramLessonServlet() {
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
		System.out.println("ProgramLessonServlet");
		
		String idlez = request.getParameter("idlez");
		String data = request.getParameter("data");
		String orainizio = request.getParameter("orainizio");
		String orafine = request.getParameter("orafine");
		String prezzo = request.getParameter("prezzo");
		String myId = request.getParameter("requesterId");
		
		System.out.println("idlez="+idlez+"&data="+data+"&orainizio="+orainizio+"&orafine="+orafine+"&prezzo="+prezzo+"\n");
		
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		
		try {
			Time orainizioT = new java.sql.Time(formatter.parse(orainizio).getTime());
			Time orafineT = new java.sql.Time(formatter.parse(orafine).getTime());
			Date dataT = Date.valueOf(data);
			FasciaOraria fascia = new FasciaOraria(1, orainizioT, orafineT, dataT, Float.valueOf(prezzo));
			
			
			
			ImplLezione lez = new ImplLezione();
			
			StateResult result = lez.addFasciaOraria(new idUser(Integer.parseInt(myId)),new idLesson(Integer.parseInt(idlez)), fascia);
			
			StringBuffer xmlReply = new StringBuffer();
			
			System.out.println(result.toString());
			
			if(result == StateResult.CREATED) {
				
				xmlReply.append("<risposta>lezioneProgrammata</risposta>");
				response.setContentType("text/xml");
				response.getWriter().write(xmlReply.toString());
				
			}else if (result == StateResult.NOCHANGES) {
				
				xmlReply.append("<risposta>lezioneNonProgrammata</risposta>");
				response.setContentType("text/xml");
				response.getWriter().write(xmlReply.toString());
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

