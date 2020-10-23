package dataLayer.videoroom;

import dataLayer.videoroom.entities.VideoRoomDB;
import utilities.StateResult;
import utilities.idFasciaOraria;
/**
 * Tale interfaccia espone le funzioni pubbliche sull'entita' persistente VideoRoom
 * offerte dal livello data layer ai livelli superiori. <p>
 * L'interfaccia segue il seguente standard: <p>
 * - ogni funzione ritorna sempre uno StateResult che rispecchia lo stato di completamento della funzione <p>
 * - le funzioni utilizzano come contenitori di informazioni solo l'entita' VideoRoomDB <p>
 * - le informazioni ottenute dall'elaborazioni delle funzioni sono restituiti tramite parametri di I/O <p>
 * 
 * @author PsssTeam
 *
 */
public interface API_VideoRoomDB {
	
	/**
	 *Questa funzione permette di creare una videochiamata
	 *
	 *@param idFasciaOraria identificativo della programmazione
	 *@param videoRoom struttura che contiene il noem della room (Input) e conterra' il token del docente (Output)
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- CREATED <p>
	 *- NOUPDATED <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult createNewRoom(idFasciaOraria idFasciaOraria, VideoRoomDB videoRoom);
	
	/**
	 *Questa funzione permette di ottenere le informazioni di una videochiamata 
	 *
	 *@param id identificativo della programmazione
	 *@param videoRoom struttura che conterra' il nome della room
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult getRoom(idFasciaOraria id, VideoRoomDB videoRoom);
	
	/**
	 *Questa funzione permette di eliminare una videochiamata
	 *
	 *@param idFasciaOraria identificativo della programmazione
	 *@return StateResult Rappresenta lo stato dell'operazione: <p>
	 *
	 *- REMOVED <p>
	 *- NOREMOVED <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult removeRoom(idFasciaOraria idFasciaOraria);
	

}
