package net.codejava;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController {
	
	private Connection sqlConnect;
	private String sqlQuery;
	private Statement sqlStatement;
	private ResultSet sqlResult;
	
	public BookController(Connection connection) {
		this.sqlConnect = connection;
	}
	//Create new book entry
	public int create(int isbn, String name, String author, int edition) {
		Statement stmt;
		int result=0;
		String createQuery = "INSERT INTO bookdomain (isbn_number, book_name, author_name, edition) " 
					+ "VALUES (" + Integer.toString(isbn) + ",'" + name + "','" + author + "'," + Integer.toString(edition) + ")";
		try {
			stmt= sqlConnect.createStatement();
			result = stmt.executeUpdate(createQuery);
		} catch (Exception ex) {
			System.out.println("Create error:" + ex.toString());
		}
		return result;
	}
	//Read Book Information
	public Book read(int isbn) {
		int tmpIsbn;
		String tmpBookName;
		String tmpAuthorName;
		int tmpEdition;
		Book tmpBook = null;
		try {
			if (sqlConnect != null) {
				System.out.println("Connected to database");
				sqlQuery = "SELECT * FROM samplebook.bookdomain "
						+ "WHERE isbn_number = " + Integer.toString(isbn);
				sqlStatement= sqlConnect.createStatement();
				sqlResult = sqlStatement.executeQuery(sqlQuery);
				while (sqlResult.next()) {
					tmpIsbn = sqlResult.getInt("isbn_number");
					tmpBookName = sqlResult.getString("book_name");
					tmpAuthorName = sqlResult.getString("author_name");
					tmpEdition = sqlResult.getInt("edition");
					tmpBook = new Book(tmpIsbn,tmpBookName,tmpAuthorName,tmpEdition);
				}
				
				sqlStatement.close();
			}
		} catch (SQLException ex) {
			System.out.println("Read threw exception " + ex.toString());
		}
		return tmpBook;
	}
	
	
	// Update Book Information		
	public Boolean update(int isbn, int edition) {
		String sql = "UPDATE bookdomain SET edition =? WHERE isbn_number=?";
		int updateCount = 0;
		try {
			PreparedStatement sqlPrepared = sqlConnect.prepareStatement(sql);
			sqlPrepared.setInt(1, edition);
			sqlPrepared.setInt(2, isbn);
			updateCount = sqlPrepared.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (updateCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//Delete Book Information
	public Boolean delete(int isbn) {
		String sql = "DELETE FROM bookdomain WHERE isbn_number=?";
		int deleteCount = 0;
		try {
			PreparedStatement sqlPrepared = sqlConnect.prepareStatement(sql);
			sqlPrepared.setInt(1, isbn);
			deleteCount = sqlPrepared.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deleteCount > 0) {
			return true;
		} else {
			return false;
		}
	}
}
