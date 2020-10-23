package serviceLayer.pagamento;

import utilities.StateResult;
import utilities.idFasciaOraria;
import utilities.idUser;

public interface IPagamento {
	public StateResult effettuaPagamento(idUser idUser, idFasciaOraria idFasciaOraria);
}
