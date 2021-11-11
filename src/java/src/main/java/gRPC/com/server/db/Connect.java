package gRPC.com.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	private static Connection connection = null;
	
	public void connect() {
		
		if(connection != null) return;
		System.out.println("PASA IF");
        try {
            // db parameters
            String url = "jdbc:sqlite:memory:myDb";
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
}
