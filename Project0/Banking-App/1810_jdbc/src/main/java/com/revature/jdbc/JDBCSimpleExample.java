package com.revature.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSimpleExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//1. load the driver
		Class.forName("oracle.jdbc.OracleDriver");
		
		String url = "jdbc:oracle:thin:@sdetjta.cvoui7q38caj.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "chinook";
		String password = "p4ssw0rd";
		
		//2. establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);
		
		//3. Run the query
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from album");
		
		//4. print the results
		while(rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) );
			//System.out.println(rs.get);
		}
		
		//5. close connection 
		rs.close();
		stmt.close();
		conn.close();
	}
}
