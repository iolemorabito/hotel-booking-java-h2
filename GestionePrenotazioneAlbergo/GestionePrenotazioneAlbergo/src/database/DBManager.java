package database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
	
private static Connection conn = null;
protected static Statement stmt=null;	
	private DBManager() {}
	
	public static Connection getConnection() throws SQLException {
			
			if(conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:h2:./gestioneprenotazionealbergo", "sa", "");
				
			}
			
			return conn;
	}
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) {
				conn.close();
			}
	}

}
