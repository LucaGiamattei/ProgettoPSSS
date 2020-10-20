package serviceLayer.lezione;

import java.util.Vector;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;

public interface ILezione {
	
	public StateResult creaLezione(idUser iduser, String nome, String descrizione, String nomeTopic, int nmax);
	public StateResult addFasciaOraria(idUser iduser, idLesson idlesson, FasciaOraria fascia);
	public StateResult getFasceOrarie(idLesson idlesson, Vector<FasciaOraria> fascia);
	public StateResult removeFasciaById(idFasciaOraria id);
	public StateResult getPayedLessons(idUser iduser, Vector<String> topics, Vector<LezioneDB> lezioni);
	public StateResult getPayedFasceByLesson(idUser iduser,idLesson idlez,  Vector<FasciaOraria> fasce);
	
}
