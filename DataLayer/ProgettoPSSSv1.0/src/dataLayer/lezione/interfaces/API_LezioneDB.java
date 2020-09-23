package dataLayer.lezione.interfaces;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.lezione.entities.result.ResultLezione;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;
import dataLayer.utilities.idTopic;

public interface API_LezioneDB {
	public ResultLezione validLezione(idLesson idLesson);
	public ResultLezione getLessonsByUser(idUser idOwnerUser);
	public ResultLezione createLesson(idUser idOwnerUser, LezioneDB infoLezione);
	public ResultLezione getLessonsbyTopics(idTopic [] infoLezione);
	public ResultLezione getLessonbyTitle(String title);
	
}
