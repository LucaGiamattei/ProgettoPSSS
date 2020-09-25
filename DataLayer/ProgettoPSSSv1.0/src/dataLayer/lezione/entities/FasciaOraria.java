package dataLayer.lezione.entities;

import java.sql.Date;

import dataLayer.utilities.idFasciaOraria;



public class FasciaOraria {
	private idFasciaOraria id;
	private int visible;
	
	private int orarioLezione ;
	private Date dataLezione ;
	
	public FasciaOraria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FasciaOraria(int visible, int orario, Date date) {
		super();
		this.visible = visible;
		this.orarioLezione = orario;
		this.dataLezione = date;
	}
	public FasciaOraria(idFasciaOraria id, int visible, int orario, Date date) {
		super();
		this.visible = visible;
		this.orarioLezione = orario;
		this.dataLezione = date;
		this.id = id;
	}
	
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public int getOrario() {
		return orarioLezione;
	}
	public void setOrarioLezione(int orario) {
		this.orarioLezione = orario;
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
		return "FasciaOraria [id=" + id + ", visible=" + visible + ", orarioLezione=" + orarioLezione + ", dataLezione="
				+ dataLezione + "]";
	}
	
	
	
}
