package serviceLayer.login;

import dataLayer.user.entities.UtenteDB;
import utilities.StateResult;

public interface ILogin {
	
	public StateResult loginUtente(UtenteDB utente, String passw);
	
}
