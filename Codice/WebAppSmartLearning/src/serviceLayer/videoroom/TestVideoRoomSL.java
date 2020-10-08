package serviceLayer.videoroom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import serviceLayer.videoroom.implementation.ImplVideoRoom;

class TestVideoRoomSL {
	static ImplVideoRoom impleVideoRoom;
	static FasciaOraria fascia;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		impleVideoRoom = new ImplVideoRoom();
		
	}

	@Test
	void testVerifyDocenteHasFasciaOraria() {
		System.out.println("verifyDocenteHasFasciaOraria: outputQ");
		fascia = new FasciaOraria();
		fascia.setId(new idFasciaOraria(1));
		if (impleVideoRoom.verifyDocenteHasFasciaOraria("1", fascia)==StateResult.VALID) {
			System.out.println("verifyDocenteHasFasciaOraria: output: "+fascia.toString());
			if(impleVideoRoom.verifyFasciaOrariaIsInProgress(fascia)==StateResult.VALID) {
				System.out.println("verifyFasciaOrariaIsInProgress: output: "+fascia.toString());
			}
		}
		
		
		
		
		
	}

	@Test
	void testVerifyFasciaOrariaIsInProgress() {
		
	}

	@Test
	void testGenNomeRoom() {
		fail("Not yet implemented");
	}

	@Test
	void testStartVideoRoom() {
		fail("Not yet implemented");
	}

}
