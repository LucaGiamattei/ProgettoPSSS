package dataLayer.docente.entities;

import dataLayer.connectorManager.DBConnectionManager;

public class DocenteDB {
	private String contoPaypall;
	private String curriculum;
	private float mediaScoreLezioni;
	
	
	public DocenteDB(String contoPaypall, String curriculum, float mediaScoreLezioni) {
		super();
		this.contoPaypall = contoPaypall;
		this.curriculum = curriculum;
		this.mediaScoreLezioni = mediaScoreLezioni;
	}
	
	

	public String getContoPaypall() {
		return contoPaypall;
	}


	public void setContoPaypall(String contoPaypall) {
		this.contoPaypall = contoPaypall;
	}


	public String getCurriculum() {
		return curriculum;
	}


	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}


	public float getMediaScoreLezioni() {
		return mediaScoreLezioni;
	}


	public void setMediaScoreLezioni(float mediaScoreLezioni) {
		this.mediaScoreLezioni = mediaScoreLezioni;
	}
	
	public void createNewDocente() throws Exception {
		
		DBConnectionManager.updateQuery("UPDATE");
	}
}
