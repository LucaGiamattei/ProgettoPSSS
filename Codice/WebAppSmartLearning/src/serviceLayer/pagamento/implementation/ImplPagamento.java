package serviceLayer.pagamento.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.pagamento.controller.ControllerPagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;
import serviceLayer.pagamento.interfaces.IPagamento;

public class ImplPagamento implements IPagamento {

	@Override
	public StateResult effettuaPagamento(idUser idUser, idFasciaOraria idFasciaOraria) {
		// TODO Auto-generated method stub
		
		
		//ATT: Effettuare controllo su Fascia non ancora iniziata!
		
		ControllerPagamentoDB controller = new ControllerPagamentoDB();
		idPagamento idPagamento = new idPagamento();
		
		StateResult result = controller.subscribePayment(idUser, idFasciaOraria, idPagamento);			//idPagamento da utilizzare?
		
		return result;
	}
	
public StateResult verifyFasciaOrariaIsNotStarted(FasciaOraria fascia) {
		//Funzione che preleva una fascia oraria
		ControllerLezioneDB contLezione = new ControllerLezioneDB();
		if ( contLezione.getFasciaOraria(fascia)==StateResult.VALID) {
			System.out.println("VALID: getFasciaOraria ");
			//System.out.println("verifyFasciaOrariaIsInProgress");
			SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			//System.out.println("verifyFasciaOrariaIsInProgress1");
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
			
			if (d1.compareTo(d2)<0) {
				System.out.println("VALID:d1.compareTo(d2)>0");
				return StateResult.VALID;
				
			}
			if (d1.compareTo(d2)==0) {
				System.out.println("La data è maggiore o uguale di zero");
				//le date sono uguali
				//String orarioFine = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.UK).format(fascia.getOrarioFine());
				String orarioInizio = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.UK).format(fascia.getOrarioInizio());
				//System.out.println("orario Inizio "+LocalTime.now().isAfter(LocalTime.parse( orarioInizio))+"\n OrarioFIne"+LocalTime.now().isBefore(LocalTime.parse( orarioFine) ));
				if(LocalTime.now().isBefore(LocalTime.parse( orarioInizio))) {
					System.out.println("l'ora attuale è dopo quella della lezione");
					return StateResult.VALID;
					
				}
				
			}
			
		}
		return StateResult.NOVALID;
		
	}
}
