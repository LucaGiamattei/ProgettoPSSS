package serviceLayer.login.implementation;

import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import serviceLayer.login.ILogin;

public class ImplLogin implements ILogin {

	@Override
	public StateResult loginUtente(UtenteDB utente, String passw) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controller.verifyLogin(utente, passw);
		
		return result;
	}

}
