package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {
	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException
	{


		
		//String dbURL = "jdbc:sqlserver://LAPTOP-DPMKGF6T\\AHUHU";
		String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost : 1433";  // Địa chỉ IP của container SQL Server
		String dbName = "DANG_KY_MON_HOC";
		String dbUsername = "sa";
		String dbPassword = "123456";
		String connectionURL = dbURL + ";databaseName=" + dbName;
		Connection conn = null;
		
		
		try { 
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(connectionURL, dbUsername, dbPassword);
			System.out.println("connect successfully");
		} catch (Exception e) {
			System.out.println("connect failure");
			e.printStackTrace();
		}
		
		return conn;
	}
}