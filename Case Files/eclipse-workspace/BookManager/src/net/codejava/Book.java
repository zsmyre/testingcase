package net.codejava;

public class Book {
	private static int nextISBN = 1000000;
	
	private final int ISBN;
	private String name;
	private String authorname;
	private int edition;
	
	private static synchronized int getNextISBN() {
		int rVal=nextISBN;
		nextISBN += 1;
		return rVal;
	}
	
	public Book(String name, String authorname, int edition) {
		this.ISBN = getNextISBN();
		this.name = name;
		this.authorname = authorname;
		this.edition = edition;
	}
	public Book(int tmpIsbn, String name, String authorname, int edition) {
		this.ISBN = tmpIsbn;
		this.name = name;
		this.authorname = authorname;
		this.edition = edition;
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	@Override
	public String toString() {
		String rVal = "Book: ";
		rVal += "ISBN = " + Integer.toString(this.ISBN);
		rVal += ", name = " + this.name;
		rVal += ", author = " + this.authorname;
		rVal += ", edition = " + Integer.toString(this.edition);
		return rVal;
	}
	
	
	

}
