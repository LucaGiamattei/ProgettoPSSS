package dataLayer.connectorManager;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBConnectionManager
{
	public static String url = "jdbc:mysql://localhost:3306/";
	public static String dbName = "Prova2";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String userName = "root"; 
	public static String password = "giorgio1996";

	public static Connection getConnection() throws Exception
	{
	  Connection conn = null;
	  
	  Class.forName(driver);
	  conn = (Connection) DriverManager.getConnection(url+dbName+"?autoReconnect=true&useSSL=false",userName,password);
	  
	  return conn;
	}
	
	
	
	public static void closeConnection(Connection c) throws Exception
	{
		c.close();
	}
	
	public static ResultSet selectQuery(String query) throws Exception
	{
		Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet ret = statement.executeQuery(query);
        //conn.close();
        return ret;
	}

	public static int updateQuery(String query) throws Exception
	{
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		int ret = statement.executeUpdate(query);
		conn.close();
		return ret;
	}
	
	
	public static Integer updateQueryReturnGeneratedKey(String query) throws Exception
	{
		Integer ret = null;
		
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()){
		    ret = rs.getInt(1);
		}
		
		conn.close();
		
		return ret;
	}


	
//----------------Gestione Transazioni-------------------//

	

	public static String getQueryNewEntryDB(String nomeTabella, Hashtable<String, String> fildsToValues ){
	
	String  values = "";
	String  fields = "";
	Set<String> keys = fildsToValues.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        fields = fields+"`"+key+"`";
        values = values+"'"+fildsToValues.get(key)+"'";
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       fields = fields+",`"+key+"`";
       values = values+",'"+fildsToValues.get(key)+"'";
       
    } 
	return  "INSERT INTO `"+dbName+"`.`"+nomeTabella+"` ("+fields+") VALUES ("+values+");";		
	
	
	}


	public static ResultSet selectQueryTrans(String query, Connection conn) throws Exception
	{
		
	    Statement statement = conn.createStatement();
	    ResultSet ret = statement.executeQuery(query);
	    return ret;
	}

	public static int updateQueryTrans(String query, Connection conn) throws Exception
	{
		Statement statement = conn.createStatement();
		int ret = statement.executeUpdate(query);
		return ret;
	}
	
	public static Integer updateQueryReturnGeneratedKeyTrans(String query, Connection conn) throws Exception
	{
		Integer ret = null;
		
		
		Statement statement = conn.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()){
		    ret = rs.getInt(1);
		}
		
		
		
		return ret;
	}




//----------------------------------Gestione Transazioni singoli------------//
/**
 * Con questa funzione è possibile creare una nuova tupla in una tabella.
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param fildsToValues Tabella del tipo ("NomeCOlonna": "Valore") elenca i valori da associare ai campi della tupla 
 * @param GeneratedKey con il valore true se vi è un campo con valore autogenerato, quest'ultimo verrà restituito dalla funzione.
 * @return createNewEntryDB Viene restituito un intero, questo intero dipende dal parametro di ingresso GeneratedKey, se tale parametro è true viene restituito il valore autogenerato altrimenti il numero di tuple aggiornate.
 * @throws Exception
 */


public static Integer createNewEntryDB(String nomeTabella, Hashtable<String, String> fildsToValues, boolean GeneratedKey ) throws Exception {
	
	String  values = "";
	String  fields = "";
	Set<String> keys = fildsToValues.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        fields = fields+"`"+key+"`";
        values = values+"'"+fildsToValues.get(key)+"'";
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       fields = fields+",`"+key+"`";
       values = values+",'"+fildsToValues.get(key)+"'";
       
    } 
	String query = "INSERT INTO `"+dbName+"`.`"+nomeTabella+"` ("+fields+") VALUES ("+values+");";		
	if (GeneratedKey) {
		 return updateQueryReturnGeneratedKey(query);
			
	}else {
		return updateQuery(query);
	}
	
	}



/**
 * Questa funzione permette di aggiornare degli specifici campi di una o più tupla all'interno di una tabella.
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param conditionsFildsToValues una hash table con cui è possibile esprimere condizioni del tipo: "Nella colonna "key" cerca le tuple che hanno il valore "value""
 * @param fildsToValues Una hashtable nella quale è possibile associare i campi e i valori da aggiornare
 * @param GeneratedKey con il valore true se vi è un campo con valore autogenerato, quest'ultimo verrà restituito dalla funzione.
 * @return UpdateEntryDB Viene restituito un intero, questo intero dipende dal parametro di ingresso GeneratedKey, se tale parametro è true viene restituito il valore autogenerato altrimenti il numero di tuple aggiornate.
 * @throws Exception
 */


