package serviceLayer.topic.interfaces;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;

public interface ITopic {

	public StateResult getLessonsByTopic(Vector<String> str, Vector<LezioneDB> lezioni);
}
