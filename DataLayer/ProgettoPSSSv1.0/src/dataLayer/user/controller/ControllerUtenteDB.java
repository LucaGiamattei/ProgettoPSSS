package dataLayer.user.controller;

import java.sql.ResultSet;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.user.entities.UtenteDB;
import dataLayer.user.entities.result.ResultUtente;
import dataLayer.user.interfaces.API_UtenteDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idUser;

public class ControllerUtenteDB implements API_UtenteDB{

	@Override

/**
 *Questa funzione permette di validare l'id di un utente
 *
 *@param id
 *@return StateResult Rappresenta lo stato dell'operazione:
 *- NOVALID
 *- VALID
 *- DEFAULT
 *- DBPROBLEM
 */
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
/**
 * Questa funzione permette di creare un utente
 * 
 * @param utente struttura che contiene le informazioni da memorizzare per il nuovo utente
 * @param password la password da memorizzare per il nuovo utente
 * @return StateResult Rappresenta lo stato del risultato:
 * - NOCHANGES
 * - ONE_ROWSCHANGED
 * - DEFAULT
 * - DBPROBLEM
 */

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
			switch(r) {
		      case 0:
		        return StateResult.NOCHANGES;
		        
		      case 1:
		    	  return StateResult.ONE_ROWSCHANGED;
		        
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
/**
 * Questa funzione permette di ottenere la struttura dati UtenteDB con le informazione 
 * riguardo l'utente identificato dal suo id
 * 
 * @param id identificativo dell'utente da selezionare
 * @return ResultUtente struttura dati contenente il risultato della selezione. 
 * Tale struttura contiene uno stato:
 * - NOVALID 
 * - VALID
 * - DEFAULT
 * - DBPROBLEM
 * e l'oggetto UtenteDB
 */
	public ResultUtente retrieveUser(idUser id) {
		ResultUtente risultato = new ResultUtente();
		
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
						UtenteDB returnUser = new UtenteDB(new idUser(result.getInt("idUtente")), result.getString("Nome"), result.getString("Cognome"), result.getString("Email"));
						risultato.setUtente(returnUser);
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
/**
 * Questa funzione permette di ottenere la password relativa  aun utente
 * 
 * @param id identificativo dell'utente da selezionare
 * @param password password da validare
 * @return StateResult Restituisce lo stato della validazione:
 * -  VALID
 * - NOVALID
 * - DBPROBLEM
 */
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

}
