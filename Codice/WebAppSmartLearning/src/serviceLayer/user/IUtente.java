package serviceLayer.user;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.user.entities.UtenteDB;
import utilities.StateResult;
import utilities.idUser;

public interface IUtente {
	
	public StateResult getUserDataById(UtenteDB utente);
	public StateResult upgradeDocente(UtenteDB utente);
	public StateResult validateUser(idUser id);
	public StateResult validateDocente(idUser id);
	

}
