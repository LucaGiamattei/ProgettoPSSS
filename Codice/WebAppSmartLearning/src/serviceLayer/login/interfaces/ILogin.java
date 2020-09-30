package serviceLayer.login.interfaces;

import dataLayer.utilities.StateResult;

public interface ILogin {
	
	public StateResult loginUtente(String email, String passw);
	
}
