package serviceLayer.videoroom.interfaces;

import java.text.ParseException;
import java.util.Vector;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.utilities.StateResult;

public interface IVideoRoom {
	/**
	 * Questa funzione deve verificare che il docente realmente possiede la lezione in quella determinata fascia oraria
	 * @param idFasciaOraria
	 * @param idDocente
	 * @return
	 */
	public StateResult verifyDocenteHasFasciaOraria(String idUtente, FasciaOraria fascia);
	
	/**
	 * Questa funzione verifica che la fascia oraria sia in corso, condizione necessaria ad esempio per poter avviare la video call
	 * @param idFasciaOraria
	 * @return
	 * @throws ParseException 
	 */
	public StateResult verifyFasciaOrariaIsInProgress(FasciaOraria fascia);
	
	/**
	 * Questa funzione ha lo scopo di generare un nome per una room che sia univoco combinando i valori di idFasciaOraria e id Docente,
	 * non deve poter succedere che per fascia orarie diverse e id docenti diversi si possa generare lo stesso nome di room.
	 * @param idFasciaOraria
	 * @param idDocente
	 * @return
	 */
	public String genNomeRoom(String idFasciaOraria, String idDocente);
	
	/**
	 * Questa funzione ha il compito di:
	 * - 1)creare un'istanza nel database di videocall, e ottenere anche la password generata automaticamente da SQL
	 * - 2) se la creazione della room sul db va  a buon fine allora si è sicuri che il nome della room sia univoco, in tal caso
	 * - - 2a) bisognerà ottenere dal db tramite idFasciaOraria tutti gli id degli utenti che hanno pagato
	 * - - - 2a.a) per ognuno di essi verrà generato un token e memorizzato. (i token devono essere differenti tra di loro)
	 * - - 2b) bisognerà interfacciarsi con il server janus per creare una room con:
	 * - - - 2b.a)la password ottenuta al passo 1)
	 * - - - 2b.b)i tokens generati al passo 2a.a)
	 * - - 2c) viene restituito un token e la password per poter fare la join
	 * @param nomeRoom
	 * @param password
	 * @return
	 */
	public StateResult startVideoRoom(String idFasciaOraria, String nomeRoom, String[] tokenDocente, Vector <String> tokens) ;
}
