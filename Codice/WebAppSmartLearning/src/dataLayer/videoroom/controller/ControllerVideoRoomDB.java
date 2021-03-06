package dataLayer.videoroom.controller;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Hashtable;

import com.mysql.jdbc.CallableStatement;
import java.sql.Connection;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.videoroom.API_VideoRoomDB;
import dataLayer.videoroom.entities.VideoRoomDB;
import utilities.StateResult;
import utilities.idFasciaOraria;



public class ControllerVideoRoomDB implements API_VideoRoomDB{
	
	
	
	@Override
	public StateResult removeRoom(idFasciaOraria idFasciaOraria) {
		Hashtable<String,String> removeFields = new Hashtable<String, String>();
		removeFields.put("FasciaOraria_idFasciaOraria", idFasciaOraria.toString());

		Integer result;
		try {
			result = DBConnectionManager.removeFromDB("Videocall", removeFields);
			
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
	public StateResult createNewRoom(idFasciaOraria idFasciaOraria, VideoRoomDB videoRoom) {

		Connection conn;
			try {
			conn = DBConnectionManager.getConnection();
			conn.setAutoCommit(false);
			
			String query = "{CALL inserisciVideocall(?,?,?)}";
			
			CallableStatement stmt = (CallableStatement) conn.prepareCall(query);
			
			stmt.setInt(1,idFasciaOraria.getId());
			stmt.setString(2,videoRoom.getNomeRoom());
			
			
			stmt.registerOutParameter(3, Types.VARCHAR);
			
			stmt.executeQuery();
			
			String outputValue = stmt.getString(3);
			videoRoom.setPasswordRoom(outputValue);

			conn.commit();
			//conn.close();
			
			if(outputValue !=null) {
				System.out.println("createNewRoom: StateResult.CREATED");
				return StateResult.CREATED;
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
	public StateResult getRoom(idFasciaOraria id, VideoRoomDB videoRoom) {

		
		// TODO Auto-generated method stub
		String [] fieldsToSelect = {"*"};
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("FasciaOraria_idFasciaOraria", id.toString());
				
		ResultSet result;
		try {
			Connection conn = null;
			result = DBConnectionManager.SelectEntryDB("Videocall", fieldsToSelect, conditionsFildsToValues, conn);
			
			
			int numOfRows = 0;
					
			while (result.next()) {
					numOfRows++;
					if (numOfRows==1) {
						videoRoom.setNomeRoom(result.getString("NomeRoom"));
						videoRoom.setPasswordRoom(result.getString("Password"));
						return StateResult.VALID;
						
						
					}
			}
			//conn.close();
			
			return StateResult.NOVALID;
			
			
					
				
		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return StateResult.DBPROBLEM;
			    	
					
		}
	}

	

}
