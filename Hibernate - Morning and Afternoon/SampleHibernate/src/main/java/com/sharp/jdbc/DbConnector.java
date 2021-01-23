package com.sharp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	private static Connection con = null;
	
	private static String URL = "jdbc:mysql://localhost:3306/employee";
	
	public static void connect() {
		try {
		
//			Class.forName("com.mysql.cj.jdbc.Driver");
			//Driver name is referred from hibernate-cfg.cml
		
			con = DriverManager.getConnection(URL, "root", "Sharp@007@");
			System.out.println("Connection Successfull");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Connection Closed");
		}
		
	}
}