public static Integer UpdateEntryDB(String nomeTabella,Hashtable<String, String> conditionsFildsToValues, Hashtable<String, String> fildsToValues, boolean GeneratedKey ) throws Exception {
	String  mappingFieldValue = "";
	String mappingFieldValueCond = "";
	Set<String> keys = fildsToValues.keySet();
	Set<String> keysCond = conditionsFildsToValues.keySet();
	  //Obtaining iterator over set entries
	Iterator<String> itr = keys.iterator();
	Iterator<String> itrCond = keysCond.iterator();
    
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+fildsToValues.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+", `"+key+"` ="+"'"+fildsToValues.get(key)+"'";
       
    } 
 
    
    if(itrCond.hasNext()) {
    	// Getting Key
        String key = itrCond.next();
        mappingFieldValueCond = mappingFieldValueCond+"`"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itrCond.hasNext()) { 
       // Getting Key
       String key = itrCond.next();
       mappingFieldValueCond = mappingFieldValueCond+" && `"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
       
    }
    
	String query = "UPDATE `"+dbName+"`.`"+nomeTabella+"` SET "+mappingFieldValue+" WHERE "+mappingFieldValueCond+" ;";		
	System.out.println(query);
	if (GeneratedKey) {
		 return updateQueryReturnGeneratedKey(query);
			
	}else {
		return updateQuery(query);
	}
	
	
	}
/**
 * COn questa funzione è possibile selezionare delle tuple all'interno della tabella che hanno un determinato valore in determinate colonne.
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param fieldsToSelect Colonne della tabella da restituire nella select
 * @param conditionsFildsToValues  una hash table con cui è possibile esprimere condizioni del tipo: "Nella colonna "key" cerca le tuple che hanno il valore "value""
 * @return SelectEntryDB Restituisce le tuple selezionate dalla query
 * @throws Exception
 */

public static ResultSet SelectEntryDB(String nomeTabella,String [] fieldsToSelect, Hashtable<String, String> conditionsFildsToValues ) throws Exception {
	
	String  mappingFieldValue = "";
	
	Set<String> keys = conditionsFildsToValues.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+" && `"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
       
    }
    String fields ="";
    if (fieldsToSelect.length>0) {
    	fields = fieldsToSelect[0];
    }
    for (int i = 1; i<fieldsToSelect.length;i++) {
    	fields = fields+","+fieldsToSelect[i];
    }
    
    
	String query = "SELECT "+fields+" FROM `"+dbName+"`.`"+nomeTabella+"` WHERE "+mappingFieldValue+" ;";
	System.out.println(query);
	return selectQuery(query);
	
	}


/**
 * Con questa funzione è possibile selezionare delle tuple all'interno della tabella che hanno come valore di una colonna il risultato di un'altra query di selezione.
 * Esempio query: "SELECT fieldsToSelect1 FROM `nomeTabella1` WHERE fieldCondition1 IN(SELECT fieldToSelect2 FROM `nomeTabella2` WHERE conditionsFildsToValues2) ;
 * 
 * @param nomeTabella1
 * @param fieldsToSelect1
 * @param fieldCondition1
 * @param nomeTabella2
 * @param fieldToSelect2
 * @param conditionsFildsToValues2
 * @return
 * @throws Exception
 */

public static ResultSet SelectEntryInSelectDB(String nomeTabella1 ,String [] fieldsToSelect1,String fieldCondition1,String nomeTabella2, String fieldToSelect2, Hashtable<String, String> conditionsFildsToValues2 ) throws Exception {
	
	String  mappingFieldValue = "";
	
	Set<String> keys = conditionsFildsToValues2.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+conditionsFildsToValues2.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+" && `"+key+"` ="+"'"+conditionsFildsToValues2.get(key)+"'";
       
    }
    String fields ="";
    if (fieldsToSelect1.length>0) {
    	fields = fieldsToSelect1[0];
    }
    for (int i = 1; i<fieldsToSelect1.length;i++) {
    	fields = fields+","+fieldsToSelect1[i];
    }
    
    
	String query = "SELECT"+fields+" FROM `"+dbName+"`.`"+nomeTabella1+"` WHERE "+fieldCondition1+" IN(SELECT "+fieldToSelect2+" FROM `"+dbName+"`.`"+nomeTabella2+"` WHERE "+mappingFieldValue+") ;";
	System.out.println(query);
	return selectQuery(query);
	
	}

