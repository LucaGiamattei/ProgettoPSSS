package dataLayer.lezione.entities;

import java.sql.Date;



public class FasciaOraria {
	private int visible;
	private int orarioLezione ;
	private Date dataLezione ;
	
	public FasciaOraria(int visible, int orario, Date date) {
		super();
		this.visible = visible;
		this.orarioLezione = orario;
		this.dataLezione = date;
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
	
	
	
}
