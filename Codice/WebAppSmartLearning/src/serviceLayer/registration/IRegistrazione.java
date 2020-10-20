package serviceLayer.registration;

import dataLayer.utilities.StateResult;

public interface IRegistrazione {
	
	public StateResult registraUtente(String nome, String cognome, String email, String passw);

}
