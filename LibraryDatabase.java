package repository;

import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Account;

public class LibraryDatabase {
	
	private List<Book> books;
	private List<Account> accounts;
	
	public LibraryDatabase() {
		books = new ArrayList<>();
		accounts = new ArrayList<>();
	}
	
	public List<Book> getAllBooks() {
		return books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public List<Account> getAllAccounts() {
		return accounts;
	}

}
