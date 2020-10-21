package dataLayer.topic;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idSubscription;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;
/**
 * Tale interfaccia espone le funzioni pubbliche sull'entità persistente Topic
 * offerte dal livello data layer ai livelli superiori. <p>
 * L'interfaccia segue il seguente standard: <p>
 * - ogni funzione ritorna sempre uno StateResult che rispecchia lo stato di completamento della funzione <p>
 * - le funzioni utilizzano come contenitori di informazioni solo l'entità TopicDB <p>
 * - le informazioni ottenute dall'elaborazioni delle funzioni sono restituiti tramite parametri di I/O <p>
 * 
 * @author PsssTeam
 *
 */
public interface API_TopicDB {

	/**
	 *Questa funzione permette di creare il topic 
	 *
	 *@param topic struttura di tipo TopicDB per la creazione di un topic
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- CREATED
	 *- NOCHANGES
	 *- DBPROBLEM
	 */
	StateResult createTopic(TopicDB topic);
	
	/**
	 *Questa funzione permette di validare l'id di un topic
	 *
	 *@param id
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DBPROBLEM
	 */
	StateResult validTopic(idTopic id);
	
	/**
	 *Questa funzione permette di creare una sottoscrizione ad un topic 
	 *
	 *@param id 
	 *@param idU 
	 *@param idS 
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- CREATED
	 *- NOCHANGES
	 *- DBPROBLEM
	 */
	StateResult createSubscription(idTopic id, idUser idU, idSubscription idS);
	
	/**
	 *Questa funzione permette di rimuovere una sottoscrizione ad un topic 
	 *
	 *@param id 
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- REMOVED
	 *- NOREMOVED
	 *- DBPROBLEM
	 */
	StateResult removeSubscription(idSubscription id);
	
	/**
	 *Questa funzione permette di ottenere l'id e i nomi dei topic con più sottoiscrizioni tramite un vettore di TopicDB
	 *
	 *@param topics
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	StateResult getMostRequestedTopic(Vector <TopicDB> topics);
	
	/**
	 *Questa funzione permette di ottenere il nome del topic in base all'id del topic 
	 *
	 *@param topic
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	StateResult getTopicName(TopicDB topic);
	
	
	
	/**
	 * Questa funzione permette di ottenere l'id del topic in base al nome del topic
	 * @param topic
	 * @return
	 */
	StateResult getTopicByName(TopicDB topic);
}
