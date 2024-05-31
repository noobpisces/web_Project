package conn;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException
	{
		return SQLServerConnection.initializeDatabase();
	}
}
