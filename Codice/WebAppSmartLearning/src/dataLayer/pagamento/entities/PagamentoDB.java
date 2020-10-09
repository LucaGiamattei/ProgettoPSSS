package dataLayer.pagamento.entities;

import dataLayer.utilities.idFasciaOraria;
import dataLayer.utilities.idUser;

public class PagamentoDB {
	idFasciaOraria idFascia;
	idUser id;
	String token;
	public idFasciaOraria getIdFascia() {
		return idFascia;
	}
	public void setIdFascia(idFasciaOraria idFascia) {
		this.idFascia = idFascia;
	}
	public idUser getId() {
		return id;
	}
	public void setId(idUser id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public PagamentoDB(idFasciaOraria idFascia, idUser id, String token) {
		super();
		this.idFascia = idFascia;
		this.id = id;
		this.token = token;
	}
	

}
