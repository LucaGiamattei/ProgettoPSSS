package dataLayer.user.entities;

import java.util.Hashtable;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.utilities.idUser;

public class UtenteDB {
	private idUser id;
	private String nome;
	private String cognome;
	private String Email;
	
	
	public UtenteDB(idUser id, String nome, String cognome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		Email = email;
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
	
	
	
}
