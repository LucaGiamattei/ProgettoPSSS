package serviceLayer.topic.interfaces;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.utilities.StateResult;

public interface ITopic {

	public StateResult getLessonsByTopic(Vector<String> str, Vector<LezioneDB> lezioni);
	public StateResult getTopics(Vector<String> topics);
}
