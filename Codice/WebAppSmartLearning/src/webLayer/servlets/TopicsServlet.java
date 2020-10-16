package webLayer.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataLayer.utilities.StateResult;
import serviceLayer.topic.implementation.ImplTopic;

/**
 * Servlet implementation class RetrieveTopicsServlet
 */
@WebServlet("/RetrieveTopicsServlet")
public class TopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DoGet_RETRIEVE_TOPICS");
		
		ImplTopic impltopics = new ImplTopic();
		Vector<String> topics = new Vector<String>();
		
		StateResult result = impltopics.getTopics(topics);
		
		if(result == StateResult.VALID) {
			StringBuffer xmlReply = new StringBuffer();
			
			xmlReply.append("<risposta>");
			for(int i=0; i<topics.size(); i++) {
				xmlReply.append("<topic>"+topics.get(i)+"</topic>");
			}
			xmlReply.append("</risposta>");
			
			System.out.println(xmlReply.toString());
			response.setContentType("text/xml"); 
			response.getWriter().write(xmlReply.toString()); 
		} else {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
	}

}
