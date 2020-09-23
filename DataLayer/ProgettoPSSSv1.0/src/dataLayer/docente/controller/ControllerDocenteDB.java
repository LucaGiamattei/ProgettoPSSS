package dataLayer.docente.controller;

import dataLayer.docente.entities.DocenteDB;
import dataLayer.docente.entities.result.ResultDocente;
import dataLayer.docente.interfaces.API_DocenteDB;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;

public class ControllerDocenteDB implements API_DocenteDB{

	@Override
	public ResultDocente getDocentebyLesson(idLesson id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultDocente updateContoPaypal(DocenteDB docente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultDocente createDocente(idUser id, DocenteDB docente) {
		// TODO Auto-generated method stub
		return null;
	}

}
