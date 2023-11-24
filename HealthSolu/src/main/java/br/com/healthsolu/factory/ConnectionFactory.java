package br.com.healthsolu.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm552262",
					"210804");
			return conn;
		}

	}