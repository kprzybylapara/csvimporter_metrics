package examples.stubs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of data base stubs.
 * 
 * @author staff
 */
public class DatabaseExample {

	/**
	 * Reads the scores for the team stored in the database and returns the
	 * average score
	 * 
	 * @param team_name
	 *            the team we want to get the average score for
	 * @return the average score
	 * @throws SQLException
	 *             if a database error occurs
	 */
	public static int getAverageScore(String team_name) throws SQLException {
		_loggedCalls.add("getAverageScore: " + team_name);
		prepare();
		Statement stmt = _connection.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM SCORES WHERE TEAM_NAME='"
						+ team_name + "'");

		int total = 0;
		int num_entries = 0;
		while (rs.next()) {
			int score = rs.getInt("SCORE");
			total += score;
			num_entries++;
		}
		return total / num_entries;
	}

	/**
	 * Reads the scores for the team stored in the database and returns the list
	 * of scores.
	 * 
	 * @param team_name
	 *            the team we want to get the scores for
	 * @return the list of scores
	 * @throws SQLException
	 *             if a database error occurs
	 */
	public static List getScores(String team_name) throws SQLException {
		_loggedCalls.add("getScores: " + team_name);
		prepare();
		List list_scores = new ArrayList();
		Statement stmt = _connection.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM SCORES WHERE TEAM_NAME='"
						+ team_name + "'");
		while (rs.next()) {
			int score = rs.getInt("SCORE");
			list_scores.add(new Integer(score));
		}
		return list_scores;
	}

	/**
	 * Adds a new score for the team stored in the database.
	 * 
	 * @param team_name
	 *            the team we want to add the score for
	 * @param score
	 *            the new score
	 * @throws SQLException
	 *             if a database error occurs
	 */
	public static void addScore(String team_name, int score)
			throws SQLException {
		_loggedCalls.add("addScore: " + team_name + ", " + score);
		prepare();
		Statement stmt = _connection.createStatement();
		stmt.executeUpdate("INSERT INTO SCORES (TEAM_NAME, SCORE) VALUES ('"
				+ team_name + "', " + score + ")");
	}

	/**
	 * Connects the program to the database containing the team scores
	 */
	private synchronized static void prepare() {
		if (_connection != null)
			return;

		try {
			Class.forName("myDriver.ClassName");
		} catch (ClassNotFoundException ex) {
		}

		try {
			_connection = DriverManager.getConnection(
					"jdbc:mySubprotocol:myDataSource", "myLogin", "myPassword");
		} catch (SQLException ex) {
		}
	}

	private static Connection _connection;

	private static List _loggedCalls = new ArrayList();
}