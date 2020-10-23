package serviceLayer.registration.implementation;

import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import serviceLayer.registration.IRegistrazione;
import utilities.StateResult;

public class ImplRegistrazione implements IRegistrazione {

	@Override
	public StateResult registraUtente(String nome, String cognome, String email, String passw) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		UtenteDB utente = new UtenteDB(nome, cognome, email);
		
		StateResult result = controller.createUser(utente, passw);
		
		return result;
	}

}
