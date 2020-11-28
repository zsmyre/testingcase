package net.codejava;

import java.sql.Connection;
import java.util.Scanner;

public class InputHandler {

	private Connection connection;

	public InputHandler(Connection connection) {
		this.connection = connection;
	}

	public void gatherInput() {
		Scanner sc;
		sc = new Scanner(System.in);
		String validInputChars = "crude";

		while (true) {
			System.out.println("Choose an option from the menu below");
			System.out.println("c for create book");
			System.out.println("r for read book information");
			System.out.println("u for update book information");
			System.out.println("d for delete book information");
			System.out.println("e to exit");

			String input = sc.nextLine();
			if (input.equals("e")) {
				System.out.println("Exiting...");
				break;
			}
			else if(validInputChars.indexOf(input) == -1 ) {
				System.out.println("Invalid input. Please try Again!");
			}
			else {
				handleInputOption(input,sc);
			}
		}
		sc.close();
	}

	private void handleInputOption(String input,Scanner sc) {
		BookController controller = new BookController(connection);
// C create new book entry
		if (input.equals("c")) {
			String tmpStr;
			int isbn;
			int edition; 
			String bookName,authorName;
			System.out.print("Enter ISBN: "); 
			tmpStr=sc.nextLine();
			isbn = Integer.parseInt(tmpStr);
			System.out.print("Enter book name: "); 
			bookName = sc.nextLine();
			System.out.print("Enter author name: "); 
			authorName = sc.nextLine();
			System.out.print("Enter edition: "); 
			tmpStr=sc.nextLine();
			edition = Integer.parseInt(tmpStr);
			controller.create(isbn, bookName, authorName, edition);
		}
		// R read book entry
		if (input.equals("r")) {
			String tmpStr;
			int isbn;
			Book tmpBook;
			System.out.print("Enter ISBN: "); 
			tmpStr=sc.nextLine();
			isbn = Integer.parseInt(tmpStr);
			tmpBook = controller.read(isbn);
			if (tmpBook == null) {
				System.out.println("The book isbn entered does not exist");
			} else {
				System.out.println("The book: " + tmpBook.toString());

			}
			
		}
		// U update book entry
		if (input.equals("u")) {
			String tmpStr;
			int isbn;
			int edition; 		
			System.out.print("Enter ISBN: "); 
			tmpStr=sc.nextLine();
			isbn = Integer.parseInt(tmpStr);
			System.out.print("Enter NEW edition: "); 
			tmpStr=sc.nextLine();
			edition = Integer.parseInt(tmpStr);
			if (controller.update(isbn,edition)) {
				System.out.println("The book has been updated");
			} else {
				System.out.println("The book has NOT updated");
			}
		}
		// D delete selected book entry
		if (input.equals("d")) {
			String tmpStr;
			int isbn;
			System.out.print("Enter ISBN: "); 
			tmpStr=sc.nextLine();
			isbn = Integer.parseInt(tmpStr);
			if (controller.delete(isbn)) {
				System.out.println("The book has been deleted");
			} else {
				System.out.println("The book has NOT deleted");
			}
		}
	}
}
