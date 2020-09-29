package dataLayer.utilities;

/**
 * 
 * @author giorgio
 *
 */
 public enum StateResult {
	 /**
	  * Non ci sono state modifica nel database
	  */
	NOCHANGES,
	/**
	 * Solo una tupla è stata aggiornata, o inserita
	 */
	ONE_ROWSCHANGED,
	/**
	 * E' stato creato
	 */
	CREATED,
	/**
	 * Valore di default nel caso in cui non sia nessuno degli altri stati
	 */
	DEFAULT,
	/**
	 * Ci sono stati problemi con il database
	 */
	DBPROBLEM,
	/**
	 * La validazione non è andata a buon fine
	 */
	NOVALID,
	/**
	 * La validazione è andata a buon fine
	 */
	VALID,
	/**
	 * L'aggiornamento è andato a buon fine
	 */
	UPDATED,
	/**
	 * L'aggiornamento non è andato a buon fine
	 */
	NOUPDATED,
	/**
	 * La rimozione non è andato a buon fine
	 */
	NOREMOVED,
	/**
	 * La rimozione è andato a buon fine
	 */
	REMOVED

	
}
