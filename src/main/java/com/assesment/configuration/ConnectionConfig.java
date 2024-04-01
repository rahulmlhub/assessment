package com.assesment.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {

	private static volatile ConnectionConfig instance = null;
	private static volatile Connection conn = null;

	private ConnectionConfig() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to load driver");
		}

		if (conn != null) {
			throw new RuntimeException("Use getConnection() method to create");
		}

		if (instance != null) {
			throw new RuntimeException("Use getInstance() method to create");
		}
	}

	public static ConnectionConfig getInstance() {
		if (instance == null) {
			synchronized (ConnectionConfig.class) {
				if (instance == null) {
					instance = new ConnectionConfig();
				}

			}
		}
		return instance;
	}

	public Connection getConnection() {
		if (conn == null) {
			synchronized (ConnectionConfig.class) {
				if (conn == null) {
					try {
						String dbUrl = "jdbc:mysql://localhost:3306/sakila";

						conn = DriverManager.getConnection(dbUrl, "root", "Admin@123");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return conn;
	}
}