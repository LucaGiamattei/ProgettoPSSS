package serviceLayer.login;

import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;

public interface ILogin {
	
	public StateResult loginUtente(UtenteDB utente, String passw);
	
}
