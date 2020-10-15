-- MySQL Script generated by MySQL Workbench
-- Wed Oct 14 18:31:35 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema prova2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema prova2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prova2` DEFAULT CHARACTER SET utf8 ;
USE `prova2` ;

-- -----------------------------------------------------
-- Table `prova2`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`topic` (
  `idTopic` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idTopic`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`utente` (
  `idUtente` INT(11) NOT NULL AUTO_INCREMENT,
  `Password` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Cognome` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Docente` INT(11) NOT NULL DEFAULT '0',
  `MediaScoreLezioni` FLOAT NULL DEFAULT '0',
  `ContoPaypal` VARCHAR(45) NULL DEFAULT NULL,
  `Curriculum` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`lezione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`lezione` (
  `idLezione` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeLezione` VARCHAR(45) NOT NULL,
  `MediaScoreLezione` FLOAT NULL DEFAULT '0',
  `NMaxStudenti` INT(11) NULL DEFAULT NULL,
  `Utente_idUtente` INT(11) NOT NULL,
  `Topic_idTopic` INT(11) NOT NULL,
  `DescrizioneLezione` VARCHAR(300) NOT NULL DEFAULT 'n.d.',
  PRIMARY KEY (`idLezione`, `Utente_idUtente`),
  UNIQUE INDEX `idLezione_UNIQUE` (`idLezione` ASC),
  INDEX `fk_Lezione_Utente1_idx` (`Utente_idUtente` ASC),
  INDEX `fk_Lezione_Topic1_idx` (`Topic_idTopic` ASC),
  CONSTRAINT `fk_Lezione_Topic1`
    FOREIGN KEY (`Topic_idTopic`)
    REFERENCES `prova2`.`topic` (`idTopic`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lezione_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `prova2`.`utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`fasciaoraria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`fasciaoraria` (
  `DataLezione` DATE NOT NULL,
  `OrarioInizioLezione` TIME NOT NULL,
  `Visibile` INT(11) NOT NULL DEFAULT '1',
  `Lezione_idLezione` INT(11) NOT NULL,
  `Lezione_Utente_idUtente` INT(11) NOT NULL,
  `idFasciaOraria` INT(11) NOT NULL AUTO_INCREMENT,
  `OrarioFineLezione` TIME NOT NULL,
  `prezzo` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`idFasciaOraria`),
  UNIQUE INDEX `idFasciaOraria_UNIQUE` (`idFasciaOraria` ASC),
  INDEX `fk_FasciaOraria_Lezione1_idx` (`Lezione_idLezione` ASC, `Lezione_Utente_idUtente` ASC),
  CONSTRAINT `fk_FasciaOraria_Lezione1`
    FOREIGN KEY (`Lezione_idLezione` , `Lezione_Utente_idUtente`)
    REFERENCES `prova2`.`lezione` (`idLezione` , `Utente_idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 107
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`pagamento` (
  `DataPagamento` DATE NULL DEFAULT NULL,
  `idPagamento` INT(11) NOT NULL AUTO_INCREMENT,
  `Utente_idUtente` INT(11) NOT NULL,
  `FasciaOraria_idFasciaOraria` INT(11) NOT NULL,
  `TOKEN` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Utente_idUtente`, `FasciaOraria_idFasciaOraria`),
  UNIQUE INDEX `idPagamento_UNIQUE` (`idPagamento` ASC),
  INDEX `fk_idFasciaOraria_idx` (`FasciaOraria_idFasciaOraria` ASC),
  CONSTRAINT `fk_idFasciaOraria`
    FOREIGN KEY (`FasciaOraria_idFasciaOraria`)
    REFERENCES `prova2`.`fasciaoraria` (`idFasciaOraria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUtente`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `prova2`.`utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`recensione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`recensione` (
  `idRecensione` INT(11) NOT NULL AUTO_INCREMENT,
  `Valutazione` INT(11) NULL DEFAULT NULL,
  UNIQUE INDEX `idRecensione_UNIQUE` (`idRecensione` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`subscriptionutentetopic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`subscriptionutentetopic` (
  `idSubscriptionUtenteTopic` INT(11) NOT NULL AUTO_INCREMENT,
  `Utente_idUtente` INT(11) NOT NULL,
  `Topic_idTopic` INT(11) NOT NULL,
  `Data` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`Topic_idTopic`, `Utente_idUtente`),
  UNIQUE INDEX `idSubscriptionUtenteTopic_UNIQUE` (`idSubscriptionUtenteTopic` ASC),
  INDEX `fk_SubscriptionUtenteTopic_Utente1_idx` (`Utente_idUtente` ASC),
  INDEX `fk_SubscriptionUtenteTopic_Topic1_idx` (`Topic_idTopic` ASC),
  CONSTRAINT `fk_SubscriptionUtenteTopic_Topic1`
    FOREIGN KEY (`Topic_idTopic`)
    REFERENCES `prova2`.`topic` (`idTopic`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SubscriptionUtenteTopic_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `prova2`.`utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prova2`.`videocall`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`videocall` (
  `NomeRoom` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `FasciaOraria_idFasciaOraria` INT(11) NOT NULL,
  PRIMARY KEY (`FasciaOraria_idFasciaOraria`),
  UNIQUE INDEX `NomeRoom_UNIQUE` (`NomeRoom` ASC),
  CONSTRAINT `fk_FasciaOraria`
    FOREIGN KEY (`FasciaOraria_idFasciaOraria`)
    REFERENCES `prova2`.`fasciaoraria` (`idFasciaOraria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `prova2` ;

-- -----------------------------------------------------
-- Placeholder table for view `prova2`.`catalogolezioni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prova2`.`catalogolezioni` (`idLezione` INT, `NomeLezione` INT, `MediaScoreLezione` INT, `NMaxStudenti` INT, `DataLezione` INT, `OrarioInizioLezione` INT, `OrarioFineLezione` INT, `prezzo` INT, `visibile` INT, `Utente_idUtente` INT, `Topic_idTopic` INT, `idFasciaOraria` INT, `DescrizioneLezione` INT);

-- -----------------------------------------------------
-- procedure genTokens
-- -----------------------------------------------------

DELIMITER $$
USE `prova2`$$
CREATE DEFINER=`giorgio@dbpsss`@`dbpsss.mysql.database.azure.com` PROCEDURE `genTokens`(IN idFasciaOraria int )
BEGIN

 UPDATE prova2.pagamento SET `TOKEN`=  LEFT(MD5(RAND()),8) WHERE  FasciaOraria_idFasciaOraria = idFasciaOraria ;
 SELECT Utente_idUtente, TOKEN FROM pagamento WHERE FasciaOraria_idFasciaOraria = idFasciaOraria;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisciCond
-- -----------------------------------------------------

DELIMITER $$
USE `prova2`$$
CREATE DEFINER=`giorgio@dbpsss`@`dbpsss.mysql.database.azure.com` PROCEDURE `inserisciCond`(IN myDataLezione DATE, IN myOrarioInizio TIME, IN myOrarioFine TIME,IN prezzo float, IN myidLezione int, IN myidUtente int, OUT idFascia INT)
BEGIN
DECLARE COND INT DEFAULT 0;

SELECT count(*)
INTO COND
FROM fasciaoraria
WHERE Lezione_Utente_idUtente = myidUtente AND ( 
(OrarioInizioLezione <= myOrarioInizio AND myOrarioInizio <= OrarioFineLezione)
OR (OrarioInizioLezione <= myOrarioFine AND myOrarioFine <= OrarioFineLezione))
AND DataLezione = myDataLezione;

SET idFascia = 0;
IF COND = 0 THEN
INSERT INTO fasciaoraria (DataLezione, OrarioInizioLezione, OrarioFineLezione,prezzo, Lezione_idLezione, Lezione_Utente_idUtente) VALUES (myDataLezione, myOrarioInizio,myOrarioFine,prezzo, myidLezione,myidUtente);
SET idFascia = LAST_INSERT_ID();
END IF;


END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisciVideocall
-- -----------------------------------------------------

DELIMITER $$
USE `prova2`$$
CREATE DEFINER=`giorgio@dbpsss`@`dbpsss.mysql.database.azure.com` PROCEDURE `inserisciVideocall`(IN idFasciaOraria INT, IN nomeRoom VARCHAR(45), OUT passw VARCHAR(45))
BEGIN
    SET passw = LEFT(MD5(RAND()),8);
    
    INSERT INTO videocall (FasciaOraria_idFasciaOraria, NomeRoom, Password) VALUES (idFasciaOraria, nomeRoom, passw);

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateCond
-- -----------------------------------------------------

DELIMITER $$
USE `prova2`$$
CREATE DEFINER=`giorgio@dbpsss`@`dbpsss.mysql.database.azure.com` PROCEDURE `updateCond`(IN myDataLezione DATE, IN myOrarioInizio TIME, IN myOrarioFine TIME,IN prezzo float, IN myidFasciaOraria int, IN myidUtente int, OUT result INT)
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
UPDATE fasciaoraria SET DataLezione=myDataLezione, OrarioInizioLezione=myOrarioInizio, OrarioFineLezione=myOrarioFine, prezzo=prezzo
WHERE idFasciaOraria = myidFasciaOraria;
END IF;
SET result = COND;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `prova2`.`catalogolezioni`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prova2`.`catalogolezioni`;
USE `prova2`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`giorgio@dbpsss`@`dbpsss.mysql.database.azure.com` SQL SECURITY DEFINER VIEW `prova2`.`catalogolezioni` AS select `prova2`.`lezione`.`idLezione` AS `idLezione`,`prova2`.`lezione`.`NomeLezione` AS `NomeLezione`,`prova2`.`lezione`.`MediaScoreLezione` AS `MediaScoreLezione`,`prova2`.`lezione`.`NMaxStudenti` AS `NMaxStudenti`,`prova2`.`fasciaoraria`.`DataLezione` AS `DataLezione`,`prova2`.`fasciaoraria`.`OrarioInizioLezione` AS `OrarioInizioLezione`,`prova2`.`fasciaoraria`.`OrarioFineLezione` AS `OrarioFineLezione`,`prova2`.`fasciaoraria`.`prezzo` AS `prezzo`,`prova2`.`fasciaoraria`.`Visibile` AS `visibile`,`prova2`.`lezione`.`Utente_idUtente` AS `Utente_idUtente`,`prova2`.`lezione`.`Topic_idTopic` AS `Topic_idTopic`,`prova2`.`fasciaoraria`.`idFasciaOraria` AS `idFasciaOraria`,`prova2`.`lezione`.`DescrizioneLezione` AS `DescrizioneLezione` from (`prova2`.`lezione` join `prova2`.`fasciaoraria` on(((`prova2`.`lezione`.`idLezione` = `prova2`.`fasciaoraria`.`Lezione_idLezione`) and (`prova2`.`lezione`.`Utente_idUtente` = `prova2`.`fasciaoraria`.`Lezione_Utente_idUtente`)))) where ((`prova2`.`fasciaoraria`.`DataLezione` > curdate()) or ((`prova2`.`fasciaoraria`.`DataLezione` = curdate()) and (`prova2`.`fasciaoraria`.`OrarioFineLezione` <= now())));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
