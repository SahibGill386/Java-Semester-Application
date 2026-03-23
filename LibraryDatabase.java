package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Account;

public class LibraryDatabase {
	
	private List<Book> books;
	private List<Account> accounts;
    private final String FILE_NAME = "library_data.ser";
	
	public LibraryDatabase() {
		books = new ArrayList<>();
		accounts = new ArrayList<>();
        loadFromFile();
	}
	
	public List<Book> getAllBooks() { return books; }
	public void addBook(Book book) { books.add(book); saveToFile(); }
	public void removeBook(Book book) { books.remove(book); saveToFile(); }
	public void addAccount(Account account) { accounts.add(account); saveToFile(); }
	public List<Account> getAllAccounts() { return accounts; }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving database: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                books = (List<Book>) ois.readObject();
                accounts = (List<Account>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading database: " + e.getMessage());
            }
        }
    }
}