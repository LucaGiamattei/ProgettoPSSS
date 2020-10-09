package serviceLayer.videoroom.implementation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.pagamento.controller.ControllerPagamentoDB;
import dataLayer.pagamento.entities.PagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idUser;
import dataLayer.videoroom.controller.ControllerVideoRoomDB;
import dataLayer.videoroom.entities.VideoRoomDB;
import serviceLayer.videoroom.interfaces.IVideoRoom;

public class ImplVideoRoom implements IVideoRoom{

	@Override
	public StateResult verifyDocenteHasFasciaOraria(String idUtente, FasciaOraria fascia) {

		ControllerLezioneDB controllerLezione = new ControllerLezioneDB();
		if (controllerLezione.getFasciaOraria(new idUser(Integer.parseInt(idUtente)), fascia)==StateResult.VALID) {
			
			return StateResult.VALID;
		}else {
			return StateResult.NOVALID;
		}
	}

	@Override
	public StateResult verifyFasciaOrariaIsInProgress(FasciaOraria fascia) {
		
		System.out.println("verifyFasciaOrariaIsInProgress");
		SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		System.out.println("verifyFasciaOrariaIsInProgress1");
		Date d1;
		Date d2;
		try {
			d1 = sdformat.parse(sdformat.format(date));
			d2 = sdformat.parse(sdformat.format(fascia.getDataLezione()));		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return StateResult.NOVALID;
		}
		System.out.println("comparo le seguenti date: "+sdformat.format(d1)+", "+ sdformat.format(d2));
		//sdformat.format(fascia.getDataLezione());
		//sdformat.format(date);
		
		if (d1.compareTo(d2)==0) {
			//le date sono uguali
			String orarioFine = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.UK).format(fascia.getOrarioFine());
			String orarioInizio = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.UK).format(fascia.getOrarioInizio());
			System.out.println("orario Inizio "+LocalTime.now().isAfter(LocalTime.parse( orarioInizio))+"\n OrarioFIne"+LocalTime.now().isBefore(LocalTime.parse( orarioFine) ));
			if(LocalTime.now().isAfter(LocalTime.parse( orarioInizio)) && LocalTime.now().isBefore(LocalTime.parse( orarioFine ))) {
				return StateResult.VALID;
			}
			
		}
		return StateResult.NOVALID;
		
	}

	@Override
	public String genNomeRoom(String idFasciaOraria, String idDocente) {
		// TODO Auto-generated method stub
		
		return idFasciaOraria;
	}
	
	/**
	 * Questa funzione ha il compito di:
	 * - 1)creare un'istanza nel database di videocall, e ottenere anche la password generata automaticamente da SQL che noi utilizziamo come token del docente
	 * - 2) se la creazione della room sul db va  a buon fine allora si è sicuri che il nome della room sia univoco, in tal caso
	 * - - 2a) bisognerà ottenere dal db tramite idFasciaOraria tutti gli id degli utenti che hanno pagato
	 * - - - 2a.a) per ognuno di essi verrà generato un token e memorizzato. (i token devono essere differenti tra di loro)
	 * - - 2b) bisognerà interfacciarsi con il server janus per creare una room con:
	 * - - - 2b.a)la password ottenuta al passo 1)
	 * - - - 2b.b)i tokens generati al passo 2a.a)
	 * - - 2c) viene restituito il token del docente e tutti i token (compreso quello del docente)
	 * @param nomeRoom
	 * @param password
	 * @return
	 */
	@Override
	public StateResult startVideoRoom(String idFasciaOraria, String nomeRoom, String[] tokenDocente, Vector <String> tokens) {
		// TODO Auto-generated method stub
		ControllerVideoRoomDB contVideoRoom = new ControllerVideoRoomDB();
		ControllerPagamentoDB contPagamento = new ControllerPagamentoDB();
		 
		
		idFasciaOraria idFascia = new idFasciaOraria(Integer.parseInt(idFasciaOraria));
		
		VideoRoomDB  videoRoom = new VideoRoomDB();
		videoRoom.setNomeRoom(nomeRoom);
		
		if(contVideoRoom.createNewRoom(idFascia, videoRoom)==StateResult.CREATED) {
			tokens.add(videoRoom.getPasswordRoom());
			tokenDocente[0] = videoRoom.getPasswordRoom();
			Vector<PagamentoDB> payments = new Vector<PagamentoDB>();
			if( contPagamento.genAndGetTokens(idFascia, payments) ==StateResult.UPDATED) {
				
				for (int i=0; i<payments.size();i++) {
					tokens.add(payments.get(i).getToken());
				}
				
					return StateResult.CREATED;
				
			}
			
		}
		
		return StateResult.NOCHANGES;
	}
	
	
}
