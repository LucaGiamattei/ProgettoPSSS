package dataLayer.lezione.controller;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Hashtable;
import java.util.Vector;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.lezione.entities.result.ResultLezione;
import dataLayer.lezione.interfaces.API_LezioneDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.user.entities.result.ResultUtente;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;
import dataLayer.utilities.idTopic;

public class ControllerLezioneDB implements API_LezioneDB{
	
	
		public StateResult validLezione(idLesson id) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("idLezione", id.toString());
			
			ResultSet result;
			try {
				result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues);
				int numOfRows = 0;
				
				while (result.next()) {
					numOfRows++;
				}
				switch(numOfRows) {
			      case 1:
			    	  return StateResult.VALID;
			      default:
			    	  return StateResult.NOVALID;
				}
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
			
		}
	
//---------------------OPERAZIONI DOCENTE---------------------------//
		
		@Override
		public StateResult createLesson(LezioneDB infoLezione) {
			// TODO Auto-generated method stub
			Hashtable<String,String> lezione = new Hashtable<String, String>();
			
			lezione.put("NomeLezione", infoLezione.getNomeLezione());
			lezione.put("DescrizioneLezione", infoLezione.getDescrizioneLezione());
			lezione.put("prezzo", ""+infoLezione.getPrezzoSlot()+"");
		 	lezione.put("NMaxStudenti", ""+infoLezione.getNmax()+"");
		 	lezione.put("Topic_idTopic", infoLezione.getIdTopic().toString());
		 	lezione.put("Utente_idUtente", infoLezione.getIdUtente().toString());
		 	try {
				Integer r = DBConnectionManager.createNewEntryDB("Lezione",lezione, true);
				if(r>0) {
					infoLezione.setId(new idLesson(r));
					return StateResult.CREATED;
				}else {
					return StateResult.NOCHANGES;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
		}
		
		@Override
		public StateResult updateFasciaOraria(idUser iduser, FasciaOraria orari) {
		
			Connection conn;
				try {
				conn = DBConnectionManager.getConnection();
				conn.setAutoCommit(false);
				
				String query = "{CALL updateCond(?,?,?,"+ orari.getId().getId() +","+ iduser +", ?)}";
				
				CallableStatement stmt = (CallableStatement) conn.prepareCall(query);
				
				stmt.setDate(1,orari.getDataLezione());
				stmt.setInt(2,orari.getOrarioInizio());
				stmt.setInt(3,orari.getOrarioFine());
				
				stmt.registerOutParameter(4, Types.INTEGER);
				
				stmt.executeQuery();
				
				int outputValue = stmt.getInt(4);
	
				conn.commit();
				conn.close();
				
				if(outputValue == 0) {
					return StateResult.UPDATED;
				}else {
					return StateResult.NOUPDATED;
				}
				
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return StateResult.DBPROBLEM;
				}
				
		}
		
		@Override
		public StateResult addFasciaOraria(idUser idOwnerUser, idLesson idLezione, FasciaOraria orari) {
			// TODO Auto-generated method stub
			
			Connection conn;
				try {
				conn = DBConnectionManager.getConnection();
				conn.setAutoCommit(false);
				
				String query = "{CALL inserisciCond(?,?,?,"+ idLezione+","+ idOwnerUser +", ?, ?)}";
				
				CallableStatement stmt = (CallableStatement) conn.prepareCall(query);
				
				stmt.setDate(1,orari.getDataLezione());
				stmt.setInt(2,orari.getOrarioInizio());
				stmt.setInt(3,orari.getOrarioFine());
				
				stmt.registerOutParameter(4, Types.INTEGER);
				stmt.registerOutParameter(5, Types.INTEGER);
				
				stmt.executeQuery();
				
				
				int outputValue = stmt.getInt(4);	
				int retId = stmt.getInt(5);
				
				conn.commit();
				conn.close();
				
				if(outputValue == 0) {
					orari.setId(new idFasciaOraria(retId));
					return StateResult.CREATED;
				}else {
					return StateResult.NOCHANGES;
				}
				
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return StateResult.DBPROBLEM;
				}
				
		}
		

		@Override
		public StateResult getLessonsByUser(idUser idOwnerUser, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("Utente_idUtente", idOwnerUser.toString());
			
			ResultSet result;
			try {
				result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")), result.getFloat("prezzo"));
					lezioni.add(lezione);
					numTuple++;
				}
				if(numTuple!=0) {
					return StateResult.VALID;
				}else {
					return StateResult.NOVALID;
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
	
		}
		
		@Override
		public  StateResult attachSlotsToLessonsDocente(idUser idOwnerUser, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			if (lezioni == null) {
				lezioni = new Vector<LezioneDB> ();
			}
			// TODO Auto-generated method stub
			
			
			
			try {
				for (int i =0;i<lezioni.size();i++) {
					String [] fieldsToSelect = {"*"};
					Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
					conditionsFildsToValues.put("Utente_idUtente", idOwnerUser.toString());
					conditionsFildsToValues.put("idLezione", lezioni.get(i).getId().toString());
					ResultSet result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues);
					
					while (result.next() ) {
						FasciaOraria slot = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")),result.getInt("visibile"), result.getInt("OrarioInizioLezione"),result.getInt("OrarioFineLezione"), result.getDate("DataLezione"));
						lezioni.get(i).getSlots().add(slot);
						
					}
					
				}
				
				return StateResult.VALID;
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
		}
		
		
	//----------------------------UTENTE------------------------------------//

		@Override
		public StateResult getLessonsbyTopics(idTopic infoTopic, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("Topic_idTopic", infoTopic.toString());
			
			try {
				ResultSet result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")),result.getFloat("prezzo"));
					lezioni.add(lezione);
					numTuple++;
				}
				if(numTuple>0) {
					return StateResult.VALID;
				}else {
					return StateResult.NOVALID;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
		
		}

		@Override
		public StateResult getLessonsbyTitle(String title, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("NomeLezione", title);
						
			try {
				ResultSet result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")),result.getFloat("prezzo"));
					lezioni.add(lezione);
					numTuple++;
				}
				if(numTuple>0) {
					return StateResult.VALID;
				}else {
					return StateResult.NOVALID;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
		}
		
		@Override
		public  StateResult attachSlotsToLessonsUtente(Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			if (lezioni == null) {
				lezioni = new Vector<LezioneDB> ();
			}
			// TODO Auto-generated method stub
			
			
			
			try {
				for (int i =0;i<lezioni.size();i++) {
					String [] fieldsToSelect = {"*"};
					Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
					conditionsFildsToValues.put("Utente_idUtente", lezioni.get(i).getIdUtente().toString());
					conditionsFildsToValues.put("idLezione", lezioni.get(i).getId().toString());
					conditionsFildsToValues.put("visibile", "1");
					ResultSet result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues);
					int numTuple = 0;
					while (result.next() ) {
						FasciaOraria slot = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")),result.getInt("visibile"), result.getInt("OrarioInizioLezione"),result.getInt("OrarioFineLezione"), result.getDate("DataLezione"));
						lezioni.get(i).getSlots().add(slot);
						numTuple++;
					}
					
				}
				
				return StateResult.VALID;
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
		}
}
