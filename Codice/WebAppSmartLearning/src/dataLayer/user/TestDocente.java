package dataLayer.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;

class TestDocente {
	private static ControllerUtenteDB controller;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controller = new ControllerUtenteDB();
	}
	
	
	@Test
	void testGetDocentebyLesson() {
		  UtenteDB user = new UtenteDB();
		  if (controller.getDocentebyLesson(new idLesson(1), user) == StateResult.VALID){
			  System.out.println("GetDocenteByLesson : Output VALID verificato \n");
			  System.out.println("Docente della lezione lezione: "+ user.toString());
		  }
	}
	@Test
	void testUpdateContoPaypal() {
		if (controller.updateContoPaypal(new idUser(1), "lorenzo@unina.it")==StateResult.UPDATED) {
			System.out.println("updateContoPaypal : Output UPDATED verificato \n");
		}
		
	}

}
