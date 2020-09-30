package serviceLayer.login.implementation;

import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.utilities.StateResult;
import serviceLayer.login.interfaces.ILogin;

public class ImplLogin implements ILogin {

	@Override
	public StateResult loginUtente(String email, String passw) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controller.verifyLogin(email, passw);
		
		return result;
	}

}
