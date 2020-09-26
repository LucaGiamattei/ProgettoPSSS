package dataLayer.lezione.interfaces;

import java.util.Vector;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
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
	 *- DBPROBLEM
	 */
	StateResult validLezione(idLesson idLesson);
	
	
//------------------------OPERAZIONI DOCENTE-------------------------------
	/**
	 *Questa funzione permette di creare una lezione 
	 *@warning La lezione è intesa in senso lato, ovvero senza alcuna specializzazione in fascia oraria
	 *
	 *@param idLesson
	 *@param idTopic
	 *@param infoLezione 
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOCHANGES
	 *- DBPROBLEM
	 *- CREATED
	 */
	StateResult createLesson(LezioneDB infoLezione);
	
	/**
	 * Questa funzione permette di selezionare tutte le lezioni di un docente (senza la fascia oraria)
	 * 
	 * @param idOwnerUser 
	 * @param lezioni vettore di lezioni dell'utente
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- DBPROBLEM
	 *- NOVALID
	 */
	StateResult getLessonsByUser(idUser idOwnerUser, Vector<LezioneDB> lezioni);
	
	/**
	 * Questa funzione permette di arricchire le lezioni di un docente con la relative fasce orarie (anche quelle non visibili agli utenti)
	 * @param idOwnerUser 
	 * @param lezioni (I/O) Da Input deve fornire principlamente gli identificativi delle lezioni, Da Output sarà arricchita ogni lezione con i riferimenti alle relative fasce orarie
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- DBPROBLEM
	 */
	StateResult attachSlotsToLessonsDocente(idUser idOwnerUser, Vector<LezioneDB> lezioni);
	
	/**
	 * Questa funzione permette di aggiungere uno o più slot a una lezione. Aggiungere più slot significherà fare un'unica transazione.
	 * @param idOwnerUser Owner della lezione
	 * @param idLezione Lezione a cui aggiungere lo slot
	 * @param slot elenco degli slot da aggiungere
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- DBPROBLEM
	 *- CREATED
	 */
	
	StateResult addFasciaOraria(idUser idOwnerUser, idLesson idLezione, FasciaOraria[] slots);
	
	/**
	 * Questa funzione permette di aggiornare uno slot di una lezione.
	 * @param id
	 * @param slotAggiornato
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- UPDATED
	 *- DBPROBLEM
	 *- NOUPDATED
	 */
	
	StateResult  updateFasciaOraria(idFasciaOraria id, FasciaOraria slotAggiornato);
	
	
	
//------------------------OPERAZIONI UTENTE-------------------------------------
	
	/**
	 *Questa funzione permette di ottenere un vettore di lezioni che riguardano un determinato topic
	 *
	 *@param lezioni (I/O) è un vettore di lezioni ognuno contenente un vettore di riferimenti alle fasce orarie in cui è possibile prenotare
	 *@param infoTopic
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DBPROBLEM
	 */
	 StateResult getLessonsbyTopics(idTopic infoTopic, Vector<LezioneDB> lezioni);
	
	/**
	 *Questa funzione permette di ottenere un vettore di lezioni che riguardano un determinato nome di una lezione
	 *@param lezioni (I/O) Da output sarà il  vettore di lezioni che riguardano il  title definito come parametro di input 
	 *@param title
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DBPROBLEM
	 */
	 StateResult getLessonsbyTitle(String title, Vector<LezioneDB> lezioni);
	 
	 /**
	  * Questa funzione permette di collegare gli slot alle lezioni (ogni lezione deve contenere almeno l'id utente e l'id lezione)
	  * @param lezioni
	  * @return StateResult Rappresenta lo stato dell'operazione:
	  *- VALID
	  *- DBPROBLEM
	  */
	 
	 StateResult attachSlotsToLessonsUtente(Vector<LezioneDB> lezioni);
	
}