/**
 * Con questa funzione è possibile eliminare delle tuple all'interno della tabella che hanno un determinato valore in determinate colonne.
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param conditionsFildsToValues  una hash table con cui è possibile esprimere condizioni del tipo: "Nella colonna "key" cerca le tuple che hanno il valore "value""
 * @return removeFromDB Rimuove le tuple selezionate dalla query
 * @throws Exception
 * 
 */
public static Integer removeFromDB(String nomeTabella, Hashtable<String, String> conditionsFildsToValues ) throws Exception {
	
String  mappingFieldValue = "";
	
	Set<String> keys = conditionsFildsToValues.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+", `"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
       
    }
    
    
	String query = "DELETE FROM `"+dbName+"`.`"+nomeTabella+"` WHERE "+mappingFieldValue+" ;";
	System.out.println(query);
	return updateQuery(query);
	
	
	}

/**
 * Con questa funzione è possibile contare le tuple all'interno della tabella che hanno un determinato valore in determinate colonne.
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param conditionsFildsToValues  una hash table con cui è possibile esprimere condizioni del tipo: "Nella colonna "key" cerca le tuple che hanno il valore "value""
 * @return removeFromDB Rimuove le tuple selezionate dalla query
 * @throws Exception
 * 
 */
public static ResultSet countEntryDB(String nomeTabella, String selectValue, String fieldToCount ) throws Exception {

    
	String query = "SELECT COUNT("+fieldToCount+") AS NumeroOccorrenze, "+selectValue+" FROM `"+dbName+"`.`"+nomeTabella+"` GROUP BY `"+selectValue+"` ORDER BY COUNT("+fieldToCount+") DESC ;";
	System.out.println(query);
	return selectQuery(query);
	
	}



/**
 * Con questa funzione � possibile effettuare una doppia query innestata
 * 
 * @param nomeTabella1
 * @param fieldsToSelect1
 * @param fieldCondition1
 * @param nomeTabella2
 * @param fieldToSelect2
 * @param conditionsFildsToValues2
 * @return
 * @throws Exception
 */
public static ResultSet SelectEntryInSelectDB2(String nomeTabella1 ,String [] fieldsToSelect1,String fieldCondition1,String nomeTabella2, String fieldsToSelect2, String fieldCondition2, String nomeTabella3, String fieldsToSelect3, Hashtable<String, String> conditionsFildsToValues2 ) throws Exception {
 
 String  mappingFieldValue = "";
 
 Set<String> keys = conditionsFildsToValues2.keySet();
  
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
     // Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+conditionsFildsToValues2.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+" && "+key+" ="+"'"+conditionsFildsToValues2.get(key)+"'";
       
    }
    String fields ="";
    if (fieldsToSelect1.length>0) {
     fields = fieldsToSelect1[0];
    }
    for (int i = 1; i<fieldsToSelect1.length;i++) {
     fields = fields+","+fieldsToSelect1[i];
    }
    
    
 
   String query = "SELECT "+fields+" FROM `"+dbName+"`.`"+nomeTabella1+"` WHERE "+fieldCondition1+" IN(SELECT "+fieldsToSelect2+" FROM `"+dbName+"`.`"+nomeTabella2+"` WHERE "+fieldCondition2+" IN(SELECT "+fieldsToSelect3+" FROM `"+dbName+"`.`"+nomeTabella3+"` WHERE "+mappingFieldValue+")) ;"; 
   System.out.println(query);
 return selectQuery(query);
 
 }

