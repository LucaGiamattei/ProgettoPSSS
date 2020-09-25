package dataLayer.topic.controller;
import java.sql.ResultSet;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.topic.entities.TopicDB;
import dataLayer.topic.interfaces.*;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;
import dataLayer.utilities.idSubscription;

public class ControllerTopicDB implements API_TopicDB{
	
	
	public StateResult createTopic(TopicDB top) {
		Hashtable<String,String> topic = new Hashtable<String, String>();
	 	topic.put("Nome", top.getNome());
	 	
		
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
		removeFields.put("idSubscription", id.toString());

		ResultSet result;
		try {
			result = DBConnectionManager.removeFromDB("SubscriptionUtenteTopic", removeFields);
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
	public StateResult getMostRequestedTopic(idTopic[] id) {
		String selectField = "Topic_idTopic";

		ResultSet result;
		try {
			result = DBConnectionManager.countEntryDB("SubscriptionUtenteTopic", selectField, selectField);
			int numOfRows = 0;
			
			while (result.next()) {
				numOfRows++;
				id[numOfRows].setId(result.getInt("Topic_idTopic"));;
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



}
