package dataLayer.user.entities.result;

import dataLayer.user.entities.UtenteDB;
import dataLayer.utilities.StateResult;

public class ResultUtente {
	private StateResult stateResult;
	private UtenteDB utente;
	public ResultUtente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StateResult getStateResult() {
		return stateResult;
	}
	public void setStateResult(StateResult stateResult) {
		this.stateResult = stateResult;
	}
	public UtenteDB getUtente() {
		return utente;
	}
	public void setUtente(UtenteDB utente) {
		this.utente = utente;
	}
	
	
}
