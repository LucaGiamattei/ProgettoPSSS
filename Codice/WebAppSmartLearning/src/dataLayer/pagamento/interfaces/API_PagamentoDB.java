package dataLayer.pagamento.interfaces;

import java.util.Vector;

import dataLayer.pagamento.entities.PagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;

public interface API_PagamentoDB {
	/**
	 * Effettua il pagamento per una fascia oraria
	 * @param idUser
	 * @param idFasciaOraria
	 * @param idPagamento
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- CREATED
	 *- NOCHANGES
	 *- DBPROBLEM
	 */
	public StateResult subscribePayment(idUser idUser, idFasciaOraria idFasciaOraria, idPagamento idPagamento);
	
	/**
	 * Verifica che l'utente abbia pagato per quella fascia oraria
	 * @param idUser
	 * @param idFasciaOraria
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	public StateResult verifyUserHasPayed(idUser idUser, idFasciaOraria idFasciaOraria);
	
	/**
	 * Restituisce un vettore di utenti paganti per quella fascia oraria di quella lezione
	 * @param idFasciaOraria
	 * @param users
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	public StateResult getUsersPayedLesson(idFasciaOraria idFasciaOraria, Vector<idUser> users);
	
	/**
	 * Genera il token
	 * @param idFasciaOraria
	 * @param payments
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- UPDATED
	 *- NOUPDATED
	 *- DBPROBLEM
	 */
	public StateResult genAndGetTokens(idFasciaOraria idFasciaOraria, Vector<PagamentoDB>payments);
	
	/**
	 * Restituisce il token di un utente
	 * @param pagamentoUtente
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	public StateResult getTokenByUtente(PagamentoDB pagamentoUtente);
	
	/**
	 * Restituisce gli id degli utenti che hanno pagato per quella fascia oraria
	 * @param idFasciaOraria
	 * @return StateResult Rappresenta lo stato dell'operazione:
	 *- VALID
	 *- NOVALID
	 *- DBPROBLEM
	 */
	public StateResult thereAreUsersPayedLesson(idFasciaOraria idFasciaOraria);

}
