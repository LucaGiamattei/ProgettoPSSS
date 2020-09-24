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
			 	
			 	//TEST CREATE//
			 	Hashtable<String,String> utente = new Hashtable<String, String>();
			 	utente.put("Nome", "Lorenzo");
			 	utente.put("Cognome", "Caso");
			 	utente.put("Password", "Lorenzo");
			 	utente.put("Email", "Lorenzsosfscxxasdsadado@unina.it");
			 	utente.put("Docente", "0");
				//Integer r = DBConnectionManager.createNewEntryDB("Utente", utente,true);
				//System.out.println(r);
				
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
				Integer r = DBConnectionManager.UpdateEntryDB("Utente", conditionsFildsToValues1, fildsToValues, false);
				System.out.println(r);
				
				//TEST SELECT IN SELECT
				String [] fieldsToSelect1 = {"*"};
				Hashtable<String,String> conditionsFildsToValues2 = new Hashtable<String, String>();
				conditionsFildsToValues2.put("idLezione", "1");
				
				ResultSet result1 = DBConnectionManager.SelectEntryInSelectDB("Utente", fieldsToSelect1, "idUtente", "Lezione", "Utente_idUtente", conditionsFildsToValues2);
				
				while (result1.next()) {
					System.out.println(result1.getString("Nome")+result1.getString("Cognome")+result1.getString("Email")+result1.getString("idUtente"));
				}
				
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
