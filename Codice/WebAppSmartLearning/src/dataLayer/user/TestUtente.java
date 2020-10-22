/**
 * 
 */
package dataLayer.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.user.controller.ControllerUtenteDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;

/**
 * @author PsssTeam
 *Il test richiede che nel db vi sia una tupla in Utente con password = "testPassword" e idUser = 1
 */
class TestUtente {
	private static ControllerUtenteDB controller;
	
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controller = new ControllerUtenteDB();
		
		
	}

	/**
	 * Test method for {@link dataLayer.user.controller.ControllerUtenteDB#validateUser(dataLayer.utilities.idUser)}.
	 */
	@Test
	void testValidateUser() {
		if (controller.validateUser(new idUser(1))==StateResult.VALID) {
			System.out.println("testValidateUser: Output VALID verificato");
		}
	}

	/**
	 * Test method for {@link dataLayer.user.controller.ControllerUtenteDB#createUser(dataLayer.user.entities.UtenteDB, java.lang.String)}.
	 */
	@Test
	void testCreateUser() {
		 
		UtenteDB utente = new UtenteDB("Giorgio", "Farina", "giorgio1996.fari96@gmail.com");
		if (controller.createUser(utente, "testPassword")==StateResult.CREATED) {
			System.out.println("createUser: Id Utente: Output CREATED "+utente.getId().toString()+"\n");
		
		};
		
	}

	/**
	 * Test method for {@link dataLayer.user.controller.ControllerUtenteDB#retrieveUser(dataLayer.utilities.idUser, dataLayer.user.entities.UtenteDB)}.
	 */
	@Test
	void testRetrieveUser() {
		
		UtenteDB utente = new UtenteDB();
		if (controller.retrieveUser(new idUser(1), utente)==StateResult.VALID) {
			System.out.println("retrieveUser: Output VALID "+utente.toString()+"\n");
		};
	}

	/**
	 * Test method for {@link dataLayer.user.controller.ControllerUtenteDB#verifyPassword(dataLayer.utilities.idUser, java.lang.String)}.
	 */
	@Test
	void testVerifyPassword() {
		
		
		if (controller.verifyPassword(new idUser(1), "testPassword")==StateResult.VALID) {
			System.out.println("verifyPassword: Output VALID verificata");
		};
	}

}
