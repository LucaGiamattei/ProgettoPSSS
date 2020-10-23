package serviceLayer.videoroom;

import java.text.ParseException;
import java.util.Vector;

import dataLayer.lezione.entities.FasciaOraria;
import utilities.StateResult;
import utilities.idFasciaOraria;

public interface IVideoRoom {
	
	public StateResult deleteVideoRoom(idFasciaOraria id);
	public StateResult avviaVideoRoom(String idFasciaOraria, String idDocente,String[] nomeRoom, String[] tokenDocente, Vector <String> tokens);
	public StateResult deleteVideoRoom(String idFasciaOraria, String idDocente);
	public StateResult getJoinUserData(String idFasciaOraria, String idUtente, String[] tokenUtente, String[] nomeRoom);
	public String genNomeRoom(String idFasciaOraria, String idDocente);
	public StateResult verifyFasciaOrariaIsInProgress(FasciaOraria fascia);
	public StateResult startVideoRoom(String idFasciaOraria, String nomeRoom, String[] tokenDocente, Vector <String> tokens);
}
