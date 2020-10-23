package serviceLayer.registration;

import utilities.StateResult;

public interface IRegistrazione {
	
	public StateResult registraUtente(String nome, String cognome, String email, String passw);

}
