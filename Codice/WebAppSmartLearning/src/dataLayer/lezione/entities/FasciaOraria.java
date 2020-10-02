package dataLayer.lezione.entities;

import java.sql.Date;
import java.sql.Time;

import dataLayer.utilities.idFasciaOraria;



public class FasciaOraria {
	private idFasciaOraria id;
	private int visible;
	
	private Time orarioInizioLezione;
	private Time orarioFineLezione;
	private Date dataLezione ;
	private float prezzo;
	
	public FasciaOraria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FasciaOraria(int visible, Time orarioInizio,Time orarioFine, Date date, float prezzo) {
		super();
		this.visible = visible;
		this.orarioInizioLezione = orarioInizio;
		this.orarioFineLezione = orarioFine;
		this.dataLezione = date;
		this.prezzo = prezzo;
	}
	
	public FasciaOraria(idFasciaOraria id, int visible, Time orarioInizio,Time orarioFine, Date date, float prezzo) {
		super();
		this.visible = visible;
		this.orarioInizioLezione = orarioInizio;
		this.orarioFineLezione = orarioFine;
		this.dataLezione = date;
		this.id = id;
		this.prezzo = prezzo;
	}
	
	public float getPrezzo() {
		  return prezzo;
	}

	public void setPrezzo(float prezzo) {
		  this.prezzo = prezzo;
	}
	
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public Time getOrarioInizio() {
		return orarioInizioLezione;
	}
	public Time getOrarioFine() {
		return orarioFineLezione;
	}
	public void setOrarioInizioLezione(Time orario) {
		this.orarioInizioLezione = orario;
	}
	public void setOrarioFineLezione(Time orario) {
		this.orarioFineLezione = orario;
	}
	public Date getDataLezione() {
		return dataLezione;
	}
	public void setDataLezione(Date date) {
		this.dataLezione = date;
	}
	
	public idFasciaOraria getId() {
		return id;
	}

	public void setId(idFasciaOraria id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FasciaOraria [id=" + id + ", visible=" + visible + ", orarioInizioLezione=" + orarioInizioLezione+", orarioFineLezione="+ orarioFineLezione + ", dataLezione="
				+ dataLezione +", prezzo=" + prezzo+  "]";
	}
	
	
	
}
