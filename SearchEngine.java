package service;

import java.util.ArrayList;
import java.util.List;
import model.Book;
import repository.LibraryDatabase;

public class SearchEngine {
	
	private LibraryDatabase database;
	
	public SearchEngine(LibraryDatabase database) {
		this.database = database;
	}
	
	
	public List<Book> searchByTitle(String title) {
		
		List<Book> results = new ArrayList<>();
		
		for (Book book : database.getAllBooks()) {
			if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
				results.add(book);
			}
		}
		return results;
	}
	
	
	public List<Book> searchByAuthor(String author) {
		List<Book> results = new ArrayList<>();
		if (author == null || author.isEmpty()) {
			return results;
		}
		
		String query = author.toLowerCase();
		
		for (Book book : database.getAllBooks()) {
			if (book.getAuthor() != null &&
				book.getAuthor().toLowerCase().contains(query)) {
				results.add(book);
			}
		}
		return results;
	}
	
	
	public Book searchByID(int id) {
		
		for (Book book : database.getAllBooks()) {
			if (book.getID() == id) {
				return book;
			}
		}
		return null;
	}

}
