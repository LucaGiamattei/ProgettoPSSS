package dataLayer.videoroom;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.videoroom.entities.VideoRoomDB;
/**
 * Tale interfaccia espone le funzioni pubbliche sull'entità persistente VideoRoom
 * offerte dal livello data layer ai livelli superiori. <p>
 * L'interfaccia segue il seguente standard: <p>
 * - ogni funzione ritorna sempre uno StateResult che rispecchia lo stato di completamento della funzione <p>
 * - le funzioni utilizzano come contenitori di informazioni solo l'entità VideoRoomDB <p>
 * - le informazioni ottenute dall'elaborazioni delle funzioni sono restituiti tramite parametri di I/O <p>
 * 
 * @author PsssTeam
 *
 */
public interface API_VideoRoomDB {
	
	/**
	 *Questa funzione permette di creare una videochiamata
	 *
	 *@param idFasciaOraria
	 *@param videoRoom
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- CREATED
	 *- NOUPDATED
	 *- DBPROBLEM
	 */
	public StateResult createNewRoom(idFasciaOraria idFasciaOraria, VideoRoomDB videoRoom);
	
	/**
	 *Questa funzione permette di ottenere le informazioni di una videochiamata 
	 *
	 *@param id 
	 *@param videoRoom
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	public StateResult getRoom(idFasciaOraria id, VideoRoomDB videoRoom);
	
	/**
	 *Questa funzione permette di eliminare una videochiamata
	 *
	 *@param idFasciaOraria
	 *@return StateResult Rappresenta lo stato dell'operazione:
	 *
	 *- REMOVED
	 *- NOREMOVED
	 *- DBPROBLEM
	 */
	public StateResult removeRoom(idFasciaOraria idFasciaOraria);
	

}
