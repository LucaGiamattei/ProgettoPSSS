package dataLayer.lezione.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.lezione.entities.result.ResultLezione;
import dataLayer.lezione.interfaces.API_LezioneDB;
import dataLayer.user.entities.UtenteDB;
import dataLayer.user.entities.result.ResultUtente;
import dataLayer.utilities.StateResult;
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
			      case 0:
			        return StateResult.NOVALID;
			        
			      case 1:
			    	  return StateResult.VALID;
			        
			     
			      default:
			    	  return StateResult.DEFAULT; 
				}
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
			
		}
	
//---------------------OPERAZIONI DOCENTE---------------------------//
		
		@Override
		public StateResult createLesson(idUser idOwnerUser, idTopic idTopic, LezioneDB infoLezione) {
			// TODO Auto-generated method stub
			Hashtable<String,String> lezione = new Hashtable<String, String>();
			
			lezione.put("NomeLezione", infoLezione.getNomeLezione());
			lezione.put("DescrizioneLezione", infoLezione.getDescrizioneLezione());
			lezione.put("prezzo", ""+infoLezione.getPrezzoSlot()+"");
		 	lezione.put("MediaScoreLezione", "0");
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
		public StateResult updateFasciaOraria(idUser idOwnerUser, idLesson idLezione, FasciaOraria slotDaAggiornare, FasciaOraria slotAggiornato) {
			// TODO Auto-generated method stub
			Hashtable<String,String> conditionsFildsToValues1 = new Hashtable<String, String>();
			conditionsFildsToValues1.put("DataLezione", slotDaAggiornare.getDataLezione().toString());
			conditionsFildsToValues1.put("OrarioLezione", ""+slotDaAggiornare.getOrario()+"");
			conditionsFildsToValues1.put("Lezione_Utente_idUtente",idOwnerUser.toString() );
			conditionsFildsToValues1.put("Lezione_idLezione", idLezione.toString());
			
			Hashtable<String,String> fildsToValues = new Hashtable<String, String>();
			fildsToValues.put("DataLezione", slotAggiornato.getDataLezione().toString());
			fildsToValues.put("OrarioLezione", ""+slotAggiornato.getOrario()+"");
			fildsToValues.put("Visibile", ""+slotAggiornato.getVisible()+"");
			
			Integer r;
			try {
				r = DBConnectionManager.UpdateEntryDB("Utente", conditionsFildsToValues1, fildsToValues, false);
				if (r==1) {
					return StateResult.ONE_ROWSCHANGED;
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
		public StateResult addFasciaOraria(idUser idOwnerUser, idLesson idLezione, FasciaOraria[] slots) {
			// TODO Auto-generated method stub
			String transazione ="";
			for (int i =0; i<slots.length;i++) {
				Hashtable<String,String> slot = new Hashtable<String, String>();
			 	slot.put("DataLezione", slots[i].getDataLezione().toString());
			 	slot.put("OrarioLezione", ""+slots[i].getOrario()+"");
			 	slot.put("Lezione_idLezione", idLezione.toString());
			 	slot.put("Lezione_Utente_idLezione", idOwnerUser.toString());
			 	slot.put("Visibile", ""+slots[i].getVisible()+"");
			 	transazione = transazione+" "+DBConnectionManager.getQueryNewEntryDB("Lezione",slot );
			}
			try {
				Integer r = DBConnectionManager.updateQuery("START TRANSACTION; "+transazione+" COMMIT;");
				if (r==slots.length) {
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
		public StateResult getLessonsByUser(idUser idOwnerUser, Vector<LezioneDB> lezioni) {
			if (lezioni == null) {
				lezioni = new Vector<LezioneDB> ();
			}
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("Utente_idUtente", idOwnerUser.toString());
			
			ResultSet result;
			try {
				result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")),result.getFloat("prezzo"));
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
		public  StateResult getLessonsByUserWithSlotsDocente(idUser idOwnerUser, Vector<LezioneDB> lezioni) {
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
					ResultSet result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues);
					int numTuple = 0;
					while (result.next() ) {
						FasciaOraria slot = new FasciaOraria(result.getInt("visibile"), result.getInt("OrarioLezione"), result.getDate("DataLezione"));
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
		
		
	//----------------------------UTENTE------------------------------------//
		
		public  StateResult getLessonsByUserWithSlotsUtente(idUser idOwnerUser, Vector<LezioneDB> lezioni) {
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
					conditionsFildsToValues.put("visibile", "0");
					ResultSet result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues);
					int numTuple = 0;
					while (result.next() ) {
						FasciaOraria slot = new FasciaOraria(result.getInt("visibile"), result.getInt("OrarioLezione"), result.getDate("DataLezione"));
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
		public StateResult getLessonbyTitle(String title, Vector<LezioneDB> lezioni) {
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
		
}
