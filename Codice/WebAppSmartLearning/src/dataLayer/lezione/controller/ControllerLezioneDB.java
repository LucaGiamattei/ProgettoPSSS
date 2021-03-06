package dataLayer.lezione.controller;

import java.sql.ResultSet;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.CallableStatement;
import java.sql.Connection;
import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.lezione.API_LezioneDB;
import dataLayer.lezione.entities.FasciaOraria;
import dataLayer.lezione.entities.LezioneDB;
import dataLayer.user.entities.UtenteDB;
import utilities.StateResult;
import utilities.idFasciaOraria;
import utilities.idLesson;
import utilities.idTopic;
import utilities.idUser;

public class ControllerLezioneDB implements API_LezioneDB{
	
	public StateResult getLessonsByCognome(String cognome, Vector<LezioneDB> lezioni) {
		  // TODO Auto-generated method stub
			
		    String [] fieldsToSelect = {"idUtente"};
		    Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		    conditionsFildsToValues.put("Cognome", cognome);
		    ControllerLezioneDB controllerlez = new ControllerLezioneDB();
		    
		      
		    ResultSet result;
		    try {
		    	Connection conn = null;
		    	result = DBConnectionManager.SelectEntryDB("utente", fieldsToSelect, conditionsFildsToValues, conn);
		    	if(result.next()) {
		    	int idUtente = result.getInt("idUtente");
		    	
		    	controllerlez.getLessonsByUser(new idUser(idUtente), lezioni);
		    	
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
			    	return StateResult.NOVALID;
			    }
		    } catch (Exception e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     return StateResult.DBPROBLEM;
		    }
		 }
	
		
		public StateResult validLezione(idLesson id) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("idLezione", id.toString());
			
