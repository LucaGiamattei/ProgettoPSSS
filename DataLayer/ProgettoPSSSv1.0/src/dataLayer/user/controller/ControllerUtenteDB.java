package dataLayer.user.controller;

import java.sql.ResultSet;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.user.entities.UtenteDB;
import dataLayer.user.entities.result.ResultUtente;
import dataLayer.user.interfaces.API_UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;

public class ControllerUtenteDB implements API_UtenteDB{

	


	
	public StateResult validateUser(idUser id) {
		// TODO Auto-generated method stub
		String [] fieldsToSelect = {"*"};
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("idUtente", id.toString());
		
		ResultSet result;
		try {
			result = DBConnectionManager.SelectEntryDB("Utente", fieldsToSelect, conditionsFildsToValues);
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
	public StateResult createUser(UtenteDB utente, String password) {
		Hashtable<String,String> u = new Hashtable<String, String>();
	 	u.put("Nome", utente.getNome());
	 	u.put("Cognome", utente.getCognome());
	 	u.put("Password", password);
	 	u.put("Email", utente.getEmail());
	 	u.put("Docente", "0");
		
		try {
			Integer r = DBConnectionManager.createNewEntryDB("Utente", u,true);
			
			if (r>0) {
				utente.setId(new idUser(r));
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

	
	public StateResult retrieveUser(idUser id,UtenteDB utente ) {
		
		
		// TODO Auto-generated method stub
		String [] fieldsToSelect = {"*"};
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("idUtente", id.toString());
				
		ResultSet result;
		try {
			result = DBConnectionManager.SelectEntryDB("Utente", fieldsToSelect, conditionsFildsToValues);
			
			
			int numOfRows = 0;
					
			while (result.next()) {
					numOfRows++;
					if (numOfRows==1) {
						utente.setCognome(result.getString("Cognome"));
						utente.setEmail(result.getString("Email"));
						utente.setId(new idUser(result.getInt("idUtente")));
						utente.setNome(result.getString("Nome"));
						
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

	
	public StateResult verifyPassword(idUser id, String password) {
		// TODO Auto-generated method stub
				String [] fieldsToSelect = {"Password"};
				Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
				conditionsFildsToValues.put("idUtente", id.toString());
				
				ResultSet result;
				try {
					result = DBConnectionManager.SelectEntryDB("Utente", fieldsToSelect, conditionsFildsToValues);
					int numOfRows = 0;
					
					while (result.next()) {
						numOfRows++;
						if(numOfRows == 1) {
							if(result.getString("Password")==password) {
								return StateResult.VALID;
							}
						}
					}
					return StateResult.NOVALID;
					
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return StateResult.DBPROBLEM;
				}
	}
	
	public StateResult getDocentebyLesson(idLesson id, UtenteDB utente) {
		// TODO Auto-generated method stub

				String [] fieldsToSelect = {"*"};
				Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
				conditionsFildsToValues.put("idLezione", id.toString());
						
				ResultSet result;
				try {
					result = DBConnectionManager.SelectEntryInSelectDB("Utente", fieldsToSelect, "idUtente", "FasciaOraria", "*", conditionsFildsToValues);
					
					
					int numOfRows = 0;
							
					while (result.next()) {
							numOfRows++;
							if (numOfRows==1) {
								utente.setCognome(result.getString("Cognome"));
								utente.setEmail(result.getString("Email"));
								utente.setId(new idUser(result.getInt("idUtente")));
								utente.setNome(result.getString("Nome"));
								utente.setContoPaypal(result.getString("ContoPayPal"));
								utente.setCurriculum(result.getString("Curriculum"));
								utente.setMediaScoreLezioni(result.getInt("MediaScoreLezione"));
								
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

	@Override
	public StateResult updateContoPaypal(idUser id, UtenteDB utente) {
		// TODO Auto-generated method stub

		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("idUtente", id.toString());
		
		Hashtable<String,String> fildsToUpdate = new Hashtable<String, String>();
		fildsToUpdate.put("ContoPayPal", utente.getContoPaypal());
	 	
				
		try {
			Integer r = DBConnectionManager.UpdateEntryDB("Utente", conditionsFildsToValues, fildsToUpdate, false);
			
			
			if (r == 1) {
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
	public StateResult createDocente(idUser id, UtenteDB utente) {
		// TODO Auto-generated method stub
		
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("idUtente", id.toString());
		
		Hashtable<String,String> fildsToUpdate = new Hashtable<String, String>();
		fildsToUpdate.put("ContoPayPal", utente.getContoPaypal());
		fildsToUpdate.put("Curriculum", utente.getCurriculum());
		fildsToUpdate.put("MediaScoreLezioni", "" + utente.getMediaScoreLezioni() + "");
		
		try {
			Integer r = DBConnectionManager.UpdateEntryDB("Utente", conditionsFildsToValues, fildsToUpdate, false);
			
			
			if (r == 1) {
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

}



