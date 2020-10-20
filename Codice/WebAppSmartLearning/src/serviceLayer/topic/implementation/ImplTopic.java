package serviceLayer.topic.implementation;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.controller.ControllerTopicDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import serviceLayer.topic.ITopic;

public class ImplTopic implements ITopic {

	@Override
	public StateResult getLessonsByTopic(Vector<String> str, Vector<LezioneDB> lezioni) {
		// TODO Auto-generated method stub
		ControllerTopicDB controllertopic = new ControllerTopicDB();
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controllertopic.getLessonsByTopicName(str.get(0), lezioni);
		
		if(lezioni.size() > 0) {
			UtenteDB utente = new UtenteDB();
			controller.retrieveUser(lezioni.get(0).getIdUtente(), utente);
			str.add(utente.getCognome());
			str.add(utente.getNome());
		}
		
		return result;
	}

	@Override
	public StateResult getTopics(Vector<String> topics) {
		// TODO Auto-generated method stub
		ControllerTopicDB controller = new ControllerTopicDB();
		
		StateResult result = controller.getTopics(topics);
		
		return result;
	}

}
