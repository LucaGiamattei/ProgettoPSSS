package serviceLayer.videoroom.interfaces;

import java.util.Vector;

import dataLayer.utilities.StateResult;

public interface IJanus {
	public StateResult createRoomWithTokens(String nomeRoom,  Vector<String> tokens);
}
