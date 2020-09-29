package dataLayer.lezione.entities.result;


import dataLayer.lezione.entities.LezioneDB;

import dataLayer.utilities.StateResult;

public class ResultLezione {
	private StateResult stateResult;
	private LezioneDB lezione;
	
	public ResultLezione() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StateResult getStateResult() {
		return stateResult;
	}
	public void setStateResult(StateResult stateResult) {
		this.stateResult = stateResult;
	}
	public LezioneDB getLezione() {
		return lezione;
	}
	public void setLezione(LezioneDB lezione) {
		this.lezione = lezione;
	}
	
}

