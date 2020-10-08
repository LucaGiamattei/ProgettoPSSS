package serviceLayer.pagamento.implementation;

import dataLayer.pagamento.controller.ControllerPagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;
import serviceLayer.pagamento.interfaces.IPagamento;

public class ImplPagamento implements IPagamento {

	@Override
	public StateResult effettuaPagamento(idUser idUser, idFasciaOraria idFasciaOraria) {
		// TODO Auto-generated method stub
		
		
		//ATT: Effettuare controllo su Fascia non ancora iniziata!
		
		ControllerPagamentoDB controller = new ControllerPagamentoDB();
		idPagamento idPagamento = new idPagamento();
		
		StateResult result = controller.subscribePayment(idUser, idFasciaOraria, idPagamento);			//idPagamento da utilizzare?
		
		return result;
	}

}
