package dataLayer.lezione.interfaces;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.lezione.entities.result.ResultLezione;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;
import dataLayer.utilities.idTopic;

public interface API_LezioneDB {
	/**
	 *Questa funzione permette di validare l'id di una lezione
	 *
	 *@param idLesson
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DEFAULT
	 *- DBPROBLEM
	 *- CREATED
	 */
	StateResult validLezione(idLesson idLesson);
	//public ResultLezione getLessonsByUser(idUser idOwnerUser);
	
	/**
	 *Questa funzione permette di creare una lezione
	 *
	 *@param idLesson
	 *@param idTopic
	 *@param infoLezione
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DEFAULT
	 *- DBPROBLEM
	 *- CREATED
	 */
	StateResult createLesson(idUser idOwnerUser, idTopic idTopic, LezioneDB infoLezione);
	
	/**
	 *Questa funzione permette di cercare una lezione per id del topic
	 *
	 *@param infoTopic
	 *@return ResultLezione Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DEFAULT
	 *- DBPROBLEM
	 *- CREATED
	 */
	 StateResult getLessonsbyTopics(idTopic infoTopic);
	
	/**
	 *Questa funzione permette di cercare una lezione per nome della lezione
	 *
	 *@param title
	 *@return ResultLezione Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DEFAULT
	 *- DBPROBLEM
	 *- CREATED
	 */
	 StateResult getLessonbyTitle(String title);
	
}
