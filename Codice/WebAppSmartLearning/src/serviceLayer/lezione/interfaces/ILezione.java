package serviceLayer.lezione.interfaces;

import java.util.Vector;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;

public interface ILezione {
	
	public StateResult creaLezione(idUser iduser, String nome, String descrizione, String nomeTopic, int nmax);
	public StateResult addFasciaOraria(idUser iduser, idLesson idlesson, FasciaOraria fascia);
	public StateResult getFasceOrarie(idLesson idlesson, Vector<FasciaOraria> fascia);
	public StateResult removeFasciaById(idFasciaOraria id);
	
}
