package serviceLayer.user.interfaces;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;

public interface IUtente {
	
	public StateResult getUserDataById(UtenteDB utente);
	public StateResult getLessonsByCognome(Vector<String> str, Vector<LezioneDB> lezioni);
	public StateResult getLessonsById(idUser myid, Vector<String> str, Vector<LezioneDB> lezioni);
	public StateResult upgradeDocente(UtenteDB utente);
	public StateResult validateUser(idUser id);
	public StateResult validateDocente(idUser id);
	

}
