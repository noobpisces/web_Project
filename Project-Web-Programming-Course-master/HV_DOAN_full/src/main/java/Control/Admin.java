package Control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.DBConnection;

public class Admin {

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public String ChangePass(String u, String op, String np)
	{
		Connection connection = null;
	    CallableStatement callableStatement = null;
	    String result = "Success";
	    try {
	        String storedProcedure = "{call proc_DoiMK(?, ?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, u);
	        callableStatement.setString(2, op);
	        callableStatement.setString(3, np);
	        boolean success = callableStatement.execute();
	        if (!success) {
	            // Check for additional results (errors or messages)
	            while (callableStatement.getMoreResults() || callableStatement.getUpdateCount() != -1) {
	                try (ResultSet rs = callableStatement.getResultSet()) {
	                    if (rs != null && rs.next()) {
	                        // Process the error message or additional results
	                        result = rs.getString(1);
	                        System.out.println(result);
	                    }
	                }
	            }
	        }

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        result =  e.getMessage();

	    } finally {
	        try {
	           
	            if (callableStatement != null) {
	                callableStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}

}
