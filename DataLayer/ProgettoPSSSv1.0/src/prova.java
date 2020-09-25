import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.idUser;
import dataLayer.user.*;
import dataLayer.user.controller.ControllerUtenteDB;
public class prova {

	public static void main(String[] args) throws SQLException {
		 try {
			 	
			 	//TEST CREATE UTENTE//
			 	Hashtable<String,String> utente = new Hashtable<String, String>();
			 	utente.put("Nome", "Lorenzo");
			 	utente.put("Cognome", "Caso");
			 	utente.put("Password", "Lorenzo");
			 	utente.put("Email", "Lorenzsosfscxxasdsadado@unina.it");
			 	utente.put("Docente", "0");
				//Integer r = DBConnectionManager.createNewEntryDB("Utente", utente,true);
				//System.out.println(r);
				
			 	
			 	//TEST CREATE DOCENTE//
			 	Hashtable<String,String> conditionsFilds = new Hashtable<String, String>();
				conditionsFilds.put("idUtente", "3");
				
				Hashtable<String,String> Update = new Hashtable<String, String>();
				Update.put("ContoPayPal", "lorenzocaso@gmail.com");
				Update.put("Curriculum", "laureato");
				Update.put("MediaScoreLezioni", "5.5");

				Integer r = DBConnectionManager.UpdateEntryDB("Utente", conditionsFilds, Update, false);
			 	
				
				
				//TEST SELECT//
				String [] fieldsToSelect = {"*"};
				Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
				conditionsFildsToValues.put("idUtente", "5");
				
				ResultSet result = DBConnectionManager.SelectEntryDB("Utente", fieldsToSelect, conditionsFildsToValues);
				
				while (result.next()) {
					System.out.println(result.getString("Nome")+result.getString("Cognome")+result.getString("Email")+result.getString("idUtente"));
				}
				
				//TEST UPDATE//
				Hashtable<String,String> conditionsFildsToValues1 = new Hashtable<String, String>();
				conditionsFildsToValues1.put("Cognome", "Caso");
				Hashtable<String,String> fildsToValues = new Hashtable<String, String>();
				fildsToValues.put("Nome", "LUCA");
				Integer up = DBConnectionManager.UpdateEntryDB("Utente", conditionsFildsToValues1, fildsToValues, false);
				System.out.println(up);
				
				//TEST SELECT IN SELECT
				String [] fieldsToSelect1 = {"*"};
				Hashtable<String,String> conditionsFildsToValues2 = new Hashtable<String, String>();
				conditionsFildsToValues2.put("idLezione", "1");
				
				ResultSet result1 = DBConnectionManager.SelectEntryInSelectDB("Utente", fieldsToSelect1, "idUtente", "Lezione", "Utente_idUtente", conditionsFildsToValues2);
				
				while (result1.next()) {
					System.out.println(result1.getString("Nome")+result1.getString("Cognome")+result1.getString("Email")+result1.getString("idUtente"));
				}
				
				
				//TEST CREATE TOPIC//
				Hashtable<String,String> topic = new Hashtable<String, String>();
			 	topic.put("Nome", "Matematica");
			 	Integer t = DBConnectionManager.createNewEntryDB("Topic", topic,true);
				System.out.println(t);
				
				
				//TEST CREATE SUBSCRIPTION//
				Hashtable<String,String> sub = new Hashtable<String, String>();
				sub.put("Topic_idTopic", "1");
				sub.put("Utente_idUtente", "1");
			 	Integer s = DBConnectionManager.createNewEntryDB("SubscriptionUtenteTopic", sub,true);
				System.out.println(s);
				
				//TEST CREATE SUBSCRIPTION//
				Hashtable<String,String> getMost = new Hashtable<String, String>();
				getMost.put("Topic_idTopic", "1");
			 	ResultSet gm = DBConnectionManager.countEntryDB("SubscriptionUtenteTopic", getMost);
				System.out.println(gm);
				
				
				//PROVA UTENTE///
				
				UtenteDB ut = new UtenteDB("lorenzo", "caso", "lorenzo@gmail.com");
				ControllerUtenteDB controller = new ControllerUtenteDB();
				controller.createUser(ut, "passw");
				System.out.println("ID UTENTE"+ut.getId().toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 System.out.println("HELLO");
	}

	
}
