package dataLayer.docente.interfaces;

import dataLayer.docente.entities.DocenteDB;
import dataLayer.docente.entities.result.ResultDocente;
import dataLayer.utilities.idLesson;
import dataLayer.utilities.idUser;

public interface API_DocenteDB {
	ResultDocente createDocente(idUser id, DocenteDB docente);
	ResultDocente getDocentebyLesson(idLesson id);
	ResultDocente updateContoPaypal(DocenteDB docente);
	
}

