package dataLayer.videoroom.controller;

import java.sql.ResultSet;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idFasciaOraria;

import dataLayer.videoroom.entities.VideoRoomDB;
import dataLayer.videoroom.interfaces.API_VideoRoomDB;

public class ControllerVideoRoomDB implements API_VideoRoomDB{

	@Override
	public StateResult createNewRoom(idFasciaOraria idFasciaOraria, VideoRoomDB videoRoom) {
		// TODO Auto-generated method stub
		Hashtable<String,String> addFildsToValues = new Hashtable<String, String>();
		addFildsToValues.put("NomeRoom", videoRoom.getNomeRoom());
		addFildsToValues.put("FasciaOraria_idFasciaOraria", idFasciaOraria.toString());
				
		try {
			Integer r = DBConnectionManager.createNewEntryDB("Videocall", addFildsToValues, true);
					
			if (r>0) {
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
	public StateResult getRoom(idFasciaOraria id, VideoRoomDB videoRoom) {

		
		// TODO Auto-generated method stub
		String [] fieldsToSelect = {"*"};
		Hashtable<String,String> conditionsFildsToValues = new Hashtable<String, String>();
		conditionsFildsToValues.put("FasciaOraria_idFasciaOraria", id.toString());
				
		ResultSet result;
		try {
			result = DBConnectionManager.SelectEntryDB("Videocall", fieldsToSelect, conditionsFildsToValues);
			
			
			int numOfRows = 0;
					
			while (result.next()) {
					numOfRows++;
					if (numOfRows==1) {
						videoRoom.setNomeRoom(result.getString("NomeRoom"));
						videoRoom.setPasswordRoom(result.getString("Password"));
						
						
					}
			}
			
			if(numOfRows ==1) {
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
