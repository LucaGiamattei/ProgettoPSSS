package dataLayer.topic.controller;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.lezione.controller.ControllerLezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.topic.API_TopicDB;
import dataLayer.topic.entities.TopicDB;

import dataLayer.utilities.StateResult;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idSubscription;
import java.sql.Connection;

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
			Connection conn = null;
			result = DBConnectionManager.SelectEntryDB("Topic", fieldsToSelect, conditionsFildsToValues, conn);
			int numOfRows = 0;
			
			while (result.next()) {
				numOfRows++;
				
			}
			//if(conn!=null) {conn.close();}
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
			Connection conn = null;
			result = DBConnectionManager.countEntryDB("SubscriptionUtenteTopic", selectField, selectField, conn);
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
			//if(conn!=null) {conn.close();}
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
			Connection conn = null;
			result = DBConnectionManager.SelectEntryDB("Topic", fieldsToSelect, conditionsFildsToValues, conn);
			int numOfRows = 0;
					
			while (result.next()) {
				numOfRows++;
				if(numOfRows==1) {
					topic.setNome(result.getString("Name"));
				}
						
			}
			//if(conn!=null) {conn.close();}
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
		   String [] fieldsToSelect = {"idTopic"};
		   Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		   conditionsFildsToValues.put("Name", nome);
		   ControllerLezioneDB controllerlez = new ControllerLezioneDB();
		   
		   try {
			   Connection conn = null;
		    ResultSet result = DBConnectionManager.SelectEntryDB("Topic", fieldsToSelect, conditionsFildsToValues, conn);
		    if(result.next()) {
			    controllerlez.getLessonsbyTopics(new idTopic(result.getInt("idTopic")), lezioni);
		    	
		    	int i = 0;
		    	while(i < lezioni.size()) {
		    		
		    		if(lezioni.get(i).getSlots().size() == 0) {
		    			lezioni.remove(i);
		    		}else{
		    			i++;
		    		}
		    	}
		    	//if(conn!=null) {conn.close();}
			     if(lezioni.size()>0) {
			     return StateResult.VALID;
			    }else {
			     return StateResult.NOVALID;
			    }
		    }else {
		    	//if(conn!=null) {conn.close();}
		    	return StateResult.NOVALID;
		    } 
		   } catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return StateResult.DBPROBLEM;
		   }
		   
		  
		  }
	
	public StateResult getTopics(Vector<String> topics) {
		  // TODO Auto-generated method stub
		  String [] fieldsToSelect = {"*"};
		  
		  
		  ResultSet result;
		  try {
			  Connection conn = null;
		   result = DBConnectionManager.SelectAll("Topic", fieldsToSelect, conn);
		   int numOfRows = 0;
		   
		   while (result.next()) {
		    numOfRows++;
		    
		    topics.add(result.getString("Name"));
		    
		   }
		   //if(conn!=null) {conn.close();}
		   if(numOfRows>0) {
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
