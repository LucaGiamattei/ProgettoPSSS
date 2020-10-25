package dataLayer.videoroom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.videoroom.controller.ControllerVideoRoomDB;
import dataLayer.videoroom.entities.VideoRoomDB;
import utilities.StateResult;
import utilities.idFasciaOraria;

class TestVideoRoom {
	static ControllerVideoRoomDB controller;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controller = new ControllerVideoRoomDB();
	}

	@Test
	void testCreateNewRoom() {
		VideoRoomDB videoRoom = new VideoRoomDB();
		videoRoom.setNomeRoom("Prova");
		if(controller.createNewRoom(new idFasciaOraria(1), videoRoom )==StateResult.CREATED) {
			System.out.println("createNewRoom: password creata: "+videoRoom.getPasswordRoom());
		}
	}

	@Test
	void testGetRoom() {
		fail("Not yet implemented");
	}

}
