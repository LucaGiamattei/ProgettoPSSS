package dataLayer.user.entities;

import java.io.File;
import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import utilities.idUser;

public class UtenteDB {
	private idUser id;
	private String nome;
	private String cognome;
	private String Email;
	private String contoPaypal;
	private File curriculum;
	private float mediaScoreLezioni;
	
	
	@Override
	public String toString() {
		return "UtenteDB [id=" + id.toString() + ", nome=" + nome + ", cognome=" + cognome + ", Email=" + Email + ", contoPaypal="
				+ contoPaypal + ", curriculum=" + curriculum + ", mediaScoreLezioni=" + mediaScoreLezioni + "]";
	}

	public UtenteDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtenteDB(idUser id, String nome, String cognome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.Email = email;
	}
	
	public UtenteDB(String nome, String cognome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.Email = email;
	}
	
	public UtenteDB(idUser id, String nome, String cognome, String email, String contoPaypal, File curriculum, float mediaScoreLezioni) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.Email = email;
		this.contoPaypal = contoPaypal;
		this.curriculum = curriculum;
		this.mediaScoreLezioni =mediaScoreLezioni;
	}
	
	
	public UtenteDB(idUser id, String contoPaypal, File curriculum) {
		this.id = id;
		this.contoPaypal = contoPaypal;
		this.curriculum = curriculum;
	}

	
	public idUser getId() {
		return id;
	}
	public void setId(idUser id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getContoPaypal() {
		return contoPaypal;
	}
	public void setContoPaypal(String contoPaypal) {
		this.contoPaypal = contoPaypal;
	}
	public File getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(File curriculum) {
		this.curriculum = curriculum;
	}
	public float getMediaScoreLezioni() {
		return mediaScoreLezioni;
	}
	public void setMediaScoreLezioni(float mediaScoreLezioni) {
		this.mediaScoreLezioni = mediaScoreLezioni;
	}
	
}