public static Integer createNewEntryDBInSelect(String nomeTabella, Hashtable<String, String> fildsToValues,String nomeTabella2,  Hashtable<String, String> conditionToValue) throws Exception {
	 
	 
	 String  mappingFieldValueCond = "";
	 Set<String> keysCond = conditionToValue.keySet();

	 Iterator<String> itrCond = keysCond.iterator();
	    
	    if(itrCond.hasNext()) {
	     // Getting Key
	        String key = itrCond.next();
	        mappingFieldValueCond = mappingFieldValueCond+"`"+key+"` ="+"'"+conditionToValue.get(key)+"'";
	        
	    }
	    //Displaying Key and value pairs
	    while (itrCond.hasNext()) { 
	       // Getting Key
	       String key = itrCond.next();
	       mappingFieldValueCond = mappingFieldValueCond+" && "+key+" ="+"'"+conditionToValue.get(key)+"'";       
	    }
	 String  values = "";
	 String  fields = "";
	 Set<String> keys = fildsToValues.keySet();
	 	 
	    //Obtaining iterator over set entries
	    Iterator<String> itr = keys.iterator();
	    if(itr.hasNext()) {
	     // Getting Key
	        String key = itr.next();

	        fields = fields+"`"+key+"`";
	        values = values+"'"+fildsToValues.get(key)+"'";
	       
	    }
	    //Displaying Key and value pairs
	    while (itr.hasNext()) { 
	       // Getting Key
	       String key = itr.next();
	       fields = fields+",`"+key+"`";
	       values = values+",'"+fildsToValues.get(key)+"'";	       
	    } 
	    	 
	 String query = "INSERT INTO "+dbName+"."+nomeTabella+" ("+fields+", Topic_idTopic) VALUES ("+values+",(SELECT idTopic FROM "+dbName+"."+nomeTabella2+" WHERE "+mappingFieldValueCond+"));";  
	 
	 return updateQuery(query);	 
	 }

/**
 * Con questa funzione � possibile selezionare tutte le tuple di una tabella
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param fieldsToSelect Colonne della tabella da restituire nella select
 * @return SelectAll Restituisce le tuple selezionate dalla query
 * @throws Exception
 */

public static ResultSet SelectAll(String nomeTabella,String [] fieldsToSelect ) throws Exception {
 
    String fields ="";
    if (fieldsToSelect.length>0) {
     fields = fieldsToSelect[0];
    }
    for (int i = 1; i<fieldsToSelect.length;i++) {
     fields = fields+","+fieldsToSelect[i];
    }
    
    
 String query = "SELECT "+fields+" FROM "+dbName+"."+nomeTabella+";";
 System.out.println(query);
 return selectQuery(query);
 
 }

/**
 * Con questa funzione è possibile selezionare delle tuple all'interno della tabella che hanno come valore di una colonna il risultato di un'altra query di selezione.
 * Esempio query: "SELECT fieldsToSelect1 FROM `nomeTabella1` WHERE fieldCondition1 IN(SELECT fieldToSelect2 FROM `nomeTabella2` WHERE conditionsFildsToValues2) ;
 * 
 * @param nomeTabella1
 * @param fieldsToSelect1
 * @param fieldCondition1
 * @param nomeTabella2
 * @param fieldToSelect2
 * @param conditionsFildsToValues2
 * @return
 * @throws Exception
 */
/*
//ATTENZIONE!!! FUNZIONE DA CONTROLLARE!!!
public static ResultSet SelectEntryInGTSelectDB(String nomeTabella1 ,String [] fieldsToSelect1,Hashtable<String, String> fieldCondition1,String fieldCondition2,String nomeTabella2, String fieldToSelect2, Hashtable<String, String> conditionsFildsToValues2 ) throws Exception {
	
	String  mappingFieldValue = "";
	String  mappingFieldValue2 = "";
	
	Set<String> keys = conditionsFildsToValues2.keySet();
	Set<String> keys2 = fieldCondition1.keySet();
	
	Iterator<String> itr2 = keys2.iterator();
	
	if(itr2.hasNext()) {
    	// Getting Key
        String key2 = itr2.next();
        mappingFieldValue2 = mappingFieldValue2+"`"+key2+"` = "+"'"+fieldCondition1.get(key2)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr2.hasNext()) { 
       // Getting Key
       String key2 = itr2.next();
       mappingFieldValue2 = mappingFieldValue2+" && `"+key2+"` ="+"'"+fieldCondition1.get(key2)+"'";
       
    }
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` > "+"'"+conditionsFildsToValues2.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+" && `"+key+"` >"+"'"+conditionsFildsToValues2.get(key)+"'";
       
    }
    String fields ="";
    if (fieldsToSelect1.length>0) {
    	fields = fieldsToSelect1[0];
    }
    for (int i = 1; i<fieldsToSelect1.length;i++) {
    	fields = fields+","+fieldsToSelect1[i];
    }
    
    //fieldCondition
	String query = "SELECT"+fields+" FROM `"+dbName+"`.`"+nomeTabella1+"` WHERE "+mappingFieldValue2+"&&"+fieldCondition2+" IN(SELECT "+fieldToSelect2+" FROM `"+dbName+"`.`"+nomeTabella2+"` WHERE "+mappingFieldValue+") ;";
	System.out.println(query);
	return selectQuery(query);
	
	}

/*
public static ResultSet queryRetrieveLezioniPayedStillUp(String iduser, String currdate, String currtime) throws Exception {
	
	   String query = "SELECT * FROM lezione WHERE idLezione IN (SELECT Lezione_idLezione FROM fasciaoraria WHERE (DataLezione > '"+currdate+"' || (DataLezione = '"+currdate+"' && OrarioInizioLezione > '"+currtime+"')) && idFasciaOraria IN (SELECT FasciaOraria_idFasciaOraria FROM pagamento WHERE Utente_idUtente = "+iduser+"));";
	   System.out.println(query);
	   return selectQuery(query);
	 
}

public static ResultSet queryRetrieveFascebyLezionePayedStillUp(String idlezione, String iduser, String currdate, String currtime) throws Exception {
	
	   String query = "SELECT * FROM fasciaoraria WHERE (DataLezione > '"+currdate+"' || (DataLezione = '"+currdate+"' && OrarioInizioLezione > '"+currtime+"'))&& Lezione_idLezione= "+idlezione+" && idFasciaOraria IN (SELECT FasciaOraria_idFasciaOraria FROM pagamento WHERE Utente_idUtente = "+iduser+");";
	   System.out.println(query);
	   return selectQuery(query);
	 
}*/

