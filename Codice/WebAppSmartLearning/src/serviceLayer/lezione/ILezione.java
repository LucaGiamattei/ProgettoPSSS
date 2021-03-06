package serviceLayer.lezione;

import java.util.Vector;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import utilities.StateResult;
import utilities.idFasciaOraria;
import utilities.idLesson;
import utilities.idUser;

public interface ILezione {
	
	public StateResult creaLezione(idUser iduser, String nome, String descrizione, String nomeTopic, int nmax);
	public StateResult addFasciaOraria(idUser iduser, idLesson idlesson, FasciaOraria fascia);
	public StateResult getFasceOrarie(idLesson idlesson, Vector<FasciaOraria> fascia);
	public StateResult removeFasciaById(idFasciaOraria id);
	public StateResult getPayedFasceByLesson(idUser iduser,idLesson idlez,  Vector<FasciaOraria> fasce);
	public StateResult verifyDocenteHasFasciaOraria(String idUtente, FasciaOraria fascia);
	public StateResult getLessonsByTopic(Vector<String> str, Vector<LezioneDB> lezioni);
	public StateResult getPayedLessons(idUser iduser, Vector<String> topics, Vector<LezioneDB> lezioni);
	public StateResult getLessonsById(idUser myid, Vector<String> str, Vector<LezioneDB> lezioni);
	public StateResult getLessonsByCognome(Vector<String> str, Vector<LezioneDB> lezioni);
	public StateResult getLessonsByTopicName(String nome, Vector<LezioneDB> lezioni);
}
