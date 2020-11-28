package net.codejava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement; 
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest {
	BookController myController;
	Connection myConnection;
	
	@BeforeEach
	void setUp() throws Exception {
		String jdbcURL = "jdbc:mysql://localhost:3306/samplebook";
		String username = "root";
		String password = "root";
		
		try {
			myConnection = DriverManager.getConnection(jdbcURL, username, password);
		} catch (SQLException ex) {
			System.out.println("Connect error:" + ex.toString());
		}
		myController = new BookController(myConnection);
	}

	@AfterEach
	void tearDown() throws Exception {
		if(myConnection!= null && !myConnection.isClosed()) {
			myConnection.close();
			myConnection = null;
		}
			
	}

	@Test 
	@Order(1)
	void testCreate() {
		int isbn = 5;
		String name = "Susan May";
		String author = "May Lee";
		int edition = 2;
		myController.create(isbn, name, author, edition);
		Book myBook = myController.read(isbn);
		assertEquals(isbn, myBook.getISBN(),"test create: isbn");
		assertEquals(name, myBook.getName(),"test create: name");
		assertEquals(author, myBook.getAuthorname(),"test create: author");
		assertEquals(edition, myBook.getEdition(),"test create: editon");
	}
	
	@Test
	@Order(2)
	void testUpdate() {
		int isbn = 5;
		String name = "Susan May";
		String author = "May Lee";
		int edition = 3;
		myController.update(isbn, edition);
		Book myBook = myController.read(isbn);
		assertEquals(isbn, myBook.getISBN(),"test update: isbn");
		assertEquals(name, myBook.getName(),"test update: name");
		assertEquals(author, myBook.getAuthorname(),"test update: author");
		assertEquals(edition, myBook.getEdition(),"test update: editon");
	}
	
	@Test
	@Order(3)
	void testdelete() {
		int isbn = 5;
		myController.delete(isbn);
		Book myBook = myController.read(isbn);
		assertEquals(myBook,null,"testdelete");
	}
}
