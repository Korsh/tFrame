package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	static Connection connection;
	static Statement dataBase;

	public static void main(String args[]) {

		try {
			// Название драйвера
			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName);

			// Create a connection to the database
			String serverName = "localhost";
			String mydatabase = "test_frame";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root";
			String password = "root";

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("is connect to DB" + connection);

			String query = "Select * FROM news";
			dataBase = connection.createStatement();

			ResultSet rs = dataBase.executeQuery(query);
			String dbtime;
			while (rs.next()) {
				dbtime = rs.getString(1);
				System.out.println(dbtime);
			} // end while

			// connection.close();
			// getProxyConfigs("uk");
		} // end try
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Could not find the database driver
		} catch (SQLException e) {
			e.printStackTrace();
			// Could not connect to the database
		}
	}

	public static void getProxyConfigs(String in_proxyCode) throws SQLException {
		String query = "select * from proxy where code = '" + in_proxyCode
				+ "';";

		ResultSet rs = dataBase.executeQuery(query);
		String dbtime;
		while (rs.next()) {
			dbtime = rs.getString(1);
			System.out.println(dbtime);
		} // end while
	}
}