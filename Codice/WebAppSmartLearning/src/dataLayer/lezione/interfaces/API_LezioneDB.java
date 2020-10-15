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
	 *@param infoLezione
	 *@param nomeTopic
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOCHANGES
	 *- DBPROBLEM
	 *- CREATED
	 */
	StateResult createLesson(LezioneDB infoLezione, String nomeTopic);
	
	/**
	 * Questa funzione permette di selezionare tutte le lezioni di un docente (con controllo visibile su fasce orarie = '1')
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
	 * Questa funzione permette di selezionare tutte le lezioni di un docente (senza controllo su visibile in fasce orarie)
	 * 
	 * @param idOwnerUser 
	 * @param lezioni vettore di lezioni dell'utente
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- DBPROBLEM
	 *- NOVALID
	 */
	StateResult getLessonsByDocente(idUser idOwnerUser, Vector<LezioneDB> lezioni);
	
	/**
	 * Questa funzione permette di aggiungere una fascia oraria alla lezione con il vincolo di non poter essere sovrapposta ad altre lezioni dello stesso utente.
	 * @param idUser User creatore della lezione
	 * @param idLesson Lezione a cui aggiungere la fascia oraria
	 * @param FasciaOraria Oggetto contenente tutti i dati da inserire (data, orari)
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- DBPROBLEM
	 *- CREATED
	 *- NOCHANGES
	 */
	
	StateResult addFasciaOraria(idUser idOwnerUser, idLesson idLezione, FasciaOraria orari);
	
	/**
	 * Questa funzione permette di aggiornare una fascia oraria di una lezione.
	 * @param idUser id utente creatore della lezione
	 * @param FasciaOraria Oggetto contenente tutti i dati da aggiornare (data, orari)
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- UPDATED
	 *- DBPROBLEM
	 *- NOUPDATED
	 */
	
	StateResult  updateFasciaOraria(idUser id, FasciaOraria slotAggiornato);
	/**
	 * Questa funzione permette di arricchire le lezioni di un docente con la relative fasce orarie (anche quelle non visibili agli utenti)
	 * @param idOwnerUser 
	 * @param lezioni (I/O) Da Input deve fornire principlamente gli identificativi delle lezioni, Da Output sar� arricchita ogni lezione con i riferimenti alle relative fasce orarie
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- DBPROBLEM
	 */
	StateResult attachSlotsToLessonsDocente(Vector<LezioneDB> lezioni);
	
	/**
	 * Verifica che la fascia oraria esista e sia stata generata da uno specificoo docente. Nel caso di esito positivo 
	 * la funzione restituisce le informazioni sulla fascia oraria.
	 * @warning la la fasciaoraria è sia di input (da cui viene valutato idFasciaOraria) sia di output (i campi vengono riempiti in base all'esito della verifica)
	 * @param id
	 * @param fascia
	 * @return
	 */
	public StateResult getFasciaOraria(idUser id, FasciaOraria fascia);
	
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
	 public StateResult getLessonsbyTopics(idTopic infoTopic, Vector<LezioneDB> lezioni);
	
	/**
	 *Questa funzione permette di ottenere un vettore di lezioni che riguardano un determinato nome di una lezione
	 *@param lezioni (I/O) Da output sarà il  vettore di lezioni che riguardano il  title definito come parametro di input 
	 *@param title
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DBPROBLEM
	 */
	 public StateResult getLessonsbyTitle(String title, Vector<LezioneDB> lezioni);
	 /**
	  * Questa funzione permette di collegare gli slot alle lezioni (ogni lezione deve contenere almeno l'id utente e l'id lezione)
	  * @param lezioni
	  * @return StateResult Rappresenta lo stato dell'operazione:
	  *- VALID
	  *- DBPROBLEM
	  */
	 
	 public StateResult attachSlotsToLessonsUtente(Vector<LezioneDB> lezioni);
	 
	 /**
	  * Questa funzione restituisce tutte le lezioni con relative fasce pagate da un utente e ancora non "scadute"
	  * @param idUser
	  * @param lezioni vettore di lezioni in uscita
	  * @return StateResult Rappresenta lo stato dell'operazione:
	  *- VALID
	  *- DBPROBLEM
	  */
	 public StateResult getLessonsPayedStillUp(idUser idUser, Vector<LezioneDB> lezioni);
	 
	 /**
	  * Questa funzione permette di ottenere una fascia oraria visibile
	  * @param fascia
	  * @return
	  */
	 public StateResult getFasciaOraria(FasciaOraria fascia);
	 
	 /**
	  * Questa funzione permette di ottenere una fascia oraria in base ad un utente, anche se scaduta
	  * @param id
	  * @param fascia
	  * @return
	  */
	 public StateResult getFasciaOrariaNoCatalogo(idUser id, FasciaOraria fascia);
	 
	 /**
	  * Questa funzione permette di ottenere una le fasce orarie relative ad una lezione pagate da un utente
	  * @param id
	  * @param idlezione
	  * @param fasce
	  * @return
	  */
	 public StateResult getFascePayedStillUpByLesson(idUser idUser, idLesson idlez, Vector<FasciaOraria> fasce);
}
