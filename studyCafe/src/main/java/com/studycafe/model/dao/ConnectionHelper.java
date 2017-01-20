package com.studycafe.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionHelper {
	
	public static Connection getConnection(String dbname) {
		
		if (dbname.toLowerCase().equals("oracle")) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				Connection conn = 
					DriverManager.getConnection(
					"jdbc:oracle:thin:@210.16.214.202:1521:xe",
					"minute", "hot");
				
				return conn;
			} catch (Exception ex) {
				return null;
			}

		} else if (dbname.toLowerCase().equals("mysql")) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = 
					DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demoweb",
					"devadmin", "mysql");
				
				return conn;
			} catch (Exception ex) {
				return null;
			}

		} else {
			return null;
		}
	}
}
