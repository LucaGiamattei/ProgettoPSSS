package dataLayer.lezione;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Vector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;

class TestLezione {
	private static ControllerLezioneDB controller;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controller = new ControllerLezioneDB();
	}
/*
	@Test
	void testValidLezione() {
		if(controller.validLezione(new idLesson(1)) == StateResult.VALID) {
			System.out.println("validLezione: output VALID: idLezione 1");
		}
		
	}

	@Test
	void testCreateLesson() {
		LezioneDB infoLezione = new LezioneDB("Logaritmi","Lezione sui logaritmi ed esponenziali", 15, new idTopic(2), new idUser(1), 25);
		if (controller.createLesson(infoLezione)==StateResult.CREATED) {
			System.out.println("createLesson: output CREATED : idLezione creata: "+infoLezione.getId().toString()+"\n");
			
		};
		
	}
	/*
	@Test
	void testUpdateFasciaOraria() {
		fail("Not yet implemented");
		FasciaOraria slotAggiornato = new FasciaOraria(1,21,new java.sql.Date(2020,5,1));
		if (controller.updateFasciaOraria(new idFasciaOraria(1), slotAggiornato) == StateResult.UPDATED) {
			System.out.println("updateFasciaOraria: output UPDATED");
		}
	}
*/
	@Test
	void testAddFasciaOraria() {
		 long millis=System.currentTimeMillis();  
	     java.sql.Date date=new java.sql.Date(millis); 
		
		FasciaOraria orari = new  FasciaOraria(1, 220000,230000, date);

		if (controller.addFasciaOraria(new idUser(1), new idLesson(1), orari) == StateResult.CREATED) {
			System.out.println("addFasciaOraria: output CREATED"+"\n");
			
		};
	}
/*
	@Test
	void testGetLessonsByUser() {
		fail("Not yet implemented");
		Vector<LezioneDB> lezioni = new Vector <LezioneDB>();
		if ( controller.getLessonsByUser(new idUser(1), lezioni) ==StateResult.VALID) {
			for(int i = 0; i<lezioni.size();i++) {
				System.out.println("Lezione: "+lezioni.get(i).toString()+"\n");
			
			}
			
			if ( controller.attachSlotsToLessonsDocente(new idUser(1), lezioni) ==StateResult.VALID) {
				for(int i = 0; i<lezioni.size();i++) {
					System.out.println("Lezione: "+lezioni.get(i).toString()+"\n");
				}
			}
		}
		
	}

	


	@Test
	void testGetLessonsbyTopics() {
		fail("Not yet implemented");
		Vector<LezioneDB> lezioni = new Vector <LezioneDB>();
		if ( controller.getLessonsbyTopics(new idTopic(2), lezioni) ==StateResult.VALID) {
			
			if ( controller.attachSlotsToLessonsUtente(lezioni) ==StateResult.VALID) {
				for(int i = 0; i<lezioni.size();i++) {
					System.out.println("Lezione: "+lezioni.get(i).toString()+"\n");
				}
			}
		}
	}

	@Test
	void testGetLessonsbyTitle() {
		Vector<LezioneDB> lezioni = new Vector <LezioneDB>();
		if ( controller.getLessonsbyTitle("logaritmi", lezioni) ==StateResult.VALID) {
			
			if ( controller.attachSlotsToLessonsUtente(lezioni) ==StateResult.VALID) {
				for(int i = 0; i<lezioni.size();i++) {
					System.out.println("Lezione: "+lezioni.get(i).toString()+"\n");
				}
			}
		}
	}
*/
}
