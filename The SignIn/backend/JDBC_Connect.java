package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/signin";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "";

	public Connection connect () throws SQLException {
		Connection conn = null;
			
		try {
			//Register JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");

			//Open a connection
			
			System.out.println("Connecting to Database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		}catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
		return conn;
	}
}
