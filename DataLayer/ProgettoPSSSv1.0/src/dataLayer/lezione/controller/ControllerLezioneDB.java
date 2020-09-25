package dataLayer.lezione.controller;

import java.sql.ResultSet;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
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
		
	

			@Override
			public StateResult createLesson(idUser idOwnerUser, idTopic idTopic, LezioneDB infoLezione) {
				Hashtable<String,String> u = new Hashtable<String, String>();
			 	u.put("NomeLezione", infoLezione.getTitolo());
			 	u.put("Utente_idUtente", ""+idOwnerUser+"");
			 	u.put("NmaxStudenti", ""+infoLezione.getNmax()+"");
			 	u.put("MediaScoreLezione", ""+infoLezione.getMedia_score()+"");
			 	u.put("Topic_idTopic", ""+idTopic+"");
			 	
				try {
					Integer r = DBConnectionManager.createNewEntryDB("Lezione", u,true);
					
					if(r>0) {
					infoLezione.setId(new idLesson(r));
					return StateResult.CREATED;
					} else {
						return StateResult.NOCHANGES;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return StateResult.DBPROBLEM;
				}
				
				
			}
			
	
			
			public StateResult getLessonsbyTitle(String title) {
				ResultLezione risultato = new ResultLezione();
				
				// TODO Auto-generated method stub
				String [] fieldsToSelect = {"*"};
				Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
				conditionsFildsToValues.put("NomeLezione", title);
						
				ResultSet result;
				try {
					result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues);
					
					
					int numOfRows = 0;
							
					while (result.next()) {
							numOfRows++;
							if (numOfRows==1) {
								LezioneDB returnLezione = new LezioneDB(new idLesson(result.getInt("idLesson")), result.getString("NomeLezione"), result.getFloat("Prezzo"), result.getFloat("MediaScoreLezioni"),result.getInt("NMaxStudenti"), new idTopic(result.getInt("idTopic")));
								risultato.setLezione(returnLezione);
							}
					}
					switch(numOfRows) {
						    case 0:
						    	risultato.setStateResult(StateResult.NOVALID);
						    	return risultato;
						        
						    case 1:
						    	risultato.setStateResult(StateResult.VALID);
						    	return risultato;
						     
						    default:
						    	risultato.setStateResult(StateResult.DEFAULT);
						    	return risultato;
						    	
					}
							
						
				} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							risultato.setStateResult(StateResult.DBPROBLEM);
					    	return risultato;
							
				}
			}
			

			public StateResult getLessonsbyTopics(idTopic infoTopic, LezioneDB lezione) {

				
				// TODO Auto-generated method stub
				String [] fieldsToSelect = {"*"};
				Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
				conditionsFildsToValues.put("Topic_idTopic", infoTopic.toString());
						
				ResultSet result;
				try {
					result = DBConnectionManager.SelectEntryInSelectDB("FasciaOraria", fieldsToSelect, "Lezione_IdLezione", "Lezione", "*", conditionsFildsToValues);
					
					
					int numOfRows = 0;
							
					while (result.next()) {
						numOfRows++;
						if (numOfRows==1) {
							utente.setCognome(result.getString("Cognome"));
							lezione.setTitolo(result.getString("Titolo"));
							lezione.setNmax(result.getInt(3)));
							
							
						}
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
}
