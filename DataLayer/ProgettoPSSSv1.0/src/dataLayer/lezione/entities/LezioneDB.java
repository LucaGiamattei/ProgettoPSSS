package dataLayer.lezione.entities;

import java.util.Hashtable;
import java.util.Vector;

import com.mysql.fabric.xmlrpc.base.Data;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;

public class LezioneDB {
	private idLesson id;
	private String nomeLezione;
	private String descrizioneLezione;
	private int Nmax;
	private float prezzoSlot;
	private float media_score;
	
	private idTopic idTopic;
	private idUser idUtente;
	
	
	
	
	
	
	
	private Vector<FasciaOraria> slots;
	
	public LezioneDB(idLesson id, String nomeLezione,String descrizioneLezione, float media_score, int nmax, dataLayer.utilities.idTopic idTopic,
			idUser idUtente, FasciaOraria slot, float prezzoSlot) {
		super();
		this.id = id;
		this.nomeLezione = nomeLezione;
		this.media_score = media_score;
		Nmax = nmax;
		this.idTopic = idTopic;
		this.idUtente = idUtente;
		this.slots = new Vector <FasciaOraria>();
		this.slots.add(slot);
		this.prezzoSlot = prezzoSlot;
		this.descrizioneLezione = descrizioneLezione;
	}
	
	public LezioneDB(idLesson id, String nomeLezione,String descrizioneLezione, float media_score, int nmax, dataLayer.utilities.idTopic idTopic,
			idUser idUtente, float prezzoSlot) {
		super();
		this.id = id;
		this.nomeLezione = nomeLezione;
		this.media_score = media_score;
		Nmax = nmax;
		this.idTopic = idTopic;
		this.idUtente = idUtente;
		this.prezzoSlot = prezzoSlot;
		this.descrizioneLezione = descrizioneLezione;
	}
	
	public float getPrezzoSlot() {
		return prezzoSlot;
	}

	public void setPrezzoSlot(float prezzoSlot) {
		this.prezzoSlot = prezzoSlot;
	}

	public Vector<FasciaOraria> getSlots() {
		return slots;
	}

	public void setSlots(Vector<FasciaOraria> slots) {
		this.slots = slots;
	}

	public idLesson getId() {
		return id;
	}

	public void setId(idLesson id) {
		this.id = id;
	}

	public String getNomeLezione() {
		return nomeLezione;
	}

	public void setNomeLezione(String nomeLezione) {
		this.nomeLezione = nomeLezione;
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

	public void setNmax(int nmax) {
		Nmax = nmax;
	}

	public idTopic getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(idTopic idTopic) {
		this.idTopic = idTopic;
	}

	public idUser getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(idUser idUtente) {
		this.idUtente = idUtente;
	}
	
	public String getDescrizioneLezione() {
		return descrizioneLezione;
	}

	public void setDescrizioneLezione(String descrizioneLezione) {
		this.descrizioneLezione = descrizioneLezione;
	}

	
	
}

