package serviceLayer.user.interfaces;

import java.util.Vector;

import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;

public interface IUtente {
	
	public StateResult getLessonsByCognome(Vector<String> str, Vector<LezioneDB> lezioni);

}
