package serviceLayer.topic;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.entities.TopicDB;
import utilities.StateResult;

public interface ITopic {

	public StateResult getTopics(Vector<String> topics);
}
