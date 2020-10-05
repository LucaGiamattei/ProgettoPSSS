package serviceLayer.lezione.implementation;

import java.util.Vector;

import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;
import serviceLayer.lezione.interfaces.ILezione;

public class ImplLezione implements ILezione {

	public StateResult creaLezione(idUser iduser, String nome, String descrizione, String nomeTopic, int nmax) {
		// TODO Auto-generated method stub
		
		ControllerLezioneDB controller = new ControllerLezioneDB();
		LezioneDB lezione = new LezioneDB(iduser,nome, descrizione,nmax);
		
		StateResult result = controller.createLesson(lezione, nomeTopic);
		
		return result;
	}

	@Override
	public StateResult addFasciaOraria(idUser iduser, idLesson idlesson, FasciaOraria fascia) {
		// TODO Auto-generated method stub
		
		ControllerLezioneDB controller = new ControllerLezioneDB();
		
		StateResult result = controller.addFasciaOraria( iduser, idlesson, fascia);
		
		return result;
	}

	@Override
	public StateResult getFasceOrarie(idLesson idlesson, Vector<FasciaOraria> fasce) {
		// TODO Auto-generated method stub
		ControllerLezioneDB controller = new ControllerLezioneDB();
		
		StateResult result = controller.getFasceOrarieByLessonId(idlesson, fasce);
		
		
		return result;
	}

	@Override
	public StateResult removeFasciaById(idFasciaOraria id) {
		// TODO Auto-generated method stub
		ControllerLezioneDB controller = new ControllerLezioneDB();
		
		StateResult result = controller.removeFasciaById(id);
		
		
		return result;
	}

}
