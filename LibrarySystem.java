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
		loginManager = new LoginManager(database);
		searchEngine = new SearchEngine(database);
		fineCalculator = new FineCalculator(0.5); // $0.50/day for now
	}
	
	// LOGIN
	public boolean login(String username, String password) {
		return loginManager.authenticate(username, password);
	}
	
	public void logout() {
		loginManager.logout();
	}
	
	public Account getCurrentUser() {
		return loginManager.getCurrentUser();
	}
	
	public boolean isAdmin() {
		return loginManager.isAdmin();
	}
	
	// SEARCH
	public List<Book> searchByTitle(String title) {
		return searchEngine.searchByTitle(title);
	}
	
	public List<Book> searchByAuthor(String author) {
		return searchEngine.searchByAuthor(author);
	}
	
	public Book searchByID(int id) {
		return searchEngine.searchByID(id);
	}
	
	// PATRON ACTIONS
	public boolean checkoutBook(Patron patron, Book book) {
		
		if (!isAdmin()) return false;
		// ^ Only librarian can checkout a book
		
		Librarian librarian = (Librarian) getCurrentUser();
		return librarian.processCheckout(patron, book);
	}
	
	public boolean returnBook(Patron patron, Book book) {
		if (!isAdmin()) return false;
		// ^ Only librarian can return a book
		
		Librarian librarian = (Librarian) getCurrentUser();
		return librarian.processReturn(patron,  book, fineCalculator);
	}
	
	// INVENTORY MANAGEMENT
	public void modifyInventory(Book book, boolean add) {
		if (!isAdmin()) return;
		// ^ Only librarian can modify inventory
		
		Librarian librarian = (Librarian) getCurrentUser();
		librarian.modifyInventory(database,  book,  add);
	}
	
	// UTILITY
	public LibraryDatabase getDatabase() {
		return database;
	}
	
	public FineCalculator getFineCalculator() {
		return fineCalculator;
	}
	
	public LoginManager getLoginManager() {
		return loginManager;
	}

}
