package io.github.bulldogboy.iverbstrainer;

import java.sql.*;

public class DataOperations {
	private final String connectionString = "jdbc:sqlite:sqlite.db";
	private Connection con;

	public DataOperations() {
		getConnection();
	}

	private void getConnection() {
		try {
			this.con = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			System.out.println("Error with connection to DB");
		}
	}
}
