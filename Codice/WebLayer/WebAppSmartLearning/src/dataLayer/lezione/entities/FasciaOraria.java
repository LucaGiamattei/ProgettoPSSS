package dataLayer.lezione.entities;

import java.sql.Date;

import dataLayer.utilities.idFasciaOraria;



public class FasciaOraria {
	private idFasciaOraria id;
	private int visible;
	
	private int orarioInizioLezione;
	private int orarioFineLezione;
	private Date dataLezione ;
	
	public FasciaOraria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FasciaOraria(int visible, int orarioInizio,int orarioFine, Date date) {
		super();
		this.visible = visible;
		this.orarioInizioLezione = orarioInizio;
		this.orarioFineLezione = orarioFine;
		this.dataLezione = date;
	}
	
	public FasciaOraria(idFasciaOraria id, int visible, int orarioInizio,int orarioFine, Date date) {
		super();
		this.visible = visible;
		this.orarioInizioLezione = orarioInizio;
		this.orarioFineLezione = orarioFine;
		this.dataLezione = date;
		this.id = id;
	}
	
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public int getOrarioInizio() {
		return orarioInizioLezione;
	}
	public int getOrarioFine() {
		return orarioFineLezione;
	}
	public void setOrarioInizioLezione(int orario) {
		this.orarioInizioLezione = orario;
	}
	public void setOrarioFineLezione(int orario) {
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
				+ dataLezione + "]";
	}
	
	
	
}
