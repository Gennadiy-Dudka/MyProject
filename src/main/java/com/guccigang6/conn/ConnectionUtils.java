package com.guccigang6.conn;

import java.sql.*;

public class ConnectionUtils {
	public static Connection getConnection()
			throws ClassNotFoundException, SQLException{
		String hostName = "localhost";
		String dbName = "myProjectDB";
		String userName = "root";
		String password = "sqlcrios999";
		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" +hostName +":3306/"+dbName;
		return DriverManager.getConnection(connectionURL, userName, password);
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			
		}
	}
	
	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch(Exception e) {
			
		}
	}
}
