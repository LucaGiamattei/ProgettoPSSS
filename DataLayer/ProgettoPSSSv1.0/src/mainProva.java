

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.fabric.jdbc.FabricMySQLConnection;

import dataLayer.connectorManager.DBConnectionManager;

public class mainProva {
	
    public static void main(String args[]) {
        
        try {
			DBConnectionManager.selectQuery("SELECT * FROM mydb.User;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

