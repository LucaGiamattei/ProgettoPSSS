package dataLayer.videoroom.interfaces;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.videoroom.entities.VideoRoomDB;

public interface API_VideoRoomDB {
	public StateResult createNewRoom(idFasciaOraria idFasciaOraria, VideoRoomDB videoRoom);
	public StateResult getRoom(idFasciaOraria id, VideoRoomDB videoRoom);

}
