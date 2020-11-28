package net.codejava;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class BookManager {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/samplebook";
		String username = "root";
		String password = "root";
		
		//Connect to database
		Connection sqlConnect = null;
		
		try {
			sqlConnect = DriverManager.getConnection(jdbcURL, username, password);
		} catch (SQLException ex) {
			System.out.println("Connect error:" + ex.toString());
		}
		
		//Create input handler and gather input
		InputHandler ih = new InputHandler(sqlConnect);
		ih.gatherInput();
	}

}
