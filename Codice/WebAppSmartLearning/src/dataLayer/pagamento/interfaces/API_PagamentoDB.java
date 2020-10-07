package dataLayer.pagamento.interfaces;

import java.util.Vector;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;

public interface API_PagamentoDB {
	public StateResult subscribePayment(idUser idUser, idFasciaOraria idFasciaOraria, idPagamento idPagamento);
	public StateResult verifyUserHasPayed(idUser idUser, idFasciaOraria idFasciaOraria);
	public StateResult getUsersPayedLesson(idFasciaOraria idFasciaOraria, Vector<idUser> users);
	
	

}
