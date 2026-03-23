package controller;

import model.*;
import service.*;
import repository.*;
import java.util.List;

public class LibrarySystem {
	
	private LibraryDatabase database;
	private LoginManager loginManager;
	private SearchEngine searchEngine;
	private FineCalculator fineCalculator;
	
	public LibrarySystem() {
		database = new LibraryDatabase();
		
		// SEED DATA: Finalized database for grading
				if (database.getAllBooks().isEmpty()) {
					// Add 10 Books
					database.addBook(new Book("The Hunger Games", "Suzanne Collins"));
					database.addBook(new Book("Catching Fire", "Suzanne Collins"));
					database.addBook(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling"));
					database.addBook(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling"));
					database.addBook(new Book("Percy Jackson and the Lightning Thief", "Rick Riordan"));
					database.addBook(new Book("Twilight", "Stephenie Meyer"));
					database.addBook(new Book("New Moon", "Stephenie Meyer"));
					database.addBook(new Book("The Maze Runner", "James Dashner"));
					database.addBook(new Book("Diary of a Wimpy Kid", "Jeff Kinney"));
					database.addBook(new Book("The Hobbit", "J.R.R. Tolkien"));
					
					// Add 3 Accounts
					database.addAccount(new Librarian("Admin Librarian", "admin", "pass"));
					database.addAccount(new Patron("Test Patron", "jdoe", "pass"));
					database.addAccount(new Patron("Sahib Gill", "sgill", "pass"));
				}
		
		loginManager = new LoginManager(database);
		searchEngine = new SearchEngine(database);
		fineCalculator = new FineCalculator(0.5); // $0.50/day
	}
	
	public boolean login(String username, String password) { return loginManager.authenticate(username, password); }
	public void logout() { loginManager.logout(); }
	public Account getCurrentUser() { return loginManager.getCurrentUser(); }
	public boolean isAdmin() { return loginManager.isAdmin(); }
	
	public List<Book> searchByTitle(String title) { return searchEngine.searchByTitle(title); }
	public List<Book> searchByAuthor(String author) { return searchEngine.searchByAuthor(author); }
	public Book searchByID(int id) { return searchEngine.searchByID(id); }
	
	public boolean checkoutBook(Patron patron, Book book) {
		if (!isAdmin()) return false;
		Librarian librarian = (Librarian) getCurrentUser();
		return librarian.processCheckout(patron, book);
	}
	
	public boolean returnBook(Patron patron, Book book) {
		if (!isAdmin()) return false;
		Librarian librarian = (Librarian) getCurrentUser();
		return librarian.processReturn(patron, book, fineCalculator);
	}
	
	public void modifyInventory(Book book, boolean add) {
		if (!isAdmin()) return;
		Librarian librarian = (Librarian) getCurrentUser();
		librarian.modifyInventory(database, book, add);
	}
	
	public LibraryDatabase getDatabase() { return database; }
	public FineCalculator getFineCalculator() { return fineCalculator; }
	public LoginManager getLoginManager() { return loginManager; }
}