package dataLayer.pagamento.controller;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Vector;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.pagamento.interfaces.API_PagamentoDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idPagamento;
import dataLayer.utilities.idUser;

public class ControllerPagamentoDB implements API_PagamentoDB{
	
	

	@Override
	public StateResult subscribePayment(idUser idUser, idFasciaOraria idFasciaOraria, idPagamento idPagamento) {
		// TODO Auto-generated method stub
		Hashtable<String,String> addFildsToValues = new Hashtable<String, String>();
		addFildsToValues.put("Utente_idUtente", idUser.toString());
		addFildsToValues.put("FasciaOraria_idFasciaOraria", idFasciaOraria.toString());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		addFildsToValues.put("DataPagamento", dtf.format(now));
		
		try {
			Integer r = DBConnectionManager.createNewEntryDB("Pagamento", addFildsToValues, true);
			
			
			if (r>0) {
				idPagamento.setId(r);
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
	public StateResult verifyUserHasPayed(idUser idUser, idFasciaOraria idFasciaOraria) {
		// TODO Auto-generated method stub
				String [] fieldsToSelect = {"*"};
				Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
				conditionsFildsToValues.put("Utente_idUtente", idUser.toString());
				conditionsFildsToValues.put("FasciaOraria_idFasciaOraria", idFasciaOraria.toString());
				ResultSet result;
				try {
					result = DBConnectionManager.SelectEntryDB("Pagamento", fieldsToSelect, conditionsFildsToValues);
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

	@Override
	public StateResult getUsersPayedLesson(idFasciaOraria idFasciaOraria, Vector<idUser> users) {
		 // TODO Auto-generated method stub
		 String [] fieldsToSelect = {"Utente_idUtente"};
		 Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		 conditionsFildsToValues.put("FasciaOraria_idFasciaOraria", idFasciaOraria.toString());
		  ResultSet result;
		  try {
			  result = DBConnectionManager.SelectEntryDB("Pagamento", fieldsToSelect, conditionsFildsToValues);
			  int numOfRows = 0;
		   
			  while (result.next()) {
			    numOfRows++;
			    
			    users.add(new idUser(result.getInt("Utente_idUtente")));
			    
			  }
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

	@Override
	public StateResult getFasceStillUpByUserId(idUser idUser, Vector<idFasciaOraria> idFasce) {
		// TODO Auto-generated method stub
		 String [] fieldsToSelect = {"*"};
		 Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		 Hashtable<String,String> conditionsFildsToValues2 = new Hashtable<String, String>();
		 conditionsFildsToValues.put("Utente_idUtente", idUser.toString());
		 
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
		 LocalDateTime now = LocalDateTime.now();
		 
		 conditionsFildsToValues2.put("DataLezione", dtf.format(now));
		 conditionsFildsToValues2.put("OrarioInizioLezione", dtf2.format(now));
		 
		  ResultSet result;
		  try {
			  result = DBConnectionManager.SelectEntryInGTSelectDB("pagamento", fieldsToSelect,conditionsFildsToValues,"FasciaOraria_idFasciaOraria", "fasciaoraria", "idFasciaOraria", conditionsFildsToValues2); 
			  int numOfRows = 0;
		   
			  while (result.next()) {
			    numOfRows++;
			    
			    idFasce.add(new idFasciaOraria(result.getInt("FasciaOraria_idFasciaOraria")));
			    
			  }
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
