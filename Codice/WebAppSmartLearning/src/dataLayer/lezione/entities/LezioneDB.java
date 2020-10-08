package dataLayer.lezione.entities;

import java.util.Hashtable;
import java.util.Vector;

/*
//import com.mysql.fabric.xmlrpc.base.Data;
*/
import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;

public class LezioneDB {
	private idLesson id;
	private String nomeLezione;
	private String descrizioneLezione;
	private int Nmax;
	private float media_score;
	
	private idTopic idTopic;
	private idUser idUtente;
	
	private Vector<FasciaOraria> slots;
	
	
	
	/**
	 * Costruttore con anche lo slot
	 * @param id
	 * @param nomeLezione
	 * @param descrizioneLezione
	 * @param media_score
	 * @param nmax
	 * @param idTopic
	 * @param idUtente
	 * @param slot
	 */
	public LezioneDB(idLesson id, String nomeLezione,String descrizioneLezione, float media_score, int nmax, dataLayer.utilities.idTopic idTopic,
			idUser idUtente, FasciaOraria slot) {
		super();
		this.id = id;
		this.nomeLezione = nomeLezione;
		this.media_score = media_score;
		Nmax = nmax;
		this.idTopic = idTopic;
		this.idUtente = idUtente;
		this.slots = new Vector <FasciaOraria>();
		this.slots.add(slot);
		this.descrizioneLezione = descrizioneLezione;
	}
	/**
	 * Costruttore senza slot
	 * @param id
	 * @param nomeLezione
	 * @param descrizioneLezione
	 * @param media_score
	 * @param nmax
	 * @param idTopic
	 * @param idUtente
	 */
	public LezioneDB(idLesson id, String nomeLezione,String descrizioneLezione, float media_score, int nmax, dataLayer.utilities.idTopic idTopic,
			idUser idUtente) {
		super();
		this.id = id;
		this.nomeLezione = nomeLezione;
		this.media_score = media_score;
		Nmax = nmax;
		this.idTopic = idTopic;
		this.idUtente = idUtente;
		this.slots = new Vector <FasciaOraria>();
		this.descrizioneLezione = descrizioneLezione;
	}
	
	/**
	 * Costruttore utilizzato per la create
	 * 
	 * @param nomeLezione
	 * @param descrizioneLezione
	 * @param nmax
	 * @param idTopic
	 * @param idUtente
	 */
	public LezioneDB(String nomeLezione,String descrizioneLezione,int nmax, dataLayer.utilities.idTopic idTopic,
			idUser idUtente) {
		super();
		
		this.nomeLezione = nomeLezione;
		this.Nmax = nmax;
		this.idTopic = idTopic;
		this.idUtente = idUtente;
		this.descrizioneLezione = descrizioneLezione;
		this.slots = new Vector <FasciaOraria>();
	}


	public LezioneDB(idUser iduser, String nome, String descrizione) {
		// TODO Auto-generated constructor stub
		this.nomeLezione = nome;
		this.descrizioneLezione = descrizione;
		this.idUtente = iduser;
		this.slots = new Vector <FasciaOraria>();
		
	}
	
	public LezioneDB(idUser iduser, String nome, String descrizione, int nmax) {
		// TODO Auto-generated constructor stub
		this.nomeLezione = nome;
		this.descrizioneLezione = descrizione;
		this.idUtente = iduser;
		this.Nmax = nmax;
		this.slots = new Vector <FasciaOraria>();
	}
	
	public LezioneDB(idUser iduser, String nome, int nmax) {
		// TODO Auto-generated constructor stub
		this.nomeLezione = nome;
		this.idUtente = iduser;
		this.Nmax = nmax;
		this.slots = new Vector <FasciaOraria>();
	}
	
	public LezioneDB(idLesson idLesson) {
		// TODO Auto-generated constructor stub
		this.id = idLesson;
		this.slots = new Vector <FasciaOraria>();
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
	@Override
	public String toString() {
		String r = "LezioneDB [id=" + id + ", nomeLezione=" + nomeLezione + ", descrizioneLezione=" + descrizioneLezione
				+ ", Nmax=" + Nmax + ", media_score=" + media_score + ", idTopic="
				+ idTopic + ", idUtente=" + idUtente+"\n"+"Slots: \n";
		if (slots != null) {
			for(int i = 0; i<slots.size();i++) {
				r=r+slots.get(i).toString()+"\n";
			}
		}
		r = r+"]";
		return r;
	}

	
	
}

