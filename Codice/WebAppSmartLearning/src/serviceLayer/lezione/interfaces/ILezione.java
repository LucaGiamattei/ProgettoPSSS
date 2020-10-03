package serviceLayer.lezione.interfaces;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;

public interface ILezione {
	
	public StateResult creaLezione(idUser iduser, String nome, String descrizione, String nomeTopic);

}
