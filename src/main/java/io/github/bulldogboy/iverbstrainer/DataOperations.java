package io.github.bulldogboy.iverbstrainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

	public List<Verb> getAllVerbs()  {
		List<Verb> verbs = new ArrayList<>();
	try {
		String sqlQuerry = "SELECT * FROM irregural_verbs";
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sqlQuerry)) {
			while (resultSet.next()) {
				Verb verb = new Verb(resultSet.getInt("id"), resultSet.getString("infinitive"),
						resultSet.getString("past_simple"), resultSet.getString("past_participle"),
						resultSet.getString("translation"), resultSet.getString("datagroup"));
				verbs.add(verb);

			}
		}
	} catch (SQLException e) {
		System.out.println("Failed to retrieve data from DB");
	}
	return verbs;
	}
}
