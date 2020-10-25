package dataLayer.topic;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.entities.TopicDB;
import utilities.StateResult;
import utilities.idSubscription;
import utilities.idTopic;
import utilities.idUser;
/**
 * Tale interfaccia espone le funzioni pubbliche sull'entita' persistente Topic
 * offerte dal livello data layer ai livelli superiori. <p>
 * L'interfaccia segue il seguente standard: <p>
 * - ogni funzione ritorna sempre uno StateResult che rispecchia lo stato di completamento della funzione <p>
 * - le funzioni utilizzano come contenitori di informazioni solo l'entita' TopicDB <p>
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
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- CREATED <p>
	 *- NOCHANGES <p>
	 *- DBPROBLEM <p>
	 */
	StateResult createTopic(TopicDB topic);
	
	/**
	 *Questa funzione permette di validare l'id di un topic
	 *
	 *@param id identificativo del topic
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *- NOVALID <p>
	 *- VALID <p>
	 *- DBPROBLEM <p>
	 */
	StateResult validTopic(idTopic id);
	
	/**
	 *Questa funzione permette di creare una sottoscrizione ad un topic 
	 *
	 *@param id  identificativo del topic
	 *@param idU 
	 *@param idS 
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- CREATED <p>
	 *- NOCHANGES <p>
	 *- DBPROBLEM <p>
	 */
	StateResult createSubscription(idTopic id, idUser idU, idSubscription idS);
	
	/**
	 *Questa funzione permette di rimuovere una sottoscrizione ad un topic 
	 *
	 *@param id identificativo della sottoscrizione
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- REMOVED <p>
	 *- NOREMOVED <p>
	 *- DBPROBLEM <p>
	 */
	StateResult removeSubscription(idSubscription id);
	
	/**
	 *Questa funzione permette di ottenere l'id e i nomi dei topic con più sottoiscrizioni tramite un vettore di TopicDB
	 *
	 *@param topics vettore di strutture che conterranno i "Most Requested topics" (parametro di Output)
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- VALID <p>
	 *- NOVALID<p>
	 *- DBPROBLEM <p>
	 */
	StateResult getMostRequestedTopic(Vector <TopicDB> topics);
	
	/**
	 *Questa funzione permette di ottenere il nome del topic in base all'id del topic 
	 *
	 *@param topic struttura che contiene l'identificativo dle topic( Input) e sara' arrichita con il nome del topic (Output)
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 * 
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	StateResult getTopicName(TopicDB topic);
	
	
	
	/**
	 * Questa funzione permette di ottenere l'id del topic in base al nome del topic
	 * @param topic struttura che contiene il nome del topic( Input) e sara' arrichita con l'id del topic (Output)
	 * @return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	StateResult getTopicByName(TopicDB topic);
}
