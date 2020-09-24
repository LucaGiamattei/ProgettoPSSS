

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.fabric.jdbc.FabricMySQLConnection;

import dataLayer.connectorManager.DBConnectionManager;
import dataLayer.user.entities.UtenteDB;

public class mainProva {
	
    public static void main(String args[]) {
        
        try {
        	UtenteDB ut = new UtenteDB(1,"giorgio","lori","ddjfnjfn");
			DBConnectionManager.selectQuery("SELECT * FROM mydb.User;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

