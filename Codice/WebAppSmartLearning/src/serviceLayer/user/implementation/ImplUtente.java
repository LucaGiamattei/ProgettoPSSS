package serviceLayer.user.implementation;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.controller.ControllerTopicDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import serviceLayer.user.interfaces.IUtente;

public class ImplUtente implements IUtente {

	@Override
	public StateResult getLessonsByCognome(Vector<String> str, Vector<LezioneDB> lezioni) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		ControllerTopicDB controllertopic = new ControllerTopicDB();
		
		
		StateResult result = controller.getLessonsByCognome(str.get(0), lezioni);
		
		if(lezioni.size() > 0) {
		UtenteDB utente = new UtenteDB();
		controller.retrieveUser(lezioni.get(0).getIdUtente(), utente);
		str.add(utente.getNome());
		
		TopicDB topicvar = new TopicDB(lezioni.get(0).getIdTopic());
		controllertopic.getTopicName(topicvar);

		str.add(topicvar.getNome());
		
		}
		
		
		System.out.println("[IMPLUTENTE] nome: "+ str.get(1)+" topic: "+str.get(2));
		return result;
	}

}
