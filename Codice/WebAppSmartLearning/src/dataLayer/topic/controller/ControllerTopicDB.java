package dataLayer.topic.controller;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.topic.interfaces.*;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idSubscription;

public class ControllerTopicDB implements API_TopicDB{
	
	
	public StateResult createTopic(TopicDB top) {
		Hashtable<String,String> topic = new Hashtable<String, String>();
	 	topic.put("Name", top.getNome());
	 	
		
		try {
			Integer r = DBConnectionManager.createNewEntryDB("Topic", topic,true);
			
			if (r>0) {
				top.setId(new idTopic(r));
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
	public StateResult validTopic(idTopic id) {
		// TODO Auto-generated method stub
		String [] fieldsToSelect = {"*"};
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("idTopic", id.toString());
		
		ResultSet result;
		try {
			result = DBConnectionManager.SelectEntryDB("Topic", fieldsToSelect, conditionsFildsToValues);
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
	public StateResult createSubscription(idTopic id, idUser idU, idSubscription idS) {
		Hashtable<String,String> addFildsToValues = new Hashtable<String, String>();
		addFildsToValues.put("Topic_idTopic", id.toString());
		addFildsToValues.put("Utente_idUtente", idU.toString());
		
		
		try {
			Integer r = DBConnectionManager.createNewEntryDB("SubscriptionUtenteTopic", addFildsToValues, true);
			
			
			if (r>0) {
				idS.setId(r);
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
	public StateResult removeSubscription(idSubscription id) {
		Hashtable<String,String> removeFields = new Hashtable<String, String>();
		removeFields.put("idSubscriptionUtenteTopic", id.toString());

		Integer result;
		try {
			result = DBConnectionManager.removeFromDB("SubscriptionUtenteTopic", removeFields);
			
			switch(result) {  
		      case 1:
		    	  return StateResult.REMOVED;
		        
		     
		      default:
		    	  return StateResult.NOREMOVED;
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return StateResult.DBPROBLEM;
		}
		
	}
	public StateResult getMostRequestedTopic(Vector <TopicDB> topics) {
		String selectField = "Topic_idTopic";

		ResultSet result;
		try {
			result = DBConnectionManager.countEntryDB("SubscriptionUtenteTopic", selectField, selectField);
			int numOfRows = 0;
			int NumeroOccorrenze = 0;
			
			while (result.next()) {
				numOfRows++;
				if (numOfRows == 1) {
					NumeroOccorrenze = result.getInt("NumeroOccorrenze");
					TopicDB topic = new TopicDB(new idTopic(result.getInt("Topic_idTopic")));
					topics.add(topic);
				}else {
					if(NumeroOccorrenze>result.getInt("NumeroOccorrenze")) {
						TopicDB topic = new TopicDB(new idTopic(result.getInt("Topic_idTopic")));
						topics.add(topic);
					}
				}
				
				
			}
			if(numOfRows > 0) {
				return StateResult.VALID;
			} else {
				return StateResult.NOVALID;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return StateResult.DBPROBLEM;
		}
	}
	@Override
	public StateResult getTopicName(TopicDB topic) {
		// TODO Auto-generated method stub
		String [] fieldsToSelect = {"*"};
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("idTopic", topic.getId().toString());
				
		ResultSet result;
		try {
			result = DBConnectionManager.SelectEntryDB("Topic", fieldsToSelect, conditionsFildsToValues);
			int numOfRows = 0;
					
			while (result.next()) {
				numOfRows++;
				if(numOfRows==1) {
					topic.setNome(result.getString("Name"));
				}
						
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

	public StateResult getLessonsByTopicName(String nome, Vector<LezioneDB> lezioni) {
		   // TODO Auto-generated method stub
		   String [] fieldsToSelect = {"*"};
		   Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		   conditionsFildsToValues.put("Name", nome);
		   ControllerLezioneDB controllerlez = new ControllerLezioneDB();
		   
		   try {
		    ResultSet result = DBConnectionManager.SelectEntryInSelectDB("Lezione", fieldsToSelect, "Topic_idTopic", "Topic", "idTopic", conditionsFildsToValues);
		    int numTuple = 0;
		    while (result.next() ) {
		     LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")));
		     Vector<FasciaOraria> fasce = new Vector<FasciaOraria>();
		      
		      controllerlez.getFasceOrarieByLessonId(new idLesson(result.getInt("idLezione")), fasce);
		      
		      lezione.setSlots(fasce);
		      
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
