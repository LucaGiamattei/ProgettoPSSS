package dataLayer.pagamento.interfaces;

import java.util.Vector;

import dataLayer.pagamento.entities.PagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;

public interface API_PagamentoDB {
	public StateResult subscribePayment(idUser idUser, idFasciaOraria idFasciaOraria, idPagamento idPagamento);
	public StateResult verifyUserHasPayed(idUser idUser, idFasciaOraria idFasciaOraria);
	public StateResult getUsersPayedLesson(idFasciaOraria idFasciaOraria, Vector<idUser> users);
	public StateResult genAndGetTokens(idFasciaOraria idFasciaOraria, Vector<PagamentoDB>payments);
	public StateResult getTokenByUtente(PagamentoDB pagamentoUtente);
	public StateResult thereAreUsersPayedLesson(idFasciaOraria idFasciaOraria);

}
