package com.sharp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.sharp.xmlparser.Assign;

public class DbConnection {
	private static Connection con = null;
	
	public static void connect() {
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "Sharp@007@");
			System.out.println("Connection Successfull");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void storeData(List<Assign> assign) {
		DbConnection.connect();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in statement creation "+e);
		}
		
		String create = "create table if not exists assign (id int primary key, name varchar(30), language varchar(20), status varchar(30), date_of_submit date)";
		
		try {
			stmt.execute(create);
			System.out.println("Table Created Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("Exception in create query"+e);
		}
		
		String insert="";
		for(Assign a:assign) {
			insert = "insert into assign values(?,?,?,?,?)";
			try {
				pstmt = con.prepareStatement(insert);
				pstmt.setInt(1, a.getId());
				pstmt.setString(2, a.getName());
				pstmt.setString(3, a.getPrg_lang());
				pstmt.setString(4, a.getStatus());
				pstmt.setString(5, a.getDate());
				pstmt.executeUpdate();
				
				System.out.println("Data Inserted Successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception in Prepared statement"+e);
			}
			
			
		}
		
	}
}
