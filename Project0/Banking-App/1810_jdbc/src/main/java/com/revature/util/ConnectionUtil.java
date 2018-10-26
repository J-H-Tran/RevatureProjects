package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException{
		String url="jdbc:oracle:thin:@revaturetrainingdatabase.c4svrsh9eep5.us-east-2.rds.amazonaws.com:1521:ORCL";	
		String user = "bank_test";
		String pass = "bt123";
		return DriverManager.getConnection(url, user, pass);
	}
}
