package serviceLayer.user.implementation;

import java.util.Vector;

import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.controller.ControllerTopicDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;
import serviceLayer.user.IUtente;

public class ImplUtente implements IUtente {



	@Override
	public StateResult getUserDataById(UtenteDB utente) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controller.retrieveUser(utente.getId(), utente);
		
		return result;
	}

	@Override
	public StateResult upgradeDocente(UtenteDB utente) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controller.createDocente(utente);
		
		return result;
	}
	
	@Override
	public StateResult validateUser(idUser id) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controller.validateUser(id);
		
		return result;
	}
	
	@Override
	public StateResult validateDocente(idUser id) {
		// TODO Auto-generated method stub
		
		ControllerUtenteDB controller = new ControllerUtenteDB();
		
		StateResult result = controller.validateDocente(id);
		
		return result;
	}
	
	
}
