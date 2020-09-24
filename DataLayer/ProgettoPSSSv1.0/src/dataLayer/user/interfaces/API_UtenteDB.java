package dataLayer.user.interfaces;

import dataLayer.user.entities.UtenteDB;
import dataLayer.user.entities.result.ResultUtente;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;

public interface API_UtenteDB {
	/**
	 *Questa funzione permette di validare l'id di un utente
	 *
	 *@param id
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *- NOVALID
	 *- VALID
	 *- DEFAULT
	 *- DBPROBLEM
	 */
	StateResult validateUser(idUser id); 
	/**
	 * Questa funzione permette di creare un utente
	 * 
	 * @param utente I/O struttura che contiene le informazioni da memorizzare per il nuovo utente.
	 * Essa verrà aggiornata con l'id dell'utente.
	 * @param password la password da memorizzare per il nuovo utente
	 * @return StateResult Rappresenta lo stato del risultato:
	 * - NOCHANGES
	 * - CREATED
	 * - DBPROBLEM
	 */
	StateResult createUser(UtenteDB utente, String password);
	/**
	 * Questa funzione permette di ottenere la struttura dati UtenteDB con le informazione 
	 * riguardo l'utente identificato dal suo id
	 * 
	 * @param id identificativo dell'utente da selezionare
	 * @param utente struttura che verrà aggiornata con le informazioni prelevate dalla base di dati perisistente
	 * @return StateResult struttura dati contenente il risultato della selezione. 
	 * Tale struttura contiene uno stato:
	 * - NOVALID 
	 * - VALID
	 * - DEFAULT
	 * - DBPROBLEM
	 * e l'oggetto UtenteDB
	 */
	StateResult retrieveUser(idUser id, UtenteDB utente);
	
	/**
	 * Questa funzione permette di ottenere la password relativa  a un utente
	 * 
	 * @param id identificativo dell'utente da selezionare
	 * @param password password da validare
	 * @return StateResult Restituisce lo stato della validazione:
	 * -  VALID
	 * - NOVALID
	 * - DBPROBLEM
	 */
	StateResult verifyPassword(idUser id, String password);
}
