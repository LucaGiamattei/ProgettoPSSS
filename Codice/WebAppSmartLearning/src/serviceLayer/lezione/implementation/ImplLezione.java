package serviceLayer.lezione.implementation;

import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;
import serviceLayer.lezione.interfaces.ILezione;

public class ImplLezione implements ILezione {

	@Override
	public StateResult creaLezione(idUser iduser, String nome, String descrizione, String nomeTopic) {
		// TODO Auto-generated method stub
		
		ControllerLezioneDB controller = new ControllerLezioneDB();
		LezioneDB lezione = new LezioneDB(iduser,nome, descrizione);
		
		StateResult result = controller.createLesson(lezione, nomeTopic);
		
		return result;
	}

}
