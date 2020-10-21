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
	public StateResult getTopics(Vector<String> topics) {
		// TODO Auto-generated method stub
		ControllerTopicDB controller = new ControllerTopicDB();
		
		StateResult result = controller.getTopics(topics);
		
		return result;
	}

}
