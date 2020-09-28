/*commento*/
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisciCond`(IN myDataLezione DATE, IN myOrarioInizio TIME, IN myOrarioFine TIME, IN myidLezione int, IN myidUtente int, OUT result INT)
BEGIN
DECLARE COND INT DEFAULT 0;

SELECT count(*)
INTO COND
FROM fasciaoraria
WHERE Lezione_Utente_idUtente = myidUtente AND ( 
(OrarioInizioLezione <= myOrarioInizio AND myOrarioInizio <= OrarioFineLezione)
OR (OrarioInizioLezione <= myOrarioFine AND myOrarioFine <= OrarioFineLezione))
AND DataLezione = myDataLezione;

IF COND = 0 THEN
INSERT INTO fasciaoraria (DataLezione, OrarioInizioLezione, OrarioFineLezione, Lezione_idLezione, Lezione_Utente_idUtente) VALUES (myDataLezione, myOrarioInizio,myOrarioFine, myidLezione,myidUtente);
END IF;
SET result = COND;
END