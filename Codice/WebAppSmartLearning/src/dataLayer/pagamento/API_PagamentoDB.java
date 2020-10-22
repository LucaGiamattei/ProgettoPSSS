package dataLayer.pagamento;

import java.util.Vector;

import dataLayer.pagamento.entities.PagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;
/**
 * Tale interfaccia espone le funzioni pubbliche sull'entita' persistente Pagamento
 * offerte dal livello data layer ai livelli superiori. <p>
 * L'interfaccia segue il seguente standard: <p>
 * - ogni funzione ritorna sempre uno StateResult che rispecchia lo stato di completamento della funzione <p>
 * - le funzioni utilizzano come contenitori di informazioni solo l'entita' PagamentoDB <p>
 * - le informazioni ottenute dall'elaborazioni delle funzioni sono restituiti tramite parametri di I/O <p>
 * 
 * @author PsssTeam
 *
 */
public interface API_PagamentoDB {
	/**
	 * Effettua il pagamento per una fascia oraria
	 * @param idUser identificativo dello stidente
	 * @param idFasciaOraria identificativo della programmazione
	 * @param idPagamento identificativo del pagamento (Output)
	 * @return StateResult Rappresenta lo stato dell'operazione: <p>
	 *- CREATED <p>
	 *- NOCHANGES <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult subscribePayment(idUser idUser, idFasciaOraria idFasciaOraria, idPagamento idPagamento);
	
	/**
	 * Verifica che l'utente abbia pagato per quella fascia oraria
	 * @param idUser identificativo dello studente
	 * @param idFasciaOraria identificativo della programmazione
	 * @return StateResult Rappresenta lo stato dell'operazione: <p>
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult verifyUserHasPayed(idUser idUser, idFasciaOraria idFasciaOraria);
	
	/**
	 * Restituisce un vettore di utenti paganti per quella fascia oraria di quella lezione
	 * @param idFasciaOraria identificativo della programmazione
	 * @param users identificativi degli studenti
	 * @return StateResult Rappresenta lo stato dell'operazione:  <p>
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult getUsersPayedLesson(idFasciaOraria idFasciaOraria, Vector<idUser> users);
	
	/**
	 * Genera e salva i token per accedere alla videochiamata
	 * @param idFasciaOraria identificativo della programmazione
	 * @param payments vettore di strutture che contengono le informazioni sui pagamenti tra cui anche il token per accedere alla videochiamata (parametro di Output)
	 * @return StateResult Rappresenta lo stato dell'operazione: <p>
	 *- UPDATED <p>
	 *- NOUPDATED <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult genAndGetTokens(idFasciaOraria idFasciaOraria, Vector<PagamentoDB>payments);
	
	/**
	 * Restituisce il token di un utente che ha pagato per una fascia oraria
	 * @param pagamentoUtente struttura che contiene le informazioni sul pagamento tra cui anche il token per accedere alla videochiamata (parametro di Output) 
	 * @return StateResult Rappresenta lo stato dell'operazione: <p>
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult getTokenByUtente(PagamentoDB pagamentoUtente);
	
	/**
	 * Restituisce gli id degli utenti che hanno pagato per quella fascia oraria
	 * @param idFasciaOraria identificativo della programmazione 
	 * @return StateResult Rappresenta lo stato dell'operazione: <p>
	 *- VALID <p>
	 *- NOVALID <p>
	 *- DBPROBLEM <p>
	 */
	public StateResult thereAreUsersPayedLesson(idFasciaOraria idFasciaOraria);

}
