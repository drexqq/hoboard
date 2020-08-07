package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static void initConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success");			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception {
		String host = "211.105.15.179";
		String port = "1521";
		String id = "hoboard";
		String pw = "hoboard";
		Connection conn = null;
		DBConnection.initConnection();
		conn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+port+":xe", id, pw);			
		return conn;		
	}
	
}
