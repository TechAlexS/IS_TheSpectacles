package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConnectionPool {
	private static List<Connection> freeDbConnections;
	private static boolean isTest = false;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String db="ecommerce";
		String username= "root";
		String password="admin";
		

		newConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ecommerce?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Rome",username, password);
		
		newConnection.setAutoCommit(true);
		return newConnection;
	}

	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void rilasciaConnessione(Connection connection) throws SQLException {
		if (connection != null)
			freeDbConnections.add(connection);
	}
	
	public static boolean isTest() {
		return isTest;
	}


	public static void setTest(boolean isTest) {
		ConnectionPool.isTest = isTest;
	}
}