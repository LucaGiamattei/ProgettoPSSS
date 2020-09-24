package dataLayer.lezione.entities;

import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idTopic;

public class LezioneDB {
	private idLesson id;
	private String titolo;
	private float media_score;
	private int Nmax;
	private idTopic idTopic;
	
	public LezioneDB(idLesson id, String titolo, float media_score, int Nmax, idTopic idTopic) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.media_score = media_score;
		this.Nmax = Nmax;
		this.idTopic = idTopic;
	}
	public idLesson getId() {
		return id;
	}
	public void setId(idLesson id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public float getMedia_score() {
		return media_score;
	}
	public void setMedia_score(float media_score) {
		this.media_score = media_score;
	}
	
	public int getNmax() {
		return Nmax;
	}
	public void setNmax(int Nmax) {
		this.Nmax = Nmax;
	}
	
	public idTopic getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(idTopic idTopic) {
		this.idTopic = idTopic;
	}

}