public static ResultSet SelectFromFasciaOrariaDB(String [] fieldsToSelect, Hashtable<String, String> conditionsFildsToValues ) throws Exception {
	
	String  mappingFieldValue = "";
	
	Set<String> keys = conditionsFildsToValues.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
        
    }
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+" && `"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
       
    }
    String fields ="";
    if (fieldsToSelect.length>0) {
    	fields = fieldsToSelect[0];
    }
    for (int i = 1; i<fieldsToSelect.length;i++) {
    	fields = fields+","+fieldsToSelect[i];
    }
    
    
	String query = "SELECT "+fields+" FROM `"+dbName+"`.`fasciaoraria` WHERE (DataLezione > CURDATE()  || (DataLezione = CURDATE() && OrarioInizioLezione > CURTIME())) &&"+mappingFieldValue+" ;";
	System.out.println(query);
	return selectQuery(query);
	
	}

/**
 * COn questa funzione è possibile selezionare delle tuple all'interno della tabella che hanno un determinato valore in determinate colonne.
 * @param nomeTabella Nome della tabella della base di dati persistente
 * @param fieldsToSelect Colonne della tabella da restituire nella select
 * @param conditionsFildsToValues  una hash table con cui è possibile esprimere condizioni del tipo: "Nella colonna "key" cerca le tuple che hanno il valore "value""
 * @return SelectEntryDB Restituisce le tuple selezionate dalla query
 * @throws Exception
 */

public static ResultSet SelectEntryORDB(String nomeTabella,String [] fieldsToSelect, Hashtable<String, List<String>> conditionsFildsToValues ) throws Exception {
	
	String  mappingFieldValue = "";
	
	Set<String> keys = conditionsFildsToValues.keySet();
	 
    //Obtaining iterator over set entries
    Iterator<String> itr = keys.iterator();
    if(itr.hasNext()) {
    	// Getting Key
        String key = itr.next();
        if(conditionsFildsToValues.get(key).size() > 0) {
        	mappingFieldValue = mappingFieldValue+"`"+key+"` ="+"'"+conditionsFildsToValues.get(key).get(0)+"'";
        	for(int i = 1; i < conditionsFildsToValues.get(key).size(); i++) {
        		mappingFieldValue = mappingFieldValue+" || `"+key+"` ="+"'"+conditionsFildsToValues.get(key).get(i)+"'";
        	}
        }
        
    }
    /*
    //Displaying Key and value pairs
    while (itr.hasNext()) { 
       // Getting Key
       String key = itr.next();
       mappingFieldValue = mappingFieldValue+" && `"+key+"` ="+"'"+conditionsFildsToValues.get(key)+"'";
       
    }*/
    String fields ="";
    if (fieldsToSelect.length>0) {
    	fields = fieldsToSelect[0];
    }
    for (int i = 1; i<fieldsToSelect.length;i++) {
    	fields = fields+","+fieldsToSelect[i];
    }
    
    
	String query = "SELECT "+fields+" FROM `"+dbName+"`.`"+nomeTabella+"` WHERE "+mappingFieldValue+" ;";
	System.out.println(query);
	return selectQuery(query);
	
	}


}

