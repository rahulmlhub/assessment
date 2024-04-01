package com.assesment.repository;

import com.assesment.configuration.ConnectionConfig;

import java.sql.*;

public class MediaServiceSystem_Repository {

	private static Connection connection = null;

	public static void viewDetailsMoviesAndActorsPro() {
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionConfig.getInstance().getConnection();
			String callProcedure = "{ call GET_MOVIE_DETAILS}";
			CallableStatement callableStatement = connection.prepareCall(callProcedure);
			 resultSet = callableStatement.executeQuery();
			// Print the results
			while (resultSet.next()) {
				String movieName = resultSet.getString("Movie Name");
				String actorName = resultSet.getString("ACTOR NAME");
				String availableLanguage = resultSet.getString("AVALIABLE LANGUAGE ");
				String movieCategory = resultSet.getString("Movies Category");

				System.out.println("Movie Name: " + movieName);
				System.out.println("Actor Name: " + actorName);
				System.out.println("Available Language: " + availableLanguage);
				System.out.println("Movie Category: " + movieCategory);
				System.out.println();
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	public static void viewDetailsMoviesAndActors() {
//		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionConfig.getInstance().getConnection();
			statement = connection.createStatement();
			String query = "SELECT DISTINCT FILM.TITLE as 'Movie Name', " +
					"CONCAT(actor.first_name,' ', actor.last_name) AS 'ACTOR NAME', " +
					"LANGUAGE.NAME AS 'AVAILABLE LANGUAGE', " +
					"category.name AS 'Movies Category' " +
					"FROM FILM " +
					"JOIN LANGUAGE ON FILM.LANGUAGE_ID = LANGUAGE.language_id " +
					"JOIN FILM_CATEGORY ON FILM.FILM_ID = FILM_CATEGORY.FILM_ID " +
					"JOIN film_actor ON film_actor.film_id = FILM.film_id " +
					"JOIN actor ON actor.actor_id = film_actor.actor_id " +
					"JOIN category ON category.category_id = FILM_CATEGORY.category_id " +
					"ORDER BY LANGUAGE.NAME";
			resultSet = statement.executeQuery(query);

			// Print the results
			while (resultSet.next()) {
				String movieName = resultSet.getString("Movie Name");
				String actorName = resultSet.getString("ACTOR NAME");
				String availableLanguage = resultSet.getString("AVAILABLE LANGUAGE");
				String movieCategory = resultSet.getString("Movies Category");

				System.out.println("Movie Name: " + movieName);
				System.out.println("Actor Name: " + actorName);
				System.out.println("Available Language: " + availableLanguage);
				System.out.println("Movie Category: " + movieCategory);
				System.out.println();
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}


	public static void searchMovie() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionConfig.getInstance().getConnection();
			statement = connection.createStatement();
			String query = "SELECT * FROM film WHERE title = 'ACE GOLDFINGER'";
			resultSet = statement.executeQuery(query);

			// Print the results
			while (resultSet.next()) {
				int film_id = resultSet.getInt("film_id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");

				System.out.println("Film ID: " + film_id);
				System.out.println("Title: " + title);
				System.out.println("Description: " + description);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}


	public static void saveMovieCategory() {
	PreparedStatement insertInto = null;

		try {
			connection = ConnectionConfig.getInstance().getConnection();
			insertInto = connection.prepareStatement("INSERT INTO category VALUES (?, ? , ?)");
			insertInto.setInt(1, 20);
			insertInto.setString(2, "education");
			insertInto.setDate(3,  new java.sql.Date(System.currentTimeMillis()));

			System.out.println("preapred statement");
			int affectedRows = insertInto.executeUpdate();
			System.out.println("inserted record");
			System.out.println("Rows affected: " + affectedRows);
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} finally {
			try {
				if (insertInto != null) {
					insertInto.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing PreparedStatement: " + e.getMessage());
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing Connection: " + e.getMessage());
			}
		}
	}



	public static void updateMovieCategory() {
		Connection connection = null;
		PreparedStatement updateStatement = null;

		try {
			connection = ConnectionConfig.getInstance().getConnection();
			updateStatement = connection.prepareStatement("UPDATE category SET name = ? WHERE category_id = ?");
			updateStatement.setString(1, "Updated Category Name");
			updateStatement.setInt(2, 6);

			int affectedRows = updateStatement.executeUpdate();

			System.out.println("Rows affected by update: " + affectedRows);
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} finally {
			try {
				if (updateStatement != null) {
					updateStatement.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing PreparedStatement: " + e.getMessage());
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing Connection: " + e.getMessage());
			}
		}
	}

	public static void deleteMovieCategory() {
		Connection connection = null;
		PreparedStatement deleteStatement = null;

		try {
			connection = ConnectionConfig.getInstance().getConnection();
			deleteStatement = connection.prepareStatement("DELETE FROM category WHERE category_id = ?");
			deleteStatement.setInt(1, 20);

			int affectedRows = deleteStatement.executeUpdate();

			System.out.println("Rows affected by delete: " + affectedRows);
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} finally {
			try {
				if (deleteStatement != null) {
					deleteStatement.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing PreparedStatement: " + e.getMessage());
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing Connection: " + e.getMessage());
			}
		}
	}


}
