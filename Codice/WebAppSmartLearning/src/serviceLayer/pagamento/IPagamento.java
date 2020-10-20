package serviceLayer.pagamento.interfaces;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;

public interface IPagamento {
	public StateResult effettuaPagamento(idUser idUser, idFasciaOraria idFasciaOraria);
}
