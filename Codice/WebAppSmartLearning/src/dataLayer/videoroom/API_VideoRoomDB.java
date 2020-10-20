package dataLayer.videoroom.interfaces;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.videoroom.entities.VideoRoomDB;

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