			ResultSet result;
			try {
				Connection conn = null;
				result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues, conn);
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
	
//---------------------OPERAZIONI DOCENTE---------------------------//
		@Override
		public StateResult getFasciaOraria(idUser id, FasciaOraria fascia) {
			
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("idFasciaOraria", fascia.getId().toString());
			conditionsFildsToValues.put("Utente_idUtente", id.toString());
						ResultSet result;
						try {
							Connection conn = null;
							result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues, conn);
							
							int numOfRows = 0;
							
							while (result.next()) {
								numOfRows++;
								fascia.setVisible(result.getInt("visibile"));
								fascia.setOrarioInizioLezione(result.getTime("OrarioInizioLezione"));
								fascia.setOrarioFineLezione(result.getTime("OrarioFineLezione"));
								fascia.setPrezzo(result.getFloat("prezzo"));
								fascia.setDataLezione(result.getDate("DataLezione"));
								
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
		
		public StateResult getFasciaOrariaNoCatalogo(idUser id, FasciaOraria fascia) {
			
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("idFasciaOraria", fascia.getId().toString());
			conditionsFildsToValues.put("Lezione_Utente_idUtente", id.toString());
						ResultSet result;
						try {
							Connection conn = null;
							result = DBConnectionManager.SelectEntryDB("fasciaoraria", fieldsToSelect, conditionsFildsToValues, conn);
							
							int numOfRows = 0;
							
							while (result.next()) {
								numOfRows++;
								fascia.setVisible(result.getInt("visibile"));
								fascia.setOrarioInizioLezione(result.getTime("OrarioInizioLezione"));
								fascia.setOrarioFineLezione(result.getTime("OrarioFineLezione"));
								fascia.setPrezzo(result.getFloat("prezzo"));
								fascia.setDataLezione(result.getDate("DataLezione"));
								
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
		
		@Override
		public StateResult createLesson(LezioneDB infoLezione, String nomeTopic) {
			   // TODO Auto-generated method stub
			   Hashtable<String,String> lezione = new Hashtable<String, String>();
			   
			   lezione.put("NomeLezione", infoLezione.getNomeLezione());
			   
			   if(infoLezione.getDescrizioneLezione() != null) {
				   lezione.put("DescrizioneLezione", infoLezione.getDescrizioneLezione());
			   }
			   
			   lezione.put("NMaxStudenti", ""+infoLezione.getNmax()+"");
			   
			   
			   lezione.put("Utente_idUtente", ""+infoLezione.getIdUtente()+"");
			    
			    Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			   conditionsFildsToValues.put("Name", nomeTopic);
			   
			   try {
			    Integer r = DBConnectionManager.createNewEntryDBInSelect("Lezione", lezione, "Topic", conditionsFildsToValues);
			    
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
				
				String query = "{CALL updateCond(?,?,?,?,"+ orari.getId().getId() +","+ iduser +", ?)}";
				
				CallableStatement stmt = (CallableStatement) conn.prepareCall(query);
				
				stmt.setDate(1,orari.getDataLezione());
				stmt.setTime(2,orari.getOrarioInizio());
				stmt.setTime(3,orari.getOrarioFine());
				stmt.setFloat(4,orari.getPrezzo());
				
				stmt.registerOutParameter(5, Types.INTEGER);
				
				stmt.executeQuery();
				
				int outputValue = stmt.getInt(5);
	
				conn.commit();
				////if(conn!=null) {conn.close();}
				
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
				
				String query = "{CALL inserisciCond(?,?,?,?,"+idLezione+","+ idOwnerUser+",?)}";
				
				CallableStatement stmt = (CallableStatement) conn.prepareCall(query);
				
				stmt.setDate(1,orari.getDataLezione());
				stmt.setTime(2,orari.getOrarioInizio());
				stmt.setTime(3,orari.getOrarioFine());
				stmt.setFloat(4,orari.getPrezzo());
				
				stmt.registerOutParameter(5, Types.INTEGER);
				
				stmt.executeQuery();
				
				int retId = stmt.getInt(5);
				
				conn.commit();
				////if(conn!=null) {conn.close();}
				
				if(retId > 0) {
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
			ControllerLezioneDB controllerlez = new ControllerLezioneDB();
			
			ResultSet result;
			
			try {
				Connection conn = null;
				result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues, conn);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")));
				    
					lezioni.add(lezione); 

					numTuple++;
				}
				if(conn!=null) {
					//if(conn!=null) {conn.close();}
				}
				if(numTuple!=0) {
					controllerlez.attachSlotsToLessonsUtente(lezioni);
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
		public StateResult getLessonsByDocente(idUser idOwnerUser, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("Utente_idUtente", idOwnerUser.toString());
			ControllerLezioneDB controllerlez = new ControllerLezioneDB();
			
			ResultSet result;
			try {
				Connection conn = null;
				result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues, conn);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")));
				    
					lezioni.add(lezione); 

					numTuple++;
				}
				
				//if(conn!=null) {conn.close();}
				if(numTuple!=0) {
					controllerlez.attachSlotsToLessonsDocente(lezioni);
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
		public  StateResult attachSlotsToLessonsDocente(Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			if (lezioni == null) {
				lezioni = new Vector<LezioneDB> ();
			}
			// TODO Auto-generated method stub
			
			try {
				for (int i =0;i<lezioni.size();i++) {
					String [] fieldsToSelect = {"*"};
					Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
					conditionsFildsToValues.put("idLezione", lezioni.get(i).getId().toString());
					Connection conn = null;
					ResultSet result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues, conn);
					
					while (result.next() ) {
						FasciaOraria slot = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")),result.getInt("visibile"), result.getTime("OrarioInizioLezione"),result.getTime("OrarioFineLezione"), result.getDate("DataLezione"), result.getFloat("prezzo"));
						lezioni.get(i).getSlots().add(slot);
						
					}
					//if(conn!=null) {conn.close();}
					
				}
				
				return StateResult.VALID;
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
		}
		
		
	//----------------------------UTENTE------------------------------------//
public StateResult getFasciaOrariaVis(FasciaOraria fascia) {
			
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("idFasciaOraria", fascia.getId().toString());
			conditionsFildsToValues.put("visibile", "1");
			
						ResultSet result;
						try {
							Connection conn = null;
							result = DBConnectionManager.SelectEntryDB("FasciaOraria", fieldsToSelect, conditionsFildsToValues, conn);
							
							int numOfRows = 0;
							
							while (result.next()) {
								numOfRows++;
								fascia.setVisible(result.getInt("visibile"));
								fascia.setOrarioInizioLezione(result.getTime("OrarioInizioLezione"));
								fascia.setOrarioFineLezione(result.getTime("OrarioFineLezione"));
								fascia.setPrezzo(result.getFloat("prezzo"));
								fascia.setDataLezione(result.getDate("DataLezione"));
								
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
		
		@Override
		public StateResult getLessonsbyTopics(idTopic infoTopic, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("Topic_idTopic", infoTopic.toString());
			ControllerLezioneDB controllerlez = new ControllerLezioneDB();
			
			try {
				Connection conn = null;
				ResultSet result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues, conn);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")));
					lezioni.add(lezione);
					numTuple++;
				}
				//if(conn!=null) {conn.close();}
				if(numTuple>0) {
					controllerlez.attachSlotsToLessonsUtente(lezioni);
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
				Connection conn = null;
				ResultSet result = DBConnectionManager.SelectEntryDB("Lezione", fieldsToSelect, conditionsFildsToValues, conn);
				int numTuple = 0;
				while (result.next() ) {
					LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")));
					lezioni.add(lezione);
					numTuple++;
				}
				//if(conn!=null) {conn.close();}
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
					conditionsFildsToValues.put("idLezione", lezioni.get(i).getId().toString());
					conditionsFildsToValues.put("visibile", "1");
					Connection conn = null;
					ResultSet result = DBConnectionManager.SelectEntryDB("CatalogoLezioni", fieldsToSelect, conditionsFildsToValues, conn);
					int numTuple = 0;
					while (result.next() ) {
						FasciaOraria slot = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")),result.getInt("visibile"), result.getTime("OrarioInizioLezione"),result.getTime("OrarioFineLezione"), result.getDate("DataLezione"), result.getFloat("prezzo"));
						lezioni.get(i).getSlots().add(slot);
						numTuple++;
					}
					//if(conn!=null) {conn.close();}
					
				}
				
			
			return StateResult.VALID;
		
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return StateResult.DBPROBLEM;
			}
			
			
		}
/*
		public StateResult getFasceOrarieByLessonId(idLesson idlezione, Vector<FasciaOraria> fasce) {
			// TODO Auto-generated method stub
			String [] fieldsToSelect = {"*"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			conditionsFildsToValues.put("Lezione_idLezione", idlezione.toString());

			try {
			 ResultSet result = DBConnectionManager.SelectEntryDB("FasciaOraria", fieldsToSelect, conditionsFildsToValues);
			 int numOfRows = 0;
			 
			 while (result.next()) {
				 FasciaOraria fascia = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")), result.getInt("Visibile"), result.getTime("OrarioInizioLezione"),result.getTime("OrarioFineLezione"), result.getDate("DataLezione"),result.getFloat("prezzo"));
				 fasce.add(fascia);
			  numOfRows++;
			  
			  
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
		*/
		
		public StateResult removeFasciaById(idFasciaOraria id) {
			   Hashtable<String,String> removeFields = new Hashtable<String, String>();
			   removeFields.put("idFasciaOraria", id.toString());

			   Integer result;
			   try {
			    result = DBConnectionManager.removeFromDB("FasciaOraria", removeFields);
			    
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
		
		@Override
		public StateResult getLessonsPayedStillUp(idUser idUser, Vector<LezioneDB> lezioni) {
			// TODO Auto-generated method stub
			
			String [] fieldsToSelect = {"FasciaOraria_idFasciaOraria"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			Hashtable<String,List<String>> conditionsFildsToValuesList = new Hashtable<String, List<String>>();
			conditionsFildsToValues.put("Utente_idUtente", idUser.toString());
			
			
			ResultSet result;
			try {
				Connection conn = null;		
				result = DBConnectionManager.SelectEntryDB("pagamento",fieldsToSelect,conditionsFildsToValues, conn);
				
				if (result.next()) {
					
					List<String> list = new ArrayList<>();
					String [] fieldsToSelectlist = {"*"};
					
					result.beforeFirst();
					while(result.next()) {
						list.add(Integer.toString(result.getInt("FasciaOraria_idFasciaOraria")));
					}
					
					conditionsFildsToValuesList.put("idFasciaOraria",list);
					Connection conn1 = null;
					result = DBConnectionManager.SelectEntryORDB("catalogolezioni",fieldsToSelectlist,conditionsFildsToValuesList, conn1);
					

					while(result.next()) {
							
						boolean newlez = true;
						int index = 0;
							
						for(int i = 0; i < lezioni.size(); i++) {
							if(lezioni.get(i).getId().getId() == result.getInt("idLezione")) {
								newlez = false;
								index = i;
								break;
							} 
						}
						
						FasciaOraria fascia = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")),result.getInt("visibile"), result.getTime("OrarioInizioLezione"),result.getTime("OrarioFineLezione"), result.getDate("DataLezione"), result.getFloat("prezzo"));

						
						if(newlez) {
							LezioneDB lezione = new LezioneDB(new idLesson(result.getInt("idLezione")), result.getString("NomeLezione"),result.getString("DescrizioneLezione"), result.getFloat("MediaScoreLezione"), result.getInt("NMaxStudenti"), new idTopic(result.getInt("Topic_idTopic")), new idUser(result.getInt("Utente_idUtente")));
							lezione.getSlots().add(fascia);
							lezioni.add(lezione);
						}else {
							lezioni.get(index).getSlots().add(fascia);
						}
					}

					
					//if(conn!=null) {conn.close();}
					//conn1.close();
					if(lezioni.size() > 0) {
						
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
		
		
		
		@Override
		public StateResult getFascePayedStillUpByLesson(idUser idUser, idLesson idlez, Vector<FasciaOraria> fasce) {
			// TODO Auto-generated method stub
			
			String [] fieldsToSelect = {"FasciaOraria_idFasciaOraria"};
			Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
			Hashtable<String,List<String>> conditionsFildsToValuesList = new Hashtable<String, List<String>>();
			conditionsFildsToValues.put("Utente_idUtente", idUser.toString());
			
			
			ResultSet result;
			try {
				Connection conn = null;		
				result = DBConnectionManager.SelectEntryDB("pagamento",fieldsToSelect,conditionsFildsToValues, conn);
				
				if (result.next()) {
					
					List<String> list = new ArrayList<>();
					String [] fieldsToSelectlist = {"*"};
					
					result.beforeFirst();
					while(result.next()) {
						list.add(Integer.toString(result.getInt("FasciaOraria_idFasciaOraria")));
					}
					
					conditionsFildsToValuesList.put("idFasciaOraria",list);
					Connection conn1 = null;
					result = DBConnectionManager.SelectEntryORDB("catalogolezioni",fieldsToSelectlist,conditionsFildsToValuesList,  conn1);
					

					while(result.next()) {
						if(result.getInt("idLezione") == idlez.getId()) {
							FasciaOraria fascia = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")),result.getInt("visibile"), result.getTime("OrarioInizioLezione"),result.getTime("OrarioFineLezione"), result.getDate("DataLezione"), result.getFloat("prezzo"));
							fasce.add(fascia);
						}
					}

					
					//if(conn!=null) {conn.close();}
					//conn1.close();
					if(fasce.size() > 0) {
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
	/*	
		public StateResult getFasceOrariePayedStillUpByLessonId(String idlezione, String idUser, String datacurr, String timecurr, Vector<FasciaOraria> fasce) {
			// TODO Auto-generated method stub
			
			try {
			 ResultSet result = DBConnectionManager.queryRetrieveFascebyLezionePayedStillUp(idlezione, idUser,datacurr,timecurr);
			 int numOfRows = 0;
			 
			 while (result.next()) {
				 FasciaOraria fascia = new FasciaOraria(new idFasciaOraria(result.getInt("idFasciaOraria")), result.getInt("Visibile"), result.getTime("OrarioInizioLezione"),result.getTime("OrarioFineLezione"), result.getDate("DataLezione"),result.getFloat("prezzo"));
				 fasce.add(fascia);
			  numOfRows++;
			  
			  
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
		}*/
		
}
