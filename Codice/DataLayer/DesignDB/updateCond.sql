CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCond`(IN myDataLezione DATE, IN myOrarioInizio TIME, IN myOrarioFine TIME, IN myidFasciaOraria int, IN myidUtente int, OUT result INT)
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
UPDATE fasciaoraria SET DataLezione=myDataLezione, OrarioInizioLezione=myOrarioInizio, OrarioFineLezione=myOrarioFine
WHERE idFasciaOraria = myidFasciaOraria;
END IF;
SET result = COND;
